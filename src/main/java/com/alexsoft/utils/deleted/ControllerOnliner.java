package com.alexsoft.utils.deleted;//package com.prices.controllers;
//
//import static com.prices.constants.Constants.NOTEBOOK;
//import static com.prices.constants.Constants.SMARTPHONE;
//import static com.prices.constants.Constants.TABLET;
//import static com.prices.constants.Constants.TV;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import com.prices.db.BaseEntityRepository;
//import com.prices.entyties.categories.BaseEntity;
//import com.prices.entyties.categories.shops.BaseShop;
//import com.prices.entyties.categories.shops.NotebookShop;
//import com.prices.entyties.categories.shops.SmartphoneShop;
//import com.prices.entyties.categories.shops.TabletShop;
//import com.prices.entyties.categories.shops.TvShop;
//import com.prices.enums.Shops;
//import com.prices.services.ServiceShop;
//import com.prices.services.ServiceOnliner;
//
//import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
//import java.util.List;
//
//@Configuration
//@RestController()
//@RequestMapping("/onliner")
//@PropertySource("classpath:application.properties")
//public class ControllerOnliner {
//
//    static final Logger log = LogManager.getLogger(ControllerOnliner.class.getSimpleName());
//
//    @Autowired
//    private Environment env;
//    @Autowired
//    private ServiceOnliner serviceOnliner;
//    @Autowired
//    private ServiceShop serviceShop;
//    @Autowired
//    private BaseEntityRepository baseEntityRepository;
//
////    @GetMapping("/allDto")
////    public List<NotebookDto> getAllDto() throws IOException, IllegalAccessException, InstantiationException,
////            NoSuchMethodException, InvocationTargetException {
////        log.info("called getAllDto");
////        List<BaseShops> notebooks = serviceOnliner.getItemsFromSite(urlNotebook, new NotebookShops(), 1);
////        NotebookDto notebookDto = new NotebookDto();
////        return notebookDto.getNotebooksDto(notebooks);
////    }
//
//    @GetMapping("/allFromSite")
//    public List<BaseShop> getAllFromSite(@RequestParam Integer pages, @RequestParam String category) throws IOException,
//            IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
//        log.info("called getAll");
//        return getBaseShops(pages, category);
//    }
//
//    @GetMapping("/allFromDB")
//    public List<BaseEntity> allFromDB(@RequestParam String category) {
//        log.info("called allFromDB");
//        return serviceOnliner.getAllBaseEntitiesFromDB(category);
//    }
//
//    @GetMapping("/id")
//    public BaseEntity findById(@RequestParam Long id, @RequestParam String category) {
//        log.info("called findById");
//        return serviceOnliner.getBaseEntityByIdFromDB(id, category);
//    }
//
//    @GetMapping("/grabDetailDataForItem")
//    public BaseEntity grabDetailDataForItem(@RequestParam Long id, @RequestParam String category) throws IOException {
//        log.info("called grabDetailDataForItem");
//        return serviceOnliner.grubFullDataById(id, category);
//    }
//
//    @GetMapping("/grabDetailDataForAll")
//    public List<BaseEntity> grabDetailDataForAll(@RequestParam String category) throws IOException {
//        log.info("called grabDetailDataForAll");
//        return serviceOnliner.grabDetailDataForAll(category);
//    }
//
//    @GetMapping("/saveItemToDB")
//    public List<BaseShop> saveItemToDB(@RequestParam Integer pages, @RequestParam String category) throws Exception {
//        log.info("called saveItemToDB {}", category);
//        List<BaseShop> shops = getBaseShops(pages, category);
//        if (shops != null) {
//            for (BaseShop shop : shops) {
//                serviceShop.save(shop, Shops.ONLINER, category);
//            }
//        }else {
//            log.info("No items for save {}", category);
//        }
//        return shops;
//    }
//
//    private List<BaseShop> getBaseShops(@RequestParam Integer pages, @RequestParam String category) throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
//        List<BaseShop> shops = null;
//        if (TV.equals(category)) {
//            shops = serviceOnliner.getItemsFromSite(env.getProperty("OnlinerTv"), new TvShop(), pages);
//        } else if (TABLET.equals(category)) {
//            shops = serviceOnliner.getItemsFromSite(env.getProperty("OnlinerTablet"), new TabletShop(), pages);
//        } else if (SMARTPHONE.equals(category)) {
//            shops = serviceOnliner.getItemsFromSite(env.getProperty("OnlinerSmartphone"), new SmartphoneShop(), pages);
//        } else if (NOTEBOOK.equals(category)) {
//            shops = serviceOnliner.getItemsFromSite(env.getProperty("OnlinerNotebook"), new NotebookShop(), pages);
//        }
//        return shops;
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
