package com.example.shdemo.service;

import com.example.shdemo.domain.Beer;
import com.example.shdemo.domain.Type;

import java.util.List;

public interface BeerManager {

    void addBeer(Beer beer);
    List<Beer> getAllBeers();
    void deleteBeer(Beer beer);
    Beer findBeerByID(Long id);
    Beer findBeerByName(String name);
    void updateBeer(Long id);


    void addType(Type type);
    List<Type> getAllTypes();
    void deleteType(Type type);
    Type findTypeById(Long id);
    Type findTypeByName(String name);
    void updateType(Long id);



}
