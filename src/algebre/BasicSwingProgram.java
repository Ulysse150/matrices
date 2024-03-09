package algebre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BasicSwingProgram {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Color Change Program");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.setLocationRelativeTo(null); // Center the window

            ColorPanel panel = new ColorPanel(); // Use our custom ColorPanel instead of JPanel

            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (panel.getBounds().contains(e.getPoint())) {
                        Color newColor = panel.getBackground().equals(Color.RED) ? Color.BLUE : Color.RED;
                        panel.setColor(newColor);
                    }
                }
            });

            frame.add(panel);
            frame.setVisible(true);
        });
    }
}

class ColorPanel extends JPanel {
    private Color color = Color.BLUE;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(150, 150, 100, 100); // Draw a box in the middle
    }

    public void setColor(Color color) {
        this.color = color;
        repaint();
    }
}
