package com.example.devopsdemo1.entity;

/**
*
* @author TANUSREE
*/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Tables")
@Data
public class TableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	@Column(name="TABLETYPE")
	private String tableType;
	@Column(name="CAPACITY")
	private int capacity;
	@Column(name="POSITION")
	private String position;
	@Column(name="AVAILABLESEATS")
	private int availableSeats;
	@Column(name="STATUS")
	private String status;
}
