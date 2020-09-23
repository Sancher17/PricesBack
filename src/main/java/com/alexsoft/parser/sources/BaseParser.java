package com.alexsoft.parser.sources;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import com.alexsoft.audit.LogExecutionTime;
import com.alexsoft.entyties.categories.BaseEntity;
import com.alexsoft.entyties.categories.shops.BaseShop;
import com.alexsoft.enums.Shops;
import com.alexsoft.parser.Mapper;
import com.alexsoft.services.AbstrWebClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Component
public abstract class BaseParser extends AbstrWebClient {

    private static int i = 0;
    private List<String> fieldsEng = new ArrayList<>();
    private List<String> fieldsRus = new ArrayList<>();

    BaseParser(MeterRegistry registry) {
        List<String> strings = new ArrayList<>();
        strings.add("aaa");
        strings.add("111");
        registry.gauge("BaseParser", strings.size());
    }

    @Timed("getShortInfo")
    public abstract List<BaseShop> getShortInfo(BaseShop baseShop, String url, Integer pages) throws Exception;

    protected abstract Map<String, String> getDetailInfo(Document document, BaseEntity baseEntity) throws IOException;

    @LogExecutionTime
    public BaseEntity getBaseEntity(BaseEntity baseEntity, String mapping, Shops shops) throws Exception {
        Document document = Jsoup.parse(getContent(baseEntity.getLinkDetailInfo()));
        Map<String, String> details = getDetailInfo(document, baseEntity);
        writeToFile(baseEntity, mapping, details, shops.name());
        Map<String, String> mappingFromYamlFile = Mapper.getMapping(mapping + shops.name());
        Map<String, String> mappedEntity = getFinalMap(details, mappingFromYamlFile);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(mappedEntity, baseEntity.getClass());
    }


    public HtmlPage getHtmlPage(String url) throws IOException {
        return getWebClient().getPage(url);
    }

    public String removeFirstWord(String string){
        return  string.split(" ", 2)[1];
    }

    public String removeRussianWords(String string){
       return string.replaceAll("([а-яА-Я])", "").trim();
    }

    public String removeBrackets(String string){
        return  string
                .replace("(", "")
                .replace(")", "")
                .trim();
    }

    public void writeToFile(BaseEntity baseEntity, String mapping, Map<String, String> details, String shops) throws IOException {
        String fileName = mapping + shops + "_" + baseEntity.getId() + ".txt";
        String path = "src/main/recources/dataFromSite/";
        StringBuilder builderFull = new StringBuilder();
        StringBuilder builderLess = new StringBuilder();
        for (Map.Entry<String, String> item : details.entrySet()) {
            builderFull.append(item.getKey()).append(" - ").append(item.getValue()).append("\n");
            builderLess.append(item.getKey()).append("\n");
            fieldsRus.add(item.getKey());
        }

        try (FileWriter fw = new FileWriter(path + fileName)) {
            fw.write(builderFull.toString());
        }

        fileName = mapping + shops + "_less_" + baseEntity.getId() + ".txt";
        try (FileWriter fw = new FileWriter(path + fileName)) {
            fw.write(builderLess.toString());
        }

        for (String s : details.keySet()) {
            String translate = translate("ru-en", s);
            fieldsEng.add(translate);
        }
        List<String> engFields = convertFields(fieldsEng);
        StringBuilder builder = new StringBuilder();
        for (String engField : engFields) {
            builder.append(engField).append("\n");
        }

        fileName = mapping + shops + "_eng_" + baseEntity.getId() + ".txt";
        try (FileWriter fw = new FileWriter(path + fileName)) {
            fw.write(builder.toString());
        }
        createYamlMapping(fieldsRus, engFields, mapping, shops);
    }

    private static String translate(String lang, String input) throws IOException {
        String urlStr = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20200103T070645Z" +
                ".927ccbd23129fd89.3da2d8a1f29bb89a7537daa6b7b766d7c3ac45a2";
        URL urlObj = new URL(urlStr);
        HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.writeBytes("text=" + URLEncoder.encode(input, "UTF-8") + "&lang=" + lang);

        InputStream response = connection.getInputStream();
        String json = new java.util.Scanner(response).nextLine();
        int start = json.indexOf("[");
        int end = json.indexOf("]");
        String translated = json.substring(start + 2, end - 1);
        i++;
        if (translated.equals(input) && i < 2) {
            // if return equal of entered text - we need change direction of translation
            return translate("en", input);
        } else return translated;
    }

    private List<String> convertFields(List<String> fields) {

        List<String> withoutSimvols = new ArrayList<>();
        //remove the and The and other simvols
        for (String field : fields) {
            field = field.toLowerCase();
            String without = field.replace("the ", "");
            without = without.replace("The ", "");
            without = without.replace(".", "");
            without = without.replace(",", "");
            without = without.replace("(", "");
            without = without.replace(")", "");
            without = without.replace("/", "");
            without = without.replace("-", "");
            without = without.replace("\"", "");
            without = without.replace("Ghz", "");
            without = without.replace("Wh", "");
            without = without.replace("H", "");
            without = without.replace("Cm", "");
            withoutSimvols.add(without);
        }

        // все с заглавной
        List<String> newCollection = new ArrayList<>();
        for (String field : withoutSimvols) {
            String[] s = field.split(" ");
            StringBuilder builder = new StringBuilder();
            for (String s1 : s) {
                String upFirst = s1.substring(0, 1).toUpperCase() + s1.substring(1);
                builder.append(upFirst);
            }
            newCollection.add(builder.toString());
        }
        // первое слово не заглавное
        List<String> finalList = new ArrayList<>();
        for (String s : newCollection) {
            String lowFirst = s.substring(0, 1).toLowerCase() + s.substring(1);
            finalList.add(lowFirst);
        }
        return finalList;
    }

    private void createYamlMapping(List<String> rus, List<String> eng, String category, String shop) throws IOException {
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < rus.size(); j++) {
            builder.append(rus.get(j)).append(" : ").append(eng.get(j)).append("\n");
        }
        try (FileWriter fw = new FileWriter("C:\\Users\\user\\Documents\\proects\\prices\\src\\main\\recources" +
                "\\mapping\\" + category + shop + ".yml")) {
            fw.write(builder.toString());
        }
    }

    protected static Map<String, String> getFinalMap(Map<String, String> details, Map<String, String> mapFromFile) {
        //конвертировать в новую мапу типа dateComesToMarket -> 2017
        Map<String, String> mapNew = new LinkedHashMap<>();
        for (Map.Entry<String, String> stringStringEntry : mapFromFile.entrySet()) {
            String tempKey = stringStringEntry.getKey();
            String key = stringStringEntry.getValue();
            String value = details.get(tempKey);
            mapNew.put(key, value);
        }
        mapNew.remove(null);
        return mapNew;
    }
}
