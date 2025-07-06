package view;

import javax.swing.*;
import java.awt.*;

/**
 * Placeholder Dashboard panel.
 * Charting library not available, so this panel is disabled.
 */
public class DashboardPanel extends JPanel {

    public DashboardPanel() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Dashboard with charts is currently unavailable due to missing charting library.");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        add(label, BorderLayout.CENTER);
    }
}
