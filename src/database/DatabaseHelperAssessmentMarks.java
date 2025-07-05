package database;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Helper class to manage assessment marks in the database.
 */
public class DatabaseHelperAssessmentMarks {
    private static final String DB_URL = "jdbc:sqlite:university.db";
    private Connection connection;

    public DatabaseHelperAssessmentMarks() throws SQLException {
        connect();
    }

    private void connect() throws SQLException {
        connection = DriverManager.getConnection(DB_URL);
    }

    /**
     * Adds or updates an assessment mark for a student in a course.
     * @param courseId the course ID
     * @param studentId the student ID
     * @param assessmentType the type of assessment (e.g., assignment, quiz, final)
     * @param marks the marks obtained
     * @throws SQLException if SQL execution fails
     */
    public void addOrUpdateAssessmentMark(int courseId, int studentId, String assessmentType, int marks) throws SQLException {
        String sql = "INSERT OR REPLACE INTO assessment_marks (courseId, studentId, assessmentType, marks) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            pstmt.setInt(2, studentId);
            pstmt.setString(3, assessmentType);
            pstmt.setInt(4, marks);
            pstmt.executeUpdate();
        }
    }

    /**
     * Retrieves all assessment marks for a student in a course.
     * @param courseId the course ID
     * @param studentId the student ID
     * @return a map of assessmentType to marks
     * @throws SQLException if SQL execution fails
     */
    public Map<String, Integer> getAssessmentMarks(int courseId, int studentId) throws SQLException {
        Map<String, Integer> marksMap = new HashMap<>();
        String sql = "SELECT assessmentType, marks FROM assessment_marks WHERE courseId = ? AND studentId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            pstmt.setInt(2, studentId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                marksMap.put(rs.getString("assessmentType"), rs.getInt("marks"));
            }
        }
        return marksMap;
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
