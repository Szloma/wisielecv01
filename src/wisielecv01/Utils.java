package wisielecv01;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Utils {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String dupa = "dupa";
		char first = getFirstCharacter(dupa);
		char nth = getCharacterAtIndex(dupa,0);
		System.out.println(first);
		System.out.println(nth);
	}
	public static char getFirstCharacter(String input) {
        return getCharacterAtIndex(input, 0);
    }
	public static char getCharacterAtIndex(String input) {
		return getCharacterAtIndex(input, 0);
    }
	public static char getCharacterAtIndex(String input, int index) {
        if (index < 0 || index >= input.length()) {
            throw new IllegalArgumentException("Invalid index.");
        }
        
        Pattern pattern = Pattern.compile(".{" + index + "}(.{1})");
        Matcher matcher = pattern.matcher(input);
        
        if (matcher.find()) {
            return matcher.group(1).charAt(0);
        }
        
        throw new IllegalArgumentException("No character found at the given index.");
    }
    public static boolean containsNumbers(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

}
