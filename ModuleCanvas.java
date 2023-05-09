import java.util.*;
import java.awt.*;
import javax.swing.*;

public class ModuleCanvas extends JPanel {
    private int width; 
    private int height; 

    private JPanel testPanel;
    private BombTimer bombTimer;
    private SimonSays ss;
    private Keypad keypad;
    private KeypadManual kp;
    private SimonSaysManual sm;
    private Filler filler;
     
    public ModuleCanvas(int w, int h) {
        width = w; 
        height = h; 

        setLayout(new GridLayout(2, 3, 10, 10));
        setPreferredSize(new Dimension(width, height));

    }

    public void setUpSwingComponents() {
        ss = new SimonSays();
        add(ss);   

        bombTimer = new BombTimer(); 
        add(bombTimer);  

        keypad = new Keypad();
        add(keypad);       

        sm = new SimonSaysManual();
        add(sm);       

        filler = new Filler();
        add(filler);

        kp = new KeypadManual();
        add(kp);

        /*JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.red);
        add(redPanel);

        JPanel orangePanel = new JPanel();
        orangePanel.setBackground(Color.orange);
        add(orangePanel);

        JPanel yellowPanel = new JPanel();
        yellowPanel.setBackground(Color.yellow);
        add(yellowPanel);

        JPanel greenPanel = new JPanel();
        greenPanel.setBackground(Color.green);
        add(greenPanel);

        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.blue);
        add(bluePanel);        
        
        */

    }

    public BombTimer getBombTimer() {
        return bombTimer; 
    }
}

