package com.example.jdbcdemo.service;

import com.example.jdbcdemo.domain.Beer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
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

	private final static String NAME_3 = "Zywiec Apa";
	private final static String TYPE_3 = "Apa";
	private final static double PERCENTOFALCOHOL_3 = 5;
	private final static double PRICE_3 = 4;

	private final static String NAME_4 = "Zywiec Sajson";
	private final static String TYPE_4 = "Sajson";
	private final static double PERCENTOFALCOHOL_4 = 5;
	private final static double PRICE_4 = 4;

	private final static String NAME_TO_UPDATE = "Zywiec Bock";
	private final static String TYPE_TO_UPDATE = "Bock";
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
		Beer beer2 = new Beer(NAME_TO_UPDATE, TYPE_TO_UPDATE, PERCENT_TO_UPDATE, PRICE_TO_UPDATE);

		beerManager.clearBeers();

		beerManager.addBeer(beer1);

		List<Beer> beers = beerManager.getAllBeers();
		Beer beerRetrieved = beers.get(0);


		assertEquals(1,beerManager.updateBeer(beer2,beerRetrieved.getId()));

		beers = beerManager.getAllBeers();
		beerRetrieved = beers.get(0);

		assertEquals(NAME_TO_UPDATE,beerRetrieved.getName());
		assertEquals(TYPE_TO_UPDATE,beerRetrieved.getType());
		assertEquals(PERCENT_TO_UPDATE,beerRetrieved.getPercentOfAlcohol(),5);
		assertEquals(PRICE_TO_UPDATE,beerRetrieved.getPercentOfAlcohol(),5);

	}

	@Test
	public void checkSearching(){
		Beer beer1 = new Beer(NAME_2, TYPE_2, PERCENTOFALCOHOL_2, PRICE_2);

		beerManager.clearBeers();

		beerManager.addBeer(beer1);

		List<Beer> beers = beerManager.searchBeer("Heineken");
		Beer beerRetrieved = beers.get(0);

		assertEquals(NAME_2,beerRetrieved.getName());
		assertEquals(TYPE_2,beerRetrieved.getType());
		assertEquals(PERCENTOFALCOHOL_2, beerRetrieved.getPercentOfAlcohol(),5);
		assertEquals(PRICE_2,beerRetrieved.getPrice(),5);

	}

	@Test
	public void checkDeleting(){
		Beer beer1 = new Beer(NAME_1, TYPE_1, PERCENTOFALCOHOL_1, PRICE_1);

		beerManager.clearBeers();
		beerManager.addBeer(beer1);

		List<Beer> beers = beerManager.searchBeer("Zywiec Marcowe");
		Beer beerRetrieved = beers.get(0);

		assertEquals(1,beerManager.deleteBeer(beerRetrieved.getName()));
	}

	@Test
	public void checkAddAllBeers(){
		Beer beer1 = new Beer(NAME_1, TYPE_2, PERCENTOFALCOHOL_1, PRICE_1);
		Beer beer2 = new Beer(NAME_2, TYPE_2, PERCENTOFALCOHOL_2, PRICE_2);
		Beer beer3 = new Beer(NAME_3, TYPE_3, PERCENTOFALCOHOL_3, PRICE_3);
		Beer beer4 = new Beer(NAME_4, TYPE_4, PERCENTOFALCOHOL_4, PRICE_4);

		beerManager.clearBeers();

		List<Beer> beers = new ArrayList<>();
		beers.add(beer1);
		beers.add(beer2);
		beers.add(beer3);
		beers.add(beer4);

		beerManager.addAllBeers(beers);

		int size = beerManager.getAllBeers().size();

		assertThat(size, either(is(4)).or(is(0)));

	}

	@Test
	public void checkDeleteAllBerrs(){
		Beer beer1 = new Beer(NAME_1, TYPE_2, PERCENTOFALCOHOL_1, PRICE_1);
		Beer beer2 = new Beer(NAME_2, TYPE_2, PERCENTOFALCOHOL_2, PRICE_2);
		Beer beer3 = new Beer(NAME_3, TYPE_3, PERCENTOFALCOHOL_3, PRICE_3);
		Beer beer4 = new Beer(NAME_4, TYPE_4, PERCENTOFALCOHOL_4, PRICE_4);

		beerManager.clearBeers();
		beerManager.addBeer(beer1);
		beerManager.addBeer(beer2);
		beerManager.addBeer(beer3);
		beerManager.addBeer(beer4);

		List<String> beersNames = new ArrayList<>();

		beersNames.add(beer1.getName());
		beersNames.add(beer2.getName());
		beersNames.add(beer3.getName());
		beersNames.add(beer4.getName());
		beersNames.add("Test");

		beerManager.deleteAllBeers(beersNames);
		int size = beerManager.getAllBeers().size();
		assertThat(size, either(is(4)).or(is(0)));
		System.out.println("Size: " + size);
	}
}
