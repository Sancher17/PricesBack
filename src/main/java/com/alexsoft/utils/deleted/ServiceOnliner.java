package com.alexsoft.utils.deleted;//package com.prices.services;
//
//
//import com.jayway.jsonpath.JsonPath;
//import com.prices.db.BaseEntityRepository;
//import com.prices.entyties.categories.BaseEntity;
//import com.prices.entyties.categories.shops.BaseShop;
//import com.prices.enums.Shops;
//import com.prices.parser.sources.OnlinerParser;
//import com.prices.utils.ContainerNew;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class ServiceOnliner extends OnlinerWebClient {
//
//    static final Logger log = LogManager.getLogger(ServiceOnliner.class.getSimpleName());
//    private static final String PRODUCTS = "$.products[*]";
//    @Autowired
//    private BaseEntityRepository baseEntityRepository;
//
//    // site
//    public List<BaseShop> getItemsFromSite(String url, BaseShop baseShop, Integer pages) throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
//        log.info("called getLinksAndModels");
//        List<BaseShop> entities = new ArrayList<>(getParsedItemsFromSite(baseShop, getContent(url)));
//        int countPage = 2;
//        for (int i = 1; i < pages; i++) {
//            entities.addAll(getParsedItemsFromSite(baseShop, getContent(url + "?page=" + countPage)));
//            countPage++;
//        }
//        return entities;
//    }
//
//    private List<BaseShop> getParsedItemsFromSite(BaseShop baseShop, String content) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
//        List<BaseShop> entities = new ArrayList<>();
//        List<String> models = JsonPath.read(content, PRODUCTS + ".full_name");
//        List<String> links = JsonPath.read(content, PRODUCTS + ".html_url");
//        List<String> productIDs = JsonPath.read(content, PRODUCTS + ".id");
//        List<String> prices = JsonPath.read(content, PRODUCTS + ".prices.price_min.amount");
//        List<String> status = JsonPath.read(content, PRODUCTS + ".status");
//        for (int i = 0; i < models.size(); i++) {
//            if (!status.get(i).equals("old") && !prices.get(i).equals("null")){
//
//                BaseShop newItem = ContainerNew.createInstanceBaseShop(baseShop.getClass());
//                newItem.setModel(getModel(models.get(i)));
//                newItem.setModelLink(links.get(i));
//                newItem.setProductId(String.valueOf(productIDs.get(i)));
//                newItem.setPrice(Double.parseDouble(prices.get(i)));
//                newItem.setUpdateDate(getDate());
//                newItem.setSource(Shops.ONLINER.name());
//                entities.add(newItem);
//            }
//        }
//        return entities;
//    }
//
//    public BaseEntity grubFullDataById(Long id, String category) throws IOException {
//        log.info("called grubFullDataById");
//        BaseEntity itemFromDB = getBaseEntityByIdFromDB(id, category);
//        BaseEntity item = null;
//        if (itemFromDB != null) {
//            item = getUpdatedItemFromSite(itemFromDB, category);
//            baseEntityRepository.save(item);
//        }
//        return item;
//    }
//
//    public BaseEntity grubFullDataByIdTEST(BaseEntity itemFromDB, String category) throws IOException {
//        log.info("called grubFullDataById");
////        BaseEntity itemFromDB = getBaseEntityByIdFromDB(id, category);
//        BaseEntity item = null;
//        if (itemFromDB != null) {
//            item = getUpdatedItemFromSite(itemFromDB, category);
//            baseEntityRepository.save(item);
//        }
//        return item;
//    }
//
//    private BaseEntity getUpdatedItemFromSite(BaseEntity itemFromDB, String category) throws IOException {
//        BaseEntity itemUpdated;
//        itemUpdated = OnlinerParser.getBaseEntity(itemFromDB, category);
//        itemUpdated.setId(itemFromDB.getId());
//        itemUpdated.setModel(itemFromDB.getModel());
//        itemUpdated.setLinkDetailInfo(itemFromDB.getLinkDetailInfo());
//        itemUpdated.setIsFillDetailData(true);
//        String producer = itemFromDB.getModel().split(" ")[0];
//        itemUpdated.setProducer(producer);
//        itemUpdated.setSource(Shops.ONLINER.name());
//        itemUpdated.setCategory(category);
//        return itemUpdated;
//    }
//
//    private String getModel(String model) {
//        String replaced1 = model.replace("(", "");
//        String replaced2 = replaced1.replace(")", "");
//        String replaced3 = replaced2.replace(" [", "");
//        String replaced4 = replaced3.replace("год", "");
//        String replaced5 = replaced4.replace("ГОД", "");
//        return replaced5.replace("]", "");
//    }
//
//    // DB
//    public BaseEntity getBaseEntityByIdFromDB(Long id, String category) {
//        return baseEntityRepository.findByCategoryAndId(category, id);
//    }
//
//    public List<BaseEntity> getAllBaseEntitiesFromDB(String model){
////        List<BaseEntity> entities = baseEntityRepository.findAllRows(category);
////        List<BaseEntity> entities = baseEntityRepository.findByCategory(category);
//        List<BaseEntity> entities = baseEntityRepository.findAllByModelContaining(model);
//        return entities;
//    }
//
//    public List<BaseEntity> grabDetailDataForAll(String category) throws IOException {
//        List<BaseEntity> entities = new ArrayList<>();
//        List<BaseEntity> allFromDb = baseEntityRepository.findAllByCategoryAndSource(category, Shops.ONLINER.name());
//        for (BaseEntity baseEntity : allFromDb) {
//            entities.add(grubFullDataByIdTEST(baseEntity, category));
//        }
//        return entities;
//    }
//}
