package model;

/**
 * Represents an enrollment of a student in a course.
 * Optionally includes marks.
 */
public class Enrollment {
    private Student student;
    private Course course;
    private Double marks; // Optional, can be null if not assigned

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.marks = null;
    }

    public Enrollment(Student student, Course course, Double marks) {
        this.student = student;
        this.course = course;
        this.marks = marks;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Double getMarks() {
        return marks;
    }

    public void setMarks(Double marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return String.format("Enrollment: %s in %s, Marks: %s",
                student.getFirstName() + " " + student.getLastName(),
                course.getCourseName(),
                marks != null ? marks.toString() : "N/A");
    }
}
