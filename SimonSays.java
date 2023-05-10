import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.swing.ButtonModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class SimonSays extends JPanel implements ActionListener, ChangeListener, ModuleTemplate {

    public int flashed, ticks, dark, i, rounds;
    public int indexPattern;
    public ArrayList<Integer> currentPattern;

    public JPanel backpanel;
    public Random random;
    public Timer timer;
    public Timer numtimer;

    //all of the images for the colors
    public ImageIcon redimage;
    public ImageIcon reddark;
    public ImageIcon yellowimage;
    public ImageIcon yellowdark;
    public ImageIcon blueimage;
    public ImageIcon bluedark;
    public ImageIcon greenimage;
    public ImageIcon greendark;
    
    public boolean makePattern;
    public boolean pressed;
    public boolean SimonStrike;

    public JButton red;
    public JButton green;
    public JButton yellow;
    public JButton blue;

    public SimonSays(){
        start();
        indexPattern = 0;
        dark = 2;
        flashed = 0;
        ticks = 0;
        random = new Random();
        makePattern = true;
        SimonStrike = false;
        pressed = false;
        setLayout(new GridLayout(1,1,10,10));
        backpanel = new JPanel();
        backpanel.setBackground(Color.GRAY);
        backpanel.setLayout(new GridLayout(2,2,10,10));
       
        
        timer = new Timer(5, new ActionListener() {
			public void actionPerformed(ActionEvent e){
                
				backpanel.repaint();
            
				//this method so its a continuous animation
			}
		});

        rounds = 0;

        numtimer = new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent e){
                
                ticks++;
                flashed = 0;
                if(dark >=0){
                    dark--;
                }

                if(makePattern){
                    if(dark <= 0){
                        if(indexPattern >= currentPattern.size()){
                            flashed = random.nextInt(40) % 4 + 1;
                            currentPattern.add(flashed);
                            indexPattern = 0;
                            makePattern = false;
                            rounds++;

                        }else{
                            flashed = currentPattern.get(indexPattern);
                            indexPattern++;
                        }
                        dark = 2;
                    }
                }else if(indexPattern == currentPattern.size()){
                    makePattern = true;
                    indexPattern = 0;
                    dark = 2;
                }
                //next set of if statements are for the flashing
                if(flashed == 2){
                    red.setIcon(redimage);
                }else{
                    red.setIcon(reddark);
                }
        
                if(flashed == 4){
                    yellow.setIcon(yellowimage);
                        
                    
                }else{
                    yellow.setIcon(yellowdark);
                        
                }
        
                if(flashed == 1){
                    green.setIcon(greenimage);
                        
                }else{
                    green.setIcon(greendark);
                    
                }
        
                if(flashed == 3){
                    blue.setIcon(blueimage);
                        
                }else{
                    blue.setIcon(bluedark);
                        
                }

                if(rounds == 5){
                    numtimer.stop();
                    solved(true);
                }
           
				//this method so its a continuous animation
			}
            
		});
        
       
        //the images used for the buttons
        redimage = new ImageIcon("Modules/SimonSaysImages/red.png");
        reddark = new ImageIcon("Modules/SimonSaysImages/reddark.png");
        yellowimage = new ImageIcon("Modules/SimonSaysImages/yellow.png");
        yellowdark = new ImageIcon("Modules/SimonSaysImages/yellowdark.png");
        greenimage = new ImageIcon("Modules/SimonSaysImages/green.png");
        greendark = new ImageIcon("Modules/SimonSaysImages/greendark.png");
        blueimage = new ImageIcon("Modules/SimonSaysImages/blue.png");
        bluedark = new ImageIcon("Modules/SimonSaysImages/darkblue.png");
        
        
        numtimer.start();
		timer.start();
        setBackground(Color.GRAY);
        setUpSwingComponents();
        backpanel.add(red);
        backpanel.add(blue);
        backpanel.add(green);
        backpanel.add(yellow);
        add(backpanel);
    
     } 


    public void start(){
        indexPattern = 0;
        currentPattern = new ArrayList<Integer>();
        SimonStrike = false;

    }

    public boolean solved(boolean i){
        return i;
    }

    public void setUpSwingComponents(){

    red = new JButton(redimage);
    red.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
                red.setIcon(reddark);
                }  
    });  
    red.getModel().addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {

            ButtonModel model = (ButtonModel) e.getSource();

            // if the current state differs from the previous state
            if (model.isPressed() != pressed) {
                red.setIcon(redimage);      
                if(currentPattern.get(indexPattern) == 1){
                    indexPattern++;
                }else{
                    SimonStrike = true;
                }

                if(SimonStrike){
                    start();
                }

                ticks = 1;
                pressed = model.isPressed();
                pressed = false;
                
            }              
        }
    });



    yellow = new JButton(yellowimage);
    yellow.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
            yellow.setIcon(yellowdark);
                }  
    });  
    yellow.getModel().addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {

            ButtonModel model = (ButtonModel) e.getSource();

            // if the current state differs from the previous state
            if (model.isPressed() != pressed) {
                yellow.setIcon(yellowimage);
                if(currentPattern.get(indexPattern) == 2){
                    indexPattern++;
                }else{
                    SimonStrike = true;
                }

                if(SimonStrike){
                    start();
                }
                
                ticks = 1;
                pressed = model.isPressed();
                pressed = false;
                
            }              
        }
    });

    

    green = new JButton(greenimage);
    green.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
            green.setIcon(greendark);
                }  
    });  
    green.getModel().addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {

            ButtonModel model = (ButtonModel) e.getSource();

            // if the current state differs from the previous state
            if (model.isPressed() != pressed) {
                green.setIcon(greenimage);
                if(currentPattern.get(indexPattern) == 3){
                    indexPattern++;
                }else{
                    SimonStrike = true;
                }

                if(SimonStrike){
                    start();
                }
                ticks = 1;
                pressed = model.isPressed();
                pressed = false;
                
            }              
        }
    });


    blue = new JButton(blueimage);
    blue.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
            blue.setIcon(bluedark);
            
                }  
    });  
    blue.getModel().addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {

            ButtonModel model = (ButtonModel) e.getSource();

            // if the current state differs from the previous state
            if (model.isPressed() != pressed) {
                blue.setIcon(blueimage);
                if(currentPattern.get(indexPattern) == 4){
                    indexPattern++;
                }else{
                    SimonStrike = true;
                }

                if(SimonStrike){
                    start();
                } 

                    ticks = 1;
                    pressed = model.isPressed();
                    pressed = false;
                    
                }              
          }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void stateChanged(ChangeEvent e) {}

    
    public class BtnModelListener implements ChangeListener{
        
        public boolean pressed;

        public BtnModelListener(){
            pressed = false; // holds the last pressed state of the button
        } 

        @Override
        public void stateChanged(ChangeEvent e) {
            ButtonModel model = (ButtonModel) e.getSource();

            // if the current state differs from the previous state
            if (model.isPressed() != pressed) {
                pressed = model.isPressed();
            }

        }
    }
    
}
