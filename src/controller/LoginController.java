
package controller;

import view.LoginView;
import database.UserDatabaseHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Controller for handling login with database authentication and logout functionality.
 */
public class LoginController {
    private LoginView view;

    private String loggedInUser;
    private String loggedInRole;

    private Runnable loginSuccessCallback;

    private UserDatabaseHelper userDbHelper;

    public LoginController(LoginView view) {
        this.view = view;
        this.view.addLoginListener(new LoginListener());
        this.view.addLogoutListener(new LogoutListener());
        this.view.setLogoutEnabled(false);

        try {
            userDbHelper = new UserDatabaseHelper();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
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

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsername();
            String password = view.getPassword();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter username and password.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (userDbHelper == null) {
                JOptionPane.showMessageDialog(null, "User database not available.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                String storedHash = userDbHelper.getPasswordHash(username);
                if (storedHash == null) {
                JOptionPane.showMessageDialog(null, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

                String inputHash = hashPassword(password);
                if (inputHash == null || !inputHash.equals(storedHash)) {
                JOptionPane.showMessageDialog(null, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

                loggedInUser = username;
                loggedInRole = userDbHelper.getUserRole(username);

                JOptionPane.showMessageDialog(null, "Login successful! Welcome " + loggedInUser + ".", "Success", JOptionPane.INFORMATION_MESSAGE);

                view.setLoginEnabled(false);
                view.setLogoutEnabled(true);

                if (loginSuccessCallback != null) {
                    loginSuccessCallback.run();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Logged out successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
