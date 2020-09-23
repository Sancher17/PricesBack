package com.alexsoft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alexsoft.Application;
import com.alexsoft.audit.LogExecutionTime;
import com.alexsoft.controllers.ShopRequest;
import com.alexsoft.db.BaseEntityRepository;
import com.alexsoft.entyties.categories.BaseEntity;
import com.alexsoft.entyties.categories.shops.BaseShop;
import com.alexsoft.enums.Shops;
import com.alexsoft.parser.sources.BaseParser;
import com.alexsoft.utils.ClassCreator;

import java.util.ArrayList;
import java.util.List;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Metrics;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Service
public class ServiceParser extends AbstrWebClient {

    @Autowired
    private Environment env;
    @Autowired
    private BaseEntityRepository baseEntityRepository;
    @Autowired
    private ServiceShop serviceShop;

    // site
    @LogExecutionTime
    @Timed("getItemsFromSite")
    public List<BaseShop> getItemsFromSite(ShopRequest request) throws Exception {
        String shopSource = request.getShopSource();
        String category = request.getCategory();
        int pages = request.getPages();

        log.info("called getItemsFromSite - "+ shopSource + " - " + category + " pages: " + pages );
        String categoryUpFirst = category.substring(0, 1).toUpperCase() + category.substring(1);
        String shopSourceUpFirst = shopSource.substring(0, 1).toUpperCase() + shopSource.substring(1);
        BaseShop baseShop = null;
       try {
           baseShop = getBaseShop(categoryUpFirst);
       }catch (Exception e){
           ClassCreator.createShopClass(request);
//           restartContext();
           baseShop =getBaseShop(categoryUpFirst);
       }

        BaseParser baseParser = (BaseParser) Class
                .forName("com.prices.parser.sources." + shopSourceUpFirst + "Parser")
                .getConstructor().newInstance();
        String url = env.getProperty(shopSource + categoryUpFirst);
        return baseParser.getShortInfo(baseShop, url, pages);
    }

    private void restartContext() {
        Thread restartThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
//                Application.restart();
            } catch (InterruptedException ignored) {
            }
        });
        restartThread.setDaemon(false);
        restartThread.start();
    }

    private BaseShop getBaseShop(String categoryUpFirst) throws InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
        return (BaseShop) Class
                .forName("com.prices.entyties.categories.shops." + categoryUpFirst + "Shop")
                .getConstructor().newInstance();
    }

    public BaseEntity grubFullDataById(ShopRequest shopRequest) throws Exception {
        log.info("called grubFullDataById");
        BaseEntity itemFromDB = getBaseEntityByIdFromDB(shopRequest);
        BaseEntity item = null;
        if (itemFromDB != null) {
            item = getUpdatedItemFromSite(itemFromDB, shopRequest);
            baseEntityRepository.save(item);
        }
        return item;
    }

    public List<BaseEntity> getAllBaseEntitiesFromDB(String model){
//        List<BaseEntity> entities = baseEntityRepository.findAllRows(category);
//        List<BaseEntity> entities = baseEntityRepository.findByCategory(category);
        List<BaseEntity> entities = baseEntityRepository.findAllByModelContaining(model);
        return entities;
    }

    public List<BaseEntity> grabDetailDataForAll(ShopRequest request) throws Exception {
        List<BaseEntity> entities = new ArrayList<>();
        String shopSource = request.getShopSource();
        String category = request.getCategory();
        List<BaseEntity> allFromDb = baseEntityRepository.findAllByCategoryAndSource(category, shopSource);
        for (BaseEntity baseEntity : allFromDb) {
            baseEntity = getUpdatedItemFromSite(baseEntity, request);
            baseEntityRepository.save(baseEntity);
            entities.add(baseEntity);
        }
        return entities;
    }

    private BaseEntity getUpdatedItemFromSite(BaseEntity itemFromDB, ShopRequest request) throws Exception {

        String shopSource = request.getShopSource();
        String shopSourceUpFirst = shopSource.substring(0, 1).toUpperCase() + shopSource.substring(1);
        BaseParser baseParser = (BaseParser) Class
                .forName("com.prices.parser.sources." + shopSourceUpFirst + "Parser")
                .getConstructor().newInstance();
        BaseEntity itemUpdated;
        Shops shopName = Shops.getName(request.getShopSource().toUpperCase());
        itemUpdated = baseParser.getBaseEntity(itemFromDB, request.getCategory(), shopName);
        itemUpdated.setId(itemFromDB.getId());
        itemUpdated.setModel(itemFromDB.getModel());
        itemUpdated.setLinkDetailInfo(itemFromDB.getLinkDetailInfo());
        itemUpdated.setIsFillDetailData(true);
        String producer = itemFromDB.getModel().split(" ")[0];
        itemUpdated.setProducer(producer);
        itemUpdated.setSource(request.getShopSource());
        itemUpdated.setCategory(request.getCategory());
        return itemUpdated;
    }

    @LogExecutionTime
    @Timed("saveItemsToDB_micro")
    public List<BaseShop> saveItemsToDB(ShopRequest request) throws Exception {
        String shopSource = request.getShopSource();
        String category = request.getCategory();
        String message = "called saveItemToDB " + shopSource + " - " + category;
        List<BaseShop> shops = getItemsFromSite(request);
        if (shops != null) {
            for (BaseShop shop : shops) {
                Metrics.counter("shop counter").increment(1.0);
                serviceShop.customSave(shop, Shops.getName(shopSource.toUpperCase()), category);
            }
        }else {
            log.info(message);
        }
        return shops;
    }
}
