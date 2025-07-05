package view;

import model.Student;
import model.Course;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import util.UIStyleUtil;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

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
        UIStyleUtil.stylePanel(this);

        // Top panel for student registration form with titled border
        JPanel formPanel = new JPanel(new GridBagLayout());
        UIStyleUtil.stylePanel(formPanel);
        formPanel.setBorder(new TitledBorder(new LineBorder(UIStyleUtil.PRIMARY_COLOR, 1, true), "Student Information"));
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel idLabel = new JLabel("Student ID:");
        UIStyleUtil.styleLabel(idLabel);
        idField = new JTextField(15);
        UIStyleUtil.styleTextField(idField);

        JLabel firstNameLabel = new JLabel("First Name:");
        UIStyleUtil.styleLabel(firstNameLabel);
        firstNameField = new JTextField(15);
        UIStyleUtil.styleTextField(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        UIStyleUtil.styleLabel(lastNameLabel);
        lastNameField = new JTextField(15);
        UIStyleUtil.styleTextField(lastNameField);

        JLabel emailLabel = new JLabel("Email:");
        UIStyleUtil.styleLabel(emailLabel);
        emailField = new JTextField(15);
        UIStyleUtil.styleTextField(emailField);

        JLabel majorLabel = new JLabel("Major:");
        UIStyleUtil.styleLabel(majorLabel);
        majorField = new JTextField(15);
        UIStyleUtil.styleTextField(majorField);

        JLabel yearLabel = new JLabel("Year:");
        UIStyleUtil.styleLabel(yearLabel);
        yearField = new JTextField(15);
        UIStyleUtil.styleTextField(yearField);

submitButton = new JButton("Submit");
UIStyleUtil.styleButton(submitButton);
UIStyleUtil.addTooltip(submitButton, "Submit the student registration form");

resetButton = new JButton("Reset");
UIStyleUtil.styleButton(resetButton);
UIStyleUtil.addTooltip(resetButton, "Reset the student registration form");

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
        UIStyleUtil.styleTable(studentTable);
        studentTable.setRowHeight(28);

        // Alternating row colors
        studentTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            private final Color evenColor = UIStyleUtil.BACKGROUND_COLOR;
            private final Color oddColor = UIStyleUtil.PANEL_BACKGROUND_COLOR;

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
        UIStyleUtil.styleScrollPane(tableScrollPane);
        JPanel tablePanel = new JPanel(new BorderLayout());
        UIStyleUtil.stylePanel(tablePanel);
        tablePanel.setBorder(new TitledBorder(new LineBorder(UIStyleUtil.PRIMARY_COLOR, 1, true), "Registered Students"));
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.CENTER);

        // Bottom panel for course registration with titled border
        JPanel coursePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        UIStyleUtil.stylePanel(coursePanel);
        coursePanel.setBorder(new TitledBorder(new LineBorder(UIStyleUtil.PRIMARY_COLOR, 1, true), "Course Enrollment"));
        courseComboBox = new JComboBox<>();
        UIStyleUtil.styleComboBox(courseComboBox);
        registerCourseButton = new JButton("Register Selected Student to Course");
        UIStyleUtil.styleButton(registerCourseButton);

        coursePanel.add(new JLabel("Select Course:"));
        UIStyleUtil.styleLabel((JLabel) coursePanel.getComponent(0));
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
