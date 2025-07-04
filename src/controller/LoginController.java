package controller;

import view.LoginPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller for handling login and access control.
 * Implements basic password authentication and role-based access.
 */
public class LoginController {
    private LoginPanel view;
    private Map<String, String> userPasswordMap; // username -> hashed password
    private Map<String, String> userRoleMap; // username -> role

    private String loggedInUser;
    private String loggedInRole;

    private Runnable loginSuccessCallback;

    public LoginController(LoginPanel view) {
        this.view = view;
        this.userPasswordMap = new HashMap<>();
        this.userRoleMap = new HashMap<>();

        // For demo, add a default admin user with password "admin"
        // Passwords should be hashed in real applications; here plain text for simplicity
        userPasswordMap.put("admin", "admin");
        userRoleMap.put("admin", "admin");

        // Add a sample user
        userPasswordMap.put("user", "user123");
        userRoleMap.put("user", "user");

        this.view.addLoginListener(new LoginListener());
    }

    public void setLoginSuccessCallback(Runnable callback) {
        this.loginSuccessCallback = callback;
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public String getLoggedInRole() {
        return loggedInRole;
    }

    private class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsername();
            String password = view.getPassword();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please enter username and password.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!userPasswordMap.containsKey(username)) {
                JOptionPane.showMessageDialog(view, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String storedPassword = userPasswordMap.get(username);
            if (!storedPassword.equals(password)) {
                JOptionPane.showMessageDialog(view, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            loggedInUser = username;
            loggedInRole = userRoleMap.get(username);

            JOptionPane.showMessageDialog(view, "Login successful! Welcome " + loggedInUser + ".", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Notify other components or enable/disable panels based on role
            // This can be extended to fire events or callbacks
            if (loginSuccessCallback != null) {
                loginSuccessCallback.run();
            }
        }
    }
}
