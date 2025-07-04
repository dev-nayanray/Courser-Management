import controller.CourseController;
import controller.StudentController;
import controller.TeacherController;
import database.DatabaseHelper;
import model.Student;
import model.Teacher;
import view.CourseRegistrationPanel;
import view.StudentRegistrationPanel;
import view.TeacherRegistrationPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Main application launcher for the University Course Management System.
 * Initializes database, GUI, and controllers.
 */
public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                DatabaseHelper dbHelper = new DatabaseHelper();

                List<Teacher> teachers = dbHelper.getAllTeachers();
                List<Student> students = dbHelper.getAllStudents();

                JFrame frame = new JFrame("University Course Management System");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(600, 500);
                frame.setLayout(new BorderLayout());

                JTabbedPane tabbedPane = new JTabbedPane();

                StudentRegistrationPanel studentPanel = new StudentRegistrationPanel();
                TeacherRegistrationPanel teacherPanel = new TeacherRegistrationPanel();
                CourseRegistrationPanel coursePanel = new CourseRegistrationPanel(teachers, students);

                new StudentController(studentPanel, dbHelper);
                new TeacherController(teacherPanel, dbHelper);
                new CourseController(coursePanel, dbHelper, teachers, students);

                tabbedPane.addTab("Student Registration", studentPanel);
                tabbedPane.addTab("Teacher Registration", teacherPanel);
                tabbedPane.addTab("Course Registration", coursePanel);

                frame.add(tabbedPane, BorderLayout.CENTER);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to initialize database: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
