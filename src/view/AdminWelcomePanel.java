package view;

import util.UIStyleUtil;
import database.DatabaseHelper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * AdminWelcomePanel shows dashboard statistics and quick actions for admin.
 */
public class AdminWelcomePanel extends JPanel {
    private JPanel totalStudentsLabel;
    private JPanel totalTeachersLabel;
    private JPanel totalCoursesLabel;
    private JLabel lastUpdatedLabel;
    private JPanel statsPanel;
    private JPanel chartPanel;

    private JButton studentsButton;
    private JButton teachersButton;
    private JButton coursesButton;
    private JButton departmentsButton;
    private JButton enrollmentButton;
    private JButton marksButton;
    private JButton userManagementButton;

    private JTextField searchField;
    private NavigationListener navigationListener;

    public interface NavigationListener {
        void navigateTo(String panelName);
        void performSearch(String query);
    }

    public void setNavigationListener(NavigationListener listener) {
        this.navigationListener = listener;
    }

    public AdminWelcomePanel() {
        setLayout(new BorderLayout(20, 20));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);

        JLabel welcomeLabel = new JLabel("Admin Dashboard");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        welcomeLabel.setForeground(UIStyleUtil.PRIMARY_COLOR);

        lastUpdatedLabel = new JLabel("Last updated: --:--:--");
        lastUpdatedLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lastUpdatedLabel.setForeground(Color.GRAY);
        lastUpdatedLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        headerPanel.add(welcomeLabel, BorderLayout.WEST);
        headerPanel.add(lastUpdatedLabel, BorderLayout.EAST);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        add(headerPanel, BorderLayout.NORTH);

