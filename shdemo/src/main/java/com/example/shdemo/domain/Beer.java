package com.example.shdemo.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "beer.all", query = "Select b from Beer b"),
        @NamedQuery(name = "beer.byName", query = "Select b from Beer b Where b.name = :name")
})


public class Beer {

    private Long id;
    private String name;
    private Type type;
    private double percentOfAlcohol;
    private double price;
    private List<Purchase> ListOfPurchase = new ArrayList<Purchase>();


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getPercentOfAlcohol() {
        return percentOfAlcohol;
    }

    public void setPercentOfAlcohol(double percentOfAlcohol) {
        this.percentOfAlcohol = percentOfAlcohol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Purchase> getListOfPurchase() {
        return ListOfPurchase;
    }

    public void setListOfPurchase(List<Purchase> listOfPurchase) {
        ListOfPurchase = listOfPurchase;
    }
}
