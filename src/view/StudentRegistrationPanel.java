package view;

import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Swing panel for student registration.
 * Provides form fields to input student details and buttons to submit or reset.
 */
public class StudentRegistrationPanel extends JPanel {
    private JTextField idField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField majorField;
    private JTextField yearField;
    private JButton submitButton;
    private JButton resetButton;

    public StudentRegistrationPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel idLabel = new JLabel("Student ID:");
        idField = new JTextField(15);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField(15);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField(15);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(15);

        JLabel majorLabel = new JLabel("Major:");
        majorField = new JTextField(15);

        JLabel yearLabel = new JLabel("Year:");
        yearField = new JTextField(15);

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
        add(majorLabel, gbc);
        gbc.gridx = 1;
        add(majorField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        add(yearLabel, gbc);
        gbc.gridx = 1;
        add(yearField, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        add(submitButton, gbc);
        gbc.gridx = 1;
        add(resetButton, gbc);
    }

    /**
     * Returns a Student object constructed from the form inputs.
     * @return Student object
     * @throws NumberFormatException if ID or year fields are invalid
     */
    public Student getStudentFromForm() throws NumberFormatException {
        int id = Integer.parseInt(idField.getText().trim());
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String major = majorField.getText().trim();
        int year = Integer.parseInt(yearField.getText().trim());

        return new Student(id, firstName, lastName, email, major, year);
    }

    /**
     * Clears all form fields.
     */
    public void clearForm() {
        idField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        majorField.setText("");
        yearField.setText("");
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
