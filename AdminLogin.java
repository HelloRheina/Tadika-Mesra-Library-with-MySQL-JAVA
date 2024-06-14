package tadikamesra;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminLogin extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton studentLoginButton;
    private Connection conn;

    public AdminLogin() {
        setTitle("Admin Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        studentLoginButton = new JButton("Login as Student");
        studentLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openStudentLogin();
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(studentLoginButton);

        add(panel);
        setVisible(true);

        // Connect to database
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_library", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void login() {
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        // Query to check username and password in database
        String query = "SELECT * FROM administrators WHERE username = ? AND password = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Admin login successful!");
                dispose(); // Close the login window
                openMainMenuAdmin(); // Open BookManagement window
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void openMainMenuAdmin() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainMenuAdmin();
            }
        });
    }

    private void openStudentLogin() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	dispose();
                new StudentLogin();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AdminLogin();
            }
        });
    }
}

