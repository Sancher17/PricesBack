package com.alexsoft.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShopRequest {

    private Long id;
    private String category;
    private String shopSource;
    private Integer pages;
}
