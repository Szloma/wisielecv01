package wisielecv01;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.List;

public class WordBase implements WordRepository{
    private List<String> englishWords;
    private List<String> polishWords;
    private String language;

    public WordBase() {
        this.englishWords = new ArrayList<>();
        this.polishWords = new ArrayList<>();
        this.language = "english";
        initializeEnglishWords();
        initializePolishWords();
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRandomWord() {
        List<String> words = getWordsList();
        Random random = new Random();
        int index = random.nextInt(words.size());
        return words.get(index);
    }

    public String getRandomWord(int length) {
        List<String> words = getWordsList();
        List<String> filteredWords = new ArrayList<>();

        for (String word : words) {
            if (word.length() == length) {
                filteredWords.add(word);
            }
        }

        Random random = new Random();
        int index = random.nextInt(filteredWords.size());
        return filteredWords.get(index);
    }

    public boolean addWord(String language,String word) {
        List<String> words = getWordsList(language);

        if (!words.contains(word.toLowerCase())) {
            words.add(word.toLowerCase());
            return true;
        }

        return false;
    }

    private List<String> getWordsList(String language) {
        if (language.equals("english")) {
            return englishWords;
        } else if (language.equals("polish")) {
            return polishWords;
        } else {
            throw new IllegalArgumentException("Unsupported language: " + language);
        }
    }


    public void DEBUGprintAllWords() {
        List<String> words = getWordsList();
        for (String word : words) {
            System.out.println(word);
        }
    }

    private List<String> getWordsList() {
        if (language.equals("english")) {
            return englishWords;
        } else if (language.equals("polish")) {
            return polishWords;
        } else {
            throw new IllegalStateException("Unsupported language: " + language);
        }
    }

    private void initializeEnglishWords() {
        // Add English words to the English dictionary
        englishWords.add("george");
        englishWords.add("pjatk");
        englishWords.add("cream");
        englishWords.add("quaternioin");
        // ...
    }

    private void initializePolishWords() {
        // Add Polish words to the Polish dictionary
        polishWords.add("janusz");
        polishWords.add("pjatk");
        polishWords.add("masz");
        polishWords.add("twarz");
        // ...
    }
}