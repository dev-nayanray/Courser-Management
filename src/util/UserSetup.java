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

            // Add teacher user
            String teacherUsername = "teacher1";
            String teacherPassword = "teacher123";
            String teacherRole = "teacher";
            userDbHelper.addUser(teacherUsername, hashPassword(teacherPassword), teacherRole);

            // Add student user
            String studentUsername = "student1";
            String studentPassword = "student123";
            String studentRole = "student";
            userDbHelper.addUser(studentUsername, hashPassword(studentPassword), studentRole);

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
