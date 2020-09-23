package com.alexsoft.entyties.categories.shops;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
public class SmartphoneShop extends BaseShop {

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "model_id", referencedColumnName = "id",insertable = false, updatable = false)
//    private Smartphone smartphone;

}
