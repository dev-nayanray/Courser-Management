import controller.*;
import database.DatabaseHelper;
import model.*;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Main application window with modern GUI and navigation tabs.
 */
public class MainApp extends JFrame {
    private JTabbedPane tabbedPane;
    private DatabaseHelper dbHelper;

    public MainApp() {
        setTitle("University Course Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        try {
            dbHelper = new DatabaseHelper();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to connect to database: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        tabbedPane = new JTabbedPane();

        // Load data for dropdowns and lists
        List<Student> students = null;
        List<Teacher> teachers = null;
        List<Department> departments = null;
        List<Course> courses = null;
        try {
            students = dbHelper.getAllStudents();
            teachers = dbHelper.getAllTeachers();
            departments = dbHelper.getAllDepartments();
            courses = dbHelper.getAllCourses();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to load initial data: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        // Initialize panels but do not add data tabs yet
        StudentRegistrationPanel studentPanel = new StudentRegistrationPanel();
        StudentController studentController = new StudentController(studentPanel, dbHelper);

        TeacherRegistrationPanel teacherPanel = new TeacherRegistrationPanel();
        TeacherController teacherController = new TeacherController(teacherPanel, dbHelper);

        CourseRegistrationPanel coursePanel = new CourseRegistrationPanel(teachers, students);
        CourseController courseController = new CourseController(coursePanel, dbHelper, teachers, students);

        DepartmentPanel departmentPanel = new DepartmentPanel();
        DepartmentController departmentController = new DepartmentController(departmentPanel, dbHelper);

        EnrollmentPanel enrollmentPanel = new EnrollmentPanel(courses);
        EnrollmentController enrollmentController = new EnrollmentController(enrollmentPanel, dbHelper);

        MarksPanel marksPanel = new MarksPanel(courses, students);
        MarksController marksController = new MarksController(marksPanel, dbHelper);

        // Login Panel
        LoginPanel loginPanel = new LoginPanel();
        LoginController loginController = new LoginController(loginPanel);

        // Add only login tab initially
        tabbedPane.addTab("Login", loginPanel);

        // Set login success callback to add other tabs and remove login tab
        loginController.setLoginSuccessCallback(() -> {
            SwingUtilities.invokeLater(() -> {
                tabbedPane.remove(loginPanel);
                tabbedPane.addTab("Students", studentPanel);
                tabbedPane.addTab("Teachers", teacherPanel);
                tabbedPane.addTab("Courses", coursePanel);
                tabbedPane.addTab("Departments", departmentPanel);
                tabbedPane.addTab("Enrollment", enrollmentPanel);
                tabbedPane.addTab("Marks", marksPanel);
                tabbedPane.setSelectedIndex(0);
            });
        });

        add(tabbedPane);

        // Apply modern look and feel with custom colors
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

            UIManager.put("control", new java.awt.Color(230, 230, 250)); // Lavender background
            UIManager.put("info", new java.awt.Color(242, 242, 189));
            UIManager.put("nimbusBase", new java.awt.Color(70, 130, 180)); // Steel blue
            UIManager.put("nimbusAlertYellow", new java.awt.Color(248, 187, 0));
            UIManager.put("nimbusDisabledText", new java.awt.Color(142, 143, 145));
            UIManager.put("nimbusFocus", new java.awt.Color(115, 164, 209));
            UIManager.put("nimbusGreen", new java.awt.Color(176, 179, 50));
            UIManager.put("nimbusInfoBlue", new java.awt.Color(66, 139, 221));
            UIManager.put("nimbusLightBackground", new java.awt.Color(255, 255, 255));
            UIManager.put("nimbusOrange", new java.awt.Color(191, 98, 4));
            UIManager.put("nimbusRed", new java.awt.Color(169, 46, 34));
            UIManager.put("nimbusSelectedText", new java.awt.Color(255, 255, 255));
            UIManager.put("nimbusSelectionBackground", new java.awt.Color(57, 105, 138));
            UIManager.put("text", new java.awt.Color(50, 50, 50));

            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            // Ignore and use default
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainApp app = new MainApp();
            app.setVisible(true);
        });
    }
}
