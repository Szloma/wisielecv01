package wisielecv01;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;

public class Wisielec extends JFrame {
	WordBase wordBase = new WordBase();
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
    private JButton resetButton;
    
    private Map<Integer, String> texturePaths;

    public Wisielec(int windowWidth, int windowHeight) {

        this.wordToGuess = wordBase.getRandomWord();
        this.currentWordState = new StringBuilder(wordToGuess.replaceAll("[a-zA-Z]", "_"));
        this.attemptsLeft = 7;
        this.usedLetters = new StringBuilder();
        this.texturePaths = TextureMap.createTextureMap();

        setTitle("Wisielec");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        wordLabel = new JLabel(currentWordState.toString());
        attemptsLabel = new JLabel("Pozostałe próby: " + attemptsLeft);
        inputField = new JTextField(1);
        guessButton = new JButton("Zgadnij");
        textureLabel = new JLabel();
        usedLettersLabel = new JLabel("Użyte litery:");
        resetButton = new JButton("Resetuj grę");

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

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        
        JMenu difficultyMenu = new JMenu("Trudność");
        JMenuItem easyMenuItem = new JMenuItem("Łatwy");
        JMenuItem mediumMenuItem = new JMenuItem("Średni");
        JMenuItem hardMenuItem = new JMenuItem("Trudny");

        easyMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDifficulty(5);
                setAttemptsLeft(10);
            }
        });

        mediumMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDifficulty(8);
                setAttemptsLeft(7);
            }
        });

        hardMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDifficulty(10);
                setAttemptsLeft(4);
            }
        });
        JMenu languageMenu = new JMenu("Język");
        JMenuItem englishMenuItem = new JMenuItem("Angielski");
        JMenuItem polishMenuItem = new JMenuItem("Polski");

        englishMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setLanguage("english");
            }
        });

        polishMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setLanguage("polish");
            }
        });

        difficultyMenu.add(easyMenuItem);
        difficultyMenu.add(mediumMenuItem);
        difficultyMenu.add(hardMenuItem);
        languageMenu.add(englishMenuItem);
        languageMenu.add(polishMenuItem);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(difficultyMenu);
        menuBar.add(languageMenu);
        
        setJMenuBar(menuBar);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(wordLabel);
        topPanel.add(attemptsLabel);
        topPanel.add(inputField);
        topPanel.add(guessButton);
        topPanel.add(resetButton);

        
        JPanel midPanel = new JPanel();
        midPanel.setLayout(new FlowLayout());
        midPanel.add(textureLabel);
        
        //bottomPanel.add(resetButton);
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(usedLettersLabel);
        
        add(topPanel, BorderLayout.NORTH);
        add(midPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setSize(windowWidth, windowHeight);
        setLocationRelativeTo(null);
        setVisible(true);

        updateTexture();
    }
    private void setDifficulty(int length) {
    	//tu napisać funkcje żeby getrandomword zwracało słowo danej długości
        wordToGuess = wordBase.getRandomWord();
        currentWordState = new StringBuilder("_".repeat(wordToGuess.length()));
        wordLabel.setText(currentWordState.toString());
        resetGame();
    }

    private void setAttemptsLeft(int attempts) {
        attemptsLeft = attempts;
        attemptsLabel.setText("Pozostałe próby: " + attemptsLeft);
        resetGame();
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
    	int totalTextures = texturePaths.size();
    	int textureIndex = Math.min(attemptsLeft, totalTextures) - 1;
    	
    	String texturePath = texturePaths.get(textureIndex);
        textureIcon = new ImageIcon(texturePath);
        Image image = textureIcon.getImage();
        Image scaledImage = image.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        textureIcon = new ImageIcon(scaledImage);
        textureLabel.setIcon(textureIcon);
    }
    private void resetGame() {
        currentWordState = new StringBuilder(wordToGuess.replaceAll("[a-zA-Z]", "_"));
        usedLetters = new StringBuilder();

        wordLabel.setText(currentWordState.toString());
        attemptsLabel.setText("Pozostałe próby: " + attemptsLeft);
        usedLettersLabel.setText("Użyte litery: ");
        inputField.setText("");

        updateTexture();
    }

    public static void main(String[] args) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height/2;
        int screenWidth = screenSize.width/2;
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Wisielec(screenWidth, screenHeight);
            }
        });
    }
}