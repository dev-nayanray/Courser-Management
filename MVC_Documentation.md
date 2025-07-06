# MVC Architecture Documentation for University Course Management System

## Overview
![MVC Diagram](https://github.com/dev-nayanray/Courser-Management/blob/main/mvc.png?raw=true)
This project follows the Model-View-Controller (MVC) architectural pattern to separate concerns and organize the codebase effectively. The MVC pattern divides the application into three interconnected components:

- **Model:** Manages the data, business logic, and rules of the application.
- **View:** Handles the presentation layer and user interface.
- **Controller:** Acts as an intermediary between Model and View, processing user input and updating the Model or View accordingly.

---

## Model Layer

Located in the `src/model/` directory, the Model layer contains classes that represent the core data entities of the system:

- **Student.java:** Represents student data including ID, name, email, major, and year.
- **Teacher.java:** Represents teacher data including ID, name, email, department, and title.
- **Course.java:** Represents course data including course ID, name, assigned teacher, and enrolled students.
- **Department.java:** Represents academic departments.
- **Enrollment.java:** Represents enrollment records linking students and courses.
- **Person.java:** Base class for person-related entities (if applicable).

The Model layer is responsible for encapsulating the data and providing methods to access and manipulate it.

---

## View Layer

Located in the `src/view/` directory, the View layer contains classes responsible for the graphical user interface (GUI) components:

- **AdminWelcomePanel.java:** Main admin dashboard panel with navigation buttons and dynamic dashboard summary.
- **LoginView.java, LoginPanel.java:** Login screen components.
- **UserPanel.java, UserManagementPanel.java:** User-related UI components.
- **DashboardPanel.java:** Dashboard UI components.
- **Other panels:** For managing students, teachers, courses, departments, enrollment, marks, etc.

The View layer uses Java Swing components to build the UI and provides methods to add event listeners for user interactions.

---

## Controller Layer

Located in the `src/controller/` directory, the Controller layer contains classes that handle user input, interact with the Model, and update the View:

- **LoginController.java:** Manages login logic and authentication.
- **UserController.java:** Handles user management operations.
- **StudentController.java, TeacherController.java, CourseController.java, DepartmentController.java, EnrollmentController.java, MarksController.java:** Controllers for respective entities managing CRUD operations and business logic.

Controllers listen to events from the View, perform necessary operations on the Model (e.g., database queries), and update the View accordingly.

---

## Database Helpers

Located in the `src/database/` directory, these classes provide database connectivity and operations:

- **DatabaseHelper.java:** Manages SQLite database connection and CRUD operations for students, teachers, courses, departments, enrollments, and marks.
- **UserDatabaseHelper.java:** Manages user authentication and role management.

These helpers are used by Controllers to persist and retrieve data.

---

## Utilities

Located in the `src/util/` directory, utility classes provide common functionality such as UI styling (`UIStyleUtil.java`) and user setup (`UserSetup.java`).

---

## Interaction Flow

1. **User Interaction:** The user interacts with the GUI (View).
2. **Event Handling:** The View notifies the Controller of user actions via event listeners.
3. **Business Logic:** The Controller processes the input, interacts with the Model and database helpers to perform operations.
4. **UI Update:** The Controller updates the View to reflect changes in the Model or application state.

---

## Summary

This MVC design promotes separation of concerns, making the application easier to maintain, test, and extend. The clear division between data management, user interface, and control logic helps in organizing the codebase effectively.

For further details, refer to the source code in the respective directories.
