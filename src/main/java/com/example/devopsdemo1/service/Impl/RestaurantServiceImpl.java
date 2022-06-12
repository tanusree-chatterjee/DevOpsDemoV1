package com.example.devopsdemo1.service.Impl;

/**
*
* @author TANUSREE
*/

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.devopsdemo1.constant.AppConstants;
import com.example.devopsdemo1.entity.Reservation;
import com.example.devopsdemo1.entity.Restaurant;
import com.example.devopsdemo1.model.ResponseDTO;
import com.example.devopsdemo1.model.RestaurantDTO;
import com.example.devopsdemo1.repository.ReservationRepository;
import com.example.devopsdemo1.repository.RestaurantRepository;
import com.example.devopsdemo1.repository.TableRepository;
import com.example.devopsdemo1.service.RestaurantService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private TableRepository tableRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;

	@Override
	public ResponseDTO fetchRestaurantDetails() {
		log.debug(" Entering RestaurantServiceImpl >> fetchRestaurantDetails() method");
		ResponseDTO responseDTO = new ResponseDTO();
		// get restaurant details
		List<Restaurant> restaurant = (List<Restaurant>) restaurantRepository.findAll();
		if(restaurant != null && restaurant.size() > 0) {
			// create new restaurant obj instance
			RestaurantDTO restaurantObj =  new RestaurantDTO();
			// get and set restaurant name
			restaurantObj.setName(restaurant.get(0).getName());
			// get and set num of tables
			restaurantObj.setNumOfTables((int)tableRepository.count());
			// get and set reservations
			restaurantObj.setReservations((List<Reservation>) reservationRepository.findAll());
			// set response
			responseDTO.setResponseBody(restaurantObj);
			responseDTO.setResponseCode(AppConstants.SUCCESS_RESPONSE_CODE);
			responseDTO.setResponseStatus(AppConstants.SUCCESS);
		}else {
			log.error(AppConstants.RESTAURANT_NOT_FOUND);
			responseDTO.setResponseCode(AppConstants.FAILURE_RESPONSE_CODE);
			responseDTO.setResponseStatus(AppConstants.FAILURE);
			responseDTO.setError(AppConstants.RESTAURANT_NOT_FOUND);
		}
		
		log.debug(" Exiting RestaurantServiceImpl << fetchRestaurantDetails() method");
		return responseDTO;
	}

}
