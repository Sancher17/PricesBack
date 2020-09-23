package com.alexsoft.controllers.dto;

import com.alexsoft.entyties.categories.BaseEntity;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseEntityDto {

    private Long id;
    private String model;
    private String linkDetailInfo;
    private String source;
    private String category;
    private Double price;


    public List<BaseEntityDto> getBaseEntityDto(List<BaseEntity> notebooks) {
        List<BaseEntityDto> baseEntityDtos = new ArrayList<>();
        notebooks.forEach(item -> {
            BaseEntityDto baseEntityDto = new BaseEntityDto();
            baseEntityDto.setId(item.getId());
            baseEntityDto.setModel(item.getModel());
            baseEntityDto.setLinkDetailInfo(item.getLinkDetailInfo());
            baseEntityDto.setSource(item.getSource());
            baseEntityDto.setCategory(item.getCategory());
//            baseEntityDto.setPrice(item.getShops().get(0).getPrice());
            baseEntityDtos.add(baseEntityDto);
        });
        return baseEntityDtos;
    }
}
