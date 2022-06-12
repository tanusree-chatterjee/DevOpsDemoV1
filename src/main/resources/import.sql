--
-- Initial Data setup for Restaurant
--

INSERT INTO Restaurant VALUES (1, 'Awesome Döner - Your friendly neighbourhood Dönerbude');    

--
-- Initial Data setup for Tables
--

INSERT INTO Tables(tableType,capacity,position,availableSeats,status) VALUES ('Indoor', 6, 'Window', 6, 'Available'); 
INSERT INTO Tables(tableType,capacity,position,availableSeats,status) VALUES ('Indoor', 6, 'Room', 6, 'Available');  
INSERT INTO Tables(tableType,capacity,position,availableSeats,status) VALUES ('Outdoor', 6, 'Street', 6, 'Available');  
INSERT INTO Tables(tableType,capacity,position,availableSeats,status) VALUES ('Outdoor', 6, 'Yard', 6, 'Available');  



 