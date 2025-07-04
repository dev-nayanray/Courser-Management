package model;

/**
 * Abstract base class representing a person in the university system.
 * Implements common attributes and methods for Student and Teacher.
 */
public abstract class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public Person(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getters and setters with encapsulation
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    } 

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    } 

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    } 

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Abstract method to get the role of the person.
     * @return role as String
     */
    public abstract String getRole();

    /**
     * Polymorphic method to print details of the person.
     * Subclasses should override this method.
     */
    public void printDetails() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return String.format("%s: %s %s (Email: %s)", getRole(), firstName, lastName, email);
    }
}
