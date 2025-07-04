package view;

import model.Course;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Swing panel for assigning marks to students and generating reports.
 */
public class MarksPanel extends JPanel {
    private JComboBox<Course> courseComboBox;
    private JComboBox<Student> studentComboBox;
    private JTextField marksField;
    private JButton assignMarksButton;
    private JButton generateReportButton;
    private JTextArea reportArea;

    public MarksPanel(List<Course> courses, List<Student> students) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel courseLabel = new JLabel("Select Course:");
        courseComboBox = new JComboBox<>();
        for (Course course : courses) {
            courseComboBox.addItem(course);
        }

        JLabel studentLabel = new JLabel("Select Student:");
        studentComboBox = new JComboBox<>();
        for (Student student : students) {
            studentComboBox.addItem(student);
        }

        JLabel marksLabel = new JLabel("Marks:");
        marksField = new JTextField(10);

        assignMarksButton = new JButton("Assign Marks");
        generateReportButton = new JButton("Generate Report");

        reportArea = new JTextArea(10, 30);
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);

        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        add(courseLabel, gbc);
        gbc.gridx = 1;
        add(courseComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(studentLabel, gbc);
        gbc.gridx = 1;
        add(studentComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(marksLabel, gbc);
        gbc.gridx = 1;
        add(marksField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(assignMarksButton, gbc);
        gbc.gridx = 1;
        add(generateReportButton, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        add(scrollPane, gbc);
    }

    public Course getSelectedCourse() {
        return (Course) courseComboBox.getSelectedItem();
    }

    public Student getSelectedStudent() {
        return (Student) studentComboBox.getSelectedItem();
    }

    public String getMarks() {
        return marksField.getText().trim();
    }

    public void addAssignMarksListener(ActionListener listener) {
        assignMarksButton.addActionListener(listener);
    }

    public void addGenerateReportListener(ActionListener listener) {
        generateReportButton.addActionListener(listener);
    }

    public void setReportText(String text) {
        reportArea.setText(text);
    }

    public void clearMarksField() {
        marksField.setText("");
    }
}
