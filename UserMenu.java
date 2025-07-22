import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UserMenu {
    public static void user_menu(String id) {
        JFrame frame = new JFrame("User Menu");
        frame.setSize(400, 300);
        frame.setLayout(null);

        JButton viewBooksBtn = new JButton("View Books");
        viewBooksBtn.setBounds(100, 50, 200, 30);
        frame.add(viewBooksBtn);

        JButton issueBookBtn = new JButton("Issue Book");
        issueBookBtn.setBounds(100, 100, 200, 30);
        frame.add(issueBookBtn);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        viewBooksBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = Connect.connect();
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM BOOKS");
                    while (rs.next()) {
                        System.out.println(rs.getString("NAME") + " by " + rs.getString("AUTHOR"));
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        issueBookBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bookId = JOptionPane.showInputDialog("Enter Book ID to issue:");
                try {
                    Connection con = Connect.connect();
                    PreparedStatement ps = con.prepareStatement("INSERT INTO ISSUE(BOOKID, USERID, ISSUEDATE) VALUES (?, ?, CURDATE())");
                    ps.setString(1, bookId);
                    ps.setString(2, id);
                    ps.executeUpdate();

                    PreparedStatement updateBook = con.prepareStatement("UPDATE BOOKS SET STATUS='issued' WHERE ID=?");
                    updateBook.setString(1, bookId);
                    updateBook.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Book Issued Successfully!");
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
    }
}
