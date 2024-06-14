package tadikamesra;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ConfirmationManagementStudent extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private JButton addButton;
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private JComboBox<String> borrowingComboBox; // ComboBox for borrowing IDs

    public ConfirmationManagementStudent() {
        setTitle("Confirmation Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Table
        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        model.addColumn("Confirmation ID");
        model.addColumn("Borrowing ID");
        model.addColumn("Confirmation Type");
        model.addColumn("Confirmation Date");
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addButton = new JButton("Add");

        buttonPanel.add(addButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Connect to database
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_library", "root", "");
            loadConfirmationsData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addConfirmation();
            }
        });

        setVisible(true);
    }

    private void loadConfirmationsData() {
        try {
            pstmt = conn.prepareStatement("SELECT * FROM confirmations");
            rs = pstmt.executeQuery();

            // Clear existing data
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            // Add new data
            while (rs.next()) {
                Object[] rowData = {
                        rs.getInt("confirmation_id"),
                        rs.getInt("borrowing_id"),
                        rs.getString("confirmation_type"),
                        rs.getDate("confirmation_date")
                };
                model.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getNextConfirmationId() {
        int nextId = 0;
        try {
            pstmt = conn.prepareStatement("SELECT MAX(confirmation_id) AS max_id FROM confirmations");
            rs = pstmt.executeQuery();
            if (rs.next()) {
                nextId = rs.getInt("max_id") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextId;
    }

    // Method to load borrowing IDs from the database
    private void loadBorrowingIds() {
        try {
            pstmt = conn.prepareStatement("SELECT borrowing_id FROM borrowings");
            rs = pstmt.executeQuery();
            borrowingComboBox.removeAllItems(); // Clear existing items
            while (rs.next()) {
                borrowingComboBox.addItem(String.valueOf(rs.getInt("borrowing_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addConfirmation() {
        // Show dialog to add a confirmation
        JTextField confirmationTypeField = new JTextField();
        JTextField confirmationDateField = new JTextField();
        
        // Initialize borrowingComboBox
        borrowingComboBox = new JComboBox<>();

        // Load borrowing IDs
        loadBorrowingIds();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Borrowing ID:"));
        panel.add(borrowingComboBox);
        panel.add(new JLabel("Confirmation Type:"));
        panel.add(confirmationTypeField);
        panel.add(new JLabel("Confirmation Date (YYYY-MM-DD):"));
        panel.add(confirmationDateField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Confirmation", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int nextId = getNextConfirmationId();
                pstmt = conn.prepareStatement("INSERT INTO confirmations (confirmation_id, borrowing_id, confirmation_type, confirmation_date) VALUES (?, ?, ?, ?)");
                pstmt.setInt(1, nextId);
                pstmt.setInt(2, Integer.parseInt((String) borrowingComboBox.getSelectedItem()));
                pstmt.setString(3, confirmationTypeField.getText());
                pstmt.setDate(4, java.sql.Date.valueOf(confirmationDateField.getText()));
                pstmt.executeUpdate();

                loadConfirmationsData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ConfirmationManagementStudent();
            }
        });
    }
}

