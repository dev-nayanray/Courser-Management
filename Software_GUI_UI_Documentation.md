# Software GUI UI Documentation

## 1. Introduction

This document provides an overview of the graphical user interface (GUI) of the Course Management software. The GUI follows the Model-View-Controller (MVC) architecture, separating the data model, user interface, and control logic for maintainability and scalability. The UI components are primarily implemented using Java Swing, organized into panels representing different functional areas.

## 2. AdminWelcomePanel

The `AdminWelcomePanel` serves as the main dashboard for administrators. It provides a summary of key statistics and quick access to common actions.

### Layout and Components
- **Header:** Displays the title "Admin Dashboard" and a last updated timestamp.
- **Search Field:** Allows searching for students, teachers, or courses with live search functionality.
- **Statistics Cards:** Three cards showing total counts of students, teachers, and courses, each styled with icons and colors.
- **Chart Panel:** A bar chart visualizing student enrollment over recent months, implemented using JFreeChart.
- **Quick Actions Panel:** Buttons for common tasks such as adding students, teachers, courses, managing departments, enrollments, marks, user management, and system settings.

### Interaction
- Buttons trigger navigation events to respective panels.
- The search field triggers search events as the user types.
- Data is refreshed dynamically from the database.

## 3. LoginPanel

The `LoginPanel` provides a simple login interface for administrator authentication.

### Layout and Components
- **Username Field:** Text input for the username.
- **Password Field:** Password input for the password.
- **Login Button:** Initiates login action.
- **Logout Button:** Allows logging out; initially disabled until login.

### Interaction
- Provides methods to get input values and clear fields.
- Supports adding listeners for login and logout button actions.
- Uses consistent styling via `UIStyleUtil`.

## 4. CourseRegistrationPanel

The `CourseRegistrationPanel` allows managing course details and student enrollment.

### Layout and Components
- **Course Details Form:** Fields for course ID, name, schedule, credits, max students, and teacher selection.
- **Enrolled Students List:** Displays students enrolled in the course with add/remove buttons.
- **Course Table:** Shows a list of courses with details in a non-editable table.
- **Search and Print:** Search field and buttons to search courses and print student details.
- **Submit and Reset Buttons:** For form submission and clearing inputs.

### Interaction
- Provides methods to get and set form data.
- Supports adding action listeners for all buttons.
- Uses `UIStyleUtil` for consistent styling and tooltips for usability.

## 5. UI Styling

The project uses a utility class `UIStyleUtil` to apply consistent styling across all UI components, including colors, fonts, borders, and icons. This ensures a uniform look and feel throughout the application.

## 6. Navigation and Interaction

Navigation between panels is handled via listener interfaces, allowing decoupled communication between UI components and controllers. Buttons in panels trigger navigation events to switch views, and search fields provide live filtering capabilities.

---

This documentation covers the primary GUI components and their roles within the Course Management software. For detailed implementation, refer to the source code in the `src/view` directory.
