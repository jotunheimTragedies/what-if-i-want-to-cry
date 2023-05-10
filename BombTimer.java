import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.io.*;
import javax.swing.*;

public class BombTimer extends JPanel implements ModuleTemplate {

    private JPanel countdownPanel; 
    private JLabel countdownLabel; 
    private Font tickingTimeBomb; 
    private Timer countdownTimer; 

    private int minutesLeft;
    private int secondsLeft; 
    private DecimalFormat decimalFormat; 
    private String doubleMinute; 
    private String doubleSecond; 

    public BombTimer() {
        setBackground(Color.CYAN);
        setLayout(null);
        
        // https://www.youtube.com/watch?v=g-wrebFVP3E
        // https://www.1001fonts.com/ticking-timebomb-bb-font.html
        try {
            InputStream inputStream = getClass().getResourceAsStream("TickingTimebombBB.ttf");
            tickingTimeBomb = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(170F);

        } catch(IOException | FontFormatException e) {

        }
        
        countdownLabel = new JLabel();
        countdownLabel.setHorizontalAlignment(JLabel.CENTER);
        countdownLabel.setFont(tickingTimeBomb);
        countdownLabel.setForeground(Color.red);
      
        decimalFormat = new DecimalFormat("00");
        countdownLabel.setText("0:05");
        minutesLeft = 0; 
        secondsLeft = 5; 

        setUpSwingComponents();
        countdownTimer.start();

        add(countdownPanel);
        repaint();

    }

    @Override
    public void setUpSwingComponents() {
        
    // https://stackoverflow.com/questions/12119327/placing-a-jlabel-at-a-specific-x-y-coordinate-on-a-jpanel
    // https://www.youtube.com/watch?v=zWw72j-EbqI
        countdownPanel = new JPanel(); 
        countdownPanel.setBackground(Color.black);
        countdownPanel.setBounds(30, 180, 420, 190);
        countdownPanel.setLayout(new BorderLayout());
        countdownPanel.add(countdownLabel, BorderLayout.CENTER);
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
                    countdownTimer.stop();
                }
            }
        });
    }

    public int getMinutesLeft() {
        return minutesLeft; 
    }

    public int getSecondsLeft() {
        return secondsLeft; 
    }
    
}