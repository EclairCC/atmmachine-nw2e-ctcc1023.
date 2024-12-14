import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LoginScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JButton exitButton; // Exit button

    // Store users in memory
    private static ArrayList<User> users = new ArrayList<>();

    public LoginScreen() {
        setTitle("ATM Login");
        setLayout(null);
        setSize(750, 350); // Increase frame size

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
        JLabel titleLabel = new JLabel("ATM SYSTEM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size and style
        titleLabel.setBounds(280, 10, 200, 40); // Center title horizontally at the top
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
        loginButton = new JButton("Login");
        loginButton.setBounds(20, 180, 100, 30); // Place login button below password
        loginButton.addActionListener(e -> loginAction());
        panel.add(loginButton);
        
        registerButton = new JButton("Register");
        registerButton.setBounds(130, 180, 100, 30); // Place register button next to login
        registerButton.addActionListener(e -> registerAction());
        panel.add(registerButton);
        
        exitButton = new JButton("Exit");
        exitButton.setBounds(240, 180, 100, 30); // Place exit button next to register
        exitButton.addActionListener(e -> exitAction());
        panel.add(exitButton);

        // Set the panel as the content pane of the JFrame
        setContentPane(panel);

        // Center the JFrame on the screen
        setLocationRelativeTo(null);

        // Set default close operation and visibility
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void loginAction() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Validate login
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                // Show "Thanks for logging in" as a toast message
                showToast("Thanks for logging in!");

                // Open ATM screen with user
                new ATMScreen(user); 
                this.setVisible(false); // Close login screen
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Invalid username or password!");
    }

    private void showToast(String message) {
        // Create a temporary label to show the toast message
        JLabel toastLabel = new JLabel(message);
        toastLabel.setFont(new Font("Arial", Font.BOLD, 14));
        toastLabel.setForeground(Color.WHITE);
        toastLabel.setBackground(new Color(0, 0, 0, 150)); // Semi-transparent background
        toastLabel.setOpaque(true);
        toastLabel.setHorizontalAlignment(SwingConstants.CENTER);
        toastLabel.setBounds(250, 250, 250, 40); // Positioning the toast message
        
        // Add the toast label to the frame
        this.add(toastLabel);
        
        // Timer to automatically remove the toast after 2 seconds
        Timer timer = new Timer(2000, e -> {
            this.remove(toastLabel); // Remove the toast label
            this.revalidate();
            this.repaint();
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void registerAction() {
        new RegistrationScreen(this); // Open registration screen
        this.setVisible(false); // Close login screen
    }

    private void exitAction() {
        // Close the application when "Exit" button is clicked
        System.exit(0);
    }

    public static void main(String[] args) {
        new LoginScreen();
    }

    // Method to add a new user
    public static void addUser(User user) {
        users.add(user);
    }
}
