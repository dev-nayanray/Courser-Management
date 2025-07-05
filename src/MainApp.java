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
    private AdminHeaderPanel headerPanel;

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

        MarksPanel marksPanel = new MarksPanel();
        MarksController marksController = new MarksController(marksPanel, dbHelper);

        // Login Panel
        LoginPanel loginPanel = new LoginPanel();
        LoginController loginController = new LoginController(loginPanel);

        // Add header panel
        headerPanel = new AdminHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        // Add footer panel
        AdminFooterPanel footerPanel = new AdminFooterPanel();
        add(footerPanel, BorderLayout.SOUTH);

        // Add only login tab initially
        tabbedPane.addTab("Login", loginPanel);
        add(tabbedPane, BorderLayout.CENTER);

        // Set login success callback to add other tabs and remove login tab
        loginController.setLoginSuccessCallback(() -> {
            SwingUtilities.invokeLater(() -> {
                tabbedPane.remove(loginPanel);
                tabbedPane.addTab("Students", studentPanel);
                tabbedPane.setTabComponentAt(tabbedPane.indexOfComponent(studentPanel), createTabTitle("Students", UIManager.getIcon("FileView.fileIcon")));

                tabbedPane.addTab("Teachers", teacherPanel);
                tabbedPane.setTabComponentAt(tabbedPane.indexOfComponent(teacherPanel), createTabTitle("Teachers", UIManager.getIcon("FileView.directoryIcon")));

                tabbedPane.addTab("Courses", coursePanel);
                tabbedPane.setTabComponentAt(tabbedPane.indexOfComponent(coursePanel), createTabTitle("Courses", UIManager.getIcon("FileView.computerIcon")));

                tabbedPane.addTab("Departments", departmentPanel);
                tabbedPane.setTabComponentAt(tabbedPane.indexOfComponent(departmentPanel), createTabTitle("Departments", UIManager.getIcon("FileView.hardDriveIcon")));

                tabbedPane.addTab("Enrollment", enrollmentPanel);
                tabbedPane.setTabComponentAt(tabbedPane.indexOfComponent(enrollmentPanel), createTabTitle("Enrollment", UIManager.getIcon("FileChooser.detailsViewIcon")));

                tabbedPane.addTab("Marks", marksPanel);
                tabbedPane.setTabComponentAt(tabbedPane.indexOfComponent(marksPanel), createTabTitle("Marks", UIManager.getIcon("FileChooser.listViewIcon")));

                tabbedPane.setSelectedIndex(0);
            });
        });

    }

    private JPanel createTabTitle(String title, Icon icon) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panel.setOpaque(false);

        JLabel iconLabel = new JLabel(icon);
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titleLabel.setForeground(UIManager.getColor("nimbusBlueGrey"));

        panel.add(iconLabel);
        panel.add(titleLabel);

        return panel;
    }

    // Add logout listener to header panel
    private void addHeaderLogoutListener(LoginPanel loginPanel) {
        headerPanel.addLogoutListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    tabbedPane.removeAll();
                    tabbedPane.addTab("Login", loginPanel);
                    tabbedPane.setSelectedIndex(0);
                });
            }
        });
    }

    // Apply modern look and feel with custom colors
    private void applyLookAndFeel() {
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
