package view;

import util.UIStyleUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Header panel for the admin panel with university branding and logout button.
 */
public class AdminHeaderPanel extends JPanel {
    private JLabel titleLabel;
    private JButton logoutButton;

    public AdminHeaderPanel() {
        setLayout(new BorderLayout());
        UIStyleUtil.stylePanel(this);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        titleLabel = new JLabel("University Course Management System");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(UIStyleUtil.PRIMARY_COLOR);

        logoutButton = new JButton("Logout");
        UIStyleUtil.styleButton(logoutButton);
        UIStyleUtil.addTooltip(logoutButton, "Logout from the admin panel");

        add(titleLabel, BorderLayout.WEST);
        add(logoutButton, BorderLayout.EAST);
    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }
}
