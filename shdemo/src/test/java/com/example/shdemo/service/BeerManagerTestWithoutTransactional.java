package com.example.shdemo.service;


import com.example.shdemo.domain.Client;
import com.example.shdemo.domain.Purchase;
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
        beerManager.deleteAllClients();
        beerManager.deleteAllPurchase();

        Client client = new Client();
        client.setSecondName(CLIENT_SECONDNAME_1);
        beerManager.addClient(client);

        Purchase purchase = new Purchase();
        purchase.setClient(client);

        beerManager.addPurchase(purchase);

        Client retrievedClient = beerManager.findClientBySecondName(CLIENT_SECONDNAME_1);

        boolean pass = false;

        try{
            System.out.println(retrievedClient.getListOfPurchase().size());
        } catch (org.hibernate.LazyInitializationException e) {
            e.printStackTrace();
            pass = true;
        }

        if (!pass)
            org.junit.Assert.fail();

        beerManager.deleteAllClients();
        beerManager.deleteAllPurchase();
    }
}
