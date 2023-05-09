
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

public class SimonSaysManual extends JPanel{
    public JPanel backpanel;
    public JTextArea manual;

    public SimonSaysManual(){
        backpanel = new JPanel();
        backpanel.setLayout(new GridLayout(1,1));

        manual = new JTextArea();
        Font font = new Font("Verdana", Font.BOLD, 20);
        manual.setFont(font);
        manual.setText("While i <= 5\n    Red flash = Yellow button\n    Green flash = Red button\n    Blue Flash = Green button\n    Yellow Flash = Blue button");
        manual.setLineWrap(true);
        manual.setEditable(false);
        manual.setBounds(0,250,500,500);
        backpanel.add(manual);
        add(backpanel);

    }
}
