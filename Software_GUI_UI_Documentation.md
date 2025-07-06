This documentation covers the primary GUI components and their roles within the Course Management software. For detailed implementation, refer to the source code in the `src/view` directory.
=======
---

## 8. Visual Diagrams and Technical Details

### 8.1 UML Class Diagram

```plantuml
@startuml
abstract class Person {
  - id: int
  - name: String
  - dept: String
  + getId(): int
  + getName(): String
  + getDept(): String
}

class Student {
  - courses: List<Course>
  + registerCourse(course: Course)
  + getCourses(): List<Course>
}

class Teacher {
  - designation: String
  - coursesTaught: List<Course>
  + assignCourse(course: Course)
  + getCoursesTaught(): List<Course>
}

class Course {
  - code: int
  - title: String
  - teacher: Teacher
  - enrolledStudents: List<Student>
  - schedule: String
  - credits: int
  - maxStudents: int
  + addStudent(student: Student)
  + removeStudent(student: Student)
  + getEnrolledStudents(): List<Student>
}

class Department {
  - name: String
  - courses: List<Course>
  + addCourse(course: Course)
  + getCourses(): List<Course>
}

class Enrollment {
  - student: Student
  - course: Course
  + getStudent(): Student
  + getCourse(): Course
}

Person <|-- Student
Person <|-- Teacher
Course "1" *-- "many" Student : enrolledStudents
Course "1" o-- "1" Teacher : teacher
Department "1" *-- "many" Course : courses
Enrollment "1" o-- "1" Student
Enrollment "1" o-- "1" Course
@enduml
```

*This UML class diagram illustrates the main classes and their relationships, showing inheritance, composition, and associations.*

---

### 8.2 Sequence Diagrams

#### 8.2.1 Login Flow

```plantuml
@startuml
actor Admin
participant LoginPanel
participant LoginController
participant DatabaseHelper

Admin -> LoginPanel: Enter username and password
LoginPanel -> LoginController: Submit credentials
LoginController -> DatabaseHelper: Validate credentials
DatabaseHelper --> LoginController: Validation result
LoginController --> LoginPanel: Success or failure
LoginPanel --> Admin: Display login result
@enduml
```

#### 8.2.2 Course Registration Flow

```plantuml
@startuml
actor Admin
participant CourseRegistrationPanel
participant CourseController
participant DatabaseHelper

Admin -> CourseRegistrationPanel: Fill course form and submit
CourseRegistrationPanel -> CourseController: Submit course data
CourseController -> DatabaseHelper: Add course to database
DatabaseHelper --> CourseController: Confirmation
CourseController --> CourseRegistrationPanel: Success message
CourseRegistrationPanel --> Admin: Display confirmation
@enduml
```

*These sequence diagrams depict the interaction between user interface, controller, and database during login and course registration.*

---

### 8.3 Component Diagram

```plantuml
@startuml
package "User Interface" {
  [LoginPanel]
  [AdminWelcomePanel]
  [CourseRegistrationPanel]
  [StudentRegistrationPanel]
  [TeacherRegistrationPanel]
  [EnrollmentPanel]
  [MarksPanel]
  [UserManagementPanel]
}

package "Controllers" {
  [LoginController]
  [AdminWelcomeController]
  [CourseController]
  [StudentController]
  [TeacherController]
  [EnrollmentController]
  [MarksController]
  [UserController]
}

package "Database" {
  [DatabaseHelper]
  [DatabaseHelperAssessmentMarks]
}

[User Interface] --> [Controllers]
[Controllers] --> [Database]
@enduml
```

*The component diagram shows the modular structure of the system and the interactions between UI, controllers, and database helpers.*

---

### 8.4 Flowcharts

#### 8.4.1 Student Registration Algorithm

```plaintext
+---------------------+
| Start               |
+---------------------+
          |
          v
+---------------------+
| Input student data   |
+---------------------+
          |
          v
+---------------------+
| Validate input       |
+---------------------+
          |
          v
+---------------------+
| Add student to DB    |
+---------------------+
          |
          v
+---------------------+
| Display success msg  |
+---------------------+
          |
          v
+---------------------+
| End                 |
+---------------------+
```

