package com.alexsoft.utils.deleted;//package com.prices.controllers;
//
//import com.prices.entyties.categories.BaseEntity;
//import com.prices.entyties.categories.shops.*;
//import com.prices.enums.Shops;
//import com.prices.services.ServiceShop;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
//import java.util.List;
//
//import static com.prices.constants.Constants.*;
//
//@RestController()
//@RequestMapping("/1k")
//public class ControllerK1 {
//
//    static final Logger log = LogManager.getLogger(ControllerK1.class.getSimpleName());
//    @Autowired
//    private ServiceShop serviceShop;
//
//     @GetMapping("/saveItemToDB")
//    public List<NotebookShop> saveItemToDB(@RequestParam Integer pages, @RequestParam String category) throws Exception {
//        log.info("called saveItemToDB {}", category);
//        List<NotebookShop> shops = getBaseShops(pages, category);
//        if (shops != null) {
//            for (BaseShop shop : shops) {
//                serviceShop.save(shop, Shops.K1, category);
//            }
//        }else {
//            log.info("No items for save {}", category);
//        }
//        return shops;
//    }
//
//    private List<NotebookShop> getBaseShops(@RequestParam Integer pages, @RequestParam String category) throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
//        List<NotebookShop> shops = null;
////        if (TV.equals(category)) {
////            shops = service1K.getItemsFromSite(env.getProperty("OnlinerTv"), new TvShop(), pages);
////        } else if (TABLET.equals(category)) {
////            shops = service1K.getItemsFromSite(env.getProperty("OnlinerTablet"), new TabletShop(), pages);
////        } else if (SMARTPHONE.equals(category)) {
////            shops = service1K.getItemsFromSite(env.getProperty("OnlinerSmartphone"), new SmartphoneShop(), pages);
////        } else if (NOTEBOOK.equals(category)) {
////            shops = service1K.getItemsFromSite(env.getProperty("1kNotebook"), new NotebookShop(), pages);
////        }
//        return shops;
//    }
//
//    @GetMapping("/grabDetailDataForItem")
//    public BaseEntity grabDetailDataForItem(@RequestParam Long id, @RequestParam String category) throws IOException {
//        log.info("called grabDetailDataForItem");
//        return service1K.grubFullDataById(id, category);
//    }
//}
