# University Course Management System - Project Report

**Northern University of Business and Technology Khulna**  
**Department of Computer Science and Engineering**  
**Course:** Software Development I (CSE 2216)  
**Section:** 4K  
**Student:** Nayan Ray  
**Student ID:** 12230320521  
**ECSE:** 4K  

## Overview  
This Java-based system implements core Object-Oriented Programming (OOP) principles to automate university course management. Designed for CSE 2216, it satisfies all specified requirements for modeling course/student/teacher relationships through robust OOP design.

## Requirements Implementation  

### Core Classes  
- **Person (Abstract):** `id`, `name` (src/model/)  
- **Student (Subclass):** `department`, `enrolledCourses` (src/model/)  
- **Teacher (Subclass):** `designation`, `assignedCourses` (src/model/)  
- **Course:** `code`, `title`, `schedule`, `credits`, `maxStudents` (src/model/)  
- **Enrollment:** Composition of `Student` + `Course` (with marks) (src/model/)  
- **Department:** `name`, `courseList` (Optional scaling) (src/model/)  

### Key Functionalities  
1. User Management: Add students/teachers (with dept/designation)  
2. Course Operations: Create courses (schedule/credits/max students), Search courses by code  
3. Registration System: Register students to courses, Assign teachers to courses  
4. Reporting: Display enrolled students per course, Print student details with registered courses, Record/display marks (Bonus)  
5. Login Authentication (Bonus)  

### OOP Principles Applied  
- **Abstraction:** `Person` base class with abstract methods  
- **Encapsulation:** Private fields with getters/setters in all classes  
- **Inheritance:** `Student` and `Teacher` extend `Person`  
- **Polymorphism:** Overridden `printDetails()` in subclasses  
- **Composition:** `Course` contains `Enrollment` objects  

## GUI Implementation  
- **Login:** Admin authentication (Bonus) - `LoginPanel.java`  
- **Student:** Add students, Register for courses - `StudentPanel.java`  
- **Teacher:** Add teachers, Assign to courses - `TeacherPanel.java`  
- **Course:** Create courses, View enrollments - `CoursePanel.java`  
- **Marks:** Record marks, Generate reports (Bonus) - `MarksPanel.java`  

## Technical Specifications  
- Language: Java 17  
- Database: SQLite (`university.db`)  
- GUI: Java Swing  
- Architecture: MVC Pattern  
- Dependencies: SQLite JDBC Driver  

## Database Structure and Security  
The system uses a SQLite database named `university.db` with the following tables:  
- **students:** Store student details including ID, name, email, major, and year.  
- **teachers:** Store teacher details including ID, name, email, department, and title.  
- **courses:** Store course information including course ID, name, and assigned teacher.  
- **enrollments:** Links students to courses they are enrolled in.  
- **marks:** Records marks assigned to students for courses.  
- **assessment_marks:** Stores marks for different assessment types per student and course.  
- **departments:** Stores department information.  
- **users:** Stores user credentials with hashed passwords and roles for authentication.  
- **waitlist:** Manages waitlisted students for courses.  

Security measures include:  
- Passwords stored as hashes in the `users` table to protect credentials.  
- Foreign key constraints ensure referential integrity between tables.  
- Input validation in the application to prevent SQL injection and data corruption.  

## Project Structure  
```
src/
  model/          # Domain classes (OOP core)  
  view/           # GUI components (Swing)  
  controller/     # Business logic  
  database/       # DB connection & queries  
bin/              # Compiled classes  
```

## Setup Guide  
1. Install JDK 17+  
2. Download [sqlite-jdbc-3.50.2.0.jar](https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.50.2.0/)  
3. Compile & Run:  
```powershell
# Compile
javac -d bin -cp "lib/sqlite-jdbc-3.50.2.0.jar" src/**/*.java

# Execute (Windows)
java -cp "bin;lib/sqlite-jdbc-3.50.2.0.jar" MainApp
```

## Validation Checklist  
- Student/Teacher/Course creation  
- Course registration & teacher assignment  
- Enrollment lists per course  
- Student reports with courses  
- Marks recording (Bonus)  
- Login authentication (Bonus)  

## Future Enhancements  
- Role-based access control (Admin/Student/Teacher)  
- Course scheduling conflict detection  
- PDF report generation  
- JavaFX migration  

---

*Developed for Software Development I (CSE 2216) - Demonstrates mastery of OOP principles through practical implementation.*
