package com.alexsoft.entyties;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Id;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Review {

    @Id
    private String id;
    private String title;
    private String date;
    private String link;
    private String modelName;
    private String source;
    private String category;
    private List<Review> reviews;
}
