package com.example.devopsdemo1.constant;

/**
*
* @author TANUSREE
*/

import java.text.SimpleDateFormat;

public class AppConstants {
	
	/** Response Code Constants */
    public static final String SUCCESS_RESPONSE_CODE = "000";
	public static final String FAILURE_RESPONSE_CODE = "111";
	
	/** Response Status Constants */
	public static final String SUCCESS = "success";
	public static final String FAILURE = "fail";
	
	/** Response Message Constants */
    public static final String MISSING_REQUIRED_PARAMETERS = "Some required attributes are missing";
    public static final String INVALID_RESERVATION_ID = "Invalid Reservation Id";
    public static final String INVALID_REQUEST_PARAM = "Invalid request parameter";
    public static final String RESERVATION_NOT_FOUND = "Reservation not found";
    public static final String RESERVATION_NOT_EXISTS_CANCELLED = "Reservation is already cancelled or it’s not exists";
    public static final String FOR_ID = " for id: ";
    public static final String INVALID_RESEVATION_DATE = "Please select the reservation date between today and the day after tomorrow.";
    public static final String TABLE_LIST_NOT_FOUND = "Please select the reservation date between today and the day after tomorrow.";
    public static final String SEATS_NOT_AVAILABLE = "Seats are not available at this moment";
    public static final String RESTAURANT_NOT_FOUND = "Unable to fetch restaurant information";

    /** Date Formatter Constant */
    public final static SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    
    /** Reservation Status Constants */
	public static final String RESERVATION_BOOKED = "Booked";
	public static final String RESERVATION_CANCELLED = "Cancelled";
	public static final String RESERVATION_CHECKEDIN = "Checked In";
	public static final String RESERVATION_CHECKEDOUT = "Checked OUT";
	
	/** Table Status Constants */
	public static final String TABLE_AVAILABLE = "Available";
	public static final String TABLE_PARTIALLY_OCCUPIED = "Partially Occupied";
	public static final String TABLE_OCCUPIED = "Occupied";
	
	/** String Constants */
	public static final String CHECKIN = "CheckIn";
	public static final String CHECKOUT = "CheckOut";
	


}
