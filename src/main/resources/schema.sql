--
-- Table structure for Restaurant
--

DROP TABLE IF EXISTS Restaurant cascade;

CREATE TABLE Restaurant (
  id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) DEFAULT NULL
);

--
-- Table structure for Tables
--

DROP TABLE IF EXISTS Tables cascade;

CREATE TABLE Tables (
  id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  tableType VARCHAR(50) DEFAULT NULL,
  capacity INT(11) DEFAULT NULL,
  position VARCHAR(50) DEFAULT NULL,
  availableSeats INT(11) NOT NULL,
  status VARCHAR(50) DEFAULT NULL
);



