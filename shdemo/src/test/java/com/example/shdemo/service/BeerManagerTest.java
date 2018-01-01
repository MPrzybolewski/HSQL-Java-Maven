package com.example.shdemo.service;

import com.example.shdemo.domain.Beer;
import com.example.shdemo.domain.Type;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

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
        beerManager.updateBeer(beer.getId());
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
        beerManager.updateType(type.getId());
        Type retrivedBeer = beerManager.findTypeByName(TYPE_UPDATENAME);
        assertEquals(TYPE_UPDATENAME, retrivedBeer.getName());
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

        System.out.println("test: " + type.getId());

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
}
