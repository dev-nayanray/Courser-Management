package model;

/**
 * Represents a student in the university system.
 * Extends the abstract Person class.
 */
public class Student extends Person {
    private String major;
    private int year;

    public Student(int id, String firstName, String lastName, String email, String major, int year) {
        super(id, firstName, lastName, email);
        this.major = major;
        this.year = year;
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
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Major: %s, Year: %d", major, year);
    }
}
