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

        // Apply modern look and feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
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
