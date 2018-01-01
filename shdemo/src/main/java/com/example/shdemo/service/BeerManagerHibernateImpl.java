package com.example.shdemo.service;

import com.example.shdemo.domain.Beer;
import com.example.shdemo.domain.Client;
import com.example.shdemo.domain.Purchase;
import com.example.shdemo.domain.Type;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
@Transactional
public class BeerManagerHibernateImpl implements BeerManager{
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {return sessionFactory; }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addBeer(Beer beer) {
        beer.setId(null);

        if(beer.getType() != null)
        {
            beer.getType().getListOfBeers().add(beer);
        }

        sessionFactory.getCurrentSession().persist(beer);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Beer> getAllBeers() {
        return sessionFactory.getCurrentSession().getNamedQuery("beer.all")
                .list();
    }

    @Override
    public void deleteBeer(Beer beer) {
        beer = (Beer) sessionFactory.getCurrentSession().get(Beer.class,
                beer.getId());

        sessionFactory.getCurrentSession().delete(beer);
    }

    @Override
    public Beer findBeerByID(Long id) {
        return (Beer) sessionFactory.getCurrentSession().get(Beer.class, id);
    }

    @Override
    public Beer findBeerByName(String name) {
        return (Beer) sessionFactory.getCurrentSession().getNamedQuery("beer.byName")
                .setString("name", name).uniqueResult();
    }

    @Override
    public void updateBeerName(Long id, String name) {
        Beer beer = (Beer) sessionFactory.getCurrentSession().get(Beer.class, id);
        beer.setName(name);
    }

    @Override
    public void addType(Type type) {
        sessionFactory.getCurrentSession().persist(type);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Type> getAllTypes() {
        return sessionFactory.getCurrentSession().getNamedQuery("type.all").list();
    }

    @Override
    public void deleteType(Type type) {
        type = (Type) sessionFactory.getCurrentSession().get(Type.class, type.getId());
        sessionFactory.getCurrentSession().delete(type);
    }

    @Override
    public Type findTypeById(Long id) {
        return (Type) sessionFactory.getCurrentSession().get(Type.class, id);
    }

    @Override
    public Type findTypeByName(String name) {
        return (Type) sessionFactory.getCurrentSession().getNamedQuery("type.byName").setString("name", name).uniqueResult();
    }

    @Override
    public void updateTypeName(Long id, String name) {
        Type type = (Type) sessionFactory.getCurrentSession().get(Type.class, id);
        type.setName(name);
    }

    @Override
    public void addPurchase(Purchase purchase) {
        sessionFactory.getCurrentSession().persist(purchase);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Purchase> getAllPurchase() {
        return sessionFactory.getCurrentSession().getNamedQuery("purchase.all").list();
    }

    @Override
    public void deletePurchase(Purchase purchase) {
        purchase = (Purchase) sessionFactory.getCurrentSession().get(Purchase.class, purchase.getId());
        sessionFactory.getCurrentSession().delete(purchase);
    }

    @Override
    public Purchase findPurchaseById(Long id) {
        return (Purchase) sessionFactory.getCurrentSession().get(Purchase.class, id);
    }

    @Override
    public void updatePurchaseDate(Long id, Date purchaseDate) {
        Purchase purchase = (Purchase) sessionFactory.getCurrentSession().get(Purchase.class, id);
        purchase.setPurchaseDate(purchaseDate);
    }


    @Override
    public void addClient(Client client) {
        sessionFactory.getCurrentSession().persist(client);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Client> getAllClients() {
        return sessionFactory.getCurrentSession().getNamedQuery("client.all").list();
    }

    @Override
    public void deleteClient(Client client) {
        client = (Client) sessionFactory.getCurrentSession().get(Client.class, client.getId());
        sessionFactory.getCurrentSession().delete(client);
    }

    @Override
    public Client findClientById(Long id) {
        return (Client) sessionFactory.getCurrentSession().get(Client.class, id);
    }

    @Override
    public Client findClientBySecondName(String secondName) {
        return (Client) sessionFactory.getCurrentSession().getNamedQuery("client.bySecondName").setString("secondName", secondName).uniqueResult();
    }

    @Override
    public void updateClientSecondName(Long id, String secondName) {
        Client client = (Client) sessionFactory.getCurrentSession().get(Client.class, id);
        client.setSecondName(secondName);
    }

    /*
    @Override
    public void sellBeer(Long beerId, Long clientId) {
        Client client = (Client) sessionFactory.getCurrentSession().get(Client.class, clientId);
        Beer beer = (Beer) sessionFactory.getCurrentSession().get(Beer.class, beerId);
        client.getListOfBeers().add(beer);
    }

    @Override
    public List<Beer> getOwnedBeers(Client client) {
        client = (Client) sessionFactory.getCurrentSession().get(Client.class, client.getId());
        List<Beer> beers = new ArrayList<Beer>(client.getListOfBeers());
        return beers;

    }
    */

}
