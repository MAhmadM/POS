package GUI;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {

    public LoginGUI() {
        setTitle("Pharmacy Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        // Heading Panel
        JPanel headingPanel = createHeadingPanel();

        // Input Panel
        JPanel inputPanel = createInputPanel();

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(headingPanel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        add(mainPanel);
        setResizable(false);
    }

    private JPanel createHeadingPanel() {
        JPanel headingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel headingLabel = new JLabel("Pharmacy Login");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingPanel.add(headingLabel);

        return headingPanel;
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridBagLayout()); // Using GridBagLayout for more precise control
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components

        JLabel welcomeLabel = new JLabel("Welcome to our pharmacy login page.");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span two columns
        inputPanel.add(welcomeLabel, gbc);

        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Reset gridwidth
        inputPanel.add(usernameLabel, gbc);

        JTextField usernameTextField = new JTextField(15); // Adjusted size of text field
        gbc.gridx = 1;
        inputPanel.add(usernameTextField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(passwordLabel, gbc);

        JPasswordField passwordTextField = new JPasswordField(15); // Adjusted size of password field
        gbc.gridx = 1;
        inputPanel.add(passwordTextField, gbc);

        JButton loginButton = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 5, 5, 5); // Adjusted padding for the button
        inputPanel.add(loginButton, gbc);

        return inputPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginGUI login = new LoginGUI();
            login.setVisible(true);
        });
    }
}

