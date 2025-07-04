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

        // Student Panel
        StudentRegistrationPanel studentPanel = new StudentRegistrationPanel();
        StudentController studentController = new StudentController(studentPanel, dbHelper);
        tabbedPane.addTab("Students", studentPanel);

        // Teacher Panel
        TeacherRegistrationPanel teacherPanel = new TeacherRegistrationPanel();
        TeacherController teacherController = new TeacherController(teacherPanel, dbHelper);
        tabbedPane.addTab("Teachers", teacherPanel);

        // Course Panel
        CourseRegistrationPanel coursePanel = new CourseRegistrationPanel(teachers, students);
        CourseController courseController = new CourseController(coursePanel, dbHelper, teachers, students);
        tabbedPane.addTab("Courses", coursePanel);

        // Department Panel
        DepartmentPanel departmentPanel = new DepartmentPanel();
        DepartmentController departmentController = new DepartmentController(departmentPanel, dbHelper);
        tabbedPane.addTab("Departments", departmentPanel);

        // Enrollment Panel
        EnrollmentPanel enrollmentPanel = new EnrollmentPanel(courses);
        EnrollmentController enrollmentController = new EnrollmentController(enrollmentPanel, dbHelper);
        tabbedPane.addTab("Enrollment", enrollmentPanel);

        // Marks Panel
        MarksPanel marksPanel = new MarksPanel(courses, students);
        MarksController marksController = new MarksController(marksPanel, dbHelper);
        tabbedPane.addTab("Marks", marksPanel);

        // Login Panel
        LoginPanel loginPanel = new LoginPanel();
        LoginController loginController = new LoginController(loginPanel);
        tabbedPane.addTab("Login", loginPanel);

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
