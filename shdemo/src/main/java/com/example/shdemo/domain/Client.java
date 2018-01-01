package com.example.shdemo.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "client.all", query = "Select c from Client c"),
        @NamedQuery(name = "client.bySecondName", query = "Select c from Client c Where c.secondName = :secondName")
})

public class Client {
   private Long id;
   private String firstName;
   private String secondName;
   private Date registrationDate = new Date();
   private List<Purchase> ListOfPurchase = new ArrayList<Purchase>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(unique = true)
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Temporal(TemporalType.DATE)
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Purchase> getListOfPurchase() {
        return ListOfPurchase;
    }

    public void setListOfPurchase(List<Purchase> listOfPurchase) {
        ListOfPurchase = listOfPurchase;
    }
}
