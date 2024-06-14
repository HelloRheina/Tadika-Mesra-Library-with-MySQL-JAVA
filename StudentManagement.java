package tadikamesra;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentManagement extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private JButton addButton, editButton, deleteButton;
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public StudentManagement() {
        setTitle("Student Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Table
        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        model.addColumn("Student ID");
        model.addColumn("Name");
        model.addColumn("Grade");
        model.addColumn("Date of Birth");
        model.addColumn("Contact Number");
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Connect to database
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_library", "root", "");
            loadStudentsData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editStudent();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });

        setVisible(true);
    }

    private void loadStudentsData() {
        try {
            pstmt = conn.prepareStatement("SELECT * FROM students");
            rs = pstmt.executeQuery();

            // Clear existing data
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            // Add new data
            while (rs.next()) {
                Object[] rowData = {
                        rs.getInt("student_id"),
                        rs.getString("student_name"),
                        rs.getString("grade"),
                        rs.getDate("dob"),
                        rs.getString("contact_number")
                };
                model.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getNextStudentId() {
        int nextId = 0;
        try {
            pstmt = conn.prepareStatement("SELECT MAX(student_id) AS max_id FROM students");
            rs = pstmt.executeQuery();
            if (rs.next()) {
                nextId = rs.getInt("max_id") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextId;
    }

    private void addStudent() {
        // Show dialog to add a student
        JTextField nameField = new JTextField();
        JTextField gradeField = new JTextField();
        JTextField dobField = new JTextField();
        JTextField contactField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Grade:"));
        panel.add(gradeField);
        panel.add(new JLabel("Date of Birth:"));
        panel.add(dobField);
        panel.add(new JLabel("Contact Number:"));
        panel.add(contactField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Student", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int nextId = getNextStudentId();
                pstmt = conn.prepareStatement("INSERT INTO students (student_id, student_name, grade, dob, contact_number) VALUES (?, ?, ?, ?, ?)");
                pstmt.setInt(1, nextId);
                pstmt.setString(2, nameField.getText());
                pstmt.setString(3, gradeField.getText());
                pstmt.setDate(4, java.sql.Date.valueOf(dobField.getText()));
                pstmt.setString(5, contactField.getText());
                pstmt.executeUpdate();

                loadStudentsData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void editStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a student to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Show dialog to edit a student
        JTextField nameField = new JTextField(model.getValueAt(selectedRow, 1).toString());
        JTextField gradeField = new JTextField(model.getValueAt(selectedRow, 2).toString());
        JTextField dobField = new JTextField(model.getValueAt(selectedRow, 3).toString());
        JTextField contactField = new JTextField(model.getValueAt(selectedRow, 4).toString());

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Grade:"));
        panel.add(gradeField);
        panel.add(new JLabel("Date of Birth:"));
        panel.add(dobField);
        panel.add(new JLabel("Contact Number:"));
        panel.add(contactField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Edit Student", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                pstmt = conn.prepareStatement("UPDATE students SET student_name=?, grade=?, dob=?, contact_number=? WHERE student_id=?");
                pstmt.setString(1, nameField.getText());
                pstmt.setString(2, gradeField.getText());
                pstmt.setDate(3, java.sql.Date.valueOf(dobField.getText()));
                pstmt.setString(4, contactField.getText());
                pstmt.setInt(5, (int) model.getValueAt(selectedRow, 0));
                pstmt.executeUpdate();

                loadStudentsData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteStudent() {
        // Get selected row
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a student to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Confirm deletion
        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this student?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            try {
                pstmt = conn.prepareStatement("DELETE FROM students WHERE student_id=?");
                pstmt.setInt(1, (int) model.getValueAt(selectedRow, 0));
                pstmt.executeUpdate();

                loadStudentsData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentManagement();
            }
        });
    }
}
