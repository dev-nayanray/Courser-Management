package controller;

import database.DatabaseHelper;
import model.Course;
import model.Student;
import view.MarksPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Controller for handling marks assignment and report generation.
 */
public class MarksController {
    private MarksPanel view;
    private DatabaseHelper dbHelper;

    public MarksController(MarksPanel view, DatabaseHelper dbHelper) {
        this.view = view;
        this.dbHelper = dbHelper;

        this.view.addAssignMarksListener(new AssignMarksListener());
        this.view.addGenerateReportListener(new GenerateReportListener());
    }

    class AssignMarksListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Course course = view.getSelectedCourse();
                Student student = view.getSelectedStudent();
                String marksStr = view.getMarks();

                if (course == null || student == null) {
                    JOptionPane.showMessageDialog(view, "Please select both course and student.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int marks = Integer.parseInt(marksStr);
                if (marks < 0 || marks > 100) {
                    JOptionPane.showMessageDialog(view, "Marks must be between 0 and 100.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                dbHelper.assignMarks(course.getCourseId(), student.getId(), marks);
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
                String report = dbHelper.generateGradeReport(course.getCourseId());
                view.setReportText(report);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
