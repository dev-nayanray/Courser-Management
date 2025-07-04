package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student in the university system.
 * Extends the abstract Person class.
 */
public class Student extends Person {
    private String major;
    private int year;
    private List<Course> courses;

    public Student(int id, String firstName, String lastName, String email, String major, int year) {
        super(id, firstName, lastName, email);
        this.major = major;
        this.year = year;
        this.courses = new ArrayList<>();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
            course.addStudent(this);
        }
    }

    public void removeCourse(Course course) {
        if (courses.contains(course)) {
            courses.remove(course);
            course.removeStudent(this);
        }
    }

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public void printDetails() {
        System.out.println("Student Details:");
        System.out.println("ID: " + getId());
        System.out.println("Name: " + getFirstName() + " " + getLastName());
        System.out.println("Email: " + getEmail());
        System.out.println("Major: " + major);
        System.out.println("Year: " + year);
        System.out.println("Courses Registered:");
        for (Course c : courses) {
            System.out.println(" - " + c.getCourseName());
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Major: %s, Year: %d, Courses: %d", major, year, courses.size());
    }
}
