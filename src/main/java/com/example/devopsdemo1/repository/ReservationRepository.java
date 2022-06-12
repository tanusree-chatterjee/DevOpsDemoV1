package com.example.devopsdemo1.repository;

/**
*
* @author TANUSREE
*/

import java.util.List;
import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.devopsdemo1.entity.Reservation;


@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer>{
	List<Reservation> findByDateOfReservation(LocalDate dateOfReservation);

}
