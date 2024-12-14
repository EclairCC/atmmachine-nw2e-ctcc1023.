import javax.swing.*;
import java.awt.*;

public class RegistrationScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton backButton;
    private LoginScreen loginScreen;

    public RegistrationScreen(LoginScreen loginScreen) {
        this.loginScreen = loginScreen;

        setTitle("Register");
        setLayout(null);
        setSize(750, 350); // Same frame size as login screen

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
        JLabel titleLabel = new JLabel("ATM SYSTEM - REGISTRATION");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size and style
        titleLabel.setBounds(200, 10, 350, 40); // Center title horizontally at the top
        titleLabel.setForeground(Color.WHITE); // Set the text color to white
        panel.add(titleLabel);

        // Add components for username and password
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20, 80, 100, 30); // Set position for username label
        usernameLabel.setForeground(Color.WHITE); // Set the text color to white
        panel.add(usernameLabel);
        
        usernameField = new JTextField();
        usernameField.setBounds(120, 80, 150, 30); // Keep username field aligned
        panel.add(usernameField);
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 130, 100, 30); // Set position for password label
        passwordLabel.setForeground(Color.WHITE); // Set the text color to white
        panel.add(passwordLabel);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(120, 130, 150, 30); // Keep password field aligned
        panel.add(passwordField);
        
        // Buttons below password field
        registerButton = new JButton("Register");
        registerButton.setBounds(50, 180, 100, 30); // Place register button below password
        registerButton.addActionListener(e -> registerUser());
        panel.add(registerButton);
        
        backButton = new JButton("Back");
        backButton.setBounds(170, 180, 100, 30); // Place back button next to register
        backButton.addActionListener(e -> backToLogin());
        panel.add(backButton);

        // Set the panel as the content pane of the JFrame
        setContentPane(panel);

        // Center the JFrame on the screen
        setLocationRelativeTo(null);

        // Set default close operation and visibility
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void registerUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Validate input
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.");
            return;
        }

        // Create and store the new user
        User newUser = new User(username, password);
        LoginScreen.addUser(newUser);

        JOptionPane.showMessageDialog(this, "User registered successfully!");
        new LoginScreen(); // Go back to login screen
        this.setVisible(false); // Close registration screen
    }

    private void backToLogin() {
        new LoginScreen(); // Go back to login screen
        this.setVisible(false); // Close registration screen
    }
}
