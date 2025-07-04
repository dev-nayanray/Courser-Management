package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a teacher in the university system.
 * Extends the abstract Person class.
 */
public class Teacher extends Person {
    private String department;
    private String title;
    private List<Course> coursesTaught;

    public Teacher(int id, String firstName, String lastName, String email, String department, String title) {
        super(id, firstName, lastName, email);
        this.department = department;
        this.title = title;
        this.coursesTaught = new ArrayList<>();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Course> getCoursesTaught() {
        return coursesTaught;
    }

    public void addCourse(Course course) {
        if (!coursesTaught.contains(course)) {
            coursesTaught.add(course);
            course.setTeacher(this);
        }
    }

    public void removeCourse(Course course) {
        if (coursesTaught.contains(course)) {
            coursesTaught.remove(course);
            if (course.getTeacher() == this) {
                course.setTeacher(null);
            }
        }
    }

    @Override
    public String getRole() {
        return "Teacher";
    }

    @Override
    public void printDetails() {
        System.out.println("Teacher Details:");
        System.out.println("ID: " + getId());
        System.out.println("Name: " + getFirstName() + " " + getLastName());
        System.out.println("Email: " + getEmail());
        System.out.println("Department: " + department);
        System.out.println("Title: " + title);
        System.out.println("Courses Taught:");
        for (Course c : coursesTaught) {
            System.out.println(" - " + c.getCourseName());
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Department: %s, Title: %s, Courses Taught: %d", department, title, coursesTaught.size());
    }
}
