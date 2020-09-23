package com.alexsoft.parser.sources;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.alexsoft.audit.LogExecutionTime;
import com.alexsoft.entyties.categories.BaseEntity;
import com.alexsoft.entyties.categories.shops.BaseShop;
import com.alexsoft.enums.Shops;
import com.alexsoft.utils.ContainerNew;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class KupitutParser extends BaseParser {


    @LogExecutionTime
    @Override
    public List<BaseShop> getShortInfo(BaseShop baseShop, String url, Integer pages) throws Exception {
        // next page: ?isOffers=0&iPageNo=2
        HtmlPage page = getHtmlPage(url);
        List<BaseShop> baseShopLinks = new ArrayList<>();
        List<HtmlDivision> names = page.getByXPath("//div[@class='head']");
        names.remove(0);
        List<HtmlDivision> prices = page.getByXPath("//div[@class='prices']");
        prices.remove(0);
        List<HtmlDivision> productIds = page.getByXPath("//div[@class='itemList_item fotorama_triger']");
        for (int i = 0; i < productIds.size(); i++) {
            //get
            String model = names.get(i).getElementsByTagName("a").get(0).asText();
            String price = prices.get(i).getElementsByTagName("big").get(0).asText();
            String link = names.get(i).getElementsByTagName("a").get(0).getAttribute("href");
            String productId = productIds.get(i).getAttribute("id");
            //creat
            BaseShop newItem = ContainerNew.createInstanceBaseShop(baseShop.getClass());
            newItem.setModel(getModel(model));
            newItem.setModelLink(link);
            newItem.setProductId(productId);
            String priceCleared = getPrice(price);
            newItem.setPrice(Double.parseDouble(priceCleared));
            newItem.setUpdateDate(getDate());
            newItem.setSource(Shops.KUPITUT.name());
            baseShopLinks.add(newItem);
        }
        return baseShopLinks;
    }

    private String getModel(String model) {
        model = removeRussianWords(model);
        model = removeBrackets(model);
        return model.trim();
    }

    private String getPrice(String price) {
        String priceEdited = price.replace(",", ".");
        priceEdited = priceEdited.replace(" ", "");
        byte[] bytes = priceEdited.getBytes();
        byte[] newBytes = new byte[8];
        int i = 0;
        for (byte aByte : bytes) {
           if(aByte > 0){
               newBytes[i] = aByte;
               i++;
           }
        }
        String str = new String(newBytes, StandardCharsets.UTF_8);
        return str.trim();
    }

    @Override
    protected Map<String, String> getDetailInfo(Document document, BaseEntity baseEntity) throws IOException {
        getComments(document);
        return null;
    }

    private void getComments(Document document) throws IOException {

        Elements item = document.getElementsByClass("item_tabs");
        Element element = item.get(0).getElementsByTag("a").get(3).getElementsByAttribute("href").get(0);
        String linkComments = element.attributes().asList().get(0).getValue();
        String testLink = "https://kupi.tut.by/noutbuki/asus-noutbuk-vivobook-15-x512-518204228.html?comments#comments";
        HtmlPage htmlPage = getHtmlPage(testLink);

        WebDriver webDriver = getWebDriver();
        webDriver.get(testLink);
        WebElement nQJtL1 = webDriver.findElement(By.className("_3nQJtL1"));
        String marketLink = nQJtL1.findElement(By.tagName("a")).getAttribute("href");
        webDriver.get(marketLink);

        Document document1 = Jsoup.parse(htmlPage.getWebResponse().getContentAsString());
        HtmlDivision byXPath = (HtmlDivision) htmlPage.getByXPath("//div[@class='_3nQJtL1'").get(0);
//        String sss = "https://market.yandex.by/product--noutbuk-asus-vivobook-15-x512/518204228/reviews?hid=91013&track=partner&pp=908&clid=2348315&vid=1&mclid=1002&distr_type=7&utm_term=model_reviews2&utm_content=offers-9&utm_campaign=2348315&utm_source=market_widget&utm_medium=cpc";
//        HtmlPage htmlPage1 = getHtmlPage(sss);

        System.out.println();


    }
}
