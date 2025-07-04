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
public class StudentController {
    private StudentRegistrationPanel view;
    private DatabaseHelper dbHelper;

    public StudentController(StudentRegistrationPanel view, DatabaseHelper dbHelper) {
        this.view = view;
        this.dbHelper = dbHelper;

        this.view.addSubmitListener(new SubmitListener());
        this.view.addResetListener(e -> view.clearForm());
    }

    class SubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Student student = view.getStudentFromForm();
                dbHelper.addStudent(student);
                JOptionPane.showMessageDialog(view, "Student added successfully!");
                view.clearForm();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Invalid input: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
