package com.example.devopsdemo1.model;

/**
*
* @author TANUSREE
*/

import java.util.List;

import com.example.devopsdemo1.entity.Reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {

	private String name;
	private int numOfTables;
	private List<Reservation> reservations;
}
