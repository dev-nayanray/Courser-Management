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
        add(teacherLabel, gbc);
        gbc.gridx = 1;
        add(teacherComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(studentsLabel, gbc);
        gbc.gridx = 1;
        add(studentListScrollPane, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(addStudentButton, gbc);
        gbc.gridx = 1;
        add(removeStudentButton, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        add(submitButton, gbc);
        gbc.gridx = 1;
        add(resetButton, gbc);
    }

    /**
     * Returns a Course object constructed from the form inputs.
     * @return Course object
     * @throws NumberFormatException if course ID field is invalid
     */
    public Course getCourseFromForm() throws NumberFormatException {
        int courseId = Integer.parseInt(courseIdField.getText().trim());
        String courseName = courseNameField.getText().trim();
        Teacher teacher = (Teacher) teacherComboBox.getSelectedItem();

        if (teacher == null) {
            throw new IllegalArgumentException("Teacher must be selected for the course.");
        }

        Course course = new Course(courseId, courseName, teacher);
        for (int i = 0; i < studentListModel.size(); i++) {
            course.addStudent(studentListModel.get(i));
        }
        return course;
    }

    /**
     * Clears all form fields.
     */
    public void clearForm() {
        courseIdField.setText("");
        courseNameField.setText("");
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
