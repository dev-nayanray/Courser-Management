package database;

import model.Student;
import model.Teacher;
import model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to manage SQLite database operations.
 * Supports adding, updating, and selecting Students, Teachers, and Courses.
 */
public class DatabaseHelper {
    private static final String DB_URL = "jdbc:sqlite:university.db";
    private Connection connection;

    public DatabaseHelper() throws SQLException {
        connect();
        createTablesIfNotExist();
    }

    /**
     * Establishes connection to the SQLite database.
     * @throws SQLException if connection fails
     */
    private void connect() throws SQLException {
        connection = DriverManager.getConnection(DB_URL);
    }

    /**
     * Creates necessary tables if they do not exist.
     * @throws SQLException if SQL execution fails
     */
    private void createTablesIfNotExist() throws SQLException {
        String createStudentTable = "CREATE TABLE IF NOT EXISTS students (" +
                "id INTEGER PRIMARY KEY," +
                "firstName TEXT NOT NULL," +
                "lastName TEXT NOT NULL," +
                "email TEXT UNIQUE NOT NULL," +
                "major TEXT," +
                "year INTEGER" +
                ");";

        String createTeacherTable = "CREATE TABLE IF NOT EXISTS teachers (" +
                "id INTEGER PRIMARY KEY," +
                "firstName TEXT NOT NULL," +
                "lastName TEXT NOT NULL," +
                "email TEXT UNIQUE NOT NULL," +
                "department TEXT," +
                "title TEXT" +
                ");";

        String createCourseTable = "CREATE TABLE IF NOT EXISTS courses (" +
                "courseId INTEGER PRIMARY KEY," +
                "courseName TEXT NOT NULL," +
                "teacherId INTEGER," +
                "FOREIGN KEY(teacherId) REFERENCES teachers(id)" +
                ");";

        String createEnrollmentTable = "CREATE TABLE IF NOT EXISTS enrollments (" +
                "courseId INTEGER," +
                "studentId INTEGER," +
                "PRIMARY KEY(courseId, studentId)," +
                "FOREIGN KEY(courseId) REFERENCES courses(courseId)," +
                "FOREIGN KEY(studentId) REFERENCES students(id)" +
                ");";

        String createMarksTable = "CREATE TABLE IF NOT EXISTS marks (" +
                "courseId INTEGER," +
                "studentId INTEGER," +
                "marks INTEGER," +
                "PRIMARY KEY(courseId, studentId)," +
                "FOREIGN KEY(courseId) REFERENCES courses(courseId)," +
                "FOREIGN KEY(studentId) REFERENCES students(id)" +
                ");";

        String createDepartmentTable = "CREATE TABLE IF NOT EXISTS departments (" +
                "id INTEGER PRIMARY KEY," +
                "name TEXT NOT NULL" +
                ");";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createStudentTable);
            stmt.execute(createTeacherTable);
            stmt.execute(createCourseTable);
            stmt.execute(createEnrollmentTable);
            stmt.execute(createMarksTable);
            stmt.execute(createDepartmentTable);
        }
    }

    // Student operations

    /**
     * Adds a new student to the database.
     * @param student the student to add
     * @throws SQLException if SQL execution fails
     */
    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (id, firstName, lastName, email, major, year) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getFirstName());
            pstmt.setString(3, student.getLastName());
            pstmt.setString(4, student.getEmail());
            pstmt.setString(5, student.getMajor());
            pstmt.setInt(6, student.getYear());
            pstmt.executeUpdate();
        }
    }

    /**
     * Updates an existing student in the database.
     * @param student the student to update
     * @throws SQLException if SQL execution fails
     */
    public void updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET firstName = ?, lastName = ?, email = ?, major = ?, year = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getLastName());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getMajor());
            pstmt.setInt(5, student.getYear());
            pstmt.setInt(6, student.getId());
            pstmt.executeUpdate();
        }
    }

    /**
     * Retrieves a student by ID.
     * @param id the student ID
     * @return the Student object or null if not found
     * @throws SQLException if SQL execution fails
     */
    public Student getStudentById(int id) throws SQLException {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Student(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("major"),
                        rs.getInt("year")
                );
            }
        }
        return null;
    }

    /**
     * Retrieves all students.
     * @return list of students
     * @throws SQLException if SQL execution fails
     */
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("major"),
                        rs.getInt("year")
                ));
            }
        }
        return students;
    }

    // Teacher operations

    /**
     * Adds a new teacher to the database.
     * @param teacher the teacher to add
     * @throws SQLException if SQL execution fails
     */
    public void addTeacher(Teacher teacher) throws SQLException {
        String sql = "INSERT INTO teachers (id, firstName, lastName, email, department, title) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, teacher.getId());
            pstmt.setString(2, teacher.getFirstName());
            pstmt.setString(3, teacher.getLastName());
            pstmt.setString(4, teacher.getEmail());
            pstmt.setString(5, teacher.getDepartment());
            pstmt.setString(6, teacher.getTitle());
            pstmt.executeUpdate();
        }
    }

    /**
     * Updates an existing teacher in the database.
     * @param teacher the teacher to update
     * @throws SQLException if SQL execution fails
     */
    public void updateTeacher(Teacher teacher) throws SQLException {
        String sql = "UPDATE teachers SET firstName = ?, lastName = ?, email = ?, department = ?, title = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, teacher.getFirstName());
            pstmt.setString(2, teacher.getLastName());
            pstmt.setString(3, teacher.getEmail());
            pstmt.setString(4, teacher.getDepartment());
            pstmt.setString(5, teacher.getTitle());
            pstmt.setInt(6, teacher.getId());
            pstmt.executeUpdate();
        }
    }

    /**
     * Retrieves a teacher by ID.
     * @param id the teacher ID
     * @return the Teacher object or null if not found
     * @throws SQLException if SQL execution fails
     */
    public Teacher getTeacherById(int id) throws SQLException {
        String sql = "SELECT * FROM teachers WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Teacher(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("department"),
                        rs.getString("title")
                );
            }
        }
        return null;
    }

    /**
     * Retrieves all teachers.
     * @return list of teachers
     * @throws SQLException if SQL execution fails
     */
    public List<Teacher> getAllTeachers() throws SQLException {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT * FROM teachers";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                teachers.add(new Teacher(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("department"),
                        rs.getString("title")
                ));
            }
        }
        return teachers;
    }

    // Course operations

    /**
     * Adds a new course to the database.
     * @param course the course to add
     * @throws SQLException if SQL execution fails
     */
    public void addCourse(Course course) throws SQLException {
        if (course.getTeacher() == null) {
            throw new SQLException("Course must have a teacher assigned before adding to database.");
        }
        String sql = "INSERT INTO courses (courseId, courseName, teacherId) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, course.getCourseId());
            pstmt.setString(2, course.getCourseName());
            pstmt.setInt(3, course.getTeacher().getId());
            pstmt.executeUpdate();
        }
        // Add enrollments
        for (Student student : course.getEnrolledStudents()) {
            addEnrollment(course.getCourseId(), student.getId());
        }
    }

    /**
     * Updates an existing course in the database.
     * @param course the course to update
     * @throws SQLException if SQL execution fails
     */
    public void updateCourse(Course course) throws SQLException {
        if (course.getTeacher() == null) {
            throw new SQLException("Course must have a teacher assigned before updating in database.");
        }
        String sql = "UPDATE courses SET courseName = ?, teacherId = ? WHERE courseId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, course.getCourseName());
            pstmt.setInt(2, course.getTeacher().getId());
            pstmt.setInt(3, course.getCourseId());
            pstmt.executeUpdate();
        }
        // Update enrollments: delete all and re-add
        removeEnrollmentsByCourse(course.getCourseId());
        for (Student student : course.getEnrolledStudents()) {
            addEnrollment(course.getCourseId(), student.getId());
        }
    }

    /**
     * Retrieves a course by ID.
     * @param courseId the course ID
     * @return the Course object or null if not found
     * @throws SQLException if SQL execution fails
     */
    public Course getCourseById(int courseId) throws SQLException {
        String sql = "SELECT * FROM courses WHERE courseId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int teacherId = rs.getInt("teacherId");
                Teacher teacher = getTeacherById(teacherId);
                Course course = new Course(
                        rs.getInt("courseId"),
                        rs.getString("courseName"),
                        teacher
                );
                // Load enrolled students
                List<Student> students = getStudentsByCourse(courseId);
                for (Student s : students) {
                    course.addStudent(s);
                }
                return course;
            }
        }
        return null;
    }

    /**
     * Retrieves all courses.
     * @return list of courses
     * @throws SQLException if SQL execution fails
     */
    public List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int teacherId = rs.getInt("teacherId");
                Teacher teacher = getTeacherById(teacherId);
                Course course = new Course(
                        rs.getInt("courseId"),
                        rs.getString("courseName"),
                        teacher
                );
                List<Student> students = getStudentsByCourse(course.getCourseId());
                for (Student s : students) {
                    course.addStudent(s);
                }
                courses.add(course);
            }
        }
        return courses;
    }

    /**
     * Searches for a course by its code.
     * @param courseCode the course code to search
     * @return the Course object or null if not found
     * @throws SQLException if SQL execution fails
     */
    public Course searchCourseByCode(int courseCode) throws SQLException {
        String sql = "SELECT * FROM courses WHERE courseId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, courseCode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int teacherId = rs.getInt("teacherId");
                Teacher teacher = getTeacherById(teacherId);
                Course course = new Course(
                        rs.getInt("courseId"),
                        rs.getString("courseName"),
                        teacher
                );
                List<Student> students = getStudentsByCourse(courseCode);
                for (Student s : students) {
                    course.addStudent(s);
                }
                return course;
            }
        }
        return null;
    }

    /**
     * Prints student details along with their registered courses.
     * @param studentId the student ID
     * @throws SQLException if SQL execution fails
     */
    public void printStudentDetailsWithCourses(int studentId) throws SQLException {
        Student student = getStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        student.printDetails();
        System.out.println("Registered Courses:");
        String sql = "SELECT c.courseId, c.courseName FROM courses c " +
                "JOIN enrollments e ON c.courseId = e.courseId " +
                "WHERE e.studentId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Course ID: " + rs.getInt("courseId") + ", Name: " + rs.getString("courseName"));
            }
        }
    }

    // Enrollment operations

    /**
     * Adds an enrollment record linking a student to a course.
     * @param courseId the course ID
     * @param studentId the student ID
     * @throws SQLException if SQL execution fails
     */
    public void addEnrollment(int courseId, int studentId) throws SQLException {
        String sql = "INSERT OR IGNORE INTO enrollments (courseId, studentId) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            pstmt.setInt(2, studentId);
            pstmt.executeUpdate();
        }
    }

    /**
     * Removes all enrollments for a given course.
     * @param courseId the course ID
     * @throws SQLException if SQL execution fails
     */
    private void removeEnrollmentsByCourse(int courseId) throws SQLException {
        String sql = "DELETE FROM enrollments WHERE courseId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            pstmt.executeUpdate();
        }
    }

    /**
     * Removes a specific enrollment record linking a student to a course.
     * @param courseId the course ID
     * @param studentId the student ID
     * @throws SQLException if SQL execution fails
     */
    public void removeEnrollment(int courseId, int studentId) throws SQLException {
        String sql = "DELETE FROM enrollments WHERE courseId = ? AND studentId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            pstmt.setInt(2, studentId);
            pstmt.executeUpdate();
        }
    }

    /**
     * Retrieves all students enrolled in a given course.
     * @param courseId the course ID
     * @return list of students
     * @throws SQLException if SQL execution fails
     */
    public List<Student> getStudentsByCourse(int courseId) throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT s.* FROM students s " +
                "JOIN enrollments e ON s.id = e.studentId " +
                "WHERE e.courseId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("major"),
                        rs.getInt("year")
                ));
            }
        }
        return students;
    }

    /**
     * Closes the database connection.
     * @throws SQLException if closing fails
     */
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * Assigns marks to a student for a course.
     * @param courseId the course ID
     * @param studentId the student ID
     * @param marks the marks to assign
     * @throws SQLException if SQL execution fails
     */
    public void assignMarks(int courseId, int studentId, int marks) throws SQLException {
        String sql = "INSERT OR REPLACE INTO marks (courseId, studentId, marks) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            pstmt.setInt(2, studentId);
            pstmt.setInt(3, marks);
            pstmt.executeUpdate();
        }
    }

    /**
     * Generates a grade report for a course.
     * @param courseId the course ID
     * @return formatted grade report string
     * @throws SQLException if SQL execution fails
     */
    public String generateGradeReport(int courseId) throws SQLException {
        StringBuilder report = new StringBuilder();
        String sql = "SELECT s.id, s.firstName, s.lastName, m.marks FROM students s " +
                "JOIN marks m ON s.id = m.studentId " +
                "WHERE m.courseId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            ResultSet rs = pstmt.executeQuery();
            report.append("Grade Report for Course ID: ").append(courseId).append("\\n");
            report.append("Student ID | Name | Marks\\n");
            report.append("--------------------------------\\n");
            while (rs.next()) {
                int studentId = rs.getInt("id");
                String name = rs.getString("firstName") + " " + rs.getString("lastName");
                int marks = rs.getInt("marks");
                report.append(studentId).append(" | ").append(name).append(" | ").append(marks).append("\\n");
            }
        }
        return report.toString();
    }

    /**
     * Adds a new department to the database.
     * @param department the department to add
     * @throws SQLException if SQL execution fails
     */
    public void addDepartment(model.Department department) throws SQLException {
        String sql = "INSERT INTO departments (id, name) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, department.getId());
            pstmt.setString(2, department.getName());
            pstmt.executeUpdate();
        }
    }

    /**
     * Retrieves all departments.
     * @return list of departments
     * @throws SQLException if SQL execution fails
     */
    public java.util.List<model.Department> getAllDepartments() throws SQLException {
        java.util.List<model.Department> departments = new java.util.ArrayList<>();
        String sql = "SELECT * FROM departments";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                departments.add(new model.Department(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }
        }
        return departments;
    }
}
