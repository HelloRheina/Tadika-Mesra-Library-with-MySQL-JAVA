package tadikamesra;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class BookList extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private Connection conn;

    public BookList() {
        setTitle("Book List");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Table
        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);

        // Connect to database and load book data
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_library", "root", "");
            loadBookData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    private void loadBookData() {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM books");
            ResultSet rs = pstmt.executeQuery();

            // Set table columns
            model.addColumn("Book ID");
            model.addColumn("Title");
            model.addColumn("Author");
            model.addColumn("Publication Year");
            model.addColumn("Availability Status");

            // Clear existing data
            model.setRowCount(0);

            // Add new data
            while (rs.next()) {
                Object[] rowData = {
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publication_year"),
                        rs.getString("availability_status")
                };
                model.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BookList();
            }
        });
    }
}
