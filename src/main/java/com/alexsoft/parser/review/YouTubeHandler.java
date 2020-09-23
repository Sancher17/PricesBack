package com.alexsoft.parser.review;

import com.alexsoft.services.AbstrWebClient;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class YouTubeHandler extends AbstrWebClient {

    public static void main(String[] args) throws IOException {
        WebClient webClient = getWebClient();
        String sourceToSearch = "FX505DY";
        String url = "https://www.youtube.com/results?search_query=";
        HtmlPage page = webClient.getPage("https://www.youtube.com/results?search_query=FX505DY");

        List<DomElement> dismissable = page.getElementsById("dismissable");
        for (DomElement domElement : dismissable) {
            List<HtmlAnchor> byXPath = domElement.getByXPath("//*[@id='video-title']");
        }

        log.info(page.getTitleText());
        List<HtmlAnchor> archors = page.getByXPath("//*[@id='video-title']");
        List<String> links = new ArrayList<>();

        for (HtmlAnchor htmlAnchor : archors) {
            String linkDetails = htmlAnchor.getHrefAttribute();
            links.add(linkDetails);
        }
        log.info(page.getTitleText());
    }
}



//    HtmlForm searchForm = (HtmlForm) page.getElementById("search-form");
//    //        HtmlSubmitInput button = searchForm.getEle("search_query");
//    HtmlTextInput textField = (HtmlTextInput) page.getByXPath("//*[@id='search']").get(1);
//        textField.setText("FX505DY");
//                DomElement button = page.getElementById("search-icon-legacy");
//                HtmlPage FX505DY = button.click();
//                Thread.sleep(5000);
//                log.info(FX505DY.getTitleText());
//                System.out.println();
