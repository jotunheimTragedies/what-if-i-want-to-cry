import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Image;

public class Filler extends JPanel {
    
    private JPanel backpanel;
    private JLabel pic;
    public ImageIcon image;

    public Filler() {
        backpanel = new JPanel();
        backpanel.setLayout(new GridLayout(1,1));

        pic = new JLabel();
        image = new ImageIcon(new ImageIcon("Modules/filler.png").getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT));
        pic = new JLabel();
        pic.setIcon(image);
        backpanel.add(pic);

        add(backpanel);
        
    }
}
