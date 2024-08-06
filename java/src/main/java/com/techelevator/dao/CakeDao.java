package com.techelevator.dao;

import com.techelevator.model.Cake;

import java.util.List;

public interface CakeDao {

    List<Cake> getCakes();

    Cake getCakeById(int cakeId);

    List<Cake> getCakesByUserId(int userId);

    List<Cake> getCakesByStyle (String style);

    List<Cake> getCakesByType (String type);

}
