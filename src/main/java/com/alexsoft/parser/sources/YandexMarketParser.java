package com.alexsoft.parser.sources;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.alexsoft.entyties.categories.BaseEntity;
import com.alexsoft.entyties.categories.shops.BaseShop;
import com.alexsoft.enums.Shops;
import com.alexsoft.utils.ContainerNew;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class YandexMarketParser extends BaseParser {


    @Override
    public Map<String, String> getDetailInfo(Document document, BaseEntity baseEntity) {
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
                    log.debug("" +e);
                }
            }
        }
        return details;
    }

    private List<Double> getPrices(WebDriver driver){
        List<Double> prices = new ArrayList<>();
        List<WebElement> priceElements = ((ChromeDriver) driver).findElementsByClassName("n-snippet-card2__price");
        for (WebElement item : priceElements) {
            String textPrice = item.getText();
            String[] s = textPrice.split(" ");
            String price = "";
            if (s.length == 3){
                price = s[1].replace(",", ".");
            }else{
                price = s[1]+s[2].replace(",", ".");
            }
            Double finalPrice = Double.parseDouble(price);
            prices.add(finalPrice);
        }
        return prices;
    }

    @Override
    public List<BaseShop> getShortInfo(BaseShop baseShop, String url, Integer pages) throws Exception {
        WebDriver driver = getWebDriver();
        driver.get(url);
        Thread.sleep(10000);
        List<BaseShop> baseShopLinks = new ArrayList<>();
        List<String> models = getModels(driver);
        List<Double> prices = getPrices(driver);
        List<String> links = getLinks(driver);

        for (int i = 0; i < models.size(); i++) {
            BaseShop newItem = ContainerNew.createInstanceBaseShop(baseShop.getClass());
            newItem.setModel(models.get(i));
            newItem.setModelLink(links.get(i));
//            newItem.setProductId(getProductId(productCell.get(i), i));
            newItem.setPrice(prices.get(i));
            newItem.setUpdateDate(getDate());
            newItem.setSource(Shops.YANDEXMARKET.name());
            baseShopLinks.add(newItem);
        }
        return baseShopLinks;
    }

    private List<String> getLinks(WebDriver driver) {
        List<String> itemLinks = new ArrayList<>();
        List<WebElement> links = driver.findElements(By.className("n-snippet-card2__title"));
        for (WebElement item : links) {
            String link = item.findElement(By.tagName("a")).getAttribute("href");
            itemLinks.add(link);
        }
        return itemLinks;
    }

    private List<String> getModels(WebDriver driver) throws InterruptedException {
        Thread.sleep(3000);
        List<WebElement> items = ((ChromeDriver) driver).findElementsByClassName("n-snippet-card2__title");
        List<String> models = new ArrayList<>();
        for (WebElement webElement : items) {
            models.add(webElement.getText());
        }
        return models;
    }
}
