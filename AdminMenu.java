import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminMenu {
    public static void admin_menu() {
        JFrame frame = new JFrame("Admin Menu");
        frame.setSize(400, 400);
        frame.setLayout(null);

        JButton addUserBtn = new JButton("Add User");
        addUserBtn.setBounds(100, 50, 200, 30);
        frame.add(addUserBtn);

        JButton addBookBtn = new JButton("Add Book");
        addBookBtn.setBounds(100, 100, 200, 30);
        frame.add(addBookBtn);

        JButton returnBookBtn = new JButton("Return Book");
        returnBookBtn.setBounds(100, 150, 200, 30);
        frame.add(returnBookBtn);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addUserBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = JOptionPane.showInputDialog("Enter Username:");
                String pass = JOptionPane.showInputDialog("Enter Password:");
                try {
                    Connection con = Connect.connect();
                    PreparedStatement ps = con.prepareStatement("INSERT INTO USERS(USERNAME, PASSWORD) VALUES (?, ?)");
                    ps.setString(1, user);
                    ps.setString(2, pass);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "User Added!");
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        addBookBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter Book Name:");
                String author = JOptionPane.showInputDialog("Enter Author:");
                try {
                    Connection con = Connect.connect();
                    PreparedStatement ps = con.prepareStatement("INSERT INTO BOOKS(NAME, AUTHOR, STATUS) VALUES (?, ?, 'available')");
                    ps.setString(1, name);
                    ps.setString(2, author);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Book Added!");
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        returnBookBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bookId = JOptionPane.showInputDialog("Enter Book ID to return:");
                try {
                    Connection con = Connect.connect();
                    PreparedStatement ps = con.prepareStatement("UPDATE BOOKS SET STATUS='available' WHERE ID=?");
                    ps.setString(1, bookId);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Book Returned!");
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
    }
}
