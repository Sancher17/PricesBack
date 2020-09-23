package com.alexsoft.entyties.categories.shops;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Data
public class Shop {

    @Id
    private Long id;
    private String name;
}
