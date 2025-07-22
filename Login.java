import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login {
    public static void login() {
        JFrame frame = new JFrame("Login Page");
        frame.setSize(400, 400);
        frame.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 100, 100, 30);
        frame.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(150, 100, 150, 30);
        frame.add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 150, 100, 30);
        frame.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(150, 150, 150, 30);
        frame.add(passField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(50, 200, 100, 30);
        frame.add(loginBtn);

        JButton createBtn = new JButton("Create DB");
        createBtn.setBounds(200, 200, 120, 30);
        frame.add(createBtn);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = String.valueOf(passField.getPassword());

                try {
                    Connection con = Connect.connect();
                    PreparedStatement stmt = con.prepareStatement("SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?");
                    stmt.setString(1, username);
                    stmt.setString(2, password);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        if (username.equals("admin") && password.equals("admin")) {
                            frame.dispose();
                            AdminMenu.admin_menu();
                        } else {
                            frame.dispose();
                            UserMenu.user_menu(rs.getString("ID"));
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid credentials!");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        createBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Create.create();
            }
        });
    }
}
