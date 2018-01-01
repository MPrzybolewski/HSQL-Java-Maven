package com.example.shdemo.service;

import com.example.shdemo.domain.Beer;
import com.example.shdemo.domain.Type;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    public void updateBeer(Long id) {
        Beer beer = (Beer) sessionFactory.getCurrentSession().get(Beer.class, id);
        beer.setName("Żywiec Marcowe");
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
    public void updateType(Long id) {
        Type type = (Type) sessionFactory.getCurrentSession().get(Type.class, id);
        type.setName("Porter");
    }

}
