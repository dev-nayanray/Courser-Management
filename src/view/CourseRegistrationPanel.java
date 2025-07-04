package view;

import model.Course;
import model.Student;
import model.Teacher;

import javax.swing.*;
import javax.swing.DefaultListCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.table.DefaultTableModel;

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
        GridBagConstraints gbc = new GridBagConstraints();

        // Initialize components
        JLabel courseIdLabel = new JLabel("Course ID:");
        courseIdField = new JTextField(10);

        JLabel courseNameLabel = new JLabel("Course Name:");
        courseNameField = new JTextField(10);

        JLabel scheduleLabel = new JLabel("Schedule:");
        scheduleField = new JTextField(10);

        JLabel creditsLabel = new JLabel("Credits:");
        creditsField = new JTextField(10);

        JLabel maxStudentsLabel = new JLabel("Max Students:");
        maxStudentsField = new JTextField(10);

        JLabel teacherLabel = new JLabel("Teacher:");
        teacherComboBox = new JComboBox<>();

        JLabel studentsLabel = new JLabel("Enrolled Students:");
        studentListModel = new DefaultListModel<>();
        studentList = new JList<>(studentListModel);
        JScrollPane studentListScrollPane = new JScrollPane(studentList);

        addStudentButton = new JButton("Add Student");
        removeStudentButton = new JButton("Remove Student");
        submitButton = new JButton("Submit");
        resetButton = new JButton("Reset");

        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        // Set fonts
        courseIdLabel.setFont(labelFont);
        courseIdField.setFont(fieldFont);
        courseNameLabel.setFont(labelFont);
        courseNameField.setFont(fieldFont);
        scheduleLabel.setFont(labelFont);
        scheduleField.setFont(fieldFont);
        creditsLabel.setFont(labelFont);
        creditsField.setFont(fieldFont);
        maxStudentsLabel.setFont(labelFont);
        maxStudentsField.setFont(fieldFont);
        teacherLabel.setFont(labelFont);
        teacherComboBox.setFont(fieldFont);
        studentsLabel.setFont(labelFont);
        studentList.setFont(fieldFont);
        addStudentButton.setFont(buttonFont);
        removeStudentButton.setFont(buttonFont);
        submitButton.setFont(buttonFont);
        resetButton.setFont(buttonFont);

        // Layout components using GridBagLayout
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        add(courseIdLabel, gbc);
        gbc.gridx = 1;
        add(courseIdField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(courseNameLabel, gbc);
        gbc.gridx = 1;
        add(courseNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(scheduleLabel, gbc);
        gbc.gridx = 1;
        add(scheduleField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(creditsLabel, gbc);
        gbc.gridx = 1;
        add(creditsField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(maxStudentsLabel, gbc);
        gbc.gridx = 1;
        add(maxStudentsField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        add(teacherLabel, gbc);
        gbc.gridx = 1;
        add(teacherComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        add(studentsLabel, gbc);
        gbc.gridx = 1;
        add(studentListScrollPane, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        add(addStudentButton, gbc);
        gbc.gridx = 1;
        add(removeStudentButton, gbc);

        gbc.gridx = 0; gbc.gridy = 8;
        add(submitButton, gbc);
        gbc.gridx = 1;
        add(resetButton, gbc);

        // Additional controls for search and print
        gbc.gridx = 0; gbc.gridy = 9;
        JLabel searchLabel = new JLabel("Search Course by ID:");
        searchLabel.setFont(labelFont);
        add(searchLabel, gbc);
        gbc.gridx = 1;
        searchField = new JTextField(10);
        searchField.setFont(fieldFont);
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        searchField.setBackground(Color.WHITE);
        add(searchField, gbc);

        gbc.gridx = 0; gbc.gridy = 10;
        searchButton = new JButton("Search");
        searchButton.setFont(buttonFont);
        searchButton.setFocusPainted(false);
        searchButton.setBackground(new Color(70, 130, 180));
        searchButton.setForeground(Color.WHITE);
        searchButton.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchButton.setBackground(new Color(100, 149, 237));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchButton.setBackground(new Color(70, 130, 180));
            }
        });
        add(searchButton, gbc);

        gbc.gridx = 1; gbc.gridy = 10;
        printStudentDetailsButton = new JButton("Print Student Details");
        printStudentDetailsButton.setFont(buttonFont);
        printStudentDetailsButton.setFocusPainted(false);
        printStudentDetailsButton.setBackground(new Color(70, 130, 180));
        printStudentDetailsButton.setForeground(Color.WHITE);
        printStudentDetailsButton.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        printStudentDetailsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        printStudentDetailsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                printStudentDetailsButton.setBackground(new Color(100, 149, 237));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                printStudentDetailsButton.setBackground(new Color(70, 130, 180));
            }
        });
        add(printStudentDetailsButton, gbc);

        // Initialize course table
        initializeCourseTable();
    }

    public CourseRegistrationPanel(List<Teacher> teachers, List<Student> students) {
        this();
        for (Teacher teacher : teachers) {
            teacherComboBox.addItem(teacher);
        }
        for (Student student : students) {
            studentListModel.addElement(student);
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
        JScrollPane courseTableScrollPane = new JScrollPane(courseTable);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 11;
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
