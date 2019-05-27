SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE IF NOT EXISTS passenger(
	id INT AUTO_INCREMENT,
	first_name VARCHAR (15),
	last_name VARCHAR (30),
	dni VARCHAR(10),
	passport VARCHAR(30),
	CONSTRAINT pk_id_passenger PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS plane(
	id INT AUTO_INCREMENT,
	model VARCHAR(30),
	total_seat INT (10),
	CONSTRAINT pk_id_plane PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS flight(
	id INT AUTO_INCREMENT,
	number_flight VARCHAR(10),
	origin INT,
	destination INT,
	plane INT,
	reservation_start_date DATE NOT NULL,
	departure_date DATE NOT NULL,
	boarding_time DATE NOT NULL,
	departure_time DATE NOT NULL,
	duration INT NOT NULL,
	company VARCHAR(30),
	CONSTRAINT pk_id_flight PRIMARY KEY (id),
	CONSTRAINT fk_plane FOREIGN KEY (plane) REFERENCES plane(id),
	CONSTRAINT fk_origin_flight FOREIGN KEY (origin) REFERENCES airport(id),
	CONSTRAINT fk_destination_flight FOREIGN KEY (destination) REFERENCES airport(id)
);

CREATE TABLE IF NOT EXISTS seat(
	id INT AUTO_INCREMENT,
	letter_seat INT,
	row_seat INT,
	CONSTRAINT pk_id_seat PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS agency(
	id INT AUTO_INCREMENT,
	first_name VARCHAR(30),
	last_name VARCHAR(30),
	password VARCHAR(30),
	username VARCHAR(30),
	CONSTRAINT fk_id_agency PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS airport(
  id INT AUTO_INCREMENT,
  ident VARCHAR(4),
  type_airport VARCHAR(30),
  airport_name VARCHAR(30),
  elevation_ft INT,
  continent VARCHAR(2),
  iso_country VARCHAR(2),
  iso_region VARCHAR(5),
  municipality VARCHAR(30),
  gps_code VARCHAR(4),
  iata_code VARCHAR(10),
  local_code VARCHAR(4),
  latitude DECIMAL,
  longitude DECIMAL,
  CONSTRAINT pk_id_airport PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS reservation(
  id INT AUTO_INCREMENT,
  outbound_flight INT,
  return_flight INT,
  reservation_date DATE NOT NULL,
  price DECIMAL,
  paid BOOLEAN DEFAULT FALSE,
  CONSTRAINT pk_id_reservation PRIMARY KEY (id),
  CONSTRAINT fk_outboud_flight FOREIGN KEY (outbound_flight) REFERENCES flight(id),
  CONSTRAINT fk_return_flight FOREIGN KEY (return_flight) REFERENCES flight(id)
);

CREATE TABLE IF NOT EXISTS reservation_passenger(
  reservation_id INT,
  passenger_id INT,
  seat_id INT,
  checked_in BOOLEAN DEFAULT FALSE,
  priority_boarding BOOLEAN DEFAULT FALSE,
  bag_number INT(2),
  CONSTRAINT fk_reservation_id FOREIGN KEY (reservation_id) REFERENCES reservation(id),
  CONSTRAINT fk_passenger_id FOREIGN KEY (passenger_id) REFERENCES passenger(id),
	CONSTRAINT fk_seat_id FOREIGN KEY (seat_id) REFERENCES seat(id)
);

SET FOREIGN_KEY_CHECKS = 1;
