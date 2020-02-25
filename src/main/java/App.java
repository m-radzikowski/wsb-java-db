import java.sql.*;

public class App {

    public static void main(String[] args) throws SQLException {
        try (
                Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "root", "secret");
                Statement statement = connection.createStatement()
        ) {
            ResultSet booksResult = statement.executeQuery("SELECT id, title FROM Book");
            System.out.println("Books:");
            while (booksResult.next()) {
                int id = booksResult.getInt("id");
                String title = booksResult.getString("title");
                System.out.println("[" + id + "] " + title);
            }
            System.out.println();

            ResultSet bookResult = statement.executeQuery("SELECT id, title FROM Book WHERE id = 2");
            boolean firstExists = bookResult.first();
            if (firstExists) {
                System.out.println("Book id = 2: " + bookResult.getString("title"));
            } else {
                System.out.println("There is no book with given id");
            }
            System.out.println();

            PreparedStatement getByIdStmt = connection.prepareStatement("SELECT id, title FROM Book WHERE id = ?");

            for (int i = 1; i <= 5; i++) {
                getByIdStmt.setInt(1, i);
                ResultSet result = getByIdStmt.executeQuery();

                boolean bookExists = result.first();
                if (bookExists) {
                    System.out.println("Book id = " + i + ": " + result.getString("title"));
                } else {
                    System.out.println("There is no book with id = " + i);
                }
            }
            System.out.println();

//            ResultSet authorsResult = statement.executeQuery("SELECT id, lastname FROM Author");
//            System.out.println("Authors:");
//            while (authorsResult.next()) {
//                int id = authorsResult.getInt("id");
//                String lastname = authorsResult.getString("lastname");
//                System.out.println("[" + id + "] " + lastname);
//            }
//            System.out.println();


//            statement.executeUpdate("INSERT INTO Book SET title = 'Legion'");
        }

//        try (
//                Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=AdventureWorks", "SA", "password-1234");
//                Statement statement = connection.createStatement()
//        ) {
//            ResultSet customersResult = statement.executeQuery("SELECT * FROM [SalesLT].[Customer]");
//            System.out.println("Customers:");
//            while (customersResult.next()) {
//                int id = customersResult.getInt("CustomerID");
//                String firstName = customersResult.getString("FirstName");
//                String lastName = customersResult.getString("LastName");
//                System.out.println("[" + id + "] " + firstName + " " + lastName);
//            }
//            System.out.println();
//        }
    }

}
