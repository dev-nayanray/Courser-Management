package view;

import model.Teacher;
import model.Course;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import util.UIStyleUtil;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

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
        UIStyleUtil.stylePanel(this);

        // Top panel for teacher registration form with titled border
        JPanel formPanel = new JPanel(new GridBagLayout());
        UIStyleUtil.stylePanel(formPanel);
        formPanel.setBorder(new TitledBorder(new LineBorder(UIStyleUtil.PRIMARY_COLOR, 1, true), "Teacher Registration"));
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel idLabel = new JLabel("Teacher ID:");
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

        JLabel departmentLabel = new JLabel("Department:");
        UIStyleUtil.styleLabel(departmentLabel);
        departmentField = new JTextField(15);
        UIStyleUtil.styleTextField(departmentField);

        JLabel titleLabel = new JLabel("Title:");
        UIStyleUtil.styleLabel(titleLabel);
        titleField = new JTextField(15);
        UIStyleUtil.styleTextField(titleField);

        submitButton = new JButton("Submit");
        UIStyleUtil.styleButton(submitButton);

        resetButton = new JButton("Reset");
        UIStyleUtil.styleButton(resetButton);

        gbc.insets = new Insets(10, 10, 10, 10);
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

        // Center panel for teacher table with titled border
        teacherTableModel = new DefaultTableModel(new Object[]{"ID", "First Name", "Last Name", "Email", "Department", "Title"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        teacherTable = new JTable(teacherTableModel);
        UIStyleUtil.styleTable(teacherTable);
        teacherTable.setRowHeight(28);

        // Alternating row colors
        teacherTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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

        JScrollPane tableScrollPane = new JScrollPane(teacherTable);
        UIStyleUtil.styleScrollPane(tableScrollPane);
        JPanel tablePanel = new JPanel(new BorderLayout());
        UIStyleUtil.stylePanel(tablePanel);
        tablePanel.setBorder(new TitledBorder(new LineBorder(UIStyleUtil.PRIMARY_COLOR, 1, true), "Registered Teachers"));
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.CENTER);

        // Bottom panel for course assignment with titled border
        JPanel coursePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        UIStyleUtil.stylePanel(coursePanel);
        courseComboBox = new JComboBox<>();
        UIStyleUtil.styleComboBox(courseComboBox);
        assignCourseButton = new JButton("Assign Selected Teacher to Course");
        UIStyleUtil.styleButton(assignCourseButton);

        coursePanel.add(new JLabel("Select Course:"));
        UIStyleUtil.styleLabel((JLabel) coursePanel.getComponent(0));
        coursePanel.add(courseComboBox);
        coursePanel.add(assignCourseButton);

        add(coursePanel, BorderLayout.SOUTH);
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
