package wisielecv01;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static char getFirstCharacter(String input) {
        Pattern pattern = Pattern.compile("^.");
        Matcher matcher = pattern.matcher(input);
        
        if (matcher.find()) {
            return matcher.group().charAt(0);
        }
        
        throw new IllegalArgumentException("Input string is empty.");
    }

}
