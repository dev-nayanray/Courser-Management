package view;

import model.Course;
import model.Student;
import model.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Swing panel for course registration.
 * Provides form fields to input course details, select teacher, and manage enrolled students.
 */
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

    public CourseRegistrationPanel(List<Teacher> teachers, List<Student> students) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel courseIdLabel = new JLabel("Course ID:");
        courseIdField = new JTextField(15);

        JLabel courseNameLabel = new JLabel("Course Name:");
        courseNameField = new JTextField(15);

        JLabel scheduleLabel = new JLabel("Schedule:");
        scheduleField = new JTextField(15);

        JLabel creditsLabel = new JLabel("Credits:");
        creditsField = new JTextField(15);

        JLabel maxStudentsLabel = new JLabel("Max Students:");
        maxStudentsField = new JTextField(15);

        JLabel teacherLabel = new JLabel("Teacher:");
        teacherComboBox = new JComboBox<>();
        for (Teacher teacher : teachers) {
            teacherComboBox.addItem(teacher);
        }

        JLabel studentsLabel = new JLabel("Enrolled Students:");
        studentListModel = new DefaultListModel<>();
        for (Student student : students) {
            studentListModel.addElement(student);
        }
        studentList = new JList<>(studentListModel);
        studentList.setVisibleRowCount(5);
        studentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane studentListScrollPane = new JScrollPane(studentList);

        addStudentButton = new JButton("Add Student");
        removeStudentButton = new JButton("Remove Student");
        submitButton = new JButton("Submit");
        resetButton = new JButton("Reset");

        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

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
        add(new JLabel("Search Course by ID:"), gbc);
        gbc.gridx = 1;
        JTextField searchField = new JTextField(10);
        add(searchField, gbc);

        gbc.gridx = 0; gbc.gridy = 10;
        JButton searchButton = new JButton("Search");
        add(searchButton, gbc);

        gbc.gridx = 1; gbc.gridy = 10;
        JButton printStudentDetailsButton = new JButton("Print Student Details");
        add(printStudentDetailsButton, gbc);

        // Expose these components for controller
        this.searchField = searchField;
        this.searchButton = searchButton;
        this.printStudentDetailsButton = printStudentDetailsButton;
    }

    private JTextField searchField;
    private JButton searchButton;
    private JButton printStudentDetailsButton;

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
