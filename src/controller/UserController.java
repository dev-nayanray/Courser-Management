package controller;

import database.UserDatabaseHelper;
import view.UserManagementPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Controller for managing user CRUD operations.
 */
public class UserController {
    private UserManagementPanel view;
    private UserDatabaseHelper userDbHelper;

    public UserController(UserManagementPanel view) {
        this.view = view;
        try {
            userDbHelper = new UserDatabaseHelper();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        loadUsers();

        view.addAddButtonListener(e -> addUser());
        view.addUpdateButtonListener(e -> updateUser());
        view.addDeleteButtonListener(e -> deleteUser());
        view.addClearButtonListener(e -> clearFields());

        view.getUserTable().getSelectionModel().addListSelectionListener(new UserSelectionListener());
    }

    private void loadUsers() {
        try {
            view.clearTable();
            String sql = "SELECT username, role FROM users";
            try (java.sql.Connection conn = java.sql.DriverManager.getConnection("jdbc:sqlite:university.db");
                 java.sql.Statement stmt = conn.createStatement();
                 java.sql.ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    String username = rs.getString("username");
                    String role = rs.getString("role");
                    view.addUserToTable(username, role);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading users: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addUser() {
        String username = view.getUsername();
        String password = view.getPassword();
        String role = view.getRole();

        if (username.isEmpty() || password.isEmpty() || role.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all fields.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String passwordHash = hashPassword(password);
            userDbHelper.addUser(username, passwordHash, role);
            JOptionPane.showMessageDialog(null, "User added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadUsers();
            clearFields();
        } catch (SQLException | NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(null, "Error adding user: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateUser() {
        String username = view.getUsername();
        String password = view.getPassword();
        String role = view.getRole();

        if (username.isEmpty() || role.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill username and role fields.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String passwordHash = password.isEmpty() ? userDbHelper.getPasswordHash(username) : hashPassword(password);
            userDbHelper.updateUser(username, passwordHash, role);
            JOptionPane.showMessageDialog(null, "User updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadUsers();
            clearFields();
        } catch (SQLException | NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(null, "Error updating user: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteUser() {
        String username = view.getUsername();
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a user to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete user: " + username + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            userDbHelper.deleteUser(username);
            JOptionPane.showMessageDialog(null, "User deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadUsers();
            clearFields();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting user: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        view.clearFields();
        view.getUserTable().clearSelection();
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private class UserSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            int selectedRow = view.getUserTable().getSelectedRow();
            if (selectedRow >= 0) {
                String username = (String) view.getUserTable().getValueAt(selectedRow, 0);
                String role = (String) view.getUserTable().getValueAt(selectedRow, 1);
                view.setUsername(username);
                view.setPassword(""); // Clear password field for security
                view.setRole(role);
            }
        }
    }
}
