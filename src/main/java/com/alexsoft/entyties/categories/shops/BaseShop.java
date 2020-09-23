package com.alexsoft.entyties.categories.shops;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class BaseShop {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "model_id")
    private Long modelId;
    @Column(name = "shop_id")
    private Long shopId;
    private Double price;
    @Transient
    private String modelLink;
    private String priceLink;
    private String productId;
    @Transient
    private String model;
    private Calendar updateDate;
    private String source;
    private String category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(insertable = false, updatable = false)
    private Shop shop;
}
