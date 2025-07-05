package database;

import java.sql.*;

/**
 * Helper class to manage user authentication and role management.
 * Supports adding users, retrieving roles, and password hashes.
 */
public class UserDatabaseHelper {
    private static final String DB_URL = "jdbc:sqlite:university.db";
    private Connection connection;

    public UserDatabaseHelper() throws SQLException {
        connect();
        createUsersTableIfNotExist();
    }

    private void connect() throws SQLException {
        connection = DriverManager.getConnection(DB_URL);
    }

    private void createUsersTableIfNotExist() throws SQLException {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                "username TEXT PRIMARY KEY," +
                "passwordHash TEXT NOT NULL," +
                "role TEXT NOT NULL" +
                ");";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createUsersTable);
        }
    }

    public void addUser(String username, String passwordHash, String role) throws SQLException {
        String sql = "INSERT INTO users (username, passwordHash, role) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, passwordHash);
            pstmt.setString(3, role);
            pstmt.executeUpdate();
        }
    }

    public String getUserRole(String username) throws SQLException {
        String sql = "SELECT role FROM users WHERE username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("role");
            }
        }
        return null;
    }

    public String getPasswordHash(String username) throws SQLException {
        String sql = "SELECT passwordHash FROM users WHERE username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("passwordHash");
            }
        }
        return null;
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
