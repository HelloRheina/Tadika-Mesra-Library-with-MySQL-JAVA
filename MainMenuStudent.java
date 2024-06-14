package tadikamesra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenuStudent extends JFrame {
    public MainMenuStudent() {
        setTitle("Main Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 1));
        
        JButton borrowingButton = new JButton("Borrowing");
        borrowingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openBorrowingManagement();
            }
        });
        panel.add(borrowingButton);

        JButton confirmationButton = new JButton("Confirmation");
        confirmationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openConfirmationManagement();
            }
        });
        panel.add(confirmationButton);

        JButton renewalButton = new JButton("Renewal");
        renewalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openRenewalManagement();
            }
        });
        panel.add(renewalButton);


        JButton bookButton = new JButton("Book List");
        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openBookList();
            }
        });
        panel.add(bookButton);

        add(panel);
        setVisible(true);
    }

    private void openBorrowingManagement() {
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BorrowingManagementStudent();
            }
        });
    }

    private void openConfirmationManagement() {
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ConfirmationManagementStudent();
            }
        });
    }

    private void openRenewalManagement() {
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RenewalManagementStudent();
            }
        });
    }


    private void openBookList() {
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BookList();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainMenuStudent();
            }
        });
    }
}
