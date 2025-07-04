package model;

/**
 * Represents a teacher in the university system.
 * Extends the abstract Person class.
 */
public class Teacher extends Person {
    private String department;
    private String title;

    public Teacher(int id, String firstName, String lastName, String email, String department, String title) {
        super(id, firstName, lastName, email);
        this.department = department;
        this.title = title;
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
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Department: %s, Title: %s", department, title);
    }
}
