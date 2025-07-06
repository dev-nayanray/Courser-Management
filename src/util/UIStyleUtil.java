package util;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Utility class for consistent modern UI styling.
 */
public class UIStyleUtil {

    // Define modern color palette
    public static final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    public static final Color PANEL_BACKGROUND_COLOR = Color.WHITE;
    public static final Color PRIMARY_COLOR = new Color(33, 150, 243); // Blue
    public static final Color PRIMARY_HOVER_COLOR = new Color(30, 136, 229);
    public static final Color BUTTON_TEXT_COLOR = Color.WHITE;
    public static final Color LABEL_TEXT_COLOR = new Color(33, 33, 33);
    public static final Color FIELD_BACKGROUND_COLOR = new Color(250, 250, 250);
    public static final Color FIELD_BORDER_COLOR = new Color(200, 200, 200);
    public static final Color SCROLLPANE_BORDER_COLOR = new Color(220, 220, 220);
    public static final Color TABLE_ROW_HOVER_COLOR = new Color(220, 235, 255);

    // Add missing colors
    public static final Color SIDEBAR_BG = new Color(33, 37, 41); // Dark gray for sidebar background
    public static final Color SECONDARY_COLOR = new Color(108, 117, 125); // Gray for secondary buttons
    public static final Color LIGHT_GRAY = new Color(211, 211, 211);

    // Define fonts
    public static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FIELD_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 14);

    // Define borders
    public static final Border FIELD_BORDER = BorderFactory.createCompoundBorder(
            new LineBorder(FIELD_BORDER_COLOR, 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
    );

    public static final Border BUTTON_BORDER = BorderFactory.createEmptyBorder(8, 16, 8, 16);

    public static final Border SCROLLPANE_BORDER = BorderFactory.createLineBorder(SCROLLPANE_BORDER_COLOR, 1);

    /**
     * Apply modern style to a JButton with hover effect.
     * @param button JButton to style
     */
    public static void styleButton(JButton button) {
        button.setFont(BUTTON_FONT);
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(BUTTON_TEXT_COLOR);
        button.setBorder(BUTTON_BORDER);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setContentAreaFilled(true);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(PRIMARY_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(PRIMARY_COLOR);
            }
        });
    }

    /**
     * Apply modern style to a JLabel.
     * @param label JLabel to style
     */
    public static void styleLabel(JLabel label) {
        label.setFont(LABEL_FONT);
        label.setForeground(LABEL_TEXT_COLOR);
    }

    /**
     * Apply modern style to a JTextField or JPasswordField.
     * @param field JTextField or JPasswordField to style
     */
    public static void styleTextField(JTextField field) {
        field.setFont(FIELD_FONT);
        field.setBackground(FIELD_BACKGROUND_COLOR);
        field.setBorder(FIELD_BORDER);
        field.setCaretColor(PRIMARY_COLOR);
    }

    public static void stylePasswordField(JPasswordField field) {
        field.setFont(FIELD_FONT);
        field.setBackground(FIELD_BACKGROUND_COLOR);
        field.setBorder(FIELD_BORDER);
        field.setCaretColor(PRIMARY_COLOR);
    }

    /**
     * Apply modern style to a JPanel.
     * @param panel JPanel to style
     */
    public static void stylePanel(JPanel panel) {
        panel.setBackground(PANEL_BACKGROUND_COLOR);
    }

    /**
     * Apply modern style to a JTable.
     * @param table JTable to style
     */
    public static void styleTable(JTable table) {
        table.setFont(FIELD_FONT);
        table.setRowHeight(24);
        table.setGridColor(new Color(220, 220, 220));
        table.setShowGrid(true);
        table.setSelectionBackground(PRIMARY_COLOR);
        table.setSelectionForeground(BUTTON_TEXT_COLOR);
        table.getTableHeader().setFont(BUTTON_FONT);
        table.getTableHeader().setBackground(PANEL_BACKGROUND_COLOR);
        table.getTableHeader().setForeground(LABEL_TEXT_COLOR);

        // Add row hover effect
        table.addMouseMotionListener(new MouseAdapter() {
            private int lastRow = -1;

            @Override
            public void mouseMoved(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row != lastRow) {
                    table.repaint();
                    lastRow = row;
                }
            }
        });

        table.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    Point mousePos = table.getMousePosition();
                    if (mousePos != null && table.rowAtPoint(mousePos) == row) {
                        c.setBackground(TABLE_ROW_HOVER_COLOR);
                    } else {
                        c.setBackground(row % 2 == 0 ? BACKGROUND_COLOR : PANEL_BACKGROUND_COLOR);
                    }
                }
                return c;
            }
        });
    }

    /**
     * Apply modern style to a JComboBox.
     * @param comboBox JComboBox to style
     */
    public static void styleComboBox(JComboBox<?> comboBox) {
        comboBox.setFont(FIELD_FONT);
        comboBox.setBackground(FIELD_BACKGROUND_COLOR);
        comboBox.setBorder(FIELD_BORDER);
        comboBox.setFocusable(false);
    }

    /**
     * Apply modern style to a JList.
     * @param list JList to style
     */
    public static void styleList(JList<?> list) {
        list.setFont(FIELD_FONT);
        list.setBackground(FIELD_BACKGROUND_COLOR);
        list.setBorder(FIELD_BORDER);
        list.setSelectionBackground(PRIMARY_COLOR);
        list.setSelectionForeground(BUTTON_TEXT_COLOR);
    }

    /**
     * Apply modern style to a JScrollPane.
     * @param scrollPane JScrollPane to style
     */
    public static void styleScrollPane(JScrollPane scrollPane) {
        scrollPane.setBorder(SCROLLPANE_BORDER);
        scrollPane.getViewport().setBackground(FIELD_BACKGROUND_COLOR);
    }

    /**
     * Apply tooltip to a JButton.
     * @param button JButton to add tooltip
     * @param tooltip String tooltip text
     */
    public static void addTooltip(JButton button, String tooltip) {
        button.setToolTipText(tooltip);
    }

    /**
     * Load and scale an image icon from resources/icons folder.
     * @param filename image file name
     * @param width desired width
     * @param height desired height
     * @return scaled ImageIcon or null if error
     */
    public static ImageIcon loadImage(String filename, int width, int height) {
        try {
            ImageIcon icon = new ImageIcon(UIStyleUtil.class.getResource("/icons/" + filename));
            Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            System.err.println("Error loading icon: " + filename);
            return null;
        }
    }
}
