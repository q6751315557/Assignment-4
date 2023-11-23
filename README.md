# Assignment-4-
Assignment 4 for 3005

the viedo link :
https://youtu.be/GODSAWhjvVQ

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

code explain
1
getAllStudents(Connection connection)
Purpose: Queries and displays all records in the students table.
Parameters: Connection - The database connection object.
Operation: Executes an SQL query to retrieve and print detailed information about all students.

2
addStudent(Connection connection, String firstName, String lastName, String email, String enrollmentDate)
Purpose: Adds a new student record to the students table.
Parameters:
Connection - The database connection object.
firstName - The student's first name.
lastName - The student's last name.
email - The student's email address.
enrollmentDate - The date of enrollment in YYYY-MM-DD format.

3
updateStudentEmail(Connection connection, int studentId, String newEmail)
Purpose: Updates the email address for a specific student in the students table.
Parameters:
Connection - The database connection object.
studentId - The ID of the student whose email is to be updated.
newEmail - The new email address to be set.

4
deleteStudent(Connection connection, int studentId)
Purpose: Deletes a student record from the students table based on the student ID.
Parameters:
Connection - The database connection object.
studentId - The ID of the student to be deleted.

5
addStudentInteractive(Connection connection, Scanner scanner)
Purpose: Interactive method to add a new student record.
Parameters:
Connection - The database connection object.
Scanner - Scanner object to read user input.
Operation: Prompts the user to enter student details and adds the student to the database.

6
updateStudentEmailInteractive(Connection connection, Scanner scanner)
Purpose: Interactive method to update a student
