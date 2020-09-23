package com.alexsoft.entyties.categories.test;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Address extends BaseEntityTest {

    private String street;

//    @OneToOne(mappedBy = "address")
//    private User user;
}
