package com.alexsoft.utils.deleted;//package com.prices.services.k1;
//
//import com.prices.db.BaseEntityRepository;
//import com.prices.entyties.categories.BaseEntity;
//import com.prices.entyties.categories.shops.BaseShop;
//import com.prices.parser.sources.K1Parser;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.gargoylesoftware.htmlunit.WebClient;
//import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
//import com.gargoylesoftware.htmlunit.html.HtmlDivision;
//import com.gargoylesoftware.htmlunit.html.HtmlPage;
//import com.prices.entyties.categories.shops.NotebookShop;
//import com.prices.enums.Shops;
//import com.prices.services.OnlinerWebClient;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class Service1K extends OnlinerWebClient {
//
//    static final Logger log = LogManager.getLogger(Service1K.class.getSimpleName());
//    private WebClient webClient = getWebClient();
//    @Autowired
//    private BaseEntityRepository baseEntityRepository;
//
////    public List<NotebookShop> getItemsFromSite(String url, BaseShop baseShop, Integer pages) throws IOException {
//////        String url = "https://komp.1k.by/mobile-notebooks/s.php?alias=mobile&alias2=notebooks&&viewmode=table";
//////        String url = "https://komp.1k.by/mobile-notebooks/s.php?alias=mobile&alias2=notebooks&viewmode=table&page=3";
////        HtmlPage page = webClient.getPage(url);
////        List<NotebookShop> notebookLinks = new ArrayList<>();
////        List<HtmlDivision> productCell = page.getByXPath("//div[@class='prod-cell']");
////        for (int i = 0; i < productCell.size(); i++) {
////            NotebookShop notebookShops = new NotebookShop();
////            notebookShops.setModel(getModel(productCell.get(i), i));
////            notebookShops.setModelLink(getLink(productCell.get(i), i));
////            notebookShops.setProductId(getProductId(productCell.get(i), i));
////            notebookShops.setPrice(getPrice(productCell.get(i), i));
////            notebookShops.setUpdateDate(getDate());
////            notebookShops.setSource(Shops.K1.name());
////            notebookLinks.add(notebookShops);
////        }
////        return notebookLinks;
////    }
//
////    private Double getPrice(HtmlDivision productCell, int i){
////        HtmlAnchor priceAnchor = (HtmlAnchor) productCell.getByXPath("//span[@class='money money--cash']//a").get(i);
////        String priceString = priceAnchor.getTextContent().trim()
////                .replace(" ", "")
////                .split(",")[0];
////        return Double.parseDouble(priceString);
////    }
//
////    private String getLink(HtmlDivision productCell, int i){
////        HtmlAnchor productCellHead = (HtmlAnchor) productCell.getByXPath("//header[@class='prod-cell__head']//a").get(i);
////        return "https://komp.1k.by"+productCellHead.getHrefAttribute();
////    }
////
////    private String getModel(HtmlDivision productCell, int i){
////        HtmlAnchor productCellHead = (HtmlAnchor) productCell.getByXPath("//header[@class='prod-cell__head']//a").get(i);
////        String linkContent = productCellHead.getTextContent();
////        String splitted = linkContent.split("Ноутбук ")[1];
////        String replaced = splitted.replace("(", "");
////        String replaced1 = replaced.replace("год", "");
////        String replaced2 = replaced1.replace("ГОД", "");
////       return replaced2.replace(")", "");
////    }
////
////    private String getProductId(HtmlDivision productCell, int i) {
////        return productCell.getAttribute("data-productid");
////    }
//
//    public BaseEntity grubFullDataById(Long id, String category) throws IOException {
//        log.info("called grubFullDataById");
//        BaseEntity itemFromDB = getBaseEntityByIdFromDB(id, category);
//        BaseEntity item = null;
//        if (itemFromDB != null) {
//            baseEntityRepository.save(item);
//        }
//        return item;
//    }
//
//    // DB
//    public BaseEntity getBaseEntityByIdFromDB(Long id, String category) {
//        return baseEntityRepository.findByCategoryAndId(category, id);
//    }
//
//    private BaseEntity getUpdatedItemFromSite(BaseEntity itemFromDB, String category) throws IOException {
//        BaseEntity itemUpdated;
//        itemUpdated = K1Parser.getBaseEntity(itemFromDB, category);
//        itemUpdated.setId(itemFromDB.getId());
//        itemUpdated.setModel(itemFromDB.getModel());
//        itemUpdated.setLinkDetailInfo(itemFromDB.getLinkDetailInfo());
//        itemUpdated.setIsFillDetailData(true);
//        String producer = itemFromDB.getModel().split(" ")[0];
//        itemUpdated.setProducer(producer);
//        itemUpdated.setSource(Shops.K1.name());
//        itemUpdated.setCategory(category);
//        return itemUpdated;
//    }
//}
