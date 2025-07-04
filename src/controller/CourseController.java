package controller;

import database.DatabaseHelper;
import model.Course;
import model.Student;
import model.Teacher;
import view.CourseRegistrationPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller for handling course registration interactions.
 * Connects the CourseRegistrationPanel with the DatabaseHelper.
 */
public class CourseController {
    private CourseRegistrationPanel view;
    private DatabaseHelper dbHelper;
    private List<Student> allStudents;
    private List<Teacher> allTeachers;

    public CourseController(CourseRegistrationPanel view, DatabaseHelper dbHelper, List<Teacher> teachers, List<Student> students) {
        this.view = view;
        this.dbHelper = dbHelper;
        this.allTeachers = teachers;
        this.allStudents = students;

        this.view.addSubmitListener(new SubmitListener());
        this.view.addResetListener(e -> view.clearForm());
        this.view.addAddStudentListener(new AddStudentListener());
        this.view.addRemoveStudentListener(new RemoveStudentListener());
    }

    class SubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Course course = view.getCourseFromForm();
                dbHelper.addCourse(course);
                JOptionPane.showMessageDialog(view, "Course added successfully!");
                view.clearForm();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Invalid input: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class AddStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // For simplicity, show a dialog to select a student to add
            Student selected = (Student) JOptionPane.showInputDialog(view, "Select student to add:",
                    "Add Student", JOptionPane.PLAIN_MESSAGE, null,
                    allStudents.toArray(), null);
            if (selected != null) {
                view.addStudentToList(selected);
            }
        }
    }

    class RemoveStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Student selected = view.getSelectedStudent();
            if (selected != null) {
                view.removeStudentFromList(selected);
            } else {
                JOptionPane.showMessageDialog(view, "No student selected to remove.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
