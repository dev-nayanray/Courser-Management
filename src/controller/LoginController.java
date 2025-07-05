
package controller;

import view.LoginPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for handling basic admin-only login with hardcoded credentials and logout functionality.
 */
public class LoginController {
    private LoginPanel view;

    private String loggedInUser;
    private String loggedInRole;

    private Runnable loginSuccessCallback;

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    public LoginController(LoginPanel view) {
        this.view = view;
        this.view.addLoginListener(new LoginListener());
        this.view.addLogoutListener(new LogoutListener());
        this.view.setLogoutEnabled(false);
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

            if (!ADMIN_USERNAME.equals(username) || !ADMIN_PASSWORD.equals(password)) {
                JOptionPane.showMessageDialog(view, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            loggedInUser = username;
            loggedInRole = "admin";

            JOptionPane.showMessageDialog(view, "Login successful! Welcome " + loggedInUser + ".", "Success", JOptionPane.INFORMATION_MESSAGE);

            view.setLoginEnabled(false);
            view.setLogoutEnabled(true);

            if (loginSuccessCallback != null) {
                loginSuccessCallback.run();
            }
        }
    }

    private class LogoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loggedInUser = null;
            loggedInRole = null;
            view.clearFields();
            view.setLoginEnabled(true);
            view.setLogoutEnabled(false);
            JOptionPane.showMessageDialog(view, "Logged out successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
