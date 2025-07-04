package view;

import model.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Swing panel for teacher registration.
 * Provides form fields to input teacher details and buttons to submit or reset.
 */
public class TeacherRegistrationPanel extends JPanel {
    private JTextField idField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField departmentField;
    private JTextField titleField;
    private JButton submitButton;
    private JButton resetButton;

    public TeacherRegistrationPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel idLabel = new JLabel("Teacher ID:");
        idField = new JTextField(15);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField(15);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField(15);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(15);

        JLabel departmentLabel = new JLabel("Department:");
        departmentField = new JTextField(15);

        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField(15);

        submitButton = new JButton("Submit");
        resetButton = new JButton("Reset");

        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        add(idLabel, gbc);
        gbc.gridx = 1;
        add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(firstNameLabel, gbc);
        gbc.gridx = 1;
        add(firstNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(lastNameLabel, gbc);
        gbc.gridx = 1;
        add(lastNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(emailLabel, gbc);
        gbc.gridx = 1;
        add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(departmentLabel, gbc);
        gbc.gridx = 1;
        add(departmentField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        add(titleLabel, gbc);
        gbc.gridx = 1;
        add(titleField, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        add(submitButton, gbc);
        gbc.gridx = 1;
        add(resetButton, gbc);
    }

    /**
     * Returns a Teacher object constructed from the form inputs.
     * @return Teacher object
     * @throws NumberFormatException if ID field is invalid
     */
    public Teacher getTeacherFromForm() throws NumberFormatException {
        int id = Integer.parseInt(idField.getText().trim());
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String department = departmentField.getText().trim();
        String title = titleField.getText().trim();

        return new Teacher(id, firstName, lastName, email, department, title);
    }

    /**
     * Clears all form fields.
     */
    public void clearForm() {
        idField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        departmentField.setText("");
        titleField.setText("");
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
}
