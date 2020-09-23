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
public class NotebookShop extends BaseShop {

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "model_id", referencedColumnName = "id",insertable = false, updatable = false)
//    private Notebook notebook;

//    @ManyToOne
//    @JoinColumn(name = "model_id", referencedColumnName = "id",insertable = false, updatable = false)
//    private Notebook notebook;
}
