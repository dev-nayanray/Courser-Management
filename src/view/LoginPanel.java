package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import util.UIStyleUtil;

/**
 * Basic login panel for admin authentication.
 */
public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton logoutButton;

    public LoginPanel() {
        setLayout(new GridBagLayout());
        UIStyleUtil.stylePanel(this);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel usernameLabel = new JLabel("Username:");
        UIStyleUtil.styleLabel(usernameLabel);
        usernameField = new JTextField(15);
        UIStyleUtil.styleTextField(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        UIStyleUtil.styleLabel(passwordLabel);
        passwordField = new JPasswordField(15);
        UIStyleUtil.stylePasswordField(passwordField);

        loginButton = new JButton("Login");
        UIStyleUtil.styleButton(loginButton);
        logoutButton = new JButton("Logout");
        UIStyleUtil.styleButton(logoutButton);
        logoutButton.setEnabled(false);

        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        add(usernameLabel, gbc);
        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(loginButton, gbc);
        gbc.gridx = 1;
        add(logoutButton, gbc);
    }

    public String getUsername() {
        return usernameField.getText().trim();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
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
}
