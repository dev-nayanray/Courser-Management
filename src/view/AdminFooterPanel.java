package view;

import util.UIStyleUtil;

import javax.swing.*;
import java.awt.*;

/**
 * Footer panel for the admin panel with university info and links.
 */
public class AdminFooterPanel extends JPanel {
    private JLabel infoLabel;

    public AdminFooterPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        UIStyleUtil.stylePanel(this);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        infoLabel = new JLabel("© 2024 University Course Management System. All rights reserved.");
        infoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        infoLabel.setForeground(UIStyleUtil.LABEL_TEXT_COLOR);

        add(infoLabel);
    }
}
