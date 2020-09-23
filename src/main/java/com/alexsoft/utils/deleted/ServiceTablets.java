package com.alexsoft.utils.deleted;//package com.prices.services.onliner;
//
//import org.springframework.stereotype.Component;
//import com.prices.services.HtmlUnitAbstract;
//
//@Component
//public class ServiceTablets extends HtmlUnitAbstract {
//
//
////    static final Logger log = LogManager.getLogger(ServiceTablets.class.getSimpleName());
////    @Autowired
////    TabletRepository repository;
////
////    public Tablet grubFullDataById(Long id) throws IOException {
////        log.info("called grubFullDataById");
////        Optional<BaseEntity> itemDB =  repository.findById(id);
////        Tablet item = null;
////        if (itemDB.isPresent()) {
////            item = getUpdatedItem((Tablet) itemDB.get());
////            repository.save(item);
////        }
////        return item;
////    }
////
////    private Tablet getUpdatedItem(Tablet itemFromDB) throws IOException {
////        Tablet itemUpdated;
////        itemUpdated = DetailParse.getTablet(itemFromDB.getLinkDetailInfo());
////        itemUpdated.setId(itemFromDB.getId());
////        itemUpdated.setModel(itemFromDB.getModel());
////        itemUpdated.setLinkDetailInfo(itemFromDB.getLinkDetailInfo());
////        itemUpdated.setIsFillDetailData(true);
////        String producer = itemFromDB.getModel().split(" ")[0];
////        itemUpdated.setProducer(producer);
////        itemUpdated.setSource(Shops.ONLINER.name());
////        return itemUpdated;
////    }
//}
