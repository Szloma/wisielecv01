package wisielecv01;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Wisielec extends JFrame {
    private JLabel wordLabel;
    private JLabel attemptsLabel;
    private JTextField inputField;
    private JButton guessButton;

    private String wordToGuess;
    private StringBuilder currentWordState;
    private int attemptsLeft;

    public Wisielec(String wordToGuess, int windowWidth, int windowHeight) {
        this.wordToGuess = wordToGuess;
        this.currentWordState = new StringBuilder(wordToGuess.replaceAll("[a-zA-Z]", "_"));
        this.attemptsLeft = 7;

        setTitle("Wisielec");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        wordLabel = new JLabel(currentWordState.toString());
        attemptsLabel = new JLabel("Pozostałe próby: " + attemptsLeft);
        inputField = new JTextField(10);
        guessButton = new JButton("Zgadnij");

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String guess = inputField.getText().toLowerCase();
                boolean correctGuess = false;

                for (int i = 0; i < wordToGuess.length(); i++) {
                    if (wordToGuess.charAt(i) == guess.charAt(0)) {
                        currentWordState.setCharAt(i, guess.charAt(0));
                        correctGuess = true;
                    }
                }

                if (!correctGuess) {
                    attemptsLeft--;
                    attemptsLabel.setText("Pozostałe próby: " + attemptsLeft);
                }

                wordLabel.setText(currentWordState.toString());
                inputField.setText("");

                if (currentWordState.toString().equals(wordToGuess)) {
                    JOptionPane.showMessageDialog(null, "Brawo! Wygrałeś!");
                    System.exit(0);
                }

                if (attemptsLeft == 0) {
                    JOptionPane.showMessageDialog(null, "Przegrałeś! Szukane słowo to: " + wordToGuess);
                    System.exit(0);
                }
            }
        });

        add(wordLabel);
        add(attemptsLabel);
        add(inputField);
        add(guessButton);

        setSize(windowWidth, windowHeight);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        String wordToGuess = "janusz"; // Słowo do zgadnięcia
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height/2;
        int screenWidth = screenSize.width/2;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Wisielec(wordToGuess, screenWidth, screenHeight);
            }
        });
    }
}