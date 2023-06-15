package wisielecv01;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;
import java.util.*;

public class WordBase {
    private Map<String, List<String>> dictionary;

    public WordBase() {
        dictionary = new HashMap<>();
        initializedictionary();
    }

    private void initializedictionary() {
        List<String> englishWords = Arrays.asList("george", "karen", "pjaots", "motorcycle", "bus");
        List<String> polishWords = Arrays.asList("janusz", "grazyna", "pjatk", "motur", "autobus");

        dictionary.put("english", englishWords);
        dictionary.put("polish", polishWords);
    }

    public void setLanguage(String language) {
        if (!dictionary.containsKey(language)) {
            throw new IllegalArgumentException("Unsupported language: " + language);
        }
    }

    public String getRandomWord() {
        Random random = new Random();
        List<String> words = new ArrayList<>(dictionary.values().stream().flatMap(List::stream).toList());
        return words.get(random.nextInt(words.size()));
    }

    public String getRandomWord(int length) {
        Random random = new Random();
        List<String> words = new ArrayList<>(dictionary.values().stream().flatMap(List::stream).toList());
        List<String> wordsWithLength = new ArrayList<>();

        for (String word : words) {
            if (word.length() == length) {
                wordsWithLength.add(word);
            }
        }

        if (wordsWithLength.isEmpty()) {
            throw new IllegalArgumentException("No words found with specified length: " + length);
        }

        return wordsWithLength.get(random.nextInt(wordsWithLength.size()));
    }
}