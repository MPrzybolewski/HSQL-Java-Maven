package com.example.shdemo.service;


import com.example.shdemo.domain.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
public class BeerManagerTestWithoutTransactional {
    @Autowired
    BeerManager beerManager;

    private final String CLIENT_SECONDNAME_1 = "Kowalski";


    @Test
    public void lazyExceptionCheck()
    {
        Client client = new Client();
        client.setSecondName(CLIENT_SECONDNAME_1);
        beerManager.addClient(client);
        beerManager.getClientOwnedPurchase(client);
        System.out.println( client.getListOfPurchase().get(0).getPurchaseDate());

        beerManager.deleteClient(client);
    }
}
