package view;

import model.Course;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Swing panel for enrollment management.
 * Allows viewing and managing student enrollments in courses.
 */
public class EnrollmentPanel extends JPanel {
    private JTable enrollmentTable;
    private DefaultTableModel enrollmentTableModel;
    private JComboBox<Course> courseComboBox;
    private JButton loadEnrollmentsButton;
    private JButton removeEnrollmentButton;

    public EnrollmentPanel(List<Course> courses) {
        setLayout(new BorderLayout());

        // Top panel for course selection
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        courseComboBox = new JComboBox<>();
        for (Course c : courses) {
            courseComboBox.addItem(c);
        }
        loadEnrollmentsButton = new JButton("Load Enrollments");
        topPanel.add(new JLabel("Select Course:"));
        topPanel.add(courseComboBox);
        topPanel.add(loadEnrollmentsButton);
        add(topPanel, BorderLayout.NORTH);

        // Table for enrollments
        enrollmentTableModel = new DefaultTableModel(new Object[]{"Student ID", "First Name", "Last Name", "Email", "Major", "Year"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        enrollmentTable = new JTable(enrollmentTableModel);
        JScrollPane scrollPane = new JScrollPane(enrollmentTable);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel for removing enrollment
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        removeEnrollmentButton = new JButton("Remove Selected Enrollment");
        bottomPanel.add(removeEnrollmentButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Adds an ActionListener to the load enrollments button.
     * @param listener the ActionListener
     */
    public void addLoadEnrollmentsListener(ActionListener listener) {
        loadEnrollmentsButton.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the remove enrollment button.
     * @param listener the ActionListener
     */
    public void addRemoveEnrollmentListener(ActionListener listener) {
        removeEnrollmentButton.addActionListener(listener);
    }

    /**
     * Returns the selected course in the combo box.
     * @return selected Course or null if none selected
     */
    public Course getSelectedCourse() {
        return (Course) courseComboBox.getSelectedItem();
    }

    /**
     * Updates the enrollment table with the given list of students.
     * @param students list of enrolled students
     */
    public void updateEnrollmentTable(List<Student> students) {
        enrollmentTableModel.setRowCount(0);
        for (Student s : students) {
            enrollmentTableModel.addRow(new Object[]{
                    s.getId(),
                    s.getFirstName(),
                    s.getLastName(),
                    s.getEmail(),
                    s.getMajor(),
                    s.getYear()
            });
        }
    }

    /**
     * Returns the currently selected student in the enrollment table.
     * @return selected Student or null if none selected
     */
    public Student getSelectedStudent() {
        int selectedRow = enrollmentTable.getSelectedRow();
        if (selectedRow == -1) {
            return null;
        }
        int id = (int) enrollmentTableModel.getValueAt(selectedRow, 0);
        String firstName = (String) enrollmentTableModel.getValueAt(selectedRow, 1);
        String lastName = (String) enrollmentTableModel.getValueAt(selectedRow, 2);
        String email = (String) enrollmentTableModel.getValueAt(selectedRow, 3);
        String major = (String) enrollmentTableModel.getValueAt(selectedRow, 4);
        int year = (int) enrollmentTableModel.getValueAt(selectedRow, 5);
        return new Student(id, firstName, lastName, email, major, year);
    }
}
