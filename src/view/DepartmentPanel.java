package view;

import model.Department;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Swing panel for department management.
 * Provides form fields to input department details and a table to display departments.
 */
public class DepartmentPanel extends JPanel {
    private JTextField idField;
    private JTextField nameField;
    private JButton submitButton;
    private JButton resetButton;

    private JTable departmentTable;
    private DefaultTableModel departmentTableModel;

    public DepartmentPanel() {
        setLayout(new BorderLayout());

        // Top panel for department form
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel idLabel = new JLabel("Department ID:");
        idField = new JTextField(15);

        JLabel nameLabel = new JLabel("Department Name:");
        nameField = new JTextField(15);

        submitButton = new JButton("Submit");
        resetButton = new JButton("Reset");

        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(idLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(submitButton, gbc);
        gbc.gridx = 1;
        formPanel.add(resetButton, gbc);

        add(formPanel, BorderLayout.NORTH);

        // Center panel for department table
        departmentTableModel = new DefaultTableModel(new Object[]{"ID", "Name"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Non-editable table
            }
        };
        departmentTable = new JTable(departmentTableModel);
        JScrollPane tableScrollPane = new JScrollPane(departmentTable);
        add(tableScrollPane, BorderLayout.CENTER);
    }

    /**
     * Returns a Department object constructed from the form inputs.
     * @return Department object
     * @throws NumberFormatException if ID field is invalid
     */
    public Department getDepartmentFromForm() throws NumberFormatException {
        int id = Integer.parseInt(idField.getText().trim());
        String name = nameField.getText().trim();
        return new Department(id, name);
    }

    /**
     * Clears all form fields.
     */
    public void clearForm() {
        idField.setText("");
        nameField.setText("");
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
     * Updates the department table with the given list of departments.
     * @param departments list of departments
     */
    public void updateDepartmentTable(List<Department> departments) {
        departmentTableModel.setRowCount(0);
        for (Department d : departments) {
            departmentTableModel.addRow(new Object[]{d.getId(), d.getName()});
        }
    }
}
