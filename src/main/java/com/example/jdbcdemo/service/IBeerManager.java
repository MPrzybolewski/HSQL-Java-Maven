package com.example.jdbcdemo.service;

import com.example.jdbcdemo.domain.Beer;
import java.util.List;

public interface IBeerManager {

    int addBeer(Beer beer);
    int deleteBeer(String name);
    int updateBeer(Beer beer, int id);
    void clearBeers();
    List<Beer> getAllBeers();
    List<Beer> searchBeer(String name);
    void addAllBeers(List<Beer> beers);
    void deleteAllBeers(List<String> beersNames);


}
