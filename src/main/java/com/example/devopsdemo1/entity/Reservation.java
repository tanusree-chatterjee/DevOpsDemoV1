package com.example.devopsdemo1.entity;

/**
*
* @author TANUSREE
*/

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name = "Reservation")
@Data
public class Reservation {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="RESERVATIONID")
	private int id;
	private String tableType;
	private int numOfSeats;
	private LocalDate dateOfReservation;
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime arriveTime;
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime leaveTime;
	private String status;
	// many-to-one association to TableEntity
	@ManyToOne
	@JoinColumn(name="TABLEID", nullable=false) 
	private TableEntity tableEntity;
	 

}
