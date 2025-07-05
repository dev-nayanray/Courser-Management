package util;

import database.UserDatabaseHelper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Utility class to add initial users to the database with hashed passwords.
 */
public class UserSetup {
    public static void main(String[] args) {
        try {
            UserDatabaseHelper userDbHelper = new UserDatabaseHelper();

            // Add admin user
            String adminUsername = "admin";
            String adminPassword = "admin123";
            String adminRole = "admin";
            userDbHelper.addUser(adminUsername, hashPassword(adminPassword), adminRole);

            // Add regular user
            String userUsername = "user";
            String userPassword = "user123";
            String userRole = "user";
            userDbHelper.addUser(userUsername, hashPassword(userPassword), userRole);

            System.out.println("Initial users added successfully.");

            userDbHelper.close();
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
