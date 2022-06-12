/*
 * package com.example.devopsdemo1;
 * 
 * import static org.junit.jupiter.api.Assertions.assertEquals; import static
 * org.junit.jupiter.api.Assertions.assertNotNull;
 * 
 * import java.time.LocalDate; import java.time.LocalTime; import
 * java.util.List;
 * 
 *//**
	 *
	 * @author TANUSREE
	 *//*
		 * 
		 * import org.junit.jupiter.api.Test; import
		 * org.springframework.beans.factory.annotation.Autowired; import
		 * org.springframework.boot.test.context.SpringBootTest;
		 * 
		 * import com.example.devopsdemo1.entity.Reservation; import
		 * com.example.devopsdemo1.entity.TableEntity; import
		 * com.example.devopsdemo1.repository.ReservationRepository; import
		 * com.example.devopsdemo1.repository.TableRepository;
		 * 
		 * 
		 * @SpringBootTest public class DevOpsDemoV1ApplicationTests {
		 * 
		 * @Autowired ReservationRepository reservationRepository;
		 * 
		 * @Autowired TableRepository tableRepository;
		 * 
		 * @Test public void testCreateReservation() { Reservation reservation = new
		 * Reservation(); reservation.setId(1); reservation.setTableType("Indoor");
		 * reservation.setNumOfSeats(4);
		 * reservation.setDateOfReservation(LocalDate.now());
		 * reservation.setArriveTime(LocalTime.of(9, 0));
		 * reservation.setLeaveTime(LocalTime.of(11, 0)); List<TableEntity> tableList =
		 * tableRepository.findByTableType(reservation.getTableType());
		 * reservation.setTableEntity(tableList.get(0));
		 * reservationRepository.save(reservation);
		 * assertNotNull(reservationRepository.findById(1));
		 * 
		 * }
		 * 
		 * 
		 * @Test public void testSingleReservationById() { Reservation reservation =
		 * reservationRepository.findById(1).get(); assertEquals("Indoor",
		 * reservation.getTableType()); }
		 * 
		 * }
		 */
