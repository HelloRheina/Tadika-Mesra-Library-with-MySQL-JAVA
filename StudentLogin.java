package tadikamesra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentLogin extends JFrame {
    private JTextField nameField;
    private JTextField dobField;
    private JButton studentLoginButton;
    private JButton adminLoginButton;
    private Connection conn;

    public StudentLogin() {
        setTitle("Student Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Name:");
        JLabel dobLabel = new JLabel("Date of Birth (YYYY-MM-DD):");

        nameField = new JTextField();
        dobField = new JTextField();

        studentLoginButton = new JButton("Login");
        studentLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginAsStudent();
            }
        });

        adminLoginButton = new JButton("Login as Admin");
        adminLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAdminLogin();
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(dobLabel);
        panel.add(dobField);
        panel.add(studentLoginButton);
        panel.add(adminLoginButton);

        add(panel);
        setVisible(true);

        // Connect to database
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_library", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loginAsStudent() {
        String name = nameField.getText();
        String dob = dobField.getText();

        // Query to check student name and dob in database
        String query = "SELECT * FROM students WHERE student_name = ? AND dob = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, dob);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Student login successful!");
                dispose(); // Close the login window
                openMainMenuStudent(); // Open BorrowingManagement window
            } else {
                JOptionPane.showMessageDialog(this, "Invalid name or date of birth", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void openMainMenuStudent() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainMenuStudent();
            }
        });
    }

    private void openAdminLogin() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	dispose();
                new AdminLogin();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentLogin();
            }
        });
    }
}

