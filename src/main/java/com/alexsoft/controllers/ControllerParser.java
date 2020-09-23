package com.alexsoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexsoft.entyties.categories.BaseEntity;
import com.alexsoft.entyties.categories.shops.BaseShop;
import com.alexsoft.services.ServiceParser;

import java.util.ArrayList;
import java.util.List;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController()
@RequestMapping("/parser")
@PropertySource("classpath:application.properties")
@Timed("ControllerParser")
public class ControllerParser {

    private List<BaseShop> baseShops ;

    @Autowired
    private ServiceParser serviceParser;

    ControllerParser(MeterRegistry registry) {
        List<String> strings = new ArrayList<>();
        strings.add("aaa");
        registry.gauge("baseShop.size",strings.size());
    }

//
//    @GetMapping("/allFromSite")
//    public List<BaseShop> getAllFromSite(@RequestParam Integer pages, @RequestParam String category) throws IOException,
//            IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
//        log.info("called getAll");
//        return getBaseShops(pages, category);
//    }
//
//    @GetMapping("/allFromDB")
//    public List<BaseEntity> allFromDB(@RequestParam String category) {
//        log.info("called allFromDB");
//        return serviceOnliner.getAllBaseEntitiesFromDB(category);
//    }
//
//    @GetMapping("/id")
//    public BaseEntity findById(@RequestParam Long id, @RequestParam String category) {
//        log.info("called findById");
//        return serviceOnliner.getBaseEntityByIdFromDB(id, category);
//    }
//
    @PostMapping("/grabDetailDataForItem")
    public BaseEntity grabDetailDataForItem(@RequestBody ShopRequest request) throws Exception {
        log.info("called grabDetailDataForItem");
        return serviceParser.grubFullDataById(request);
    }

    @PostMapping("/grabDetailDataForAll")
    public List<BaseEntity> grabDetailDataForAll(@RequestBody ShopRequest request) throws Exception {
        log.info("called grabDetailDataForAll");
        return serviceParser.grabDetailDataForAll(request);
    }

    @PostMapping("/saveItemToDB")
    public List<BaseShop> saveItemsToDB(@RequestBody ShopRequest request) throws Exception {
        log.info("called saveItemToDB ");
        baseShops = serviceParser.saveItemsToDB(request);
        return baseShops;
    }
}


























