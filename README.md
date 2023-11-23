# Assignment-4-
Assignment 4 for 3005
use that to create database 

CREATE TABLE students (
    student_id SERIAL PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    enrollment_date DATE
);

use that to give base case
INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES
('John', 'Doe', 'john.doe@example.com', '2023-09-01'),
('Jane', 'Smith', 'jane.smith@example.com', '2023-09-01'),
('Jim', 'Beam', 'jim.beam@example.com', '2023-09-02');


use 
javac -cp ".;postgresql-42.7.0.jar" DatabaseConnector.java
to compile

use
java -cp ".;postgresql-42.7.0.jar" DatabaseConnector
to run code
