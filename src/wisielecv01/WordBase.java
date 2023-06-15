package wisielecv01;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;

public class WordBase {
	private Hashtable<Integer, String> dictionary;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public WordBase() {
        dictionary = new Hashtable<>();
        initializeDictionary();
    }
	public void initializeDictionary() {
		dictionary.put(1, "janusz");
		dictionary.put(2, "grazyna");
		dictionary.put(3, "autobus");
		dictionary.put(4, "pjatk");
		dictionary.put(5, "motur");
	}
	public void addWord(String word) {
		dictionary.put(dictionary.size()+1, word);
	}
	public void DEBUGoutputDictionary() {
		 Enumeration<Integer> e = dictionary.keys();
	        while (e.hasMoreElements()) { 
	            // Getting the key of a particular entry
	            int key = e.nextElement();
	            // Print and display the Rank and Name
	            System.out.println("Rank : " + key
	                               + "\t\t Name : "
	                               + dictionary.get(key));
	        }
	}
	public String getRandomWord() {
        Random random = new Random();
        Object[] values = dictionary.values().toArray();
        Object randomValue = values[random.nextInt(values.length)];
        return randomValue.toString();
    }

}
