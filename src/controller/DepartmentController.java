package controller;

import database.DatabaseHelper;
import model.Department;
import view.DepartmentPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller for handling department management interactions.
 * Connects the DepartmentPanel with the DatabaseHelper.
 */
public class DepartmentController {
    private DepartmentPanel view;
    private DatabaseHelper dbHelper;

    public DepartmentController(DepartmentPanel view, DatabaseHelper dbHelper) {
        this.view = view;
        this.dbHelper = dbHelper;

        this.view.addSubmitListener(new SubmitListener());
        this.view.addResetListener(e -> view.clearForm());

        loadDepartments();
    }

    private void loadDepartments() {
        try {
            List<Department> departments = dbHelper.getAllDepartments();
            view.updateDepartmentTable(departments);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Failed to load departments: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    class SubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Department department = view.getDepartmentFromForm();
                dbHelper.addDepartment(department);
                JOptionPane.showMessageDialog(view, "Department added successfully!");
                view.clearForm();
                loadDepartments();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Invalid input: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
