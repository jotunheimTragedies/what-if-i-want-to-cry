
import java.awt.*;
import javax.swing.*;

public class KeypadManual extends JPanel{
    public JPanel backpanel;
    public JTextArea manual;
    public JLabel pic;
    public ImageIcon image;

    public KeypadManual(){
        backpanel = new JPanel();
        backpanel.setLayout(new GridLayout(2, 1));

        manual = new JTextArea();
        Font font = new Font("Verdana", Font.BOLD, 15);
        manual.setFont(font);
        manual.setText("Int i = 1;\nIf (!Unlocked)\n    for(int x : Column i)\n        If(x.get(symbol) = currentSymbol)\n            PressButton();\n        i++;");
        //manual.setLineWrap(true);
        manual.setEditable(false);
        //manual.setBounds(0,250,500,500);

        image = new ImageIcon(new ImageIcon("Modules/KeypadImages/order of rows for keypad.png").getImage().getScaledInstance(450, 200, Image.SCALE_DEFAULT));
        pic = new JLabel();
        pic.setIcon(image);
        //pic.setBounds(50,50,50,50);
        backpanel.add(pic);
        backpanel.add(manual);
        
        add(backpanel);
    }
}