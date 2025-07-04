package controller;

import database.DatabaseHelper;
import model.Course;
import model.Student;
import view.EnrollmentPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller for handling enrollment management interactions.
 * Connects the EnrollmentPanel with the DatabaseHelper.
 */
public class EnrollmentController {
    private EnrollmentPanel view;
    private DatabaseHelper dbHelper;

    public EnrollmentController(EnrollmentPanel view, DatabaseHelper dbHelper) {
        this.view = view;
        this.dbHelper = dbHelper;

        this.view.addLoadEnrollmentsListener(new LoadEnrollmentsListener());
        this.view.addRemoveEnrollmentListener(new RemoveEnrollmentListener());
    }

    class LoadEnrollmentsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Course selectedCourse = view.getSelectedCourse();
                if (selectedCourse == null) {
                    JOptionPane.showMessageDialog(view, "Please select a course.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                List<Student> enrolledStudents = dbHelper.getStudentsByCourse(selectedCourse.getCourseId());
                view.updateEnrollmentTable(enrolledStudents);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Failed to load enrollments: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class RemoveEnrollmentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Course selectedCourse = view.getSelectedCourse();
                Student selectedStudent = view.getSelectedStudent();
                if (selectedCourse == null || selectedStudent == null) {
                    JOptionPane.showMessageDialog(view, "Please select both a course and a student.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                dbHelper.removeEnrollment(selectedCourse.getCourseId(), selectedStudent.getId());
                JOptionPane.showMessageDialog(view, "Enrollment removed successfully.");
                // Refresh enrollment list
                List<Student> enrolledStudents = dbHelper.getStudentsByCourse(selectedCourse.getCourseId());
                view.updateEnrollmentTable(enrolledStudents);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Failed to remove enrollment: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
