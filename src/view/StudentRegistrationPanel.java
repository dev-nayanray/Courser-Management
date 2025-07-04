package view;

import model.Student;
import model.Course;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Swing panel for student registration and course enrollment.
 * Provides form fields to input student details, buttons to submit or reset,
 * a table to display students, and controls to register students to courses.
 */
public class StudentRegistrationPanel extends JPanel {
    private JTextField idField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField majorField;
    private JTextField yearField;
    private JButton submitButton;
    private JButton resetButton;

    private JTable studentTable;
    private DefaultTableModel studentTableModel;

    private JComboBox<Course> courseComboBox;
    private JButton registerCourseButton;

    public StudentRegistrationPanel() {
        setLayout(new BorderLayout());

        // Top panel for student registration form
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel idLabel = new JLabel("Student ID:");
        idField = new JTextField(15);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField(15);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField(15);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(15);

        JLabel majorLabel = new JLabel("Major:");
        majorField = new JTextField(15);

        JLabel yearLabel = new JLabel("Year:");
        yearField = new JTextField(15);

        submitButton = new JButton("Submit");
        resetButton = new JButton("Reset");

        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(idLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(firstNameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(firstNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(lastNameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(lastNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(majorLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(majorField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(yearLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(yearField, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        formPanel.add(submitButton, gbc);
        gbc.gridx = 1;
        formPanel.add(resetButton, gbc);

        add(formPanel, BorderLayout.NORTH);

        // Center panel for student table
        studentTableModel = new DefaultTableModel(new Object[]{"ID", "First Name", "Last Name", "Email", "Major", "Year"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        studentTable = new JTable(studentTableModel);
        JScrollPane tableScrollPane = new JScrollPane(studentTable);
        add(tableScrollPane, BorderLayout.CENTER);

        // Bottom panel for course registration
        JPanel coursePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        courseComboBox = new JComboBox<>();
        registerCourseButton = new JButton("Register Selected Student to Course");

        coursePanel.add(new JLabel("Select Course:"));
        coursePanel.add(courseComboBox);
        coursePanel.add(registerCourseButton);

        add(coursePanel, BorderLayout.SOUTH);
    }

    /**
     * Returns a Student object constructed from the form inputs.
     * @return Student object
     * @throws NumberFormatException if ID or year fields are invalid
     */
    public Student getStudentFromForm() throws NumberFormatException {
        int id = Integer.parseInt(idField.getText().trim());
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String major = majorField.getText().trim();
        int year = Integer.parseInt(yearField.getText().trim());

        return new Student(id, firstName, lastName, email, major, year);
    }

    /**
     * Clears all form fields.
     */
    public void clearForm() {
        idField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        majorField.setText("");
        yearField.setText("");
    }

    /**
     * Adds an ActionListener to the submit button.
     * @param listener the ActionListener
     */
    public void addSubmitListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the reset button.
     * @param listener the ActionListener
     */
    public void addResetListener(ActionListener listener) {
        resetButton.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the register course button.
     * @param listener the ActionListener
     */
    public void addRegisterCourseListener(ActionListener listener) {
        registerCourseButton.addActionListener(listener);
    }

    /**
     * Updates the student table with the given list of students.
     * @param students list of students
     */
    public void updateStudentTable(List<Student> students) {
        studentTableModel.setRowCount(0);
        for (Student s : students) {
            studentTableModel.addRow(new Object[]{
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
     * Returns the currently selected student in the table.
     * @return selected Student or null if none selected
     */
    public Student getSelectedStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            return null;
        }
        int id = (int) studentTableModel.getValueAt(selectedRow, 0);
        String firstName = (String) studentTableModel.getValueAt(selectedRow, 1);
        String lastName = (String) studentTableModel.getValueAt(selectedRow, 2);
        String email = (String) studentTableModel.getValueAt(selectedRow, 3);
        String major = (String) studentTableModel.getValueAt(selectedRow, 4);
        int year = (int) studentTableModel.getValueAt(selectedRow, 5);
        return new Student(id, firstName, lastName, email, major, year);
    }

    /**
     * Updates the course combo box with the given list of courses.
     * @param courses list of courses
     */
    public void updateCourseComboBox(List<Course> courses) {
        courseComboBox.removeAllItems();
        for (Course c : courses) {
            courseComboBox.addItem(c);
        }
    }

    /**
     * Returns the currently selected course in the combo box.
     * @return selected Course or null if none selected
     */
    public Course getSelectedCourse() {
        return (Course) courseComboBox.getSelectedItem();
    }
}
