package com.alexsoft.utils.deleted;//package com.prices.services.onliner;
//
//import com.prices.db.SmartphoneRepository;
//import com.prices.entyties.categories.Smartphone;
//import com.prices.entyties.categories.shops.BaseShops;
//import com.prices.enums.Shops;
//import com.prices.grabber.detail.DetailParse;
//import com.prices.services.HtmlUnitAbstract;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.Optional;
//
//@Component
//public class ServiceSmartphones extends HtmlUnitAbstract {
//
//    static final Logger log = LogManager.getLogger(ServiceSmartphones.class.getSimpleName());
//    @Autowired
//    SmartphoneRepository repository;
//
//    public Smartphone grubFullDataById(Long id) throws IOException {
//        log.info("called grubFullDataById");
//        Optional<Smartphone> itemDB = repository.findById(id);
//        Smartphone smartphone = null;
//        if (itemDB.isPresent()) {
//            smartphone = getUpdatedNotebook(itemDB.get());
//            repository.save(smartphone);
//        }
//        return smartphone;
//    }
//
//    private Smartphone getUpdatedNotebook(Smartphone itemFromDB) throws IOException {
//        Smartphone smartphoneUpdated;
//        smartphoneUpdated = DetailParse.getSmartphone(itemFromDB.getLinkDetailInfo());
//        smartphoneUpdated.setId(itemFromDB.getId());
//        smartphoneUpdated.setModel(itemFromDB.getModel());
//        smartphoneUpdated.setLinkDetailInfo(itemFromDB.getLinkDetailInfo());
//        smartphoneUpdated.setIsFillDetailData(true);
//        String producer = itemFromDB.getModel().split(" ")[0];
//        smartphoneUpdated.setProducer(producer);
//        smartphoneUpdated.setSource(Shops.ONLINER.name());
//        return smartphoneUpdated;
//    }
//}
