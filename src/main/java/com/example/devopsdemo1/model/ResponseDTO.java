package com.example.devopsdemo1.model;

/**
*
* @author TANUSREE
*/

import lombok.Data;

@Data
public class ResponseDTO {
	
	 private String responseCode;
	 private String responseStatus;
	 private Object responseBody;
	 private String error;

}
