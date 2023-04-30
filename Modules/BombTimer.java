package Modules;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;

public class BombTimer extends JPanel implements ModuleTemplate {
    private JLabel countdownLabel; 
    private Timer countdownTimer; 

    private int minutesLeft;
    private int secondsLeft; 
    private DecimalFormat decimalFormat; 
    private String doubleMinute; 
    private String doubleSecond; 


    public BombTimer() {
        setBackground(Color.CYAN);

        countdownLabel = new JLabel();
        countdownLabel.setHorizontalAlignment(JLabel.CENTER);
        
        countdownLabel.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        
        decimalFormat = new DecimalFormat("00");
        countdownLabel.setText("5:00");
        minutesLeft = 5; 
        secondsLeft = 0; 
        setUpSwingComponents();
        countdownTimer.start();

        add(countdownLabel);
        

        /* countdownLabel = new JLabel("Pedro Pascal");
        add(countdownLabel); */
    }

    @Override
    public void setUpSwingComponents() {
        countdownTimer = new Timer(1000, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsLeft--; 
                doubleMinute = decimalFormat.format(minutesLeft);
                doubleSecond = decimalFormat.format(secondsLeft);
                countdownLabel.setText(doubleMinute + ":" + doubleSecond);

                if(secondsLeft == -1) {
                    secondsLeft = 59; 
                    minutesLeft--; 
                    doubleMinute = decimalFormat.format(minutesLeft);
                    doubleSecond = decimalFormat.format(secondsLeft);
                    countdownLabel.setText(doubleMinute + ":" + doubleSecond);
                }

                if(minutesLeft == 0 && secondsLeft == 0) {
                    //bombExplodes(); - proceed to gameOverState 
                    countdownTimer.stop();

                }

            }
            
        });
    }

    
}