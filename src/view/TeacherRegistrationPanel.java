package view;

import model.Teacher;
import model.Course;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Swing panel for teacher registration and course assignment.
 * Provides form fields to input teacher details, buttons to submit or reset,
 * a table to display teachers, and controls to assign teachers to courses.
 */
public class TeacherRegistrationPanel extends JPanel {
    private JTextField idField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField departmentField;
    private JTextField titleField;
    private JButton submitButton;
    private JButton resetButton;

    private JTable teacherTable;
    private DefaultTableModel teacherTableModel;

    private JComboBox<Course> courseComboBox;
    private JButton assignCourseButton;

    public TeacherRegistrationPanel() {
        setLayout(new BorderLayout());

        // Fonts and colors
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        Color buttonBgColor = new Color(70, 130, 180);
        Color buttonHoverColor = new Color(100, 149, 237);
        Color buttonFgColor = Color.WHITE;

        // Top panel for teacher registration form with titled border
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Teacher Registration", TitledBorder.LEFT, TitledBorder.TOP, labelFont));
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel idLabel = new JLabel("Teacher ID:");
        idLabel.setFont(labelFont);
        idField = new JTextField(15);
        idField.setFont(fieldFont);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(labelFont);
        firstNameField = new JTextField(15);
        firstNameField.setFont(fieldFont);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(labelFont);
        lastNameField = new JTextField(15);
        lastNameField.setFont(fieldFont);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(labelFont);
        emailField = new JTextField(15);
        emailField.setFont(fieldFont);

        JLabel departmentLabel = new JLabel("Department:");
        departmentLabel.setFont(labelFont);
        departmentField = new JTextField(15);
        departmentField.setFont(fieldFont);

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setFont(labelFont);
        titleField = new JTextField(15);
        titleField.setFont(fieldFont);

        submitButton = new JButton("Submit");
        styleButton(submitButton, buttonBgColor, buttonFgColor, buttonHoverColor, buttonFont);

        resetButton = new JButton("Reset");
        styleButton(resetButton, buttonBgColor, buttonFgColor, buttonHoverColor, buttonFont);

        gbc.insets = new Insets(8, 8, 8, 8);
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
        formPanel.add(departmentLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(departmentField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(titleLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(titleField, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        formPanel.add(submitButton, gbc);
        gbc.gridx = 1;
        formPanel.add(resetButton, gbc);

        add(formPanel, BorderLayout.NORTH);

        // Center panel for teacher table
        teacherTableModel = new DefaultTableModel(new Object[]{"ID", "First Name", "Last Name", "Email", "Department", "Title"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        teacherTable = new JTable(teacherTableModel);
        JScrollPane tableScrollPane = new JScrollPane(teacherTable);
        add(tableScrollPane, BorderLayout.CENTER);

        // Bottom panel for course assignment
        JPanel coursePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        courseComboBox = new JComboBox<>();
        courseComboBox.setPreferredSize(new Dimension(250, 25));
        assignCourseButton = new JButton("Assign Selected Teacher to Course");
        styleButton(assignCourseButton, buttonBgColor, buttonFgColor, buttonHoverColor, buttonFont);

        coursePanel.add(new JLabel("Select Course:"));
        coursePanel.add(courseComboBox);
        coursePanel.add(assignCourseButton);

        add(coursePanel, BorderLayout.SOUTH);
    }

    private void styleButton(JButton button, Color bgColor, Color fgColor, Color hoverColor, Font font) {
        button.setFont(font);
        button.setFocusPainted(false);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }

    /**
     * Returns a Teacher object constructed from the form inputs.
     * @return Teacher object
     * @throws NumberFormatException if ID field is invalid
     */
    public Teacher getTeacherFromForm() throws NumberFormatException {
        int id = Integer.parseInt(idField.getText().trim());
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String department = departmentField.getText().trim();
        String title = titleField.getText().trim();

        return new Teacher(id, firstName, lastName, email, department, title);
    }

    /**
     * Clears all form fields.
     */
    public void clearForm() {
        idField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        departmentField.setText("");
        titleField.setText("");
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
     * Adds an ActionListener to the assign course button.
     * @param listener the ActionListener
     */
    public void addAssignCourseListener(ActionListener listener) {
        assignCourseButton.addActionListener(listener);
    }

    /**
     * Updates the teacher table with the given list of teachers.
     * @param teachers list of teachers
     */
    public void updateTeacherTable(List<Teacher> teachers) {
        teacherTableModel.setRowCount(0);
        for (Teacher t : teachers) {
            teacherTableModel.addRow(new Object[]{
                    t.getId(),
                    t.getFirstName(),
                    t.getLastName(),
                    t.getEmail(),
                    t.getDepartment(),
                    t.getTitle()
            });
        }
    }

    /**
     * Returns the currently selected teacher in the table.
     * @return selected Teacher or null if none selected
     */
    public Teacher getSelectedTeacher() {
        int selectedRow = teacherTable.getSelectedRow();
        if (selectedRow == -1) {
            return null;
        }
        int id = (int) teacherTableModel.getValueAt(selectedRow, 0);
        String firstName = (String) teacherTableModel.getValueAt(selectedRow, 1);
        String lastName = (String) teacherTableModel.getValueAt(selectedRow, 2);
        String email = (String) teacherTableModel.getValueAt(selectedRow, 3);
        String department = (String) teacherTableModel.getValueAt(selectedRow, 4);
        String title = (String) teacherTableModel.getValueAt(selectedRow, 5);
        return new Teacher(id, firstName, lastName, email, department, title);
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
