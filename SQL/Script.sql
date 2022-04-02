DROP DATABASE IF EXISTS horizonsDB;

CREATE DATABASE horizonsDB;
USE horizonsDB;

SET @@SESSION.auto_increment_increment=1;

# ID
# Name
# DOB
# Address
# Phone no.
CREATE TABLE teacher(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    dob VARCHAR(100) NOT NULL,
    address VARCHAR(256) NOT NULL,
    phone VARCHAR(20) NOT NULL,

    CONSTRAINT pk_teacher PRIMARY KEY(id)
);

INSERT INTO teacher(name,dob,address,phone)
VALUES
("teacher 1", "1990-01-01", "address 1", "phone 1"),
("teacher 2", "1990-01-01", "address 2", "phone 2"),
("teacher 3", "1990-01-01", "address 3", "phone 3"),
("teacher 4", "1990-01-01", "address 4", "phone 4"),
("teacher 5", "1990-01-01", "address 5", "phone 5");
