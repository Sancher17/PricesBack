package com.alexsoft.parser.sources;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import com.alexsoft.audit.LogExecutionTime;
import com.alexsoft.entyties.categories.BaseEntity;
import com.alexsoft.entyties.categories.shops.BaseShop;
import com.alexsoft.enums.Shops;
import com.alexsoft.utils.ContainerNew;
import com.jayway.jsonpath.JsonPath;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
@Timed("OnlinerParser")
public class OnlinerParser extends BaseParser {

    private static final String PRODUCTS = "$.products[*]";

    //get short info
    @LogExecutionTime
    @Override
    @Timed("getShortInfo")
    public List<BaseShop> getShortInfo(BaseShop baseShop, String url, Integer pages) throws Exception {
        log.info("getShortInfo called");
        List<BaseShop> entities = new ArrayList<>(handleItems(baseShop, getContent(url)));
        int countPage = 2;
        for (int i = 1; i < pages; i++) {
            entities.addAll(handleItems(baseShop, getContent(url + "?page=" + countPage)));
            countPage++;
        }
        return entities;
    }

//    private List<BaseShop> handleItems(BaseShop baseShop, String content) throws Exception{
//        List<BaseShop> entities = new ArrayList<>();
//        List<String> models = JsonPath.read(content, PRODUCTS + ".full_name");
//        List<String> links = JsonPath.read(content, PRODUCTS + ".html_url");
//        List<String> productIDs = JsonPath.read(content, PRODUCTS + ".id");
//        List<String> prices = JsonPath.read(content, PRODUCTS + ".prices.price_min.amount");
//        List<String> status = JsonPath.read(content, PRODUCTS + ".status");
//        //лист с ценами всех мазгазинов одной модели
//        List<String> pricesDetail = JsonPath.read(content, PRODUCTS + ".prices.url");
//        for (int i = 0; i < models.size(); i++) {
//            if (!status.get(i).equals("old") && !prices.get(i).equals("null")){
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

    // пробую вернуть разные магазины вместо просто онлинера
    private List<BaseShop> handleItems(BaseShop baseShop, String content) throws Exception {
        long start = System.currentTimeMillis();
        List<BaseShop> entities = new ArrayList<>();
        List<String> models = JsonPath.read(content, PRODUCTS + ".full_name");
        List<String> detailLinks = JsonPath.read(content, PRODUCTS + ".html_url");
        List<String> productIDs = JsonPath.read(content, PRODUCTS + ".id");
//        List<String> prices = JsonPath.read(content, PRODUCTS + ".prices.price_min.amount");
        List<String> status = JsonPath.read(content, PRODUCTS + ".status");
        //лист с ценами всех мазгазинов одной модели
        List<String> pricesDetailUrl = JsonPath.read(content, PRODUCTS + ".prices.url");

        for (int j = 0; j < pricesDetailUrl.size(); j++) {
            String item = pricesDetailUrl.get(j);
            String model = models.get(j);
            String productID = String.valueOf(productIDs.get(j));
            String productIdSuffix = productID + "_onliner";
            String detailLink = detailLinks.get(j);
            log.info(model);
            setShops(baseShop, entities, model, productIdSuffix, item, detailLink);
        }
        System.out.println((System.currentTimeMillis() - start) / 1000);
        entities.sort(Comparator.comparing(BaseShop::getModel));
        return entities;
    }

    private void setShops(BaseShop baseShop, List<BaseShop> entities, String model, String productID,
                          String item, String detailLink) throws Exception {
        String fromPriceUrl = getContent(item);
        List<Integer> shops = JsonPath.read(fromPriceUrl, "$.positions.primary[*].shop_id");
        List<String> prices = JsonPath.read(fromPriceUrl, "$.positions.primary[*].position_price.amount");
        for (int i = 0; i < prices.size(); i++) {
            BaseShop newItem = ContainerNew.createInstanceBaseShop(baseShop.getClass());
            newItem.setModel(getModel(model));
            try {

                String url = Shops.getById(shops.get(i)).getUrl();
                if (!url.equals(" ")) {
                    String modelWithoutSpase = model.replace(" ", "%20");
                    newItem.setPriceLink(url + modelWithoutSpase);
                }
            } catch (Exception e) {
                log.error("Can't find SHOPS into enum Shops" + shops.get(i));
            }
            newItem.setProductId(String.valueOf(productID));
            newItem.setModelLink(detailLink);
            newItem.setPrice(Double.parseDouble(prices.get(i)));
            newItem.setUpdateDate(getDate());
            newItem.setSource(Shops.getNameOfValue(shops.get(i)));
            entities.add(newItem);
        }
    }

    private String getModel(String model) {
        String replaced1 = model.replace("(", "");
        String replaced2 = replaced1.replace(")", "");
        String replaced3 = replaced2.replace(" [", "");
        String replaced4 = replaced3.replace("год", "");
        String replaced5 = replaced4.replace("ГОД", "");
        return replaced5.replace("]", "");
    }

    // get detail info
    @Override
    public Map<String, String> getDetailInfo(Document document, BaseEntity baseEntity) {
        Map<String, String> details = new LinkedHashMap<>();
        Elements elementsByClass = document.getElementsByTag("tbody");
        for (Element item : elementsByClass) {
            Elements tr = item.getElementsByTag("tr");
            for (Element element : tr) {
                String key;
                String value;
                try {
                    key = element.getElementsByTag("td").get(0).html().split("\n")[0].trim();
                    value = element.getElementsByClass("value__text").get(0).text().trim();
                    details.put(key, value);
                } catch (Exception e) {
                    log.debug("" + e);
                }
            }
        }
        return details;
    }
}
