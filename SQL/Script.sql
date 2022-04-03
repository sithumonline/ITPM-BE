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

# ID
# Name
# Date
# Time
# Link
CREATE TABLE session(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    date VARCHAR(100) NOT NULL,
    time VARCHAR(100) NOT NULL,
    link VARCHAR(256) NOT NULL,

    CONSTRAINT pk_class PRIMARY KEY(id)
);

INSERT INTO session(name,date,time,link)
VALUES
("session 1", "2020-01-01", "time 1", "link 1"),
("session 2", "2020-01-01", "time 2", "link 2"),
("session 3", "2020-01-01", "time 3", "link 3"),
("session 4", "2020-01-01", "time 4", "link 4"),
("session 5", "2020-01-01", "time 5", "link 5");

# ID
# Name
# DOB
# Address
# Phone no.
CREATE TABLE student(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    dob VARCHAR(100) NOT NULL,
    address VARCHAR(256) NOT NULL,
    phone VARCHAR(20) NOT NULL,

    CONSTRAINT pk_student PRIMARY KEY(id)
);

INSERT INTO student(name,dob,address,phone)
VALUES
("student 1", "1990-01-01", "address 1", "phone 1"),
("student 2", "1990-01-01", "address 2", "phone 2"),
("student 3", "1990-01-01", "address 3", "phone 3"),
("student 4", "1990-01-01", "address 4", "phone 4"),
("student 5", "1990-01-01", "address 5", "phone 5");

# ID
# Title
# Author
# Subject
# Grade
CREATE TABLE material(
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    subject VARCHAR(100) NOT NULL,
    grade VARCHAR(100) NOT NULL,

    CONSTRAINT pk_book PRIMARY KEY(id)
);

INSERT INTO material(title,author,subject,grade)
VALUES
("book 1", "author 1", "subject 1", "grade 1"),
("book 2", "author 2", "subject 2", "grade 2"),
("book 3", "author 3", "subject 3", "grade 3"),
("book 4", "author 4", "subject 4", "grade 4"),
("book 5", "author 5", "subject 5", "grade 5");
