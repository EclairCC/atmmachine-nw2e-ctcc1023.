import javax.swing.*;
import java.awt.*;

public class ATMScreen extends JFrame {
    private JButton checkBalanceButton;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton logoutButton;
    private JLabel balanceLabel;
    private User loggedInUser;

    public ATMScreen(User user) {
        this.loggedInUser = user; // Set the logged-in user
        setTitle("ATM");
        setLayout(null);
        setSize(750, 350); // Adjusted size for consistency

        // Load the image
        ImageIcon backgroundIcon = new ImageIcon("src/resources/bank.jpg"); // Change path as needed
        Image backgroundImage = backgroundIcon.getImage();

        // Create a custom JPanel to draw the background
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image scaled to fit the panel
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Set the layout of the panel to null for absolute positioning
        panel.setLayout(null);

        // Add centered title text
        JLabel titleLabel = new JLabel("ATM SYSTEM - WELCOME");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size and style
        titleLabel.setBounds(200, 10, 350, 40); // Center title horizontally at the top
        titleLabel.setForeground(Color.WHITE); // Set the text color to white
        panel.add(titleLabel);


        // Add buttons below the balance label
        checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.setBounds(50, 120, 200, 30);
        checkBalanceButton.addActionListener(e -> showBalance());
        panel.add(checkBalanceButton);

        depositButton = new JButton("Deposit");
        depositButton.setBounds(50, 160, 200, 30);
        depositButton.addActionListener(e -> depositAction());
        panel.add(depositButton);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(50, 200, 200, 30);
        withdrawButton.addActionListener(e -> withdrawAction());
        panel.add(withdrawButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(50, 240, 200, 30);
        logoutButton.addActionListener(e -> logoutAction());
        panel.add(logoutButton);

        // Set the panel as the content pane of the JFrame
        setContentPane(panel);

        // Center the JFrame on the screen
        setLocationRelativeTo(null);

        // Set default close operation and visibility
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void showBalance() {
        JOptionPane.showMessageDialog(this, "Your balance is: PHP" + loggedInUser.getBalance());
    }

    private void depositAction() {
        String amountStr = JOptionPane.showInputDialog("Enter amount to deposit:");
        try {
            double amount = Double.parseDouble(amountStr);
            loggedInUser.setBalance(loggedInUser.getBalance() + amount);
            balanceLabel.setText("Balance: PHP" + loggedInUser.getBalance());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount!");
        }
    }

    private void withdrawAction() {
        String amountStr = JOptionPane.showInputDialog("Enter amount to withdraw:");
        try {
            double amount = Double.parseDouble(amountStr);
            if (loggedInUser.getBalance() >= amount) {
                loggedInUser.setBalance(loggedInUser.getBalance() - amount);
                balanceLabel.setText("Balance: PHP" + loggedInUser.getBalance());
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient funds!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount!");
        }
    }

    private void logoutAction() {
        new LoginScreen(); // Go back to login screen
        this.setVisible(false);
    }
}
