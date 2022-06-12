package com.example.devopsdemo1.service;

/**
*
* @author TANUSREE
*/

import java.time.LocalDate;

import com.example.devopsdemo1.entity.Reservation;
import com.example.devopsdemo1.model.ResponseDTO;


public interface ReservationService {
	
	ResponseDTO createReservation(Reservation reservation);

	ResponseDTO fetchReservationById(int reservationId);
	
	ResponseDTO fetchReservationsByDate(LocalDate date);

	ResponseDTO cancelReservation(int reservationId);

	ResponseDTO checkInOutReservation(int reservationId, String type);

}
