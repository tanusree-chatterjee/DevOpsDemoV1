package com.example.devopsdemo1.repository;

/**
*
* @author TANUSREE
*/

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.devopsdemo1.entity.Restaurant;


@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer>{

}
