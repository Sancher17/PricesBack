package com.alexsoft.utils.deleted;//package com.prices.services;
//
//import com.prices.entyties.categories.shops.BaseShops;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import com.prices.db.SmartphoneRepository;
//import com.prices.db.TabletRepository;
//import com.prices.db.shops.TabletShopsRepository;
//import com.prices.entyties.categories.Smartphone;
//import com.prices.entyties.categories.Tablet;
//import com.prices.entyties.categories.shops.SmartphoneShops;
//import com.prices.entyties.categories.shops.TabletShops;
//import com.prices.enums.Shops;
//
//@Component
//public class ServiceTabletShops extends HtmlUnitAbstract {
//
//    static final Logger log = LogManager.getLogger(ServiceTabletShops.class.getSimpleName());
//
//    @Autowired
//    private TabletShopsRepository shopsRepository;
//    @Autowired
//    private TabletRepository detailRepository;
//
//    public void save(TabletShops tabletShops, Shops shops){
//        String model = tabletShops.getModel();
//        Tablet detailItem = detailRepository.getByModel(model);
//        // если модель есть в детальном репозитории сохраняем ее в репозиториии с магазинами
//        if (detailItem != null) {
//            tabletShops.setModelId(detailItem.getId());
//            tabletShops.setShopId(shops.getValue());
//            TabletShops itemFromDB = shopsRepository.getByProductId(tabletShops.getProductId());
//            if (itemFromDB == null){
//                // если нет записи то сохраняем как новую
//                log.info("Item saved to *****Shops as new, ID: " + detailItem.getId() + " --- " + tabletShops.getModel());
//                shopsRepository.save(tabletShops);
//            }else {
//                // если есть запись, то обновляем только цену
//                itemFromDB.setPrice(tabletShops.getPrice());
//                itemFromDB.setUpdateDate(tabletShops.getUpdateDate());
//                log.info("Item update into *****Shops. ID: " + itemFromDB.getModelId() +
//                        " new price: " + itemFromDB.getPrice());
//                shopsRepository.save(itemFromDB);
//            }
//            // если модели нет то добавляем в детальный, НО потом ее надо будет заполнить данными, для этого есть флаг
//        }else {
//            saveToDetailDB(tabletShops, shops);
//        }
//    }
//
//    private void saveToDetailDB(TabletShops itemShops, Shops shops) {
//        Tablet item = new Tablet();
//        item.setModel(itemShops.getModel());
//        item.setLinkDetailInfo(itemShops.getModelLink());
//        item.setIsFillDetailData(false);
//        detailRepository.save(item);
//        save(itemShops,shops);
//        log.info("called saveToDetailDB. Item saved into Notebook: " + itemShops.getModel());
//    }
//
//    @Override
//    public void save(BaseShops tvShops, Shops shops) {
//
//    }
//}
