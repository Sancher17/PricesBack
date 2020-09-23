package com.alexsoft.utils.deleted;//package com.prices.services;
//
//import com.prices.db.SmartphoneRepository;
//import com.prices.db.shops.SmartphoneShopsRepository;
//import com.prices.entyties.categories.BaseEntity;
//import com.prices.entyties.categories.Smartphone;
//import com.prices.entyties.categories.shops.BaseShops;
//import com.prices.entyties.categories.shops.SmartphoneShops;
//import com.prices.enums.Shops;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ServiceSmartphoneShops extends HtmlUnitAbstract {
//
//    static final Logger log = LogManager.getLogger(ServiceSmartphoneShops.class.getSimpleName());
//
//    @Autowired
//    private SmartphoneShopsRepository shopsRepository;
//    @Autowired
//    private SmartphoneRepository detailRepository;
//
//    public void save(SmartphoneShops smartphoneShops, Shops shops){
//        String model = smartphoneShops.getModel();
//        BaseEntity detailItem = detailRepository.getByModel(model);
//        // если модель есть в детальном репозитории сохраняем ее в репозиториии с магазинами
//        if (detailItem != null) {
//            smartphoneShops.setModelId(detailItem.getId());
//            smartphoneShops.setShopId(shops.getValue());
//            SmartphoneShops itemFromDB = shopsRepository.getByProductId(smartphoneShops.getProductId());
//            if (itemFromDB == null){
//                // если нет записи то сохраняем как новую
//                log.info("Item saved to *****Shops as new, ID: " + detailItem.getId() + " --- " + smartphoneShops.getModel());
//                shopsRepository.save(smartphoneShops);
//            }else {
//                // если есть запись, то обновляем только цену
//                itemFromDB.setPrice(smartphoneShops.getPrice());
//                itemFromDB.setUpdateDate(smartphoneShops.getUpdateDate());
//                log.info("Item update into *****Shops. ID: " + itemFromDB.getModelId() +
//                        " new price: " + itemFromDB.getPrice());
//                shopsRepository.save(itemFromDB);
//            }
//            // если модели нет то добавляем в детальный, НО потом ее надо будет заполнить данными, для этого есть флаг
//        }else {
//            saveToDetailDB(smartphoneShops, shops);
//        }
//    }
//
//    private void saveToDetailDB(SmartphoneShops itemShops, Shops shops) {
//        Smartphone item = new Smartphone();
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
