package tadikamesra;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RenewalManagement extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private JButton addButton, editButton, deleteButton;
    private JComboBox<String> borrowingComboBox;
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public RenewalManagement() {
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

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editRenewal();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteRenewal();
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

    private void editRenewal() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a renewal to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Show dialog to edit a renewal
        JTextField renewalDateField = new JTextField(model.getValueAt(selectedRow, 2).toString());
        borrowingComboBox = new JComboBox<>();
        loadBorrowingComboBoxData();
        borrowingComboBox.setSelectedItem(String.valueOf(model.getValueAt(selectedRow, 1)));

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Borrowing ID:"));
        panel.add(borrowingComboBox);
        panel.add(new JLabel("Renewal Date (YYYY-MM-DD):"));
        panel.add(renewalDateField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Edit Renewal", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                pstmt = conn.prepareStatement("UPDATE renewals SET borrowing_id=?, renewal_date=? WHERE renewal_id=?");
                pstmt.setInt(1, Integer.parseInt((String) borrowingComboBox.getSelectedItem()));
                pstmt.setDate(2, java.sql.Date.valueOf(renewalDateField.getText()));
                pstmt.setInt(3, (int) model.getValueAt(selectedRow, 0));
                pstmt.executeUpdate();

                loadRenewalsData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteRenewal() {
        // Get selected row
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a renewal to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Confirm deletion
        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this renewal?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            try {
                pstmt = conn.prepareStatement("DELETE FROM renewals WHERE renewal_id=?");
                pstmt.setInt(1, (int) model.getValueAt(selectedRow, 0));
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
                new RenewalManagement();
            }
        });
    }
}
