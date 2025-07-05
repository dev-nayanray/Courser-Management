package controller;

import database.DatabaseHelper;
import database.DatabaseHelperAssessmentMarks;
import model.Course;
import model.Student;
import view.MarksPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Controller for handling marks assignment and report generation with detailed assessments and GPA calculation.
 */
public class MarksController {
    private MarksPanel view;
    private DatabaseHelperAssessmentMarks dbHelperAssessmentMarks;
    private DatabaseHelper dbHelper;

    public MarksController(MarksPanel view, DatabaseHelper dbHelper) {
        this.view = view;
        this.dbHelper = dbHelper;
        try {
            this.dbHelperAssessmentMarks = new DatabaseHelperAssessmentMarks();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Database connection error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        this.view.addAssignMarksListener(new AssignMarksListener());
        this.view.addGenerateReportListener(new GenerateReportListener());

        // Load initial data for courses and students
        loadInitialData();
    }

    private void loadInitialData() {
        try {
            List<Course> courses = dbHelper.getAllCourses();
            List<Student> students = dbHelper.getAllStudents();
            view.refreshData(courses, students);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Failed to load courses or students: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    class AssignMarksListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Course course = view.getSelectedCourse();
                Student student = view.getSelectedStudent();
                String assessmentType = view.getAssessmentType();
                String marksStr = view.getMarks();

                if (course == null || student == null || assessmentType == null || assessmentType.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Please select course, student, and assessment type.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int marks = Integer.parseInt(marksStr);
                if (marks < 0 || marks > 100) {
                    JOptionPane.showMessageDialog(view, "Marks must be between 0 and 100.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                dbHelperAssessmentMarks.addOrUpdateAssessmentMark(course.getCourseId(), student.getId(), assessmentType, marks);
                JOptionPane.showMessageDialog(view, "Marks assigned successfully.");
                view.clearMarksField();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Invalid marks input.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class GenerateReportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Course course = view.getSelectedCourse();
                if (course == null) {
                    JOptionPane.showMessageDialog(view, "Please select a course.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Generate detailed report with GPA calculation
                StringBuilder report = new StringBuilder();
                report.append("Grade Report for Course: ").append(course.getCourseName()).append("\\n");
                report.append("Student ID | Name | Assignment | Quiz | Final Exam | GPA\\n");
                report.append("---------------------------------------------------------\\n");

                for (Student student : course.getEnrolledStudents()) {
                    Map<String, Integer> marksMap = dbHelperAssessmentMarks.getAssessmentMarks(course.getCourseId(), student.getId());
                    int assignment = marksMap.getOrDefault("assignment", 0);
                    int quiz = marksMap.getOrDefault("quiz", 0);
                    int finalExam = marksMap.getOrDefault("final", 0);
                    double gpa = calculateGPA(assignment, quiz, finalExam);
                    report.append(String.format("%d | %s %s | %d | %d | %d | %.2f\\n",
                            student.getId(), student.getFirstName(), student.getLastName(),
                            assignment, quiz, finalExam, gpa));
                }
                view.setReportText(report.toString());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private double calculateGPA(int assignment, int quiz, int finalExam) {
        // Simple weighted average for GPA calculation
        double total = assignment * 0.3 + quiz * 0.3 + finalExam * 0.4;
        return total / 25.0; // Scale to 4.0 GPA scale
    }
}
