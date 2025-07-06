package controller;

import view.AdminWelcomePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for AdminWelcomePanel.
 * Handles user interactions and navigation for the admin welcome dashboard.
 */
public class AdminWelcomeController {

    private AdminWelcomePanel adminWelcomePanel;

    public AdminWelcomeController(AdminWelcomePanel panel) {
        this.adminWelcomePanel = panel;
        initController();
    }

    private void initController() {
        adminWelcomePanel.addStudentsButtonListener(new StudentsButtonListener());
        adminWelcomePanel.addTeachersButtonListener(new TeachersButtonListener());
        adminWelcomePanel.addCoursesButtonListener(new CoursesButtonListener());
        adminWelcomePanel.addDepartmentsButtonListener(new DepartmentsButtonListener());
        adminWelcomePanel.addEnrollmentButtonListener(new EnrollmentButtonListener());
        adminWelcomePanel.addMarksButtonListener(new MarksButtonListener());
        adminWelcomePanel.addUserManagementButtonListener(new UserManagementButtonListener());

        // Add search listener
        adminWelcomePanel.setNavigationListener(new AdminWelcomePanel.NavigationListener() {
            @Override
            public void navigateTo(String panelName) {
                // Delegate to existing navigation logic
                switch (panelName) {
                    case "Students":
                        System.out.println("Students button clicked");
                        break;
                    case "Teachers":
                        System.out.println("Teachers button clicked");
                        break;
                    case "Courses":
                        System.out.println("Courses button clicked");
                        break;
                    case "Departments":
                        System.out.println("Departments button clicked");
                        break;
                    case "Enrollment":
                        System.out.println("Enrollment button clicked");
                        break;
                    case "Marks":
                        System.out.println("Marks button clicked");
                        break;
                    case "Users":
                        System.out.println("User Management button clicked");
                        break;
                    default:
                        System.out.println("Unknown navigation: " + panelName);
                }
            }

            @Override
            public void performSearch(String query) {
                // Implement search handling here
                System.out.println("Search query: " + query);
                // TODO: Add actual search logic or navigation based on query
            }
        });
    }

    private class StudentsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Navigate to Students management panel
            System.out.println("Students button clicked");
        }
    }

    private class TeachersButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Navigate to Teachers management panel
            System.out.println("Teachers button clicked");
        }
    }

    private class CoursesButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Navigate to Courses management panel
            System.out.println("Courses button clicked");
        }
    }

    private class DepartmentsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Navigate to Departments management panel
            System.out.println("Departments button clicked");
        }
    }

    private class EnrollmentButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Navigate to Enrollment management panel
            System.out.println("Enrollment button clicked");
        }
    }

    private class MarksButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Navigate to Marks management panel
            System.out.println("Marks button clicked");
        }
    }

    private class UserManagementButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Navigate to User Management panel
            System.out.println("User Management button clicked");
        }
    }
}
