package com.example.jdbcdemo.service;

import com.example.jdbcdemo.domain.Beer;
import java.util.List;

public interface IBeerManager {

    int addBeer(Beer beer);
    int deleteBeer(int id);
    int updateBeer(Beer beer, int id);
    void clearBeers();
    List<Beer> getAllBeers();
    List<Beer> searchBeer(String name);

}
