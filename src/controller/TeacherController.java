package controller;

import database.DatabaseHelper;
import model.Teacher;
import view.TeacherRegistrationPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Controller for handling teacher registration interactions.
 * Connects the TeacherRegistrationPanel with the DatabaseHelper.
 */
import model.Course;
import java.util.List;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class TeacherController {
    private TeacherRegistrationPanel view;
    private DatabaseHelper dbHelper;

    public TeacherController(TeacherRegistrationPanel view, DatabaseHelper dbHelper) {
        this.view = view;
        this.dbHelper = dbHelper;

        this.view.addSubmitListener(new SubmitListener());
        this.view.addResetListener(e -> view.clearForm());
        this.view.addAssignCourseListener(new AssignCourseListener());

        loadTeachers();
        loadCourses();
    }

    private void loadTeachers() {
        try {
            List<Teacher> teachers = dbHelper.getAllTeachers();
            view.updateTeacherTable(teachers);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Failed to load teachers: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
                Teacher teacher = view.getTeacherFromForm();
                dbHelper.addTeacher(teacher);
                JOptionPane.showMessageDialog(view, "Teacher added successfully!");
                view.clearForm();
                loadTeachers();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Invalid input: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class AssignCourseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Teacher selectedTeacher = view.getSelectedTeacher();
                Course selectedCourse = view.getSelectedCourse();
                if (selectedTeacher == null) {
                    JOptionPane.showMessageDialog(view, "Please select a teacher from the table.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (selectedCourse == null) {
                    JOptionPane.showMessageDialog(view, "Please select a course from the dropdown.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Assign teacher to course by updating course's teacherId
                selectedCourse.setTeacher(selectedTeacher);
                dbHelper.updateCourse(selectedCourse);
                JOptionPane.showMessageDialog(view, "Teacher assigned to course successfully!");
                loadTeachers();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
