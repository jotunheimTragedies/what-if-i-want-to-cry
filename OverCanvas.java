import java.awt.*;
import javax.swing.*;

import java.awt.geom.*;
import java.awt.event.*;;

public class OverCanvas extends JComponent {
    private int width; 
    private int height; 
    private GameCanvas gameCanvas;

    public OverCanvas(int w, int h) {
        width = w; 
        height = h; 
        setPreferredSize(new Dimension(width, height));
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; 

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        Rectangle2D.Double defaultBackground = new Rectangle2D.Double(0, 0, width, height);
        g2d.setPaint(Color.red);
        g2d.fill(defaultBackground);

        
        g2d.setFont(new Font("Times New Roman", Font.BOLD, 150));
        g2d.setColor(Color.white);
        g2d.drawString("Game Over!", 340, 330);

        g2d.setFont(new Font("Times New Roman", Font.PLAIN, 75));
        g2d.setColor(Color.white);
        g2d.drawString("Better luck next time.", 430, 450);

        
    }
}

