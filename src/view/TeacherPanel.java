package view;

import model.Course;
import model.Teacher;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import util.UIStyleUtil;

/**
 * GUI panel for managing teachers: add new teachers, assign teachers to courses, show assigned courses.
 */
public class TeacherPanel extends JPanel {
    private JTextField teacherIdField;
    private JTextField teacherNameField;
    private JComboBox<String> designationComboBox;
    private JButton addTeacherButton;

    private JTable teacherTable;
    private DefaultTableModel teacherTableModel;

    private JComboBox<Teacher> teacherComboBox;
    private JComboBox<Course> courseComboBox;
    private JButton assignCourseButton;

    public TeacherPanel() {
        setLayout(new BorderLayout());
        UIStyleUtil.stylePanel(this);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        UIStyleUtil.stylePanel(inputPanel);
        GridBagConstraints gbc = new GridBagConstraints();

        // Add Teacher Section
        JLabel teacherIdLabel = new JLabel("Teacher ID:");
        UIStyleUtil.styleLabel(teacherIdLabel);
        teacherIdField = new JTextField(10);
        UIStyleUtil.styleTextField(teacherIdField);

        JLabel teacherNameLabel = new JLabel("Name:");
        UIStyleUtil.styleLabel(teacherNameLabel);
        teacherNameField = new JTextField(15);
        UIStyleUtil.styleTextField(teacherNameField);

        JLabel designationLabel = new JLabel("Designation:");
        UIStyleUtil.styleLabel(designationLabel);
        designationComboBox = new JComboBox<>(new String[]{"Lecturer", "Assistant Professor", "Associate Professor", "Professor"});
        UIStyleUtil.styleComboBox(designationComboBox);

addTeacherButton = new JButton("Add Teacher");
UIStyleUtil.styleButton(addTeacherButton);
UIStyleUtil.addTooltip(addTeacherButton, "Add a new teacher");

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(teacherIdLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(teacherIdField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(teacherNameLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(teacherNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        inputPanel.add(designationLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(designationComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        inputPanel.add(addTeacherButton, gbc);

        // Teacher Table Section
        String[] columnNames = {"ID", "Name", "Designation"};
        teacherTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        teacherTable = new JTable(teacherTableModel);
        UIStyleUtil.styleTable(teacherTable);
        JScrollPane tableScrollPane = new JScrollPane(teacherTable);
        UIStyleUtil.styleScrollPane(tableScrollPane);

        // Assignment Section
        JPanel assignmentPanel = new JPanel(new GridBagLayout());
        UIStyleUtil.stylePanel(assignmentPanel);
        GridBagConstraints assignGbc = new GridBagConstraints();

        JLabel selectTeacherLabel = new JLabel("Select Teacher:");
        UIStyleUtil.styleLabel(selectTeacherLabel);
        teacherComboBox = new JComboBox<>();
        UIStyleUtil.styleComboBox(teacherComboBox);
        teacherComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Teacher) {
                    Teacher teacher = (Teacher) value;
                    setText(teacher.getFirstName() + " " + teacher.getLastName() + " (" + teacher.getId() + ")");
                }
                return this;
            }
        });

        JLabel selectCourseLabel = new JLabel("Select Course:");
        UIStyleUtil.styleLabel(selectCourseLabel);
        courseComboBox = new JComboBox<>();
        UIStyleUtil.styleComboBox(courseComboBox);
        courseComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Course) {
                    setText(((Course) value).getCourseName());
                }
                return this;
            }
        });

        assignCourseButton = new JButton("Assign Course");
        UIStyleUtil.styleButton(assignCourseButton);

        assignGbc.insets = new Insets(10, 10, 10, 10);
        assignGbc.anchor = GridBagConstraints.WEST;

        assignGbc.gridx = 0; assignGbc.gridy = 0;
        assignmentPanel.add(selectTeacherLabel, assignGbc);
        assignGbc.gridx = 1;
        assignmentPanel.add(teacherComboBox, assignGbc);

        assignGbc.gridx = 0; assignGbc.gridy = 1;
        assignmentPanel.add(selectCourseLabel, assignGbc);
        assignGbc.gridx = 1;
        assignmentPanel.add(courseComboBox, assignGbc);

        assignGbc.gridx = 0; assignGbc.gridy = 2; assignGbc.gridwidth = 2;
        assignmentPanel.add(assignCourseButton, assignGbc);

        // Add components to main panel
        add(inputPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(assignmentPanel, BorderLayout.SOUTH);
    }

    public void addAddTeacherListener(ActionListener listener) {
        addTeacherButton.addActionListener(listener);
    }

    public void addAssignCourseListener(ActionListener listener) {
        assignCourseButton.addActionListener(listener);
    }

    public void updateTeacherTable(List<Teacher> teachers) {
        teacherTableModel.setRowCount(0);
        for (Teacher teacher : teachers) {
            teacherTableModel.addRow(new Object[]{teacher.getId(), teacher.getFirstName() + " " + teacher.getLastName(), teacher.getTitle()});
        }
    }

    public void updateTeacherComboBox(List<Teacher> teachers) {
        teacherComboBox.removeAllItems();
        for (Teacher teacher : teachers) {
            teacherComboBox.addItem(teacher);
        }
    }

    public void updateCourseComboBox(List<Course> courses) {
        courseComboBox.removeAllItems();
        for (Course course : courses) {
            courseComboBox.addItem(course);
        }
    }

    public String getTeacherId() {
        return teacherIdField.getText().trim();
    }

    public String getTeacherName() {
        return teacherNameField.getText().trim();
    }

    public String getSelectedDesignation() {
        return (String) designationComboBox.getSelectedItem();
    }

    public Teacher getSelectedTeacher() {
        return (Teacher) teacherComboBox.getSelectedItem();
    }

    public Course getSelectedCourse() {
        return (Course) courseComboBox.getSelectedItem();
    }

    public void clearTeacherInputFields() {
        teacherIdField.setText("");
        teacherNameField.setText("");
        if (designationComboBox.getItemCount() > 0) {
            designationComboBox.setSelectedIndex(0);
        }
    }
}
