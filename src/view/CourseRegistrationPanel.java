package view;

import model.Course;
import model.Student;
import model.Teacher;

import javax.swing.*;
import javax.swing.DefaultListCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import util.UIStyleUtil;

public class CourseRegistrationPanel extends JPanel {
    private JTextField courseIdField;
    private JTextField courseNameField;
    private JTextField scheduleField;
    private JTextField creditsField;
    private JTextField maxStudentsField;
    private JComboBox<Teacher> teacherComboBox;
    private JList<Student> studentList;
    private DefaultListModel<Student> studentListModel;
    private JButton addStudentButton;
    private JButton removeStudentButton;
    private JButton submitButton;
    private JButton resetButton;

    private JTable courseTable;
    private DefaultTableModel courseTableModel;

    private JTextField searchField;
    private JButton searchButton;
    private JButton printStudentDetailsButton;

    public CourseRegistrationPanel() {
        setLayout(new GridBagLayout());
        UIStyleUtil.stylePanel(this);
        GridBagConstraints gbc = new GridBagConstraints();

        // Initialize components
        JLabel courseIdLabel = new JLabel("Course ID:");
        UIStyleUtil.styleLabel(courseIdLabel);
        courseIdField = new JTextField(10);
        UIStyleUtil.styleTextField(courseIdField);

        JLabel courseNameLabel = new JLabel("Course Name:");
        UIStyleUtil.styleLabel(courseNameLabel);
        courseNameField = new JTextField(10);
        UIStyleUtil.styleTextField(courseNameField);

        JLabel scheduleLabel = new JLabel("Schedule:");
        UIStyleUtil.styleLabel(scheduleLabel);
        scheduleField = new JTextField(10);
        UIStyleUtil.styleTextField(scheduleField);

        JLabel creditsLabel = new JLabel("Credits:");
        UIStyleUtil.styleLabel(creditsLabel);
        creditsField = new JTextField(10);
        UIStyleUtil.styleTextField(creditsField);

        JLabel maxStudentsLabel = new JLabel("Max Students:");
        UIStyleUtil.styleLabel(maxStudentsLabel);
        maxStudentsField = new JTextField(10);
        UIStyleUtil.styleTextField(maxStudentsField);

        JLabel teacherLabel = new JLabel("Teacher:");
        UIStyleUtil.styleLabel(teacherLabel);
        teacherComboBox = new JComboBox<>();
        UIStyleUtil.styleComboBox(teacherComboBox);

        JLabel studentsLabel = new JLabel("Enrolled Students:");
        UIStyleUtil.styleLabel(studentsLabel);
        studentListModel = new DefaultListModel<>();
        studentList = new JList<>(studentListModel);
        UIStyleUtil.styleList(studentList);
        JScrollPane studentListScrollPane = new JScrollPane(studentList);
        UIStyleUtil.styleScrollPane(studentListScrollPane);

addStudentButton = new JButton("Add Student");
UIStyleUtil.styleButton(addStudentButton);
UIStyleUtil.addTooltip(addStudentButton, "Add a student to the course");
removeStudentButton = new JButton("Remove Student");
UIStyleUtil.styleButton(removeStudentButton);
UIStyleUtil.addTooltip(removeStudentButton, "Remove selected student from the course");
submitButton = new JButton("Submit");
UIStyleUtil.styleButton(submitButton);
UIStyleUtil.addTooltip(submitButton, "Submit the course registration form");
resetButton = new JButton("Reset");
UIStyleUtil.styleButton(resetButton);
UIStyleUtil.addTooltip(resetButton, "Reset the course registration form");

        // Layout components using GridBagLayout
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Group course details in a panel with titled border
        JPanel courseDetailsPanel = new JPanel(new GridBagLayout());
        UIStyleUtil.stylePanel(courseDetailsPanel);
        courseDetailsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(UIStyleUtil.PRIMARY_COLOR, 1, true), "Course Details"));
        GridBagConstraints courseGbc = new GridBagConstraints();
        courseGbc.insets = new Insets(10, 10, 10, 10);
        courseGbc.fill = GridBagConstraints.HORIZONTAL;

        courseGbc.gridx = 0; courseGbc.gridy = 0;
        courseDetailsPanel.add(courseIdLabel, courseGbc);
        courseGbc.gridx = 1;
        courseDetailsPanel.add(courseIdField, courseGbc);

        courseGbc.gridx = 0; courseGbc.gridy = 1;
        courseDetailsPanel.add(courseNameLabel, courseGbc);
        courseGbc.gridx = 1;
        courseDetailsPanel.add(courseNameField, courseGbc);

        courseGbc.gridx = 0; courseGbc.gridy = 2;
        courseDetailsPanel.add(scheduleLabel, courseGbc);
        courseGbc.gridx = 1;
        courseDetailsPanel.add(scheduleField, courseGbc);

        courseGbc.gridx = 0; courseGbc.gridy = 3;
        courseDetailsPanel.add(creditsLabel, courseGbc);
        courseGbc.gridx = 1;
        courseDetailsPanel.add(creditsField, courseGbc);

        courseGbc.gridx = 0; courseGbc.gridy = 4;
        courseDetailsPanel.add(maxStudentsLabel, courseGbc);
        courseGbc.gridx = 1;
        courseDetailsPanel.add(maxStudentsField, courseGbc);

        courseGbc.gridx = 0; courseGbc.gridy = 5;
        courseDetailsPanel.add(teacherLabel, courseGbc);
        courseGbc.gridx = 1;
        courseDetailsPanel.add(teacherComboBox, courseGbc);

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(courseDetailsPanel, gbc);

        // Student enrollment panel with titled border
        JPanel studentEnrollmentPanel = new JPanel(new BorderLayout(10, 10));
        UIStyleUtil.stylePanel(studentEnrollmentPanel);
        studentEnrollmentPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(UIStyleUtil.PRIMARY_COLOR, 1, true), "Enrolled Students"));

        JScrollPane studentListScrollPane2 = new JScrollPane(studentList);
        UIStyleUtil.styleScrollPane(studentListScrollPane2);
        studentEnrollmentPanel.add(studentListScrollPane2, BorderLayout.CENTER);

        JPanel studentButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        UIStyleUtil.stylePanel(studentButtonPanel);
        if (addStudentButton != null) {
            studentButtonPanel.add(addStudentButton);
        }
        if (removeStudentButton != null) {
            studentButtonPanel.add(removeStudentButton);
        }
        studentEnrollmentPanel.add(studentButtonPanel, BorderLayout.SOUTH);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.BOTH; gbc.weightx = 1.0; gbc.weighty = 0.5;
        if (studentEnrollmentPanel != null) {
            add(studentEnrollmentPanel, gbc);
        }

        // Initialize missing components
        searchField = new JTextField(10);
        UIStyleUtil.styleTextField(searchField);
        searchButton = new JButton("Search");
        UIStyleUtil.styleButton(searchButton);
        printStudentDetailsButton = new JButton("Print Student Details");
        UIStyleUtil.styleButton(printStudentDetailsButton);

        // Buttons panel for submit, reset, search, and print
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        UIStyleUtil.stylePanel(buttonsPanel);
        GridBagConstraints buttonsGbc = new GridBagConstraints();
        buttonsGbc.insets = new Insets(10, 10, 10, 10);
        buttonsGbc.fill = GridBagConstraints.HORIZONTAL;

        buttonsGbc.gridx = 0; buttonsGbc.gridy = 0;
        buttonsPanel.add(submitButton, buttonsGbc);
        buttonsGbc.gridx = 1;
        buttonsPanel.add(resetButton, buttonsGbc);

        buttonsGbc.gridx = 0; buttonsGbc.gridy = 1;
        JLabel searchLabel = new JLabel("Search Course by ID:");
        UIStyleUtil.styleLabel(searchLabel);
        buttonsPanel.add(searchLabel, buttonsGbc);
        buttonsGbc.gridx = 1;
        buttonsPanel.add(searchField, buttonsGbc);

        buttonsGbc.gridx = 0; buttonsGbc.gridy = 2;
        buttonsPanel.add(searchButton, buttonsGbc);
        buttonsGbc.gridx = 1;
        buttonsPanel.add(printStudentDetailsButton, buttonsGbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0; gbc.weighty = 0.0;
        add(buttonsPanel, gbc);

        // Initialize course table
        initializeCourseTable();
    }

    public CourseRegistrationPanel(List<Teacher> teachers, List<Student> students) {
        this();
        // Defensive initialization to avoid null components
        if (teacherComboBox == null) {
            teacherComboBox = new JComboBox<>();
            UIStyleUtil.styleComboBox(teacherComboBox);
        }
        if (studentListModel == null) {
            studentListModel = new DefaultListModel<>();
        }
        if (studentList == null) {
            studentList = new JList<>(studentListModel);
            UIStyleUtil.styleList(studentList);
        }
        // Clear existing items before adding new ones
        teacherComboBox.removeAllItems();
        studentListModel.clear();
        for (Teacher teacher : teachers) {
            if (teacher != null) {
                teacherComboBox.addItem(teacher);
            }
        }
        for (Student student : students) {
            if (student != null) {
                studentListModel.addElement(student);
            }
        }
    }

    public void initializeCourseTable() {
        String[] columnNames = {"Course ID", "Name", "Teacher", "Schedule", "Credits", "Max Students"};
        courseTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table cells non-editable
            }
        };
        courseTable = new JTable(courseTableModel);
        courseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        courseTable.setFillsViewportHeight(true);
        UIStyleUtil.styleTable(courseTable);
        courseTable.setRowHeight(28);
        // Alternating row colors
        courseTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
        JScrollPane courseTableScrollPane = new JScrollPane(courseTable);
        UIStyleUtil.styleScrollPane(courseTableScrollPane);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(courseTableScrollPane, gbc);
    }

    /**
     * Sets the course data in the course table.
     * @param courses List of Course objects to display
     */
    public void setCourseTableData(List<Course> courses) {
        courseTableModel.setRowCount(0); // Clear existing data
        for (Course course : courses) {
            Object[] rowData = {
                course.getCourseId(),
                course.getCourseName(),
                course.getTeacher(),
                course.getSchedule(),
                course.getCredits(),
                course.getMaxStudents()
            };
            courseTableModel.addRow(rowData);
        }
    }

    /**
     * Returns the selected course ID from the course table.
     * @return selected course ID or -1 if none selected
     */
    public int getSelectedCourseId() {
        int selectedRow = courseTable.getSelectedRow();
        if (selectedRow >= 0) {
            return (int) courseTableModel.getValueAt(selectedRow, 0);
        }
        return -1;
    }

    /**
     * Returns the text in the search field.
     * @return search text
     */
    public String getSearchText() {
        return searchField.getText().trim();
    }

    /**
     * Adds an ActionListener to the search button.
     * @param listener the ActionListener
     */
    public void addSearchListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the print student details button.
     * @param listener the ActionListener
     */
    public void addPrintStudentDetailsListener(ActionListener listener) {
        printStudentDetailsButton.addActionListener(listener);
    }

    /**
     * Returns a Course object constructed from the form inputs.
     * @return Course object
     * @throws NumberFormatException if course ID field is invalid
     */
    public Course getCourseFromForm() throws NumberFormatException {
        int courseId = Integer.parseInt(courseIdField.getText().trim());
        String courseName = courseNameField.getText().trim();
        String schedule = scheduleField.getText().trim();
        int credits = Integer.parseInt(creditsField.getText().trim());
        int maxStudents = Integer.parseInt(maxStudentsField.getText().trim());
        Teacher teacher = (Teacher) teacherComboBox.getSelectedItem();

        if (teacher == null) {
            throw new IllegalArgumentException("Teacher must be selected for the course.");
        }

        Course course = new Course(courseId, courseName, teacher);
        course.setSchedule(schedule);
        course.setCredits(credits);
        course.setMaxStudents(maxStudents);
        for (int i = 0; i < studentListModel.size(); i++) {
            course.addStudent(studentListModel.get(i));
        }
        return course;
    }

    // New public setter methods for controller to update form fields and student list

    public void setCourseIdField(String text) {
        courseIdField.setText(text);
    }

    public void setCourseNameField(String text) {
        courseNameField.setText(text);
    }

    public void setScheduleField(String text) {
        scheduleField.setText(text);
    }

    public void setCreditsField(String text) {
        creditsField.setText(text);
    }

    public void setMaxStudentsField(String text) {
        maxStudentsField.setText(text);
    }

    public void setTeacherComboBoxSelectedItem(Teacher teacher) {
        teacherComboBox.setSelectedItem(teacher);
    }

    public void clearStudentList() {
        studentListModel.clear();
    }

    public void addStudentToListModel(Student student) {
        if (!studentListModel.contains(student)) {
            studentListModel.addElement(student);
        }
    }

    /**
     * Clears all form fields.
     */
    public void clearForm() {
        courseIdField.setText("");
        courseNameField.setText("");
        scheduleField.setText("");
        creditsField.setText("");
        maxStudentsField.setText("");
        if (teacherComboBox.getItemCount() > 0) {
            teacherComboBox.setSelectedIndex(0);
        }
        studentListModel.clear();
    }

    /**
     * Adds an ActionListener to the add student button.
     * @param listener the ActionListener
     */
    public void addAddStudentListener(ActionListener listener) {
        addStudentButton.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the remove student button.
     * @param listener the ActionListener
     */
    public void addRemoveStudentListener(ActionListener listener) {
        removeStudentButton.addActionListener(listener);
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
     * Returns the selected student in the list.
     * @return selected Student or null if none selected
     */
    public Student getSelectedStudent() {
        return studentList.getSelectedValue();
    }

    /**
     * Adds a student to the enrolled students list.
     * @param student the student to add
     */
    public void addStudentToList(Student student) {
        if (!studentListModel.contains(student)) {
            studentListModel.addElement(student);
        }
    }

    /**
     * Removes a student from the enrolled students list.
     * @param student the student to remove
     */
    public void removeStudentFromList(Student student) {
        studentListModel.removeElement(student);
    }
}
