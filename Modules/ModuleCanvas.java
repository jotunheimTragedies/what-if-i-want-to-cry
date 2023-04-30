package Modules;

import java.util.*;
import java.awt.*;
import javax.swing.*;

//Create Jpanels 
// set up method 
// add to gameFrame
public class ModuleCanvas extends JPanel {
    private int width; 
    private int height; 
    //private ArrayList<ModuleTemplate> bombModules; 

    private JPanel testPanel;
    private BombTimer bombTimer;
     


    public ModuleCanvas(int w, int h) {
        width = w; 
        height = h; 

        setLayout(new GridLayout(2, 3, 10, 10));
        setPreferredSize(new Dimension(width, height));




    }

    public void setUpSwingComponents() {
        bombTimer = new BombTimer(); 
        add(bombTimer);
        

        /* testPanel = new JPanel();
        testPanel.setBackground(Color.pink);
        add(testPanel);

        JPanel orangePanel = new JPanel();
        orangePanel.setBackground(Color.orange);
        add(orangePanel); */
    }
}