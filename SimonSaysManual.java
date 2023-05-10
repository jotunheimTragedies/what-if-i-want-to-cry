import java.awt.*;
import java.io.*;
import javax.swing.*;

public class SimonSaysManual extends JPanel implements ModuleTemplate {
    private JPanel simonPanel;
    private JTextArea simonManual;
    private Font cascadia; 

    public SimonSaysManual() {
        setBackground(Color.blue);
        setLayout(null);

        // https://www.fontsquirrel.com/fonts/cascadia-code
        try {
            InputStream simonStream = getClass().getResourceAsStream("Cascadia.ttf");
            cascadia = Font.createFont(Font.TRUETYPE_FONT, simonStream).deriveFont(25F);

        } catch(IOException | FontFormatException ex) {

        }

        setUpSwingComponents();
        add(simonPanel);
    }

    @Override
    public void setUpSwingComponents() {
        simonManual = new JTextArea();
        simonManual.setFont(cascadia);
        simonManual.setText(" While i <= 5\n    Red flash = Yellow button\n    Green flash = Red button\n    Blue Flash = Green button\n    Yellow Flash = Blue button");
        simonManual.setLineWrap(true);
        simonManual.setEditable(false);

        simonPanel = new JPanel();
        simonPanel.setBackground(Color.blue);
        simonPanel.setBounds(10, 10, 455, 250);
        simonPanel.setLayout(new BorderLayout());
        simonPanel.add(simonManual, BorderLayout.CENTER);
    }
}

