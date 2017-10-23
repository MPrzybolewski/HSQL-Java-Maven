package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.Beer;

public class BeerManagerTest {
	
	
	BeerManager beerManager = new BeerManager();
	
	private final static String NAME_1 = "Zenek";
	private final static int YOB_1 = 1945;
	
	@Test
	public void checkConnection(){
		assertNotNull(beerManager.getConnection());
	}
	
	@Test
	public void checkAdding(){
		
		Beer beer = new Beer(NAME_1, YOB_1);
		
		beerManager.clearPersons();
		assertEquals(1,beerManager.addBeer(beer));
		
		List<Beer> persons = beerManager.getAllBeers();
		Beer personRetrieved = persons.get(0);
		
		assertEquals(NAME_1, personRetrieved.getName());
		
	}

}
