package controller;

import database.DatabaseHelper;
import model.Student;
import view.StudentRegistrationPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Controller for handling student registration interactions.
 * Connects the StudentRegistrationPanel with the DatabaseHelper.
 */
import model.Course;
import java.util.List;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class StudentController {
    private StudentRegistrationPanel view;
    private DatabaseHelper dbHelper;

    public StudentController(StudentRegistrationPanel view, DatabaseHelper dbHelper) {
        this.view = view;
        this.dbHelper = dbHelper;

        this.view.addSubmitListener(new SubmitListener());
        this.view.addResetListener(e -> view.clearForm());
        this.view.addRegisterCourseListener(new RegisterCourseListener());

        loadStudents();
        loadCourses();
    }

    private void loadStudents() {
        try {
            List<Student> students = dbHelper.getAllStudents();
            view.updateStudentTable(students);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Failed to load students: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadCourses() {
        try {
            List<Course> courses = dbHelper.getAllCourses();
            view.updateCourseComboBox(courses);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Failed to load courses: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    class SubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Student student = view.getStudentFromForm();
                dbHelper.addStudent(student);
                JOptionPane.showMessageDialog(view, "Student added successfully!");
                view.clearForm();
                loadStudents();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Invalid input: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class RegisterCourseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Student selectedStudent = view.getSelectedStudent();
                Course selectedCourse = view.getSelectedCourse();
                if (selectedStudent == null) {
                    JOptionPane.showMessageDialog(view, "Please select a student from the table.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (selectedCourse == null) {
                    JOptionPane.showMessageDialog(view, "Please select a course from the dropdown.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                dbHelper.addEnrollment(selectedCourse.getCourseId(), selectedStudent.getId());
                JOptionPane.showMessageDialog(view, "Student registered to course successfully!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
