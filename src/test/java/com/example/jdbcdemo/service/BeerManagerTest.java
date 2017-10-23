package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.Beer;

public class BeerManagerTest {
	
	
	BeerManager beerManager = new BeerManager();
	
	private final static String NAME_1 = "Zenek";
	private final static String TYPE_1 = "Marcowe";
	private final static double PERCENTOFALCOHOL_1 = 4.5;
	private final static double PRICE_1 = 4.5;
	
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
		
	}

}