        // Search panel
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        searchField = new JTextField();
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        searchField.setToolTipText("Search students, teachers, courses...");
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIStyleUtil.LIGHT_GRAY, 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (navigationListener != null) {
                    navigationListener.performSearch(searchField.getText().trim());
                }
            }
        });

        searchPanel.add(searchField, BorderLayout.CENTER);
        add(searchPanel, BorderLayout.AFTER_LAST_LINE);

        // Stats panel
        statsPanel = new JPanel(new GridLayout(1, 3, 20, 20));
        statsPanel.setBackground(Color.WHITE);

        totalStudentsLabel = createStatCard("Total Students", "0", UIStyleUtil.PRIMARY_COLOR, "students_icon.png");
        totalTeachersLabel = createStatCard("Total Teachers", "0", UIStyleUtil.SECONDARY_COLOR, "teachers_icon.png");
        totalCoursesLabel = createStatCard("Total Courses", "0", new Color(52, 152, 219), "courses_icon.png");

        statsPanel.add(totalStudentsLabel);
        statsPanel.add(totalTeachersLabel);
        statsPanel.add(totalCoursesLabel);

        add(statsPanel, BorderLayout.CENTER);

        // Chart panel
        chartPanel = createChartPanel();
        add(chartPanel, BorderLayout.EAST);

        // Quick actions panel
        JPanel quickActionsPanel = new JPanel(new GridLayout(2, 4, 15, 15));
        quickActionsPanel.setBackground(Color.WHITE);
        quickActionsPanel.setBorder(BorderFactory.createTitledBorder("Quick Actions"));

        studentsButton = createQuickActionButton("Add Student", "add_user.png", "Students");
        teachersButton = createQuickActionButton("Add Teacher", "add_teacher.png", "Teachers");
        coursesButton = createQuickActionButton("Create Course", "add_course.png", "Courses");
        departmentsButton = createQuickActionButton("New Department", "department.png", "Departments");
        enrollmentButton = createQuickActionButton("Manage Enrollment", "enrollment.png", "Enrollment");
        marksButton = createQuickActionButton("Record Marks", "marks.png", "Marks");
        userManagementButton = createQuickActionButton("Create User", "add_user.png", "Users");
        JButton systemSettingsButton = createQuickActionButton("System Settings", "settings.png", "");

        quickActionsPanel.add(studentsButton);
        quickActionsPanel.add(teachersButton);
        quickActionsPanel.add(coursesButton);
        quickActionsPanel.add(departmentsButton);
        quickActionsPanel.add(enrollmentButton);
        quickActionsPanel.add(marksButton);
        quickActionsPanel.add(userManagementButton);
        quickActionsPanel.add(systemSettingsButton);

        add(quickActionsPanel, BorderLayout.SOUTH);

        // Load initial data
        refreshData();
    }

    private JPanel createChartPanel() {
        // Create a sample bar chart using JFreeChart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(50, "Students", "Jan");
        dataset.addValue(75, "Students", "Feb");
        dataset.addValue(60, "Students", "Mar");
        dataset.addValue(80, "Students", "Apr");
        dataset.addValue(90, "Students", "May");

        JFreeChart barChart = ChartFactory.createBarChart(
            "Student Enrollment Over Months",
            "Month",
            "Number of Students",
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        return chartPanel;
    }

    private JPanel createStatCard(String title, String value, Color color, String iconPath) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(10, 10));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 2, 5, 2, color.brighter()),
            BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));

        // Icon and title
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        headerPanel.setBackground(Color.WHITE);

        ImageIcon icon = UIStyleUtil.loadImage(iconPath, 40, 40);
        if (icon != null) {
            JLabel iconLabel = new JLabel(icon);
            headerPanel.add(iconLabel);
        }

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Color.DARK_GRAY);
        headerPanel.add(titleLabel);

        card.add(headerPanel, BorderLayout.NORTH);

        // Value
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 40));
        valueLabel.setForeground(color);
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }

    private JButton createQuickActionButton(String text, String iconPath, String targetPanel) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setBackground(Color.WHITE);
        button.setForeground(Color.DARK_GRAY);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIStyleUtil.LIGHT_GRAY, 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setHorizontalAlignment(SwingConstants.LEFT);

        ImageIcon icon = UIStyleUtil.loadImage(iconPath, 24, 24);
        if (icon != null) {
            button.setIcon(icon);
        }

        if (!targetPanel.isEmpty()) {
            button.addActionListener(e -> {
                if (navigationListener != null) {
                    navigationListener.navigateTo(targetPanel);
                }
            });
        }

        return button;
    }

    public void refreshData() {
        SwingUtilities.invokeLater(() -> {
            try {
                DatabaseHelper dbHelper = new DatabaseHelper();

                int totalStudents = dbHelper.getAllStudents().size();
                int totalTeachers = dbHelper.getAllTeachers().size();
                int totalCourses = dbHelper.getAllCourses().size();

                // Update cards
                updateStatCard(totalStudentsLabel, String.valueOf(totalStudents));
                updateStatCard(totalTeachersLabel, String.valueOf(totalTeachers));
                updateStatCard(totalCoursesLabel, String.valueOf(totalCourses));

                // Update timestamp
                lastUpdatedLabel.setText("Last updated: " + java.time.LocalTime.now().format(
                    java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")));

                dbHelper.close();
            } catch (SQLException e) {
                updateStatCard(totalStudentsLabel, "Error");
                updateStatCard(totalTeachersLabel, "Error");
                updateStatCard(totalCoursesLabel, "Error");
                JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void updateStatCard(JPanel card, String value) {
        for (Component comp : card.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                if (label.getFont().getSize() == 36) {
                    label.setText(value);
                    break;
                }
            }
        }
    }

    public void addStudentsButtonListener(java.awt.event.ActionListener listener) {
        studentsButton.addActionListener(listener);
    }

    public void addTeachersButtonListener(java.awt.event.ActionListener listener) {
        teachersButton.addActionListener(listener);
    }

    public void addCoursesButtonListener(java.awt.event.ActionListener listener) {
        coursesButton.addActionListener(listener);
    }

    public void addDepartmentsButtonListener(java.awt.event.ActionListener listener) {
        departmentsButton.addActionListener(listener);
    }

    public void addEnrollmentButtonListener(java.awt.event.ActionListener listener) {
        enrollmentButton.addActionListener(listener);
    }

    public void addMarksButtonListener(java.awt.event.ActionListener listener) {
        marksButton.addActionListener(listener);
    }

    public void addUserManagementButtonListener(java.awt.event.ActionListener listener) {
        userManagementButton.addActionListener(listener);
    }
}
