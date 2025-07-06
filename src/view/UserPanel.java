package view;

import util.UIStyleUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Modern user panel for login with improved UI.
 */
import view.LoginView;

public class UserPanel extends JPanel implements LoginView {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton logoutButton;

    public UserPanel() {
        setLayout(new GridBagLayout());
        UIStyleUtil.stylePanel(this);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("User Login");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(UIStyleUtil.PRIMARY_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        UIStyleUtil.styleTextField(usernameField);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        UIStyleUtil.styleTextField(passwordField);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(passwordField, gbc);

        loginButton = new JButton("Login");
        UIStyleUtil.styleButton(loginButton);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(loginButton, gbc);

        logoutButton = new JButton("Logout");
        UIStyleUtil.styleButton(logoutButton);
        logoutButton.setEnabled(false);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(logoutButton, gbc);
    }

    public String getUsername() {
        return usernameField.getText().trim();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
    }

    public void setLoginEnabled(boolean enabled) {
        loginButton.setEnabled(enabled);
    }

    public void setLogoutEnabled(boolean enabled) {
        logoutButton.setEnabled(enabled);
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }
}
