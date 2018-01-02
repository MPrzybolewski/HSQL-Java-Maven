package com.example.shdemo.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "type.all", query = "Select t from Type t"),
        @NamedQuery(name = "type.byName", query = "Select t from Type t Where t.name = :name"),
        @NamedQuery(name = "type.deleteAll", query = "Delete from Type")
})
public class Type {
    private long id;
    private String name;
    private List<Beer> listOfBeers = new ArrayList<Beer>();


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Beer> getListOfBeers() {
        return listOfBeers;
    }

    public void setListOfBeers(List<Beer> listOfBeers) {
        this.listOfBeers = listOfBeers;
    }
}
