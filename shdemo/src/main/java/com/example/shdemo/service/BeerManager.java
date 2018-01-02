package com.example.shdemo.service;

import com.example.shdemo.domain.Beer;
import com.example.shdemo.domain.Client;
import com.example.shdemo.domain.Purchase;
import com.example.shdemo.domain.Type;

import java.util.Date;
import java.util.List;

public interface BeerManager {

    void addBeer(Beer beer);
    List<Beer> getAllBeers();
    void deleteBeer(Beer beer);
    Beer findBeerByID(Long id);
    Beer findBeerByName(String name);
    void updateBeerName(Long id, String name);
    void deleteAllBeers();


    void addType(Type type);
    List<Type> getAllTypes();
    void deleteType(Type type);
    Type findTypeById(Long id);
    Type findTypeByName(String name);
    void updateTypeName(Long id, String name);
    void deleteAllTypes();

    void addPurchase(Purchase purchase);
    List<Purchase> getAllPurchase();
    void deletePurchase(Purchase purchase);
    Purchase findPurchaseById(Long id);
    void updatePurchaseDate(Long id, Date purchaseDate);
    void deleteAllPurchase();

    void addClient(Client client);
    List<Client> getAllClients();
    void deleteClient(Client client);
    Client findClientById(Long id);
    Client findClientBySecondName(String secondName);
    void updateClientSecondName(Long id, String secondName);
    void deleteAllClients();
/*
    void sellBeer(Long beerId, Long clientId);
    List<Beer> getOwnedBeers(Client client);
*/

    List<Purchase> getClientOwnedPurchase(Client client);

}
