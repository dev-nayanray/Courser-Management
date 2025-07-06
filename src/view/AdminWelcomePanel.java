package view;

import util.UIStyleUtil;
import database.DatabaseHelper;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * AdminWelcomePanel shows dashboard statistics and quick actions for admin.
 */
public class AdminWelcomePanel extends JPanel {
    private JPanel totalStudentsLabel;
    private JPanel totalTeachersLabel;
    private JPanel totalCoursesLabel;
    private JLabel lastUpdatedLabel;
    private JPanel statsPanel;

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

        // Quick actions panel
        JPanel quickActionsPanel = new JPanel(new GridLayout(2, 4, 15, 15));
        quickActionsPanel.setBackground(Color.WHITE);
        quickActionsPanel.setBorder(BorderFactory.createTitledBorder("Quick Actions"));
        
        quickActionsPanel.add(createQuickActionButton("Add Student", "add_user.png", "Students"));
        quickActionsPanel.add(createQuickActionButton("Add Teacher", "add_teacher.png", "Teachers"));
        quickActionsPanel.add(createQuickActionButton("Create Course", "add_course.png", "Courses"));
        quickActionsPanel.add(createQuickActionButton("New Department", "department.png", "Departments"));
        quickActionsPanel.add(createQuickActionButton("Manage Enrollment", "enrollment.png", "Enrollment"));
        quickActionsPanel.add(createQuickActionButton("Record Marks", "marks.png", "Marks"));
        quickActionsPanel.add(createQuickActionButton("Create User", "add_user.png", "Users"));
        quickActionsPanel.add(createQuickActionButton("System Settings", "settings.png", ""));
        
        add(quickActionsPanel, BorderLayout.SOUTH);
        
        // Load initial data
        refreshData();
    }

    private JPanel createStatCard(String title, String value, Color color, String iconPath) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(10, 10));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 1, 3, 1, color.brighter()),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        // Icon and title
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        headerPanel.setBackground(Color.WHITE);
        
        ImageIcon icon = UIStyleUtil.loadImage(iconPath, 32, 32);
        if (icon != null) {
            JLabel iconLabel = new JLabel(icon);
            headerPanel.add(iconLabel);
        }
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(Color.DARK_GRAY);
        headerPanel.add(titleLabel);
        
        card.add(headerPanel, BorderLayout.NORTH);
        
        // Value
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
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
                // No parentFrame to call showPanel, so no action or alternative handling here
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
}
