package com.example.devopsdemo1.controller;

import java.time.LocalDate;

/**
*
* @author TANUSREE
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.devopsdemo1.entity.Reservation;
import com.example.devopsdemo1.model.ResponseDTO;
import com.example.devopsdemo1.service.ReservationService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/api/reservation")
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	/**
	* Method to fetch Reservation Details by Reservation Id
	* @param reservationId Accept reservation id
	* @return Reservation details for given reservation id with custom code and status
	*/

	@GetMapping(value = "/fetchReservation/{reservationId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO>fetchReservationById(@PathVariable int reservationId){
			 log.info(" Entering ReservationController >> fetchReservationById() method"); 
			 return new ResponseEntity<>(reservationService.fetchReservationById(reservationId),
			 HttpStatus.OK);
	}
	
	/**
	* Method to Create Reservation
	* @param reservation Accept reservation objects with all required information
	* @return Custom response obj with custom code and status
	*/

	@PostMapping(value = "/createReservation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> createReservation(@RequestBody Reservation reservation){
		return new ResponseEntity<>(reservationService.createReservation(reservation), HttpStatus.OK);
		
	}
	
	/**
	* Method to fetch Reservations by Date
	* @param date Accept date as a parameter
	* @return Reservation details for given date with custom code and status
	*/

	@GetMapping(value = "/fetchReservationsByDate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO>fetchReservationsByDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
			 log.info(" Entering ReservationController >> fetchReservationsByDate() method"); 
			 return new ResponseEntity<>(reservationService.fetchReservationsByDate(date),
			 HttpStatus.OK);
	}
	
	/**
	* Method to cancel existing reservation with the given ID
	* @param reservationId Accept reservation id
	* @return Custom response obj with custom code and status
	*/

	@PutMapping(value = "/cancelReservation/{reservationId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO>cancelReservation(@PathVariable int reservationId){
			 log.info(" Entering ReservationController >> cancelReservation() method"); 
			 return new ResponseEntity<>(reservationService.cancelReservation(reservationId),
			 HttpStatus.OK);
	}
	
	/**
	* Method to Check In/ Check Out Reservation
	* @param reservationId Accept reservation id
	* @param type Accept type either CheckIn or CheckOut
	* @return Custom response obj with custom code and status
	*/

	@PutMapping(value = "/checkInOut/{reservationId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO>checkInOutReservation(@PathVariable int reservationId, @RequestParam String type){
			 log.info(" Entering ReservationController >> checkInOutReservation() method"); 
			 return new ResponseEntity<>(reservationService.checkInOutReservation(reservationId, type),
			 HttpStatus.OK);
	}
	

}
