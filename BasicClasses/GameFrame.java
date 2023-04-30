package BasicClasses;

import java.awt.*;
import javax.swing.*;

import Modules.BombTimer;
import Modules.ModuleCanvas;
//import Modules.RunningCanvas;

public class GameFrame {
    private int width; 
    private int height; 
    private JFrame gameFrame;
    private Container cp;  
    private GameCanvas gameCanvas; 
    private ModuleCanvas moduleCanvas; 
   
    private JLabel rawrLabel; 
    private BombTimer bombTimer;
    private JPanel rawrPanel;

    //private int currentCanvasState; 
    //private boolean timetoChangeState; 

    //private BombTimer bombTimer; 

    public GameFrame(int w, int h) {
        width = w; 
        height = h; 
        gameFrame = new JFrame();
        gameCanvas = new GameCanvas(width, height);
        moduleCanvas = new ModuleCanvas(width, height);
        

        rawrLabel = new JLabel("Pedro Pascal is Daddy");
        rawrPanel = new JPanel();

    }

    public void setUpGUI() {
       
        Container cp1 = gameFrame.getContentPane();
        cp1.add(gameCanvas, BorderLayout.CENTER);
        
        gameFrame.setTitle("No Human Being Detonates");
        gameCanvas.setPreferredSize(new Dimension(width, height));
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.pack();
        gameFrame.setVisible(true);

    }

    public void getCurrentGameState() {
        while(true) {
            int currentCanvasState = gameCanvas.getCurrentState();

            if(currentCanvasState == gameCanvas.titleState) {
                System.out.println("");
            }

            if(currentCanvasState == gameCanvas.runningState) {
                gameFrame.getContentPane().removeAll();
                gameFrame.repaint();
                gameFrame.revalidate();
                break;
            }
        }
        addRunningCanvas();
    }

    public void addRunningCanvas() {
        moduleCanvas.setUpSwingComponents();
        Container cp2 = gameFrame.getContentPane();
        cp2.add(moduleCanvas, BorderLayout.CENTER);

        
        cp2.repaint();
        cp2.revalidate();
    }




}