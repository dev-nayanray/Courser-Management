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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;

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
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);

        // Top panel for student registration form with titled border
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1, true), "Student Information"));
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setFont(labelFont);
        idField = new JTextField(15);
        idField.setFont(fieldFont);
        idField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        idField.setBackground(Color.WHITE);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(labelFont);
        firstNameField = new JTextField(15);
        firstNameField.setFont(fieldFont);
        firstNameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        firstNameField.setBackground(Color.WHITE);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(labelFont);
        lastNameField = new JTextField(15);
        lastNameField.setFont(fieldFont);
        lastNameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        lastNameField.setBackground(Color.WHITE);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(labelFont);
        emailField = new JTextField(15);
        emailField.setFont(fieldFont);
        emailField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        emailField.setBackground(Color.WHITE);

        JLabel majorLabel = new JLabel("Major:");
        majorLabel.setFont(labelFont);
        majorField = new JTextField(15);
        majorField.setFont(fieldFont);
        majorField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        majorField.setBackground(Color.WHITE);

        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setFont(labelFont);
        yearField = new JTextField(15);
        yearField.setFont(fieldFont);
        yearField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        yearField.setBackground(Color.WHITE);

        submitButton = new JButton("Submit");
        submitButton.setFont(buttonFont);
        submitButton.setFocusPainted(false);
        submitButton.setBackground(new Color(70, 130, 180));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submitButton.setBackground(new Color(100, 149, 237));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                submitButton.setBackground(new Color(70, 130, 180));
            }
        });

        resetButton = new JButton("Reset");
        resetButton.setFont(buttonFont);
        resetButton.setFocusPainted(false);
        resetButton.setBackground(new Color(220, 20, 60));
        resetButton.setForeground(Color.WHITE);
        resetButton.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        resetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                resetButton.setBackground(new Color(255, 69, 0));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                resetButton.setBackground(new Color(220, 20, 60));
            }
        });

        gbc.insets = new Insets(10,10,10,10);
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

        // Center panel for student table with titled border
        studentTableModel = new DefaultTableModel(new Object[]{"ID", "First Name", "Last Name", "Email", "Major", "Year"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        studentTable = new JTable(studentTableModel);
        studentTable.setFont(fieldFont);
        studentTable.setRowHeight(28);
        studentTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        studentTable.getTableHeader().setReorderingAllowed(false);
        studentTable.getTableHeader().setBackground(new Color(70, 130, 180));
        studentTable.getTableHeader().setForeground(Color.WHITE);
        studentTable.setSelectionBackground(new Color(100, 149, 237));
        studentTable.setSelectionForeground(Color.WHITE);

        // Alternating row colors
        studentTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            private final Color evenColor = new Color(245, 245, 245);
            private final Color oddColor = Color.WHITE;

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? evenColor : oddColor);
                }
                return c;
            }
        });

        JScrollPane tableScrollPane = new JScrollPane(studentTable);
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(new TitledBorder(new LineBorder(new Color(70, 130, 180), 1, true), "Registered Students"));
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.CENTER);

        // Bottom panel for course registration with titled border
        JPanel coursePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        coursePanel.setBorder(new TitledBorder(new LineBorder(new Color(70, 130, 180), 1, true), "Course Enrollment"));
        courseComboBox = new JComboBox<>();
        courseComboBox.setFont(fieldFont);
        courseComboBox.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
        registerCourseButton = new JButton("Register Selected Student to Course");
        registerCourseButton.setFont(buttonFont);
        registerCourseButton.setFocusPainted(false);
        registerCourseButton.setBackground(new Color(70, 130, 180));
        registerCourseButton.setForeground(Color.WHITE);
        registerCourseButton.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        registerCourseButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerCourseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerCourseButton.setBackground(new Color(100, 149, 237));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerCourseButton.setBackground(new Color(70, 130, 180));
            }
        });

        coursePanel.add(new JLabel("Select Course:"));
        coursePanel.getComponent(0).setFont(labelFont);
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
