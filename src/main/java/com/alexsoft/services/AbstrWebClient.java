package com.alexsoft.services;

import com.alexsoft.controllers.ShopRequest;
import com.alexsoft.db.BaseEntityRepository;
import com.alexsoft.entyties.categories.BaseEntity;
import com.gargoylesoftware.htmlunit.AjaxController;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.chrome.ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY;


public abstract class AbstrWebClient {

    @Autowired
    private BaseEntityRepository baseEntityRepository;

    protected static WebDriver getWebDriver(){
        String path = "/home/alex/IdeaProjects/prices/chromedriver/chromedriver";
        File file;
        String os = System.getProperty("os.name");
        if(!os.equals("Linux")){
            path = "C:\\chrome\\chromedriver.exe";
        }
        file = new File(path);
        System.setProperty(CHROME_DRIVER_EXE_PROPERTY, file.getAbsolutePath());
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        return driver;
    }

    protected static WebClient getWebClient() {

        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_52);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.setAjaxController(new AjaxController(){
            @Override
            public boolean processSynchron(HtmlPage page, WebRequest request, boolean async) {
                return true;
            }
        });
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getCookieManager().setCookiesEnabled(true);
        return webClient;
    }

     static void getCurrentPageTitle(WebClient webClient) {
        HtmlPage currentPage = (HtmlPage) webClient.getCurrentWindow().getEnclosedPage();
        System.out.println(currentPage.getTitleText());
    }

    public String getContent(String url) throws IOException {
        WebRequest request =  new WebRequest(new URL(url), HttpMethod.GET);
        request.setAdditionalHeader("Accept", "application/json; utf-8");
        return getWebClient().getPage(request).getWebResponse().getContentAsString();
    }

    public Calendar getDate(){
        Calendar currentDate = Calendar.getInstance(); // creates calendar
        currentDate.setTime(new Date()); // sets calendar time/date
        currentDate.add(Calendar.HOUR_OF_DAY, 0); // adds one hour
        currentDate.getTime();
        return currentDate;
    }

    // DB
    public BaseEntity getBaseEntityByIdFromDB(ShopRequest shopRequest ) {
        return baseEntityRepository.findByCategoryAndId(shopRequest.getCategory(), shopRequest.getId());
    }
}
