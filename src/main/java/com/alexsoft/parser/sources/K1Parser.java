package com.alexsoft.parser.sources;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.alexsoft.entyties.categories.BaseEntity;
import com.alexsoft.entyties.categories.shops.BaseShop;
import com.alexsoft.enums.Shops;
import com.alexsoft.utils.ContainerNew;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class K1Parser extends BaseParser {

    @Override
    public  Map<String, String> getDetailInfo(Document document, BaseEntity baseEntity) {
        Map<String, String> details = new LinkedHashMap<>();
        Elements elementsByClass = document.getElementsByClass("spec-unit");
        for (Element item : elementsByClass) {
            Elements tr = item.getElementsByTag("tr");
            for (Element element : tr) {
                String key;
                String value;
                try {
                    key = element.getElementsByTag("span").text();
                    value = element.getElementsByTag("td").text();
                    details.put(key, value);
                } catch (Exception e) {
                    log.debug(" " + e);
                }
            }
        }
        return details;
    }

    @Override
    public List<BaseShop> getShortInfo(BaseShop baseShop, String url, Integer pages) throws Exception {
        HtmlPage page = getHtmlPage(url);
        List<BaseShop> baseShopLinks = new ArrayList<>();
        List<HtmlDivision> productCell = page.getByXPath("//div[@class='prod-cell']");
        for (int i = 0; i < productCell.size(); i++) {
            BaseShop newItem = ContainerNew.createInstanceBaseShop(baseShop.getClass());
            newItem.setModel(getModel(productCell.get(i), i));
            newItem.setModelLink(getLink(productCell.get(i), i));
            newItem.setProductId(getProductId(productCell.get(i), i));
            newItem.setPrice(getPrice(productCell.get(i), i));
            newItem.setUpdateDate(getDate());
            newItem.setSource(Shops.K1.name());
            baseShopLinks.add(newItem);
        }
//        getDetailPrices(baseShopLinks);
        return baseShopLinks;
    }


    private void getDetailPrices(List<BaseShop> baseShopLinks) throws IOException {
        for (BaseShop item : baseShopLinks) {
            String newLink = item.getModelLink().replace(".html", "/offers/");
            HtmlPage htmlPage = getHtmlPage(newLink);
            List<HtmlDivision> prices = htmlPage.getByXPath("//div[@class='seller__price']");
            for (HtmlDivision price : prices) {
                System.out.println(item.getModel() + " " + price.getTextContent());
            }
        }
    }

    private String getLink(HtmlDivision productCell, int i){
        HtmlAnchor productCellHead = (HtmlAnchor) productCell.getByXPath("//header[@class='prod-cell__head']//a").get(i);
        return "https://komp.1k.by"+productCellHead.getHrefAttribute();
    }

    private String getModel(HtmlDivision productCell, int i){
        HtmlAnchor productCellHead = (HtmlAnchor) productCell.getByXPath("//header[@class='prod-cell__head']//a").get(i);
        String linkContent = productCellHead.getTextContent();
        String[] splitted = linkContent.split(" ");
        StringBuilder withoutFirstWord = new StringBuilder();
        int j = 1;
        if (splitted[0].equals("Телевизор")){
            j = 2; // skip 2 first words
        }
        for (; j < splitted.length; j++) {
            withoutFirstWord.append(splitted[j]).append(" ");
        }
        String replaced = withoutFirstWord.toString().trim().replace("(", "");
        String replaced1 = replaced.replace("год", "");
        String replaced2 = replaced1.replace("ГОД", "");
        return replaced2.replace(")", "");
    }

    private String getProductId(HtmlDivision productCell, int i) {
        return productCell.getAttribute("data-productid");
    }

    private Double getPrice(HtmlDivision productCell, int i){
        HtmlAnchor priceAnchor = (HtmlAnchor) productCell.getByXPath("//span[@class='money money--cash']//a").get(i);
        String priceString = priceAnchor.getTextContent().trim()
                .replace(" ", "")
                .split(",")[0];
        return Double.parseDouble(priceString);
    }
}
