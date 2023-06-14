package wisielecv01;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Wisielec extends JFrame {

    public Wisielec(String wordToGuess) {


    }

    public static void main(String[] args) {
        String wordToGuess = "wisielec"; // Słowo do zgadnięcia
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	JFrame frame = new JFrame();
                frame.setSize(screenWidth/2,screenHeight/2);
                frame.setLocationByPlatform(true);
                frame.setTitle("Wisielec v0.01");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}