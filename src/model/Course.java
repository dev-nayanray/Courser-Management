package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a university course.
 * Contains a list of enrolled students and a reference to the teacher.
 */
public class Course {
    private int courseId;
    private String courseName;
    private Teacher teacher;
    private List<Student> enrolledStudents;
    private String schedule;
    private int credits;
    private int maxStudents;

    public Course(int courseId, String courseName, Teacher teacher) {
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher cannot be null for a Course.");
        }
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacher = teacher;
        this.enrolledStudents = new ArrayList<>();
        this.schedule = "";
        this.credits = 0;
        this.maxStudents = 0;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        if (credits < 0) {
            throw new IllegalArgumentException("Credits cannot be negative.");
        }
        this.credits = credits;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        if (maxStudents < 0) {
            throw new IllegalArgumentException("Max students cannot be negative.");
        }
        this.maxStudents = maxStudents;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    /**
     * Adds a student to the course.
     * @param student the student to add
     */
    public void addStudent(Student student) {
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
        }
    }

    /**
     * Removes a student from the course.
     * @param student the student to remove
     */
    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
    }

    @Override
    public String toString() {
        return String.format("Course: %s (ID: %d), Teacher: %s, Enrolled Students: %d",
                courseName, courseId, teacher.getFirstName() + " " + teacher.getLastName(), enrolledStudents.size());
    }
}
