import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

class User {

    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}

class WelcomePage extends JFrame {

    private final Color backgroundPink = new Color(255, 225, 237);
    private final Color darkPink = new Color(213, 63, 119);
    private final Color textColor = new Color(105, 45, 70);

    public WelcomePage(User user) {
        setTitle("Welcome");
        setSize(420, 280);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(backgroundPink);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(30, 25, 30, 25));

        JLabel title = new JLabel("Welcome!");
        title.setFont(new Font("SansSerif", Font.BOLD, 30));
        title.setForeground(darkPink);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel hello = new JLabel(
                "Hello, " + user.getUsername() + "!"
        );
        hello.setFont(new Font("SansSerif", Font.BOLD, 21));
        hello.setForeground(textColor);
        hello.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel role = new JLabel(
                "Your Role: " + user.getRole()
        );
        role.setFont(new Font("SansSerif", Font.PLAIN, 17));
        role.setForeground(textColor);
        role.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(25));
        mainPanel.add(hello);
        mainPanel.add(Box.createVerticalStrut(12));
        mainPanel.add(role);

        add(mainPanel);
        setVisible(true);
    }
}

public class LoginFormTask extends JFrame {

    private final List<User> usersList = new ArrayList<>();
    private int failedAttempts = 0;

    private final Color backgroundPink = new Color(255, 225, 237);
    private final Color darkPink = new Color(213, 63, 119);
    private final Color lightPink = new Color(255, 248, 251);
    private final Color textColor = new Color(105, 45, 70);
    private final Color borderPink = new Color(245, 180, 205);

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleBox;
    private JLabel messageLabel;
    private JButton loginButton;

    public LoginFormTask() {
        setTitle("Pink Login");
        setSize(500, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        loadUsers();

        // Main background
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(backgroundPink);
        mainPanel.setBorder(new EmptyBorder(25, 30, 25, 30));

        // White login card
        JPanel card = new JPanel(new BorderLayout(15, 20));
        card.setBackground(Color.WHITE);
        card.setBorder(new CompoundBorder(
                new LineBorder(borderPink, 2, true),
                new EmptyBorder(25, 25, 25, 25)
        ));

        // Header
        JPanel header = new JPanel();
        header.setBackground(Color.WHITE);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Welcome ♡");
        title.setFont(new Font("SansSerif", Font.BOLD, 30));
        title.setForeground(darkPink);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Please log in to continue");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitle.setForeground(textColor);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(title);
        header.add(Box.createVerticalStrut(8));
        header.add(subtitle);

        card.add(header, BorderLayout.NORTH);

        // Form
        JPanel form = new JPanel(new GridLayout(3, 1, 0, 12));
        form.setBackground(Color.WHITE);

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        roleBox = new JComboBox<>(
                new String[]{"Student", "TA", "Engineer"}
        );

        styleField(usernameField);
        styleField(passwordField);
        styleComboBox(roleBox);

        form.add(createFieldPanel("Username", usernameField));
        form.add(createFieldPanel("Password", passwordField));
        form.add(createFieldPanel("Role", roleBox));

        card.add(form, BorderLayout.CENTER);

        // Bottom section
        JPanel bottom = new JPanel();
        bottom.setBackground(Color.WHITE);
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));

        messageLabel = new JLabel(" ");
        messageLabel.setForeground(darkPink);
        messageLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel(new GridLayout(1, 2, 12, 0));
        buttons.setBackground(Color.WHITE);

        JButton cancelButton = new JButton("Cancel");
        loginButton = new JButton("Login ♡");

        styleButton(loginButton, darkPink, Color.WHITE);
        styleButton(cancelButton, lightPink, darkPink);

        buttons.add(cancelButton);
        buttons.add(loginButton);

        bottom.add(messageLabel);
        bottom.add(Box.createVerticalStrut(10));
        bottom.add(buttons);

        card.add(bottom, BorderLayout.SOUTH);

        mainPanel.add(card);
        add(mainPanel);

        // Login action
        loginButton.addActionListener(e -> login());

        // Enter key submits the form
        getRootPane().setDefaultButton(loginButton);

        // Cancel action
        cancelButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    private JPanel createFieldPanel(String labelText, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout(0, 6));
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setForeground(textColor);

        panel.add(label, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);

        return panel;
    }

    private void styleField(JTextField field) {
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setForeground(textColor);
        field.setBackground(lightPink);
        field.setCaretColor(darkPink);
        field.setBorder(new CompoundBorder(
                new LineBorder(borderPink, 1, true),
                new EmptyBorder(8, 10, 8, 10)
        ));
    }

    private void styleComboBox(JComboBox<String> combo) {
        combo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        combo.setForeground(textColor);
        combo.setBackground(lightPink);
        combo.setBorder(new LineBorder(borderPink, 1, true));
    }

    private void styleButton(
            JButton button,
            Color background,
            Color foreground
    ) {
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBorder(new CompoundBorder(
                new LineBorder(borderPink, 1, true),
                new EmptyBorder(10, 15, 10, 15)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void login() {

        if (failedAttempts >= 3) {
            return;
        }

        String inputUser = usernameField.getText().trim();
        String inputPass = new String(passwordField.getPassword());
        String selectedRole = (String) roleBox.getSelectedItem();

        if (inputUser.isEmpty() || inputPass.isEmpty()) {
            messageLabel.setText("Please fill in all fields!");
            return;
        }

        User foundUser = findUser(inputUser);

        if (foundUser != null
                && foundUser.getPassword().equals(inputPass)
                && foundUser.getRole().equals(selectedRole)) {

            dispose();
            new WelcomePage(foundUser);

        } else {

            failedAttempts++;

            if (failedAttempts >= 3) {
                loginButton.setEnabled(false);
                messageLabel.setText(
                        "Account locked after 3 failed attempts."
                );
            } else {
                messageLabel.setText(
                        "Invalid login! Attempts left: "
                                + (3 - failedAttempts)
                );
            }
        }
    }

    private void loadUsers() {
        usersList.add(new User("farida", "4747", "Student"));
        usersList.add(new User("deda", "4705", "TA"));
        usersList.add(new User("faridarizk", "1347", "Engineer"));
    }

    private User findUser(String name) {
        for (User user : usersList) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginFormTask::new);
    }
}
