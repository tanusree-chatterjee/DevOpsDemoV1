package com.example.devopsdemo1.model;

/**
*
* @author TANUSREE
*/

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
	
	// customizing timestamp format
	@DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
	private Date timestamp;
	private int code;
    private String error;
    private String message;
    
}
