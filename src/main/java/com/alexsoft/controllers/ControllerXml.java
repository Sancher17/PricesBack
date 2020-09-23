package com.alexsoft.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alexsoft.services.AbstrWebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HTMLParser;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.xml.XmlPage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController()
@RequestMapping("/xml")
public class ControllerXml extends AbstrWebClient {

    @GetMapping("/getXml")
    public Integer getXml() throws IOException {
        log.info("called getXml");
        XmlPage page = getWebClient().getPage("https://24shop.by/sitemap.xml");;
        WebResponse webResponse = page.getWebResponse();
        HtmlPage htmlPage = HTMLParser.parseXHtml(webResponse, page.getEnclosingWindow());
        DomNodeList<DomElement> loc = htmlPage.getElementsByTagName("loc");
        List<String> links = new ArrayList<>();
        List<String> linksNotebook = new ArrayList<>();

        for (DomElement domElement : loc) {
            String textContent = domElement.getTextContent();
            links.add(textContent);
        }
        int count= 0;
        for (String link : links) {
            XmlPage page1 = getWebClient().getPage(link);;
            WebResponse webResponse1 = page1.getWebResponse();
            HtmlPage htmlPage1 = HTMLParser.parseXHtml(webResponse1, page1.getEnclosingWindow());
            DomNodeList<DomElement> loc1 = htmlPage1.getElementsByTagName("loc");

            for (DomElement domElement : loc1) {
                String textContent = domElement.getTextContent();
                if (textContent.contains("/noutbuki/noutbuk")){
                    linksNotebook.add(textContent);
                }
            }
            for (String s : linksNotebook) {
                System.out.println(count + " - " +s);
                count++;
            }
        }
        String path = "src/main/recources/sitemap/";
        String fileName = "24shop.txt";

        StringBuilder builder = new StringBuilder();
        for (String s : linksNotebook) {
            builder.append(s).append("\n");
        }

        try (FileWriter fw = new FileWriter(path + fileName)) {
            fw.write(builder.toString());
        }

        System.out.println("END");
        return count;
    }
}
