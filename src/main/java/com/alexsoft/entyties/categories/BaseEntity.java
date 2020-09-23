package com.alexsoft.entyties.categories;


import com.alexsoft.entyties.Review;
import com.alexsoft.entyties.categories.shops.BaseShop;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.Data;

@Data
//@MappedSuperclass
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String producer;
    private String model;
    private String modelShort2;
    private String modelShort3;
    private String modelShort4;
    private String linkDetailInfo;
    private String imageLink;
    private String source;
    private Boolean isFillDetailData;
    private String category;
    @OneToMany(mappedBy = "modelId")
    private List<BaseShop> shops;


    @Transient
    private List<Review> reviewList;


}
