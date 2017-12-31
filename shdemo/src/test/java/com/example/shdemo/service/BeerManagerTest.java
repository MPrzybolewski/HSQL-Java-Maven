package com.example.shdemo.service;

import com.example.shdemo.domain.Beer;
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

    private final String NAME_1 = "Bock";
    private final double PRICE_1 = 4;

    private final String NAME_2 = "Sajson";
    private final double PRICE_2 = 3;

    private final String UPDATENAME = "Marcowe";




    @Test
    public void addBeerCheck() {


        Beer beer = new Beer();
        beer.setName(NAME_1);
        beer.setPrice(PRICE_1);
        beerManager.addBeer(beer);

        Beer retrievedBeer = beerManager.findBeerByName(NAME_1);

        assertEquals(NAME_1, retrievedBeer.getName());
        assertEquals(PRICE_1, retrievedBeer.getPrice(),5);
        // ... check other properties here
    }

    @Test
    public void getAllBeersCheck()
    {
        Beer beer = new Beer();
        beer.setName(NAME_1);
        beer.setPrice(PRICE_1);
        beerManager.addBeer(beer);

        Beer beer2 = new Beer();
        beer.setName(NAME_2);
        beer.setPrice(PRICE_2);
        beerManager.addBeer(beer2);

        assertEquals(2,beerManager.getAllBeers().size());
    }

    @Test
    public void updateBeerCheck(){
        Beer beer = new Beer();
        beer.setName(NAME_2);
        beer.setPrice(PRICE_2);
        beerManager.addBeer(beer);
        System.out.println("PIWO: " + beer.getId());
        beerManager.updateBeer(beer.getId());
        Beer retrivedBeer = beerManager.findBeerByName(UPDATENAME);
        assertEquals(UPDATENAME, retrivedBeer.getName());
    }

    @Test
    public void deleteBeerCheck()
    {
        Beer beer = new Beer();
        beer.setName(NAME_2);
        beer.setPrice(PRICE_2);
        beerManager.addBeer(beer);

        beerManager.deleteBeer(beer);
        assertEquals(0,beerManager.getAllBeers().size());
    }



}
