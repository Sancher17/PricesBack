package com.alexsoft.parser.sources;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.alexsoft.audit.LogExecutionTime;
import com.alexsoft.entyties.categories.BaseEntity;
import com.alexsoft.entyties.categories.shops.BaseShop;
import com.alexsoft.enums.Shops;
import com.alexsoft.utils.ContainerNew;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShopbyParser extends BaseParser {

    private String itemsList = "//div[@class='ModelList__InfoModelBlock ModelList__DescModelBlock']";

    @Override
    public List<BaseShop> getShortInfo(BaseShop baseShop, String url, Integer pages) throws Exception {
        // next page: &page_id=2
        HtmlPage page = getHtmlPage(url);
        List<BaseShop> baseShopLinks = new ArrayList<>();
        List<HtmlDivision> item = page.getByXPath(itemsList);
        for (int i = 0; i < item.size(); i++) {
            //get
            String model = item.get(i).getElementsByTagName("a").get(0).asText();
            String price = item.get(i).getElementsByTagName("span").get(4).asText();
            String link = item.get(i).getElementsByTagName("a").get(0).getAttribute("href");
            String productId = item.get(i).getElementsByTagName("a").get(0).getAttribute("data-model");
            //creat
            BaseShop newItem = ContainerNew.createInstanceBaseShop(baseShop.getClass());
            newItem.setModel(getModel(model));
            newItem.setModelLink(getLink(link));
            newItem.setProductId(productId);
            newItem.setPrice(Double.parseDouble(getPrice(price)));
            newItem.setUpdateDate(getDate());
            newItem.setSource(Shops.SHOP_BY.name());
            baseShopLinks.add(newItem);
        }
        return baseShopLinks;
    }

    @Override
    @LogExecutionTime
    public  Map<String, String> getDetailInfo(Document document, BaseEntity baseEntity) {
        Map<String, String> details = new LinkedHashMap<>();
        getPhotos(document, baseEntity);
        Elements elementsByClass = document.getElementsByClass("PageForTable__GroupBlock");
        for (Element item : elementsByClass) {
            Elements keys = item.getElementsByClass("PageForTable__InfoBlockTitle");
            Elements values = item.getElementsByClass("PageForTable__ValueCharacter " +
                    "PageForTable__InfoCharacter col-xs-12");
            for (int i = 0; i < keys.size(); i++) {
                String key = keys.get(i).text();
                String value = values.get(i).text();
                details.put(key, value);
            }
        }
        return details;
    }

    private String getModel(String model) {
        model = removeRussianWords(model);
        model = removeBrackets(model);
        return model.trim();
    }

    private String getPrice(String price) {
        return price.replace(",", ".")
                .replace(" ", "");
    }

    private String getLink(String link) {
        return "https://shop.by" + link;
    }

    private void getPhotos(Document document, BaseEntity baseEntity)  {
        int countBig = 1;
        String folderModel = baseEntity.getModel();
        Element links = document.getElementsByClass("ModelInfo__BlockIcon col-lg-12 col-md-10 col-xs-24").get(0);
        Elements bigImages = links.getElementsByClass("ModelGallery__BigImage");
        for (Element element : bigImages) {
            String bigImage = "https://shop.by/" + element.attributes().asList().get(2).getValue();
            try(InputStream in = new URL(bigImage).openStream()){
                String file = countBig +".jpg";
                String os = System.getProperty("os.name");
                if(os.equals("Linux")){
                    String baseDir = "/home/alex/Downloads/1/";
                    String fileFull = baseDir + folderModel;
                    File directory = new File(fileFull);
                    if (! directory.exists()){
                        directory.mkdir();
                    }
                    Files.copy(in, Paths.get( fileFull + "/big" + file));
                }else{
                    Files.copy(in, Paths.get("C:/Users/user/Downloads/1/" + folderModel + "/big" + file));
                }
                countBig++;
            }catch (Exception e){
                log.info("" + e);
            }
        }
        Elements smallImages = links.getElementsByClass("Page__ImageLazyLoad");
        int countSmall = 1;
        for (Element element : smallImages) {
            String img1 = "https://shop.by/" + element.attributes().asList().get(1).getValue();
            try(InputStream in = new URL(img1).openStream()){
                String file = countSmall +".jpg";
                String os = System.getProperty("os.name");
                if(os.equals("Linux")){
                    String baseDir = "/home/alex/Downloads/1/";
                    String fileFull = baseDir + folderModel;
                    Files.copy(in, Paths.get( fileFull + "/small" + file));
                }else{
//                    Files.copy(in, Paths.get("C:/Users/user/Downloads/1/small" + file));
                    Files.copy(in, Paths.get("C:/Users/user/Downloads/1/" + folderModel + "/small" + file));
                }
                countSmall++;
            }catch (Exception e){
                log.info(" " +e);
            }
        }
    }
}










