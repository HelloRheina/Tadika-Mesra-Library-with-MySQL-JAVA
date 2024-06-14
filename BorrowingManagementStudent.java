package tadikamesra;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class BorrowingManagementStudent extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private JButton addButton;
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private JComboBox<String> studentComboBox;
    private JComboBox<String> bookComboBox;

    public BorrowingManagementStudent() {
        setTitle("Borrowing Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        studentComboBox = new JComboBox<>();
        bookComboBox = new JComboBox<>();
        
        // Table
        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        // Add column names to the table
        model.addColumn("Borrowing ID");
        model.addColumn("Student ID");
        model.addColumn("Book ID");
        model.addColumn("Borrow Date");
        model.addColumn("Return Date");
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addButton = new JButton("Add");
        
        buttonPanel.add(addButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Connect to database
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_library", "root", "");
            loadBorrowingsData();
            loadStudentsDataToComboBox();
            loadBooksDataToComboBox();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBorrowing();
            }
        });

        setVisible(true);
    }

    private void loadBorrowingsData() {
        try {
            pstmt = conn.prepareStatement("SELECT * FROM borrowings");
            rs = pstmt.executeQuery();

            // Clear existing data
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            // Add new data
            while (rs.next()) {
                Object[] rowData = {
                        rs.getInt("borrowing_id"),
                        rs.getInt("student_id"),
                        rs.getInt("book_id"),
                        rs.getDate("borrow_date"),
                        rs.getDate("return_date")
                };
                model.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getNextBorrowingId() {
        int nextId = 0;
        try {
            pstmt = conn.prepareStatement("SELECT MAX(borrowing_id) AS max_id FROM borrowings");
            rs = pstmt.executeQuery();
            if (rs.next()) {
                nextId = rs.getInt("max_id") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextId;
    }
    private void loadStudentsDataToComboBox() {
        try {
            pstmt = conn.prepareStatement("SELECT student_id, student_name FROM students");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                studentComboBox.addItem(rs.getInt("student_id") + " - " + rs.getString("student_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadBooksDataToComboBox() {
        try {
            pstmt = conn.prepareStatement("SELECT book_id, title FROM books");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                bookComboBox.addItem(rs.getInt("book_id") + " - " + rs.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void addBorrowing() {
        // Load data into combo boxes
        loadStudentsDataToComboBox();
        loadBooksDataToComboBox();
        
        // Show dialog to add a borrowing
        JTextField borrowDateField = new JTextField();
        JTextField returnDateField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Borrow Date (YYYY-MM-DD):"));
        panel.add(borrowDateField);
        panel.add(new JLabel("Return Date (YYYY-MM-DD):"));
        panel.add(returnDateField);

        panel.add(new JLabel("Student ID:"));
        panel.add(studentComboBox);
        panel.add(new JLabel("Book ID:"));
        panel.add(bookComboBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Borrowing", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int nextId = getNextBorrowingId();
                int selectedStudentId = Integer.parseInt(studentComboBox.getSelectedItem().toString().split(" - ")[0]);
                int selectedBookId = Integer.parseInt(bookComboBox.getSelectedItem().toString().split(" - ")[0]);
                pstmt = conn.prepareStatement("INSERT INTO borrowings (borrowing_id, student_id, book_id, borrow_date, return_date) VALUES (?, ?, ?, ?, ?)");
                pstmt.setInt(1, nextId);
                pstmt.setInt(2, selectedStudentId);
                pstmt.setInt(3, selectedBookId);
                pstmt.setDate(4, java.sql.Date.valueOf(borrowDateField.getText()));
                pstmt.setDate(5, java.sql.Date.valueOf(returnDateField.getText()));
                pstmt.executeUpdate();

                loadBorrowingsData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BorrowingManagementStudent();
            }
        });
    }
}
