import java.sql.*;

public class Create {
    public static void create() {
        try {
            Connection con = Connect.connect();
            Statement stmt = con.createStatement();

            String createUserTable = "CREATE TABLE IF NOT EXISTS USERS(ID INT AUTO_INCREMENT PRIMARY KEY, USERNAME VARCHAR(50), PASSWORD VARCHAR(50))";
            String createBookTable = "CREATE TABLE IF NOT EXISTS BOOKS(ID INT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(50), AUTHOR VARCHAR(50), STATUS VARCHAR(10))";
            String createIssueTable = "CREATE TABLE IF NOT EXISTS ISSUE(ID INT AUTO_INCREMENT PRIMARY KEY, BOOKID INT, USERID INT, ISSUEDATE DATE, RETURNDATE DATE)";

            stmt.executeUpdate(createUserTable);
            stmt.executeUpdate(createBookTable);
            stmt.executeUpdate(createIssueTable);

            stmt.executeUpdate("INSERT INTO USERS(USERNAME, PASSWORD) VALUES('admin', 'admin')");

            System.out.println("Database and tables created successfully.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
