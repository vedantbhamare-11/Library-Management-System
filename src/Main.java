import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            try {
                boolean running = true;
                while (running) {
                    System.out.println("1. View all books");
                    System.out.println("2. View a book by ID");
                    System.out.println("3. Exit");
                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            viewAllBooks(connection);
                            break;
                        case 2:
                            System.out.print("Enter the book ID: ");
                            int bookId = scanner.nextInt();
                            scanner.nextLine();
                            viewBookById(connection, bookId);
                            break;
                        case 3:
                            running = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                }

                DatabaseConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }

    private static void viewAllBooks(Connection connection) throws SQLException {
        String sql = "SELECT * FROM book";
        try (PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int quantity = resultSet.getInt("quantity");
                int subjectId = resultSet.getInt("subject_id");

                System.out.println("Book ID: " + id);
                System.out.println("Title: " + title);
                System.out.println("Author: " + author);
                System.out.println("Quantity: " + quantity);
                System.out.println("Subject ID: " + subjectId);
                System.out.println("--------------------");
            }
        }
    }

    private static void viewBookById(Connection connection, int bookId) throws SQLException {
        String sql = "SELECT * FROM book WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("author");
                    int quantity = resultSet.getInt("quantity");
                    int subjectId = resultSet.getInt("subject_id");

                    System.out.println("Book ID: " + id);
                    System.out.println("Title: " + title);
                    System.out.println("Author: " + author);
                    System.out.println("Quantity: " + quantity);
                    System.out.println("Subject ID: " + subjectId);
                    System.out.println("--------------------");
                } else {
                    System.out.println("Book with ID " + bookId + " not found.");
                }
            }
        }
    }
}
