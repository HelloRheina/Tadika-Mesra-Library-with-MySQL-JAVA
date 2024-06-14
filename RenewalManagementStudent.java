package tadikamesra;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RenewalManagementStudent extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private JButton addButton;
    private JComboBox<String> borrowingComboBox;
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public RenewalManagementStudent() {
        setTitle("Renewal Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Table
        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        model.addColumn("Renewal ID");
        model.addColumn("Borrowing ID");
        model.addColumn("Renewal Date");
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addButton = new JButton("Add");

        buttonPanel.add(addButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Connect to database
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_library", "root", "");
            loadRenewalsData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addRenewal();
            }
        });

        setVisible(true);
    }

    private void loadRenewalsData() {
        try {
            pstmt = conn.prepareStatement("SELECT * FROM renewals");
            rs = pstmt.executeQuery();

            // Clear existing data
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            // Add new data
            while (rs.next()) {
                Object[] rowData = {
                        rs.getInt("renewal_id"),
                        rs.getInt("borrowing_id"),
                        rs.getDate("renewal_date")
                };
                model.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getNextRenewalId() {
        int nextId = 0;
        try {
            pstmt = conn.prepareStatement("SELECT MAX(renewal_id) AS max_id FROM renewals");
            rs = pstmt.executeQuery();
            if (rs.next()) {
                nextId = rs.getInt("max_id") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextId;
    }

    private void addRenewal() {
        // Show dialog to add a renewal
        JTextField renewalDateField = new JTextField();
        borrowingComboBox = new JComboBox<>();
        loadBorrowingComboBoxData();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Borrowing ID:"));
        panel.add(borrowingComboBox);
        panel.add(new JLabel("Renewal Date (YYYY-MM-DD):"));
        panel.add(renewalDateField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Renewal", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int nextId = getNextRenewalId();
                pstmt = conn.prepareStatement("INSERT INTO renewals (renewal_id, borrowing_id, renewal_date) VALUES (?, ?, ?)");
                pstmt.setInt(1, nextId);
                pstmt.setInt(2, Integer.parseInt((String) borrowingComboBox.getSelectedItem()));
                pstmt.setDate(3, java.sql.Date.valueOf(renewalDateField.getText()));
                pstmt.executeUpdate();

                loadRenewalsData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private void loadBorrowingComboBoxData() {
        try {
            pstmt = conn.prepareStatement("SELECT borrowing_id FROM borrowings");
            rs = pstmt.executeQuery();

            // Clear existing items
            borrowingComboBox.removeAllItems();

            // Add new items
            while (rs.next()) {
                borrowingComboBox.addItem(String.valueOf(rs.getInt("borrowing_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RenewalManagementStudent();
            }
        });
    }
}
