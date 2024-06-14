package tadikamesra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenuAdmin extends JFrame {
    public MainMenuAdmin() {
        setTitle("Main Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 1));
        
        JButton borrowingButton = new JButton("Borrowing Management");
        borrowingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openBorrowingManagement();
            }
        });
        panel.add(borrowingButton);

        JButton confirmationButton = new JButton("Confirmation Management");
        confirmationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openConfirmationManagement();
            }
        });
        panel.add(confirmationButton);

        JButton renewalButton = new JButton("Renewal Management");
        renewalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openRenewalManagement();
            }
        });
        panel.add(renewalButton);

        JButton studentButton = new JButton("Student Management");
        studentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openStudentManagement();
            }
        });
        panel.add(studentButton);

        JButton bookButton = new JButton("Book Management");
        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openBookManagement();
            }
        });
        panel.add(bookButton);

        add(panel);
        setVisible(true);
    }

    private void openBorrowingManagement() {
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BorrowingManagement();
            }
        });
    }

    private void openConfirmationManagement() {
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ConfirmationManagement();
            }
        });
    }

    private void openRenewalManagement() {
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RenewalManagement();
            }
        });
    }

    private void openStudentManagement() {
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentManagement();
            }
        });
    }

    private void openBookManagement() {
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BookManagement();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainMenuAdmin();
            }
        });
    }
}
