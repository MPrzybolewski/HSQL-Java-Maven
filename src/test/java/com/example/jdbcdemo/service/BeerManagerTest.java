package com.example.jdbcdemo.service;

import com.example.jdbcdemo.domain.Beer;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BeerManagerTest {
	
	
	BeerManager beerManager = new BeerManager();
	
	private final static String NAME_1 = "Zywiec Marcowe";
	private final static String TYPE_1 = "Marcowe";
	private final static double PERCENTOFALCOHOL_1 = 4.55;
	private final static double PRICE_1 = 4.55;

	private final static String NAME_2 = "Heineken";
	private final static String TYPE_2 = "Lager";
	private final static double PERCENTOFALCOHOL_2 = 5;
	private final static double PRICE_2 = 4;

	private final static String NAME_TO_UPDATE = "Test";
	private final static String TYPE_TO_UPDATE = "Test";
	private final static double PERCENT_TO_UPDATE = 1;
	private final static double PRICE_TO_UPDATE = 1;
	
	@Test
	public void checkConnection(){
		assertNotNull(beerManager.getConnection());
	}
	
	@Test
	public void checkAdding(){
		
		Beer beer = new Beer(NAME_1, TYPE_1, PERCENTOFALCOHOL_1,PRICE_1);
		
		beerManager.clearBeers();
		assertEquals(1,beerManager.addBeer(beer));
		
		List<Beer> beers = beerManager.getAllBeers();
		Beer beerRetrieved = beers.get(0);
		
		assertEquals(NAME_1, beerRetrieved.getName());
		assertEquals(TYPE_1, beerRetrieved.getType());
		assertEquals(PERCENTOFALCOHOL_1, beerRetrieved.getPercentOfAlcohol(), 5);
		assertEquals(PRICE_1, beerRetrieved.getPrice(), 5);
		
	}

	@Test
	public void checkUpdating(){
		Beer beer1 = new Beer(NAME_1, TYPE_1, PERCENTOFALCOHOL_1, PRICE_1);
		Beer beer2 = new Beer(NAME_2, TYPE_2, PERCENTOFALCOHOL_2, PRICE_2);

		beerManager.clearBeers();

		beerManager.addBeer(beer1);
		beerManager.addBeer(beer2);

		List<Beer> beers = beerManager.getAllBeers();
		Beer beerRetrieved = beers.get(0);

		assertEquals(1,beerManager.updateBeerName(NAME_TO_UPDATE,beerRetrieved.getId()));
		assertEquals(1,beerManager.updateBeerType(TYPE_TO_UPDATE,beerRetrieved.getId()));
		assertEquals(1,beerManager.updateBeerPercentOfAlcohol(PERCENT_TO_UPDATE,beerRetrieved.getId()));
		assertEquals(1,beerManager.updateBeerPrice(PRICE_TO_UPDATE,beerRetrieved.getId()));

		beers = beerManager.getAllBeers();
		beerRetrieved = beers.get(1);

		assertEquals(NAME_TO_UPDATE,beerRetrieved.getName());
		assertEquals(TYPE_TO_UPDATE,beerRetrieved.getType());
		assertEquals(PERCENT_TO_UPDATE,beerRetrieved.getPercentOfAlcohol(),5);
		assertEquals(PRICE_TO_UPDATE,beerRetrieved.getPercentOfAlcohol(),5);

	}
}
