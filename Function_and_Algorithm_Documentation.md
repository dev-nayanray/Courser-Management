# Function and Algorithm Documentation

## 1. Introduction

This document provides detailed information about the key functions and algorithms implemented in the University Course Management System project. The system follows the Model-View-Controller (MVC) architecture, separating concerns between data models, user interface, and control logic. The project is developed in Java using Object-Oriented Programming (OOP) principles such as abstraction, encapsulation, inheritance, polymorphism, and composition.

## 2. Project Architecture and MVC Pattern

- **Model:** Represents the core data structures such as `Person` (abstract), `Student`, `Teacher`, `Course`, `Department`, and `Enrollment`.
- **View:** GUI components implemented as Swing panels for different functionalities (e.g., student registration, course management).
- **Controller:** Handles user interactions, business logic, and communication between the view and model/database.

## 3. CourseController

The `CourseController` manages course-related operations including adding new courses, searching courses by ID, printing student grade reports, and managing enrolled students.

### Key Functions and Algorithms

- **Adding a Course:** Validates input from the course registration form and adds the course to the database using `DatabaseHelper`.
- **Searching a Course:** Parses the course ID input, queries the database, and updates the UI with course details and enrolled students.
- **Printing Grade Reports:** Generates a grade report for a course by fetching data from the database and displaying it to the user.
- **Managing Enrolled Students:** Allows adding or removing students from the enrolled list with UI updates.

### Algorithm: Registering a Course

1. User inputs course details in the form.
2. Validate all fields for correct format and completeness.
3. Create a `Course` object with the input data.
4. Call `DatabaseHelper.addCourse(course)` to save the course.
5. Notify the user of success or display error messages if validation or database operations fail.

### Algorithm: Searching a Course by ID

1. User enters a course ID in the search field.
2. Validate the input is a valid integer.
3. Query the database using `DatabaseHelper.searchCourseByCode(courseId)`.
4. If found, populate the form fields with course details and enrolled students.
5. If not found, notify the user.

### Algorithm: Printing Grade Reports

1. User requests to print student grade report for a course.
2. Validate the course ID input.
3. Fetch the grade report string from `DatabaseHelper.generateGradeReport(courseId)`.
4. Display the report in a dialog box.

### Error Handling

- Input validation errors (e.g., invalid course ID format) are caught and displayed.
- Database errors are handled gracefully with user notifications.

## 4. StudentController

The `StudentController` oversees student registration and course enrollment processes.

### Key Functions and Algorithms

- **Loading Students and Courses:** Fetches all students and courses from the database to populate UI components.
- **Adding a Student:** Validates and adds new student records to the database.
- **Registering a Student to a Course:** Checks course capacity before enrollment. If the course is full, the student is added to a waitlist using `DatabaseHelperAssessmentMarks`.
- **Waitlist Management:** Implements logic to handle waitlisted students for full courses.

### Algorithm: Registering a Student to a Course with Waitlist Handling

1. User selects a student and a course.
2. Check if the course has reached its maximum capacity.
3. If not full:
    - Add enrollment record in the database.
    - Notify the user of successful registration.
4. If full:
    - Add the student to the waitlist using `DatabaseHelperAssessmentMarks`.
    - Notify the user that the student is waitlisted.

### Algorithm: Adding a New Student

1. User inputs student details in the form.
2. Validate all fields for correctness.
3. Create a `Student` object with the input data.
4. Call `DatabaseHelper.addStudent(student)` to save the student.
5. Notify the user of success or display error messages.

### Error Handling

- Validates user inputs and displays appropriate error messages.
- Handles database exceptions with user alerts.

## 5. EnrollmentController

The `EnrollmentController` manages enrollment data display and removal.

### Key Functions and Algorithms

- **Loading Enrollments:** Retrieves students enrolled in a selected course and updates the enrollment table in the UI.
- **Removing Enrollments:** Allows removal of a student from a course enrollment and refreshes the UI accordingly.

### Algorithm: Removing an Enrollment

1. User selects a course and a student from the enrollment panel.
2. Validate selections are made.
3. Call `DatabaseHelper.removeEnrollment(courseId, studentId)` to delete the enrollment.
4. Refresh the enrollment list in the UI.
5. Notify the user of success or errors.

### Algorithm: Loading Enrollments

1. User selects a course.
2. Query the database for students enrolled in the course.
3. Update the enrollment table in the UI with the retrieved data.

### Error Handling

- Ensures both course and student selections are made before performing actions.
- Catches and reports database errors.

## 6. Database Interaction

Controllers interact with `DatabaseHelper` and `DatabaseHelperAssessmentMarks` classes to perform CRUD operations on the database. These helpers abstract the SQL queries and provide methods for fetching, adding, updating, and deleting records related to students, teachers, courses, enrollments, and waitlists.

## 7. Error Handling and User Feedback

Throughout the controllers, input validation and exception handling are implemented to ensure robustness. Users receive clear feedback via dialog boxes for successful operations, warnings, and error messages, enhancing usability and reliability.

---

This documentation summarizes the core functions and algorithms that enable the University Course Management System to fulfill its requirements effectively.
