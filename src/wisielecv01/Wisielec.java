package wisielecv01;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Wisielec extends JFrame {
	private JLabel wordLabel;
    private JLabel attemptsLabel;
    private JTextField inputField;
    private JButton guessButton;
    private JLabel textureLabel;
    private JLabel usedLettersLabel;

    private String wordToGuess;
    private StringBuilder currentWordState;
    private int attemptsLeft;
    private StringBuilder usedLetters;

    public Wisielec(String wordToGuess, int windowWidth, int windowHeight) {
        this.wordToGuess = wordToGuess;
        this.currentWordState = new StringBuilder(wordToGuess.replaceAll("[a-zA-Z]", "_"));
        this.attemptsLeft = 7;
        this.usedLetters = new StringBuilder();

        setTitle("Wisielec");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        wordLabel = new JLabel(currentWordState.toString());
        attemptsLabel = new JLabel("Pozostałe próby: " + attemptsLeft);
        inputField = new JTextField(10);
        guessButton = new JButton("Zgadnij");
        textureLabel = new JLabel();
        usedLettersLabel = new JLabel();

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guessWord();
            }
        });

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guessWord();
            }
        });

        add(wordLabel);
        add(attemptsLabel);
        add(inputField);
        add(guessButton);
        add(textureLabel);
        add(usedLettersLabel);
        usedLettersLabel.setText("Użyte litery:");
        setSize(windowWidth, windowHeight);	
        setLocationRelativeTo(null);
        setVisible(true);

        updateTexture();
    }

    private void guessWord() {
    	
        String guess = inputField.getText().toLowerCase();
        if(guess.length()!=0) {
        	boolean correctGuess = false;
        	
        	//sekcja użytych liter
            if (!usedLetters.toString().contains(guess)) {
            	
                usedLetters.append(Utils.getFirstCharacter(guess)).append(" ");
            }      
            usedLettersLabel.setText("Użyte litery: " + usedLetters.toString());

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

            updateTexture();

            if (currentWordState.toString().equals(wordToGuess)) {
                JOptionPane.showMessageDialog(null, "Brawo! Wygrałeś!");
                System.exit(0);
            }

            if (attemptsLeft == 0) {
                JOptionPane.showMessageDialog(null, "Przegrałeś! Szukane słowo to: " + wordToGuess);
                System.exit(0);
            }
        }
        
    }
    private void updateTexture() {
        ImageIcon textureIcon;
        if (attemptsLeft >= 7) {
            textureIcon = new ImageIcon("textures/texture1.png"); // Ścieżka do tekstury dla 7 pozostałych prób
        } else if (attemptsLeft == 6) {
            textureIcon = new ImageIcon("textures/texture2.png"); // Ścieżka do tekstury dla 6 pozostałych prób
        } else if (attemptsLeft == 5) {
            textureIcon = new ImageIcon("textures/texture3.png"); // Ścieżka do tekstury dla 5 pozostałych prób
        } else if (attemptsLeft == 4) {
            textureIcon = new ImageIcon("textures/texture4.png"); // Ścieżka do tekstury dla 4 pozostałych prób
        } else if (attemptsLeft == 3) {
            textureIcon = new ImageIcon("textures/texture5.png"); // Ścieżka do tekstury dla 3 pozostałych prób
        } else if (attemptsLeft == 2) {
            textureIcon = new ImageIcon("textures/texture6.png"); // Ścieżka do tekstury dla 2 pozostałych prób
        } else {
            textureIcon = new ImageIcon("textures/blank.png"); // Ścieżka do tekstury dla 1 pozostałej próby
        }
        Image image = textureIcon.getImage();
        Image scaledImage = image.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        textureIcon = new ImageIcon(scaledImage);
        textureLabel.setIcon(textureIcon);
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