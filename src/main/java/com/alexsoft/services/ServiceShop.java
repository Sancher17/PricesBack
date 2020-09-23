package com.alexsoft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alexsoft.db.BaseEntityRepository;
import com.alexsoft.db.BaseShopRepository;
import com.alexsoft.db.PriceHistoryRepo;
import com.alexsoft.entyties.PriceHistory;
import com.alexsoft.entyties.categories.BaseEntity;
import com.alexsoft.entyties.categories.shops.BaseShop;
import com.alexsoft.enums.Shops;
import com.alexsoft.utils.ContainerNew;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import javax.sql.DataSource;

import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class ServiceShop<T extends BaseEntity>  {

    @Autowired
    private BaseShopRepository baseShopRepository;
    @Autowired
    private BaseEntityRepository baseEntityRepository;
    @Autowired
    private PriceHistoryRepo priceHistoryRepo;

    @Autowired
    DataSource dataSource;

    public void customSave(BaseShop baseShop, Shops shops, String category) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String model = baseShop.getModel();

        System.out.println(dataSource);
        BaseEntity detailItem = baseEntityRepository.findByCategoryAndModel(category, model);
        if (detailItem == null) {
            String output = category.substring(0, 1).toUpperCase() + category.substring(1);;
            detailItem  = (BaseEntity) Class.forName("com.prices.entyties.categories." + output)
                    .getConstructor().newInstance();
        }
        BaseShop itemFromDB = baseShopRepository.findByCategoryAndSourceAndModelId(category, baseShop.getSource(),
                detailItem.getId());
//        BaseShop itemFromDB = baseShopRepository.findByCategoryAndProductId(category, baseShop.getProductId());
        handleCustomSave(baseShop, shops, detailItem, itemFromDB, category);
    }

    private void handleCustomSave(BaseShop baseShopFromSite, Shops shops, BaseEntity detailItem, BaseShop baseShopFromDB,
                                  String category) {
        // если модель есть в детальном репозитории сохраняем ее в репозиториии с магазинами
        if (detailItem.getId() != null) {
            baseShopFromSite.setModelId(detailItem.getId());
            if(baseShopFromSite.getSource() != null){
                String sourceId = baseShopFromSite.getSource();
                if (sourceId.equals("238")){
                    System.out.println();
                }
                Shops id = Shops.getName(sourceId);
                if (id != null){
                    baseShopFromSite.setShopId(Shops.getName(sourceId).getId());
                }
                baseShopFromSite.setCategory(detailItem.getCategory());
                if (baseShopFromDB == null) {
                    // SAVE as new
                    saveToDB(baseShopFromSite, detailItem);
                } else {
                    // UPDATE existed row
                    updateIntoDB(baseShopFromSite, baseShopFromDB);
                }
            }
            // если модели нет то добавляем в детальный, НО потом ее надо будет заполнить данными, для этого есть флаг
        } else {
            try {
                saveToDetailDB(baseShopFromSite, shops, detailItem, category);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | ClassNotFoundException e) {
                log.error("Can't save entity to detail DB " + e);
            }
        }
    }

    @Timed("updateIntoDB")
    private void updateIntoDB(BaseShop baseShopFromSite, BaseShop baseShopFromDB) {
        Double price = baseShopFromSite.getPrice();
        baseShopFromDB.setPrice(price);
        Calendar updateDate = baseShopFromSite.getUpdateDate();
        baseShopFromDB.setUpdateDate(updateDate);
        baseShopFromDB.setModelLink(baseShopFromSite.getModelLink());
        log.info("Price update into ID: " + baseShopFromDB.getModelId() +
                " new price: " + baseShopFromDB.getPrice() +
                " source: " + baseShopFromSite.getSource() +
                " link: " + baseShopFromDB.getModelLink());
        baseShopRepository.save(baseShopFromDB);
        savePriceHistory(baseShopFromDB, price, updateDate);
    }

    private void saveToDB(BaseShop baseShopFromSite, BaseEntity detailItem) {
        log.info("Item saved to " + baseShopFromSite + " as new, ID: " + detailItem.getId() + " --- " + baseShopFromSite.getModel());
        baseShopRepository.save(baseShopFromSite);
    }

    private void savePriceHistory(BaseShop itemFromDB, Double price, Calendar updateDate) {
        PriceHistory priceHistory = new PriceHistory();
        priceHistory.setGoodsId(itemFromDB.getModelId());
        priceHistory.setPrice(price);
        priceHistory.setUpdateDate(updateDate);
        priceHistoryRepo.save(priceHistory);
    }

    @Timed("saveToDetailDB")
    private void saveToDetailDB(BaseShop itemShops, Shops shops, BaseEntity baseEntity, String category) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        BaseEntity item =  ContainerNew.createInstanceBaseEntity(baseEntity.getClass());
        item.setModel(itemShops.getModel());
        item.setLinkDetailInfo(itemShops.getModelLink());
        item.setIsFillDetailData(false);
        item.setCategory(category);
        item.setSource(itemShops.getSource());
        baseEntityRepository.save(item);
        customSave(itemShops, shops, category);
        log.info("called saveToDetailDB. Item saved into " + category + " : " + itemShops.getModel());
    }
}
