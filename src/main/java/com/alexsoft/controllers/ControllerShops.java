package com.alexsoft.controllers;

import com.alexsoft.db.BaseShopRepository;
import com.alexsoft.entyties.categories.shops.BaseShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/shops")
public class ControllerShops {

    @Autowired
    BaseShopRepository baseShopRepository;

    @GetMapping("/")
    public List<BaseShop> findById(@RequestParam String category, @RequestParam Long modelId) {
        List<BaseShop> shops = baseShopRepository.findAllByCategoryAndModelId(category, modelId);
        return shops;
    }

    @GetMapping("/resp")
    public ShopResponse findByIdResp(@RequestParam String category, @RequestParam Long modelId) {
        List<BaseShop> shops = baseShopRepository.findAllByCategoryAndModelId(category, modelId);
        ShopResponse response = new ShopResponse();
        response.setItemsCount(shops.size());
        response.setShops(shops);
        return response;
    }
}
