import java.sql.*;
import java.util.Scanner;

public class DatabaseConnector {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost/studentandemail";
        String user = "postgres";
        String password = "987654321XX";
        Connection connection = null;

        try {
            // Load PostgreSQL JDBC Driver
            Class.forName("org.postgresql.Driver");

            // Connect to the database
            connection = DriverManager.getConnection(url, user, password);

            // Check if connection is successful
            if (connection != null) {
                System.out.println("Connected to PostgreSQL successfully!");

                // Interactive menu for CRUD operations
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.println("Please choose an operation:");
                    System.out.println("1 - List all students");
                    System.out.println("2 - Add a new student");
                    System.out.println("3 - Update student email");
                    System.out.println("4 - Delete a student");
                    System.out.println("5 - Exit");
                    System.out.print("Enter your choice: ");

                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            getAllStudents(connection);
                            break;
                        case 2:
                            addStudentInteractive(connection, scanner);
                            break;
                        case 3:
                            updateStudentEmailInteractive(connection, scanner);
                            break;
                        case 4:
                            deleteStudentInteractive(connection, scanner);
                            break;
                        case 5:
                            return;
                        default:
                            System.out.println("Invalid option, please try again.");
                    }
                }
            } else {
                System.out.println("Failed to establish connection.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    // List all students
    public static void getAllStudents(Connection connection) {
        String sql = "SELECT * FROM students";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                System.out.println("               ");
                System.out.println("Student ID: " + resultSet.getInt("student_id"));
                System.out.println("First Name: " + resultSet.getString("first_name"));
                System.out.println("Last Name: " + resultSet.getString("last_name"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("Enrollment Date: " + resultSet.getDate("enrollment_date"));
                System.out.println("               ");
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add student
    public static void addStudent(Connection connection, String firstName, String lastName, String email, String enrollmentDate) {
        String sql = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setDate(4, Date.valueOf(enrollmentDate));
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student added successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update student email
    public static void updateStudentEmail(Connection connection, int studentId, String newEmail) {
        String sql = "UPDATE students SET email = ? WHERE student_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newEmail);
            preparedStatement.setInt(2, studentId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student email updated successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete student
    public static void deleteStudent(Connection connection, int studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, studentId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student deleted successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Interactive method to add a student
    public static void addStudentInteractive(Connection connection, Scanner scanner) {
        System.out.print("Enter student's first name: ");
        String firstName = scanner.next();
        System.out.print("Enter student's last name: ");
        String lastName = scanner.next();
        System.out.print("Enter student's email: ");
        String email = scanner.next();
        System.out.print("Enter year of enrollment (YYYY): ");
        int year = scanner.nextInt();
        System.out.print("Enter month of enrollment (MM): ");
        int month = scanner.nextInt();
        System.out.print("Enter day of enrollment (DD): ");
        int day = scanner.nextInt();
        String enrollmentDate = year + "-" + month + "-" + day;
        addStudent(connection, firstName, lastName, email, enrollmentDate);
    }




    // Interactive method to update student email
    public static void updateStudentEmailInteractive(Connection connection, Scanner scanner) {
        try {
            System.out.print("Enter the student ID to update: ");
            int studentId = scanner.nextInt();

            // find the student old information
            String querySQL = "SELECT * FROM students WHERE student_id = ?";
            try (PreparedStatement pstmtQuery = connection.prepareStatement(querySQL)) {
                pstmtQuery.setInt(1, studentId);
                ResultSet resultSet = pstmtQuery.executeQuery();
                if (resultSet.next()) {
                    System.out.println("Current information:");
                    System.out.println("Student ID: " + resultSet.getInt("student_id"));
                    System.out.println("First Name: " + resultSet.getString("first_name"));
                    System.out.println("Last Name: " + resultSet.getString("last_name"));
                    System.out.println("Email: " + resultSet.getString("email"));
                    System.out.println("Enrollment Date: " + resultSet.getDate("enrollment_date"));
                    System.out.println("---------------");

                    // updata new email
                    System.out.print("Enter new email address: ");
                    String newEmail = scanner.next();

                    String updateSQL = "UPDATE students SET email = ? WHERE student_id = ?";
                    try (PreparedStatement pstmtUpdate = connection.prepareStatement(updateSQL)) {
                        pstmtUpdate.setString(1, newEmail);
                        pstmtUpdate.setInt(2, studentId);
                        int rowsAffected = pstmtUpdate.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Student email updated successfully.");
                        }
                    }
                } else {
                    System.out.println("Student with ID " + studentId + " not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Interactive method to delete a student
    public static void deleteStudentInteractive(Connection connection, Scanner scanner) {
        System.out.print("Enter the student ID to delete: ");
        int studentId = scanner.nextInt();
        deleteStudent(connection, studentId);
    }
}
