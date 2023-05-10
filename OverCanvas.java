import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.io.*;

public class OverCanvas extends JComponent {
    private int width; 
    private int height; 
    //private GameCanvas gameCanvas;
    private Font topSecret; 
    private Font cascadia; 

    public OverCanvas(int w, int h) {
        width = w; 
        height = h; 
        setPreferredSize(new Dimension(width, height));

        try {
            InputStream secretStream = getClass().getResourceAsStream("Top Secret Stamp.ttf");
            topSecret = Font.createFont(Font.TRUETYPE_FONT, secretStream).deriveFont(170F);

            InputStream luckStream = getClass().getResourceAsStream("Cascadia.ttf");
            cascadia = Font.createFont(Font.TRUETYPE_FONT, luckStream).deriveFont(55F);

        } catch(IOException | FontFormatException ex) {

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; 

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        Rectangle2D.Double defaultBackground = new Rectangle2D.Double(0, 0, width, height);
        g2d.setPaint(Color.red);
        g2d.fill(defaultBackground);

        g2d.setFont(topSecret);
        g2d.setColor(Color.white);
        g2d.drawString("Game Over!", 330, 330);

        g2d.setFont(cascadia);
        g2d.setColor(Color.white);
        g2d.drawString("Better luck next time.", 410, 450);

    }
}

