package com.alexsoft.utils.deleted;//package com.prices.controllers;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.prices.entyties.categories.shops.NotebookShop;
//import com.prices.enums.Shops;
//import com.prices.services.ServiceShop;
//import com.prices.services.vek21.ServiceVek21;
//
//import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
//import java.util.List;
//
//@RestController()
//@RequestMapping("/21vek")
//public class Controller21Vek {
//
//    static final Logger log = LogManager.getLogger(Controller21Vek.class.getSimpleName());
//    @Autowired
//    private ServiceVek21 serviceVek21;
//    @Autowired
//    private ServiceShop serviceShop;
//
//    @GetMapping("/saveNotebooksShopsToDB")
//    List<NotebookShop> saveNotebooksShopsToDB() throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        log.info("called saveNotebooksShopsToDB");
//        List<NotebookShop> notebooks = serviceVek21.getNotebooksLinksAndModels();
//        for (NotebookShop item : notebooks) {
//            serviceShop.save(item, Shops.VEK21, "заглушка");
//        }
//        return notebooks;
//    }
//
//
//}
