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
@Table(name = "Restaurant")
@Data
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="RESTATURANTID")
	private int id;
	@Column(name="RESTATURANTNAME")
	private String name;
}
