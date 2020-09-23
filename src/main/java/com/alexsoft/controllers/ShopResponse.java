package com.alexsoft.controllers;

import com.alexsoft.entyties.categories.shops.BaseShop;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShopResponse {

    private Integer itemsCount;
    private List<BaseShop> shops;
}