#### 8.4.2 Course Enrollment Algorithm

```plaintext
+-----------------------------+
| Start                       |
+-----------------------------+
            |
            v
+-----------------------------+
| Select student and course   |
+-----------------------------+
            |
            v
+-----------------------------+
| Check course capacity       |
+-----------------------------+
            |
     +------+------+
     |             |
     v             v
+---------+   +-----------------+
| Not full|   | Full            |
+---------+   +-----------------+
     |             |
     v             v
+-----------------------------+
| Enroll student in course    |
+-----------------------------+
            |
            v
+-----------------------------+
| Display success message     |
+-----------------------------+
            |
            v
+-----------------------------+
| End                         |
+-----------------------------+
```

#### 8.4.3 Login Process Algorithm

```plaintext
+---------------------+
| Start               |
+---------------------+
          |
          v
+---------------------+
| Enter username       |
+---------------------+
          |
          v
+---------------------+
| Enter password       |
+---------------------+
          |
          v
+---------------------+
| Validate credentials |
+---------------------+
          |
     +----+----+
     |         |
     v         v
+--------+ +------------+
| Success| | Failure    |
+--------+ +------------+
     |         |
     v         v
+---------------------+
| Display message     |
+---------------------+
          |
          v
+---------------------+
| End                 |
+---------------------+
```

#### 8.4.4 Course Search Algorithm

```plaintext
+---------------------+
| Start               |
+---------------------+
          |
          v
+---------------------+
| Enter course ID      |
+---------------------+
          |
          v
+---------------------+
| Validate input       |
+---------------------+
          |
          v
+---------------------+
| Search course in DB  |
+---------------------+
          |
     +----+----+
     |         |
     v         v
+--------+ +------------+
| Found  | | Not found  |
+--------+ +------------+
     |         |
     v         v
+---------------------+
| Display details or   |
| error message       |
+---------------------+
          |
          v
+---------------------+
| End                 |
+---------------------+
```

#### 8.4.5 Grade Report Generation Algorithm

```plaintext
+---------------------+
| Start               |
+---------------------+
          |
          v
+---------------------+
| Enter course ID      |
+---------------------+
          |
          v
+---------------------+
| Validate input       |
+---------------------+
          |
          v
+---------------------+
| Generate report from |
| DB                  |
+---------------------+
          |
          v
+---------------------+
| Display report       |
+---------------------+
          |
          v
+---------------------+
| End                 |
+---------------------+
```

#### 8.4.6 Enrollment Removal Algorithm

```plaintext
+---------------------+
| Start               |
+---------------------+
          |
          v
+---------------------+
| Select course       |
+---------------------+
          |
          v
+---------------------+
| Select student      |
+---------------------+
          |
          v
+---------------------+
| Remove enrollment   |
+---------------------+
          |
          v
+---------------------+
| Refresh UI          |
+---------------------+
          |
          v
+---------------------+
| Display success msg |
+---------------------+
          |
          v
+---------------------+
| End                 |
+---------------------+
```
+---------------------+
+---------------------+
+-----------------------------+
+---------------------+

*These flowcharts illustrate the step-by-step process for student registration and course enrollment.*

---

### 8.5 Technical Details

- The UML class diagram reflects the core data model and relationships, supporting OOP principles such as inheritance and composition.
- Sequence diagrams demonstrate the flow of control and data between UI, controllers, and database during key operations.
- The component diagram highlights the modular architecture, showing clear separation of concerns.
- Flowcharts provide a visual representation of the main algorithms, aiding understanding and maintenance.
- All diagrams are represented in PlantUML syntax for easy visualization and modification.
- The system uses Java Swing for UI, controllers for business logic, and database helpers for data persistence, following MVC architecture.

---

This section enhances the documentation with visual and technical insights, improving clarity and professionalism.
