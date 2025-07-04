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
public class TeacherController {
    private TeacherRegistrationPanel view;
    private DatabaseHelper dbHelper;

    public TeacherController(TeacherRegistrationPanel view, DatabaseHelper dbHelper) {
        this.view = view;
        this.dbHelper = dbHelper;

        this.view.addSubmitListener(new SubmitListener());
        this.view.addResetListener(e -> view.clearForm());
    }

    class SubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Teacher teacher = view.getTeacherFromForm();
                dbHelper.addTeacher(teacher);
                JOptionPane.showMessageDialog(view, "Teacher added successfully!");
                view.clearForm();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Invalid input: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
