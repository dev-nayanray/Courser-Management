    package view;

import model.Course;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import util.UIStyleUtil;

/**
 * GUI panel for marks assignment and report generation.
 */
public class MarksPanel extends JPanel {
    private JComboBox<Course> courseComboBox;
    private JComboBox<Student> studentComboBox;
    private JComboBox<String> assessmentTypeComboBox;
    private JTextField marksField;
    private JButton assignMarksButton;
    private JButton generateReportButton;
    private JTextArea reportArea;

    public MarksPanel() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        UIStyleUtil.stylePanel(inputPanel);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel courseLabel = new JLabel("Course:");
        UIStyleUtil.styleLabel(courseLabel);
        courseComboBox = new JComboBox<>();
        UIStyleUtil.styleComboBox(courseComboBox);
        courseComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof model.Course) {
                    setText(((model.Course) value).getCourseName());
                }
                return this;
            }
        });

        JLabel studentLabel = new JLabel("Student:");
        UIStyleUtil.styleLabel(studentLabel);
        studentComboBox = new JComboBox<>();
        UIStyleUtil.styleComboBox(studentComboBox);
        studentComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof model.Student) {
                    model.Student student = (model.Student) value;
                    setText(student.getFirstName() + " " + student.getLastName());
                }
                return this;
            }
        });

        JLabel assessmentTypeLabel = new JLabel("Assessment Type:");
        UIStyleUtil.styleLabel(assessmentTypeLabel);
        assessmentTypeComboBox = new JComboBox<>(new String[]{"assignment", "quiz", "final"});
        UIStyleUtil.styleComboBox(assessmentTypeComboBox);

        JLabel marksLabel = new JLabel("Marks:");
        UIStyleUtil.styleLabel(marksLabel);
        marksField = new JTextField(5);
        UIStyleUtil.styleTextField(marksField);

        assignMarksButton = new JButton("Assign Marks");
        UIStyleUtil.styleButton(assignMarksButton);
        generateReportButton = new JButton("Generate Report");
        UIStyleUtil.styleButton(generateReportButton);

        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(courseLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(courseComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(studentLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(studentComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(assessmentTypeLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(assessmentTypeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(marksLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(marksField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(assignMarksButton, gbc);
        gbc.gridx = 1;
        inputPanel.add(generateReportButton, gbc);

        add(inputPanel, BorderLayout.NORTH);

        reportArea = new JTextArea(15, 50);
        reportArea.setEditable(false);
        // JTextArea is not a JTextField, so use default font and background instead of styleTextField
        reportArea.setFont(UIStyleUtil.FIELD_FONT);
        reportArea.setBackground(UIStyleUtil.FIELD_BACKGROUND_COLOR);
        reportArea.setBorder(UIStyleUtil.FIELD_BORDER);
        JScrollPane scrollPane = new JScrollPane(reportArea);
        UIStyleUtil.styleScrollPane(scrollPane);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateCourseComboBox(List<Course> courses) {
        courseComboBox.removeAllItems();
        for (Course course : courses) {
            courseComboBox.addItem(course);
        }
    }

    public void updateStudentComboBox(List<Student> students) {
        studentComboBox.removeAllItems();
        for (Student student : students) {
            studentComboBox.addItem(student);
        }
    }

    /**
     * Refreshes the course and student combo boxes with the latest data.
     * @param courses List of courses from the database
     * @param students List of students from the database
     */
    public void refreshData(List<Course> courses, List<Student> students) {
        updateCourseComboBox(courses);
        updateStudentComboBox(students);
    }

    public Course getSelectedCourse() {
        return (Course) courseComboBox.getSelectedItem();
    }

    public Student getSelectedStudent() {
        return (Student) studentComboBox.getSelectedItem();
    }

    public String getAssessmentType() {
        return (String) assessmentTypeComboBox.getSelectedItem();
    }

    public String getMarks() {
        return marksField.getText().trim();
    }

    public void clearMarksField() {
        marksField.setText("");
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
}
