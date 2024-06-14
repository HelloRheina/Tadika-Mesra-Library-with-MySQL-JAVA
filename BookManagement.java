package tadikamesra;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class BookManagement extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private JButton addButton, editButton, deleteButton;
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public BookManagement() {
        setTitle("Book Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Table
        model = new DefaultTableModel();
        table = new JTable(model);
        model.addColumn("Book ID");
        model.addColumn("Title");
        model.addColumn("Author");
        model.addColumn("Publication Year");
        model.addColumn("Availability Status");
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
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
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editBook();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });

        setVisible(true);
    }

    private void loadData() {
        try {
            pstmt = conn.prepareStatement("SELECT * FROM books");
            rs = pstmt.executeQuery();

            // Clear existing data
            model.setRowCount(0);
            // Add new data
            while (rs.next()) {
                Object[] rowData = {
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("publication_year"),
                        rs.getBoolean("availability_status")
                };
                model.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addBook() {
        // Show dialog to add a book
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField yearField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Author:"));
        panel.add(authorField);
        panel.add(new JLabel("Year:"));
        panel.add(yearField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Book", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                // Get the latest book_id
                pstmt = conn.prepareStatement("SELECT MAX(book_id) AS max_id FROM books");
                rs = pstmt.executeQuery();
                int latestBookId = 0;
                if (rs.next()) {
                    latestBookId = rs.getInt("max_id");
                }

                // Add 1 to get the new book_id
                int newBookId = latestBookId + 1;

                pstmt = conn.prepareStatement("INSERT INTO books (book_id, title, author, publication_year, availability_status) VALUES (?, ?, ?, ?, true)");
                pstmt.setInt(1, newBookId);
                pstmt.setString(2, titleField.getText());
                pstmt.setString(3, authorField.getText());
                pstmt.setInt(4, Integer.parseInt(yearField.getText()));
                pstmt.executeUpdate();

                loadData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void editBook() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a book to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Show dialog to edit a book
        JTextField titleField = new JTextField(model.getValueAt(selectedRow, 1).toString());
        JTextField authorField = new JTextField(model.getValueAt(selectedRow, 2).toString());
        JTextField yearField = new JTextField(model.getValueAt(selectedRow, 3).toString());

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Author:"));
        panel.add(authorField);
        panel.add(new JLabel("Year:"));
        panel.add(yearField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Edit Book", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                pstmt = conn.prepareStatement("UPDATE books SET title=?, author=?, publication_year=? WHERE book_id=?");
                pstmt.setString(1, titleField.getText());
                pstmt.setString(2, authorField.getText());
                pstmt.setInt(3, Integer.parseInt(yearField.getText()));
                pstmt.setInt(4, (int) model.getValueAt(selectedRow, 0));
                pstmt.executeUpdate();

                loadData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteBook() {
        // Get selected row
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a book to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Confirm deletion
        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this book?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            try {
                pstmt = conn.prepareStatement("DELETE FROM books WHERE book_id=?");
                pstmt.setInt(1, (int) model.getValueAt(selectedRow, 0));
                pstmt.executeUpdate();

                loadData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BookManagement();
            }
        });
    }
}
