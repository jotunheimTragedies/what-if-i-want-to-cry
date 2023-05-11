import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class GameFrame {
    private int width; 
    private int height; 

    private JFrame gameFrame;
    private GameCanvas gameCanvas; 
    private ModuleCanvas moduleCanvas; 
    private OverCanvas overCanvas; 

    public int playerID;
    private Socket clientSocket; 

    public GameFrame(int id, int w, int h) {
        playerID = id; 
        width = w; 
        height = h; 
        gameFrame = new JFrame();
        gameCanvas = new GameCanvas(playerID, width, height);
        moduleCanvas = new ModuleCanvas(width, height);
        overCanvas = new OverCanvas(width, height);

    }

    public void setUpGUI() {
       
        Container cp1 = gameFrame.getContentPane();
        cp1.add(gameCanvas, BorderLayout.CENTER); 
        
        gameFrame.setTitle("Player #" + playerID + "_No Human Being Detonates");
        gameCanvas.setPreferredSize(new Dimension(width, height));
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.pack();
        gameFrame.setVisible(true);

    }

    public void changetoRunningState() {
        while(true) {
            int currentCanvasState = gameCanvas.getCurrentGameState();

            if(currentCanvasState == gameCanvas.titleState) {
                System.out.println();
            }

            if(currentCanvasState == gameCanvas.runningState) {
                gameFrame.getContentPane().removeAll();
                gameFrame.repaint();
                gameFrame.revalidate();
                break;
            }
        }
        setUpRunningCanvas();
    }

    public void setUpRunningCanvas() {
        moduleCanvas.setUpSwingComponents();
        Container cp2 = gameFrame.getContentPane();
        cp2.add(moduleCanvas, BorderLayout.CENTER);

        cp2.repaint();
        cp2.revalidate();
    }

    public void changetoOverState() {
        while(true) {
            BombTimer timerBomb = moduleCanvas.getBombTimer();
            int zeroMinutes = timerBomb.getMinutesLeft();
            int zeroSeconds = timerBomb.getSecondsLeft();
            System.out.println();

            if(zeroMinutes == 0 && zeroSeconds == 0) {
                gameCanvas.gameState = gameCanvas.gameOverState;
                int currentCanvasState = gameCanvas.getCurrentGameState();
                //System.out.println(currentCanvasState);
                gameFrame.getContentPane().removeAll();
                gameFrame.repaint();
                gameFrame.revalidate();
                break;
   
            }
        }
        setUpOverCanvas();  
    }

    public void setUpOverCanvas() {
        Container cp3 = gameFrame.getContentPane();
        cp3.add(overCanvas, BorderLayout.CENTER);

        cp3.repaint();
        cp3.revalidate();
    }
}


   
