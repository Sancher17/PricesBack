package com.alexsoft.utils.deleted;//package com.prices.services.vek21;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.stereotype.Service;
//import com.gargoylesoftware.htmlunit.WebClient;
//import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
//import com.gargoylesoftware.htmlunit.html.HtmlPage;
//import com.gargoylesoftware.htmlunit.html.HtmlSpan;
//import com.prices.entyties.categories.shops.NotebookShop;
//import com.prices.enums.Shops;
//import com.prices.services.OnlinerWebClient;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class ServiceVek21 extends OnlinerWebClient {
//
//    static final Logger log = LogManager.getLogger(ServiceVek21.class.getSimpleName());
//    private WebClient webClient = getWebClient();
//
//    public List<NotebookShop> getNotebooksLinksAndModels() throws IOException {
//        String url = "https://www.21vek.by/notebooks/";
//        HtmlPage page = webClient.getPage(url);
//
//        /** Делал этот кейс (Case 2) чтобы улучшить получение данных, однако также остались проблемы 2 штуки итем со
//         * страницы
//         * не приходят полностью с ланными. Пока исполбзую Case 1
//         *
//         * Case 2
//        List<HtmlDefinitionTerm> root = page.getByXPath("//dt[@class='result__root']");
//        int count = 1;
//        for (HtmlDefinitionTerm item : root) {
//            int span1 = item.getElementsByTagName("span").size();
//            HtmlElement span = item.getElementsByTagName("span").get(4);;
//            NamedNodeMap attributes = span.getAttributes();
//            if ( attributes.getLength() < 10) {
//                span = item.getElementsByTagName("span").get(2);
//            }
//            String attribute = span.getAttribute("data-code");
//            System.out.println("span size " + span1 + " // "+count + " " +attribute);
//            count++;
//        }
//        */
//
//        //Case 1
//        List<NotebookShop> notebookLinks = new ArrayList<>();
//        List<HtmlSpan> spans = page.getByXPath("//span[@data-code]");
//        List<HtmlAnchor> detailLinks = page.getByXPath("//a[@class='result__link j-ga_track']");
//        String productId = null;
//        try {
//            for (int i = 0; i < spans.size(); i++) {
//                NotebookShop notebookShops = new NotebookShop();
//                try {
//                    String model = getModel(spans.get(i), i);
//                    notebookShops.setModel(model);
//                    notebookShops.setModelLink(detailLinks.get(i).getHrefAttribute());
//                    productId = spans.get(i).getAttribute("data-code");
//                    notebookShops.setProductId(productId);
//                    notebookShops.setSource(Shops.VEK21.name());
//                    String price = spans.get(i).getAttribute("data-price");
//                    notebookShops.setPrice(Double.parseDouble(price));
//                    notebookShops.setUpdateDate(getDate());
//                }catch (Exception e){
//                    log.info("Error during parse data from page. " + " productId: " + productId);
//                }
//                notebookLinks.add(notebookShops);
//            }
//        }catch (Exception e){
//            log.info("Error during parse data from page. " + " productId: " + productId);
//        }
//        return notebookLinks;
//    }
//
//    private String getModel(HtmlSpan productCell, int i){
//        String model = productCell.getAttribute("data-name");
//        String replaced0 = model.replaceAll("(?i)ноутбук ", "");
//        replaced0 = replaced0.replaceAll("(?i)Ноутбук ", "");
//        String replaced1 = replaced0.replaceAll("(?i)Игровой ", "");
//        String replaced2 = replaced1.replace("(", "");
//        String replaced3 = replaced2.replace("год", "");
//        String replaced4 = replaced3.replace("ГОД", "");
//       return replaced4.replace(")", "");
//    }
//}
