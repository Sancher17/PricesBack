package com.alexsoft.utils.deleted;//package com.prices.services;
//
//import com.prices.db.BaseEntityRepository;
//import com.prices.db.TvRepository;
//import com.prices.db.BaseShopRepository;
//import com.prices.db.shops.TvShopsRepository;
//import com.prices.entyties.categories.BaseEntity;
//import com.prices.entyties.categories.Tv;
//import com.prices.entyties.categories.shops.BaseShops;
//import com.prices.entyties.categories.shops.TvShops;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import com.prices.db.NotebookRepository;
//import com.prices.db.shops.NotebookShopsRepository;
//import com.prices.entyties.categories.Notebook;
//import com.prices.entyties.categories.shops.NotebookShops;
//import com.prices.enums.Shops;
//
//@Component
//public class ServiceNotebookShops extends HtmlUnitAbstract  implements IShopService{
//
//    static final Logger log = LogManager.getLogger(ServiceNotebookShops.class.getSimpleName());
//
//    @Autowired
//    private NotebookShopsRepository shopsRepository;
//    @Autowired
//    private NotebookRepository detailRepository;
//    @Autowired
//    private TvShopsRepository tvShopsRepository;
//    @Autowired
//    private TvRepository tvRepository;
//    @Autowired
//    private NotebookRepository notebookRepository;
//    @Autowired
//    private NotebookShopsRepository notebookShopsRepository;
//    @Autowired
//    private BaseShopRepository baseShopRepository;
//    @Autowired
//    private BaseEntityRepository baseEntityRepository;
//
////    public void customSave(NotebookShops notebookShops, Shops shops) {
////        String model = notebookShops.getModel();
////        BaseEntity notebookDetail = detailRepository.getByModel(model);
////        // если модель есть в детальном репозитории сохраняем ее в репозиториии с магазинами
////        if (notebookDetail != null) {
////            notebookShops.setModelId(notebookDetail.getId());
////            notebookShops.setShopId(shops.getValue());
////            NotebookShops notebookShopsFromDB = shopsRepository.getByProductId(notebookShops.getProductId());
////            if (notebookShopsFromDB == null) {
////                // если нет записи то сохраняем как новую
////                log.info("Item saved to NotebooksShops as new, ID: " + notebookDetail.getId() + " --- " + notebookShops.getModel());
////                shopsRepository.save(notebookShops);
////            } else {
////                // если есть запись, то обновляем только цену
////                notebookShopsFromDB.setPrice(notebookShops.getPrice());
////                notebookShopsFromDB.setUpdateDate(notebookShops.getUpdateDate());
////                log.info("Item update into NotebooksShops. ID: " + notebookShopsFromDB.getModelId() +
////                        " new price: " + notebookShopsFromDB.getPrice());
////                shopsRepository.save(notebookShopsFromDB);
////            }
////            // если модели нет то добавляем в детальный, НО потом ее надо будет заполнить данными, для этого есть флаг
////        } else {
////            saveToDetailDB(notebookShops, shops);
////        }
////    }
//
////    private void saveToDetailDB(NotebookShops notebookShops, Shops shops) {
////        Notebook notebook = new Notebook();
////        notebook.setModel(notebookShops.getModel());
////        notebook.setLinkDetailInfo(notebookShops.getModelLink());
////        notebook.setIsFillDetailData(false);
////        notebook.setSource(notebookShops.getSource());
////        detailRepository.save(notebook);
////        customSave(notebookShops, shops);
////        log.info("called saveToDetailDB. Item saved into Notebook: " + notebookShops.getModel());
////    }
//
//    @Override
//    public void save(BaseShops tvShops, Shops shops) {
//
//        String model = tvShops.getModel();
//
//        BaseEntity detailItem = null;
//        BaseShops itemFromDB = null;
//        if (tvShops instanceof TvShops){
//            detailItem = tvRepository.getByModel(model);
//            itemFromDB = tvShopsRepository.getByProductId(tvShops.getProductId());
//        }else if(tvShops instanceof NotebookShops){
//            detailItem = detailRepository.getByModel(model);
//            itemFromDB = notebookShopsRepository.getByProductId(tvShops.getProductId());
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
//        BaseEntity item = new Tv();
//        item.setModel(itemShops.getModel());
//        item.setLinkDetailInfo(itemShops.getModelLink());
//        item.setIsFillDetailData(false);
//        baseEntityRepository.save(item);
//        save(itemShops,shops);
//        log.info("called saveToDetailDB. Item saved into Notebook: " + itemShops.getModel());
//    }
//}
