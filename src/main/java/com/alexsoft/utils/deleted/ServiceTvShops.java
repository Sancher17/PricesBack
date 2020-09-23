package com.alexsoft.utils.deleted;//package com.prices.services;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import com.prices.db.BaseEntityRepository;
//import com.prices.db.TvRepository;
//import com.prices.db.BaseShopRepository;
//import com.prices.db.shops.TvShopsRepository;
//import com.prices.entyties.categories.BaseEntity;
//import com.prices.entyties.categories.Tv;
//import com.prices.entyties.categories.shops.BaseShops;
//import com.prices.entyties.categories.shops.TvShops;
//import com.prices.enums.Shops;
//
//@Component
//public class ServiceTvShops extends HtmlUnitAbstract {
//
//    static final Logger log = LogManager.getLogger(ServiceTvShops.class.getSimpleName());
//
//    @Autowired
//    private TvShopsRepository tvShopsRepository;
//    @Autowired
//    private TvRepository tvRepository;
//    @Autowired
//    private BaseShopRepository baseShopRepository;
//    @Autowired
//    private BaseEntityRepository baseEntityRepository;
//
//    public void save(BaseShops tvShops, Shops shops){
//        String model = tvShops.getModel();
//
//        BaseEntity detailItem = null;
//        BaseShops itemFromDB = null;
//        if (tvShops instanceof TvShops){
//            detailItem = tvRepository.getByModel(model);
//            itemFromDB = tvShopsRepository.getByProductId(tvShops.getProductId());
//        }
//
//        // если модель есть в детальном репозитории сохраняем ее в репозиториии с магазинами
//        if (detailItem != null) {
//            tvShops.setModelId(detailItem.getId());
//            tvShops.setShopId(shops.getValue());
//
//            if (itemFromDB == null){
//                // если нет записи то сохраняем как новую
//                log.info("Item saved to *****Shops as new, ID: " + detailItem.getId() + " --- " + tvShops.getModel());
//                baseShopRepository.save(tvShops);
//            }else {
//                // если есть запись, то обновляем только цену
//                itemFromDB.setPrice(tvShops.getPrice());
//                itemFromDB.setUpdateDate(tvShops.getUpdateDate());
//                log.info("Item update into *****Shops. ID: " + itemFromDB.getModelId() +
//                        " new price: " + itemFromDB.getPrice());
//                baseShopRepository.save(itemFromDB);
//            }
//            // если модели нет то добавляем в детальный, НО потом ее надо будет заполнить данными, для этого есть флаг
//        }else {
//            saveToDetailDB(tvShops, shops);
//        }
//    }
//
//    private void saveToDetailDB(BaseShops itemShops, Shops shops) {
//        Tv item = new Tv();
//        item.setModel(itemShops.getModel());
//        item.setLinkDetailInfo(itemShops.getModelLink());
//        item.setIsFillDetailData(false);
//        baseEntityRepository.save(item);
//        save(itemShops,shops);
//        log.info("called saveToDetailDB. Item saved into Notebook: " + itemShops.getModel());
//    }
//}
