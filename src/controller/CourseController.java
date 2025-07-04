package controller;

import database.DatabaseHelper;
import model.Course;
import model.Student;
import model.Teacher;
import view.CourseRegistrationPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller for handling course registration interactions.
 * Connects the CourseRegistrationPanel with the DatabaseHelper.
 */
public class CourseController {
    private CourseRegistrationPanel view;
    private DatabaseHelper dbHelper;
    private List<Student> allStudents;
    private List<Teacher> allTeachers;

    public CourseController(CourseRegistrationPanel view, DatabaseHelper dbHelper, List<Teacher> teachers, List<Student> students) {
        this.view = view;
        this.dbHelper = dbHelper;
        this.allTeachers = teachers;
        this.allStudents = students;

        this.view.addSubmitListener(new SubmitListener());
        this.view.addResetListener(e -> view.clearForm());
        this.view.addAddStudentListener(new AddStudentListener());
        this.view.addRemoveStudentListener(new RemoveStudentListener());
        this.view.addSearchListener(new SearchListener());
        this.view.addPrintStudentDetailsListener(new PrintStudentDetailsListener());
    }

    class SubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Course course = view.getCourseFromForm();
                dbHelper.addCourse(course);
                JOptionPane.showMessageDialog(view, "Course added successfully!");
                view.clearForm();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Invalid input: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String searchText = view.getSearchText();
                if (searchText.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Please enter a course ID to search.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int courseId = Integer.parseInt(searchText);
                Course course = dbHelper.searchCourseByCode(courseId);
                if (course == null) {
                    JOptionPane.showMessageDialog(view, "Course not found.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                // Update form with course details
                view.clearForm();
                view.setCourseIdField(String.valueOf(course.getCourseId()));
                view.setCourseNameField(course.getCourseName());
                view.setScheduleField(course.getSchedule());
                view.setCreditsField(String.valueOf(course.getCredits()));
                view.setMaxStudentsField(String.valueOf(course.getMaxStudents()));
                view.setTeacherComboBoxSelectedItem(course.getTeacher());
                // Update enrolled students list
                view.clearStudentList();
                for (Student s : course.getEnrolledStudents()) {
                    view.addStudentToListModel(s);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Invalid course ID format.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class PrintStudentDetailsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String searchText = view.getSearchText();
                if (searchText.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Please enter a course ID to print student details.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int courseId = Integer.parseInt(searchText);
                String report = dbHelper.generateGradeReport(courseId);
                JOptionPane.showMessageDialog(view, report, "Grade Report", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Invalid course ID format.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class AddStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // For simplicity, show a dialog to select a student to add
            Student selected = (Student) JOptionPane.showInputDialog(view, "Select student to add:",
                    "Add Student", JOptionPane.PLAIN_MESSAGE, null,
                    allStudents.toArray(), null);
            if (selected != null) {
                view.addStudentToList(selected);
            }
        }
    }

    class RemoveStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Student selected = view.getSelectedStudent();
            if (selected != null) {
                view.removeStudentFromList(selected);
            } else {
                JOptionPane.showMessageDialog(view, "No student selected to remove.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
