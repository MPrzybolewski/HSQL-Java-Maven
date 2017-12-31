package com.example.shdemo.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "beer.all", query = "Select b from Beer b"),
        @NamedQuery(name = "beer.byName", query = "Select b from Beer b Where b.name = :name")
})


public class Beer {

    private Long id;
    private String name;
    private String type;
    private double percentOfAlcohol;
    private double price;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public String getType() {
        return type;
    }

    public void setType(String type) {
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
}
