package com.alexsoft.utils.deleted;//package com.prices.services.onliner;
//
//import com.prices.db.NotebookRepository;
//import com.prices.entyties.categories.Notebook;
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
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class ServiceNotebooks extends HtmlUnitAbstract {
//
//    static final Logger log = LogManager.getLogger(ServiceNotebooks.class.getSimpleName());
//    @Autowired
//    NotebookRepository notebookRepository;
//
//    public List<Notebook> grubFullDataForAll() throws IOException {
//        log.info("called grubFullDataForAll");
//        List<Notebook> notebooksUpdated = new ArrayList<>();
//        List<Notebook> notebooksFromDB =
//                notebookRepository.findAllByIsFillDetailDataIsFalseAndSourceContaining(Shops.ONLINER.name());
//        for (Notebook itemFromDB: notebooksFromDB){
//            Notebook updatedNotebook = getUpdatedNotebook(itemFromDB);
//            notebooksUpdated.add(updatedNotebook);
//            log.info("got data for: " + updatedNotebook.getModel() );
//        }
//        notebookRepository.saveAll(notebooksUpdated);
//        return notebooksUpdated;
//    }
//
//    public Notebook grubFullDataById(Long id) throws IOException {
//        log.info("called grubFullDataById");
//        Optional<Notebook> itemDB = notebookRepository.findById(id);
//        Notebook notebook = null;
//        if (itemDB.isPresent()) {
//            notebook = getUpdatedNotebook(itemDB.get());
//            notebookRepository.save(notebook);
//        }
//        return notebook;
//    }
//
//    private Notebook getUpdatedNotebook(Notebook itemFromDB) throws IOException {
//        Notebook notebookUpdated;
//        notebookUpdated = DetailParse.getNotebook(itemFromDB.getLinkDetailInfo());
//        notebookUpdated.setId(itemFromDB.getId());
//        notebookUpdated.setModel(itemFromDB.getModel());
//        notebookUpdated.setLinkDetailInfo(itemFromDB.getLinkDetailInfo());
//        notebookUpdated.setIsFillDetailData(true);
//        String producer = itemFromDB.getModel().split(" ")[0];
//        notebookUpdated.setProducer(producer);
//        return notebookUpdated;
//    }
//}
