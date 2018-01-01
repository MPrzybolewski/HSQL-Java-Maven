package com.example.shdemo.service;

import com.example.shdemo.domain.Beer;
import com.example.shdemo.domain.Client;
import com.example.shdemo.domain.Purchase;
import com.example.shdemo.domain.Type;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class BeerManagerTest {

    @Autowired
    BeerManager beerManager;

    private final String BEER_NAME_1 = "Żywiec Bock";
    private final double BEER_PRICE_1 = 4;

    private final String BEER_NAME_2 = "Żywiec Sajson";
    private final double BEER_PRICE_2 = 3;

    private final String BEER_UPDATENAME = "Żywiec Marcowe";

    private final String TYPE_NAME_1 = "Marcowe";
    private final String TYPE_NAME_2 = "Bock";
    private final String TYPE_UPDATENAME = "Porter";

    private final String CLIENT_FIRSTNAME_1 = "Jan";
    private final String CLIENT_SECONDNAME_1 = "Kowalski";

    private final String CLIENT_FIRSTNAME_2 = "Zenon";
    private final String CLIENT_SECONDNAME_2 = "Martyniuk";

    private final String CLIENT_UPDATESECONDNAME = "Król";


    private final Date PURCHASE_DATE_1 = new Date(2010,10,10);
    private final Date PURCHASE_DATE_2 = new Date(2011,11,11);
    private final Date PURCHASE_UPDATEDATE = new Date(2012, 12, 12);

    @Test
    public void addBeerCheck() {
        Beer beer = new Beer();
        beer.setName(BEER_NAME_1);
        beer.setPrice(BEER_PRICE_1);

        beerManager.addBeer(beer);

        Beer retrievedBeer = beerManager.findBeerByName(BEER_NAME_1);

        assertEquals(BEER_NAME_1, retrievedBeer.getName());
        assertEquals(BEER_PRICE_1, retrievedBeer.getPrice(),5);
    }

    @Test
    public void getAllBeersCheck()
    {
        Beer beer = new Beer();
        beer.setName(BEER_NAME_1);
        beer.setPrice(BEER_PRICE_1);

        beerManager.addBeer(beer);

        Beer beer2 = new Beer();
        beer.setName(BEER_NAME_2);
        beer.setPrice(BEER_PRICE_2);
        beerManager.addBeer(beer2);

        assertEquals(2,beerManager.getAllBeers().size());
    }
    @Test
    public void updateBeerCheck(){
        Beer beer = new Beer();
        beer.setName(BEER_NAME_2);
        beer.setPrice(BEER_PRICE_2);
        beerManager.addBeer(beer);
        beerManager.updateBeerName(beer.getId(), BEER_UPDATENAME);
        Beer retrivedBeer = beerManager.findBeerByName(BEER_UPDATENAME);
        assertEquals(BEER_UPDATENAME, retrivedBeer.getName());
    }

    @Test
    public void deleteBeerCheck()
    {
        Beer beer = new Beer();
        beer.setName(BEER_NAME_2);
        beer.setPrice(BEER_PRICE_2);
        beerManager.addBeer(beer);

        beerManager.deleteBeer(beer);
        assertEquals(0,beerManager.getAllBeers().size());
    }

    @Test
    public void addTypeCheck()
    {
        Type type = new Type();
        type.setName(TYPE_NAME_1);
        beerManager.addType(type);

        Type retrievedType = beerManager.findTypeByName(TYPE_NAME_1);

        assertEquals(TYPE_NAME_1, retrievedType.getName());
    }

    @Test
    public void getAllTypesCheck()
    {
        Type type = new Type();
        type.setName(TYPE_NAME_1);
        beerManager.addType(type);

        Type type2 = new Type();
        type.setName(TYPE_NAME_2);
        beerManager.addType(type2);

        assertEquals(2,beerManager.getAllTypes().size());
    }

    @Test
    public void updateTypeCheck()
    {
        Type type = new Type();
        type.setName(TYPE_NAME_2);
        beerManager.addType(type);
        beerManager.updateTypeName(type.getId(), TYPE_UPDATENAME);
        Type retrivedType = beerManager.findTypeByName(TYPE_UPDATENAME);
        assertEquals(TYPE_UPDATENAME, retrivedType.getName());
    }

    @Test
    public void deleteTypeCheck()
    {
        Type type = new Type();
        type.setName(TYPE_NAME_2);
        beerManager.addType(type);

        beerManager.deleteType(type);
        assertEquals(0,beerManager.getAllTypes().size());
    }

    @Test
    public void listOfBeerInTypeCheck()
    {
        Type type = new Type();
        type.setName(TYPE_NAME_1);
        beerManager.addType(type);

        Beer beer = new Beer();
        beer.setName(BEER_NAME_1);
        beer.setPrice(BEER_PRICE_1);
        beer.setType(type);

        beerManager.addBeer(beer);

        Type retrievedType = beerManager.findTypeByName(TYPE_NAME_1);
        assertEquals(1, retrievedType.getListOfBeers().size());
        assertEquals(1, type.getListOfBeers().size());

        beerManager.deleteType(type);

        assertEquals(0, beerManager.getAllBeers().size());
    }


    @Test
    public void addClientCheck()
    {
        Client client = new Client();
        client.setFirstName(CLIENT_FIRSTNAME_1);
        client.setSecondName(CLIENT_SECONDNAME_1);

        beerManager.addClient(client);

        Client retrievedClient = beerManager.findClientBySecondName(CLIENT_SECONDNAME_1);

        assertEquals(CLIENT_SECONDNAME_1, retrievedClient.getSecondName());
    }

    @Test
    public void getAllClientsCheck()
    {
        Client client = new Client();
        client.setFirstName(CLIENT_FIRSTNAME_1);
        client.setSecondName(CLIENT_SECONDNAME_1);
        beerManager.addClient(client);

        Client client2 = new Client();
        client.setFirstName(CLIENT_FIRSTNAME_2);
        client.setSecondName(CLIENT_SECONDNAME_2);
        beerManager.addClient(client2);

        assertEquals(2,beerManager.getAllClients().size());
    }

    @Test
    public void updateClientCheck()
    {
        Client client = new Client();
        client.setFirstName(CLIENT_FIRSTNAME_1);
        client.setSecondName(CLIENT_SECONDNAME_1);

        beerManager.addClient(client);

        beerManager.updateClientSecondName(client.getId(), CLIENT_UPDATESECONDNAME);
        Client retrivedClient = beerManager.findClientBySecondName(CLIENT_UPDATESECONDNAME);
        assertEquals(CLIENT_UPDATESECONDNAME, retrivedClient.getSecondName());
    }

    @Test
    public void deleteClientCheck()
    {
        Client client = new Client();
        client.setFirstName(CLIENT_FIRSTNAME_1);
        client.setSecondName(CLIENT_SECONDNAME_1);

        beerManager.addClient(client);

        beerManager.deleteClient(client);
        assertEquals(0,beerManager.getAllClients().size());
    }

    @Test
    public void addPurchaseCheck()
    {
        Purchase purchase = new Purchase();
        purchase.setPurchaseDate(PURCHASE_DATE_1);
        beerManager.addPurchase(purchase);


        Purchase retrievedPurchase = beerManager.findPurchaseById(purchase.getId());

        assertEquals(retrievedPurchase.getPurchaseDate(), retrievedPurchase.getPurchaseDate());
    }

    @Test
    public void getAllPurchaseCheck()
    {
        Purchase purchase = new Purchase();
        purchase.setPurchaseDate(PURCHASE_DATE_1);
        beerManager.addPurchase(purchase);

        Purchase purchase2 = new Purchase();
        purchase.setPurchaseDate(PURCHASE_DATE_2);
        beerManager.addPurchase(purchase2);

        assertEquals(2,beerManager.getAllPurchase().size());
    }

    @Test
    public void updatePurchaseCheck()
    {
        Purchase purchase = new Purchase();
        purchase.setPurchaseDate(PURCHASE_DATE_1);
        beerManager.addPurchase(purchase);
        beerManager.updatePurchaseDate(purchase.getId(), PURCHASE_UPDATEDATE);
        Purchase retrivedPurchase = beerManager.findPurchaseById(purchase.getId());
        assertEquals(PURCHASE_UPDATEDATE, retrivedPurchase.getPurchaseDate());
    }

    @Test
    public void deletePurchaseCheck()
    {
        Purchase purchase = new Purchase();
        purchase.setPurchaseDate(PURCHASE_DATE_1);
        beerManager.addPurchase(purchase);

        beerManager.deletePurchase(purchase);
        assertEquals(0,beerManager.getAllPurchase().size());
    }
}
