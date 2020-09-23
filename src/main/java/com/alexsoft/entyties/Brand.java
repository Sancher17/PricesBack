package com.alexsoft.entyties;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Brand {

    @Id
    private Long id;
    private String name;
    private String slug;
    private String image;
//    private String description;
}
