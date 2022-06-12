package com.example.devopsdemo1.service.Impl;

/**
*
* @author TANUSREE
*/


import java.time.LocalDate;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.devopsdemo1.constant.AppConstants;
import com.example.devopsdemo1.entity.Reservation;
import com.example.devopsdemo1.entity.TableEntity;
import com.example.devopsdemo1.model.ResponseDTO;
import com.example.devopsdemo1.repository.ReservationRepository;
import com.example.devopsdemo1.repository.TableRepository;
import com.example.devopsdemo1.service.ReservationService;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private TableRepository tableRepository;

	@Override
	public ResponseDTO fetchReservationById(int reservationId) {
		log.debug(" Entering ResevationServiceImpl >> fetchReservationById() method");
		ResponseDTO responseDTO = new ResponseDTO();
		// check reservationId
		if (reservationId != 0) {
			// find reservation by id
			Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
			if (reservation != null) {
				responseDTO.setResponseBody(reservation);
				responseDTO.setResponseCode(AppConstants.SUCCESS_RESPONSE_CODE);
				responseDTO.setResponseStatus(AppConstants.SUCCESS);
			} else {
				log.debug(AppConstants.RESERVATION_NOT_FOUND + AppConstants.FOR_ID + reservationId);
				responseDTO.setResponseCode(AppConstants.FAILURE_RESPONSE_CODE);
				responseDTO.setResponseStatus(AppConstants.FAILURE);
				responseDTO.setError(AppConstants.RESERVATION_NOT_FOUND);
			}
		} else {
			log.debug(AppConstants.INVALID_RESERVATION_ID);
			responseDTO.setResponseCode(AppConstants.FAILURE_RESPONSE_CODE);
			responseDTO.setResponseStatus(AppConstants.FAILURE);
			responseDTO.setError(AppConstants.INVALID_RESERVATION_ID);
		}
		log.debug(" Exiting ResevationServiceImpl << fetchReservationById() method");
		return responseDTO;
	}

	@Override
	public ResponseDTO createReservation(Reservation reservation) {
		log.debug(" Entering ResevationServiceImpl >> createReservation() method");
		ResponseDTO responseDTO = new ResponseDTO();
		// check if all the required parameters are present or not
		if (reservation != null && reservation.getTableType() != null && !reservation.getTableType().isEmpty()
				&& reservation.getNumOfSeats() != 0 && reservation.getDateOfReservation() != null
				&& reservation.getArriveTime() != null && reservation.getLeaveTime() != null) {

			// check whether the reservation request is between today and the day after
			// tomorrow
			if (checkReservationDate(reservation.getDateOfReservation())) {

				// fetch tables details by selected type
				List<TableEntity> tableList = tableRepository.findByTableType(reservation.getTableType());

				if (tableList != null && !tableList.isEmpty()) {
					// get available seats & table details
					List<TableEntity> availableTableList = getAvailableSeats(tableList, reservation.getNumOfSeats());
					if (availableTableList != null && !availableTableList.isEmpty()) {
						// try {
						// setting reservation attributes and save it to db
						// reservation.setDateOfReservation(AppConstants.FORMATTER
						// .parse(AppConstants.FORMATTER.format(reservation.getDateOfReservation())));
						reservation.setDateOfReservation(reservation.getDateOfReservation());
						reservation.setStatus(AppConstants.RESERVATION_BOOKED);
						reservation.setTableEntity(availableTableList.get(0));
						Reservation record = reservationRepository.save(reservation);
						if (record != null) {
							// update table details
							updateTableDetails(availableTableList.get(0), reservation);
							responseDTO.setResponseBody(record);
							responseDTO.setResponseCode(AppConstants.SUCCESS_RESPONSE_CODE);
							responseDTO.setResponseStatus(AppConstants.SUCCESS);
						}
//						} catch (ParseException e) {
//							log.error(e.getMessage());
//							responseDTO.setResponseCode(AppConstants.FAILURE_RESPONSE_CODE);
//							responseDTO.setResponseStatus(AppConstants.FAILURE);
//							responseDTO.setError(e.getMessage());
//						}

					} else {
						log.debug(AppConstants.SEATS_NOT_AVAILABLE);
						responseDTO.setResponseCode(AppConstants.FAILURE_RESPONSE_CODE);
						responseDTO.setResponseStatus(AppConstants.FAILURE);
						responseDTO.setError(AppConstants.SEATS_NOT_AVAILABLE);
					}

				} else {
					log.debug(AppConstants.TABLE_LIST_NOT_FOUND);
					responseDTO.setResponseCode(AppConstants.FAILURE_RESPONSE_CODE);
					responseDTO.setResponseStatus(AppConstants.FAILURE);
					responseDTO.setError(AppConstants.TABLE_LIST_NOT_FOUND);
				}

			} else {
				responseDTO.setResponseCode(AppConstants.FAILURE_RESPONSE_CODE);
				responseDTO.setResponseStatus(AppConstants.FAILURE);
				responseDTO.setError(AppConstants.INVALID_RESEVATION_DATE);
			}

		} else {
			responseDTO.setResponseCode(AppConstants.FAILURE_RESPONSE_CODE);
			responseDTO.setResponseStatus(AppConstants.FAILURE);
			responseDTO.setError(AppConstants.MISSING_REQUIRED_PARAMETERS);
		}
		log.debug(" Exiting ResevationServiceImpl << createReservation() method");
		return responseDTO;

	}

	/**
	 * Method to Update Table Details
	 * 
	 * @param tableEntity Accept table object
	 * @param reservation Accept reservation object
	 * 
	 */

	private void updateTableDetails(TableEntity tableEntity, Reservation reservation) {

		TableEntity tableRecord = new TableEntity();

		if (reservation.getStatus().equals(AppConstants.RESERVATION_CANCELLED)
				|| reservation.getStatus().equals(AppConstants.RESERVATION_CHECKEDOUT)) {
			// get table entity
			tableRecord = tableEntity;
			if (tableRecord != null) {
				int availableSeats = tableRecord.getAvailableSeats() + reservation.getNumOfSeats();
				// set available seats
				tableRecord.setAvailableSeats(availableSeats);
				// set table status available or partially occupied
				if (availableSeats == 6) {
					tableRecord.setStatus(AppConstants.TABLE_AVAILABLE);
				} else {
					tableRecord.setStatus(AppConstants.TABLE_PARTIALLY_OCCUPIED);
				}
			}
		} else {
			// find the table
			tableRecord = tableRepository.findById(tableEntity.getId()).orElse(null);
			if (tableRecord != null) {
				int remainingSeats = tableEntity.getAvailableSeats() - reservation.getNumOfSeats();
				// set available seats
				tableRecord.setAvailableSeats(remainingSeats);
				// set status occupied or partially occupied
				if (remainingSeats == 0) {
					tableRecord.setStatus(AppConstants.TABLE_OCCUPIED);
				} else {
					tableRecord.setStatus(AppConstants.TABLE_PARTIALLY_OCCUPIED);
				}
			}
		}
		// update table record
		tableRepository.save(tableRecord);

	}

	/**
	 * Method to Validate Reservation Date
	 * 
	 * @param date Accept reservation date
	 * @return True if the given date between the range of today and day after
	 *         tomorrow else false
	 */

	private boolean checkReservationDate(LocalDate date) {

		// get current date
		LocalDate today = LocalDate.now();
		// get day after tomorrow
		LocalDate dayAfterTomorrow = today.plusDays(2);
		// check given date with today and tomorrow
		if (date.isAfter(today) && date.isBefore(dayAfterTomorrow)) {
			return true;
		} else if (date.isEqual(today)) {
			return true;
		} else if (date.isEqual(dayAfterTomorrow)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to Get Available Seats
	 * 
	 * @param tableList  Accept list of table objects
	 * @param numOfSeats Accept seats number
	 * @return All the table objects where the seats are available
	 */
	private List<TableEntity> getAvailableSeats(List<TableEntity> tableList, int numOfSeats) {
		// From the list of tables identify where seats are available and return the
		// available list
		List<TableEntity> availableSeats = tableList.stream().filter(seats -> seats.getAvailableSeats() >= numOfSeats)
				.collect(Collectors.toList());

		return availableSeats;

	}

	@Override
	public ResponseDTO fetchReservationsByDate(LocalDate date) {
		log.debug(" Entering ResevationServiceImpl >> fetchReservationsByDate() method");
		ResponseDTO responseDTO = new ResponseDTO();
		// find reservations for the given date
		responseDTO.setResponseBody(reservationRepository.findByDateOfReservation(date));
		responseDTO.setResponseCode(AppConstants.SUCCESS_RESPONSE_CODE);
		responseDTO.setResponseStatus(AppConstants.SUCCESS);
		log.debug(" Exiting ResevationServiceImpl << fetchReservationsByDate() method");
		return responseDTO;
	}

	@Override
	public ResponseDTO cancelReservation(int reservationId) {
		log.debug(" Entering ResevationServiceImpl >> fetchReservationById() method");
		ResponseDTO responseDTO = new ResponseDTO();
		if (reservationId != 0) {
			// find existing reservation by id
			Reservation existingReservation = reservationRepository.findById(reservationId).orElse(null);
			// check if reservation is exists and not in cancelled status
			if (existingReservation != null
					&& !existingReservation.getStatus().equals(AppConstants.RESERVATION_CANCELLED)) {
				// set status
				existingReservation.setStatus(AppConstants.RESERVATION_CANCELLED);
				// cancel reservation
				reservationRepository.save(existingReservation);
				// get table details
				TableEntity tableRecord = tableRepository.findById(existingReservation.getTableEntity().getId())
						.orElse(null);
				// update table
				updateTableDetails(tableRecord, existingReservation);
				responseDTO.setResponseCode(AppConstants.SUCCESS_RESPONSE_CODE);
				responseDTO.setResponseStatus(AppConstants.SUCCESS);
			} else {
				responseDTO.setResponseCode(AppConstants.FAILURE_RESPONSE_CODE);
				responseDTO.setResponseStatus(AppConstants.FAILURE);
				responseDTO.setError(AppConstants.RESERVATION_NOT_EXISTS_CANCELLED);

			}

		} else {
			responseDTO.setResponseCode(AppConstants.FAILURE_RESPONSE_CODE);
			responseDTO.setResponseStatus(AppConstants.FAILURE);
			responseDTO.setError(AppConstants.INVALID_RESERVATION_ID);
		}
		log.debug(" Exiting ResevationServiceImpl << createReservation() method");
		return responseDTO;
	}

	@Override
	public ResponseDTO checkInOutReservation(int reservationId, String type) {
		log.debug(" Entering ResevationServiceImpl >> checkInOutReservation() method");
		ResponseDTO responseDTO = new ResponseDTO();
		if (reservationId != 0 && type != null) {
			// find reservation
			Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
			if (type.equals(AppConstants.CHECKIN)) {
				// check in
				if (reservation != null) {
					// set reservation status
					reservation.setStatus(AppConstants.RESERVATION_CHECKEDIN);
					// update reservation
					reservationRepository.save(reservation);
				}
				responseDTO.setResponseCode(AppConstants.SUCCESS_RESPONSE_CODE);
				responseDTO.setResponseStatus(AppConstants.SUCCESS);

			} else if (type.equals(AppConstants.CHECKOUT)) {
				// check out
				if (reservation != null) {
					// set reservation status
					reservation.setStatus(AppConstants.RESERVATION_CHECKEDOUT);
					// update reservation
					reservationRepository.save(reservation);
					// get table details
					TableEntity table = tableRepository.findById(reservation.getTableEntity().getId()).orElse(null);
					// update table
					updateTableDetails(table, reservation);
				}
				responseDTO.setResponseCode(AppConstants.SUCCESS_RESPONSE_CODE);
				responseDTO.setResponseStatus(AppConstants.SUCCESS);

			} else {
				responseDTO.setResponseCode(AppConstants.FAILURE_RESPONSE_CODE);
				responseDTO.setResponseStatus(AppConstants.FAILURE);
				responseDTO.setError(AppConstants.INVALID_REQUEST_PARAM);
			}
		} else {
			responseDTO.setResponseCode(AppConstants.FAILURE_RESPONSE_CODE);
			responseDTO.setResponseStatus(AppConstants.FAILURE);
			responseDTO.setError(AppConstants.INVALID_RESERVATION_ID);
		}

		log.debug(" Exiting ResevationServiceImpl << checkInOutReservation() method");
		return responseDTO;
	}

}
