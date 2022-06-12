package com.example.devopsdemo1.controller;

/**
*
* @author TANUSREE
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.devopsdemo1.model.ResponseDTO;
import com.example.devopsdemo1.service.RestaurantService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/api/restaurant")
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;

	/**
	* Method to fetch all Restaurant related information
	* @return All information related to Restaurant
	*/

	@GetMapping(value = "/getRestaurantDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO>fetchRestaurantDetails(){
			 log.info(" Entering RestaurantController >> fetchRestaurantDetails() method"); 
			 return new ResponseEntity<>(restaurantService.fetchRestaurantDetails(),
			 HttpStatus.OK);
	}
}
