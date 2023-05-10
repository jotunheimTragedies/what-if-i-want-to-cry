import java.util.*;
import java.awt.*;
import javax.swing.*;

public class ModuleCanvas extends JPanel {
    private int width; 
    private int height; 

    //private JPanel testPanel;
    private BombTimer bombTimer;
    private SimonSays simonModule;
    private Keypad keypadModule;
    private KeypadManual keypadManual;
    private SimonSaysManual simonManual;
    private Filler fillerModule;
     
    public ModuleCanvas(int w, int h) {
        width = w; 
        height = h; 

        setLayout(new GridLayout(2, 3, 10, 10));
        setPreferredSize(new Dimension(width, height));

    }

    public void setUpSwingComponents() {
        simonModule = new SimonSays();
        add(simonModule);   

        fillerModule = new Filler();
        add(fillerModule);

        keypadModule = new Keypad();
        add(keypadModule);       

        simonManual = new SimonSaysManual();
        add(simonManual);      
        
        bombTimer = new BombTimer(); 
        add(bombTimer);  

        keypadManual = new KeypadManual();
        add(keypadManual);

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

