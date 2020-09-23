package com.alexsoft.entyties.categories;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({
    "hibernateLazyInitializer",
    "handler"
})
public class Monitor extends BaseEntity {
}
