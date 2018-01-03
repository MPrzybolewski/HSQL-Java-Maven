package com.example.shdemo.service;


import com.example.shdemo.domain.Client;
import com.example.shdemo.domain.Purchase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
public class BeerManagerTestWithoutTransactional {
    @Autowired
    BeerManager beerManager;

    private final String CLIENT_SECONDNAME_1 = "Kowalski";


    @Test
    public void lazyExceptionCheck(){

        List<Client> retrievedClients = beerManager.getAllClients();
        for(Client client: retrievedClients){
            if(client.getSecondName().equals(CLIENT_SECONDNAME_1)){
                beerManager.deleteClient(client);
            }
        }

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
    }

    @Test
    public void eagerCheck(){
        List<Client> retrievedClients = beerManager.getAllClients();
        for(Client client: retrievedClients){
            if(client.getSecondName().equals(CLIENT_SECONDNAME_1)){
                beerManager.deleteClient(client);
            }
        }

        Client client = new Client();
        client.setSecondName(CLIENT_SECONDNAME_1);
        beerManager.addClient(client);

        Purchase purchase = new Purchase();
        purchase.setClient(client);

        beerManager.addPurchase(purchase);

        Purchase retrievedPurchase = beerManager.findPurchaseById(purchase.getId());

        assertNotNull(retrievedPurchase.getClient().getSecondName());
    }
}
