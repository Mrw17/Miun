package dt066g.assignments.assignment3.task1;

/**
 * @author Daniel Westerlund
 * Class that can encode text -> robber-language
 * and decode robber-language -> text
 */
public class RobberLanguage {
	private static final String CONSONANTS = "bcdfghjklmnpqrstvwxz";
	private static final String VOWELS = "aeiouyåäö";

	/**
	 * Encrypt a text in to robber-language, dont support extra exceptions
	 * Example: Jag talar rövarspråket -> jojagog totalolaror rorövovarorsospoproråkoketo
	 * @param text to be transformed
	 * @return string with robber-language
	 */
	public static String toRobber(String text) {
		StringBuilder sb = new StringBuilder();
		for(char c : text.toCharArray()){
			//Always add first char
			sb.append(c);
			//Checks if it a consonant, adds o and itself to output
			//for(char c1 : CONSONANTS.toCharArray()){
				if(checkIfConstant(Character.toLowerCase(c))){
					sb.append("o");
					sb.append(c);
					//break;
				}
			//}
		}
		return sb.toString();
	}

	/**
	 * Decodes a text from robber-language, dont support extra exceptions
	 * Transforms jojagog totalolaror rorövovarorsospoproråkoketo -> Jag talar rövarspråket
	 * @param robberText to be transformed
	 * @return a string with decoded robber-language
	 */
	public static String fromRobber(String robberText) {
		StringBuilder sb = new StringBuilder();
		String[] parts = robberText.split(" ");

		//Splits all words
		for (String s : parts) {
			//Walks through all chars in a word
			for (int i = 0; i < s.length(); i++) {
				boolean hit = false;
				//Compare char[i] from a word with all consonants
				//If we gets a hit, we add that char to output and ignore the two next chars (with i+2)
				if (checkIfConstant(s.charAt(i))) {
					sb.append(s.charAt(i));
					i = i + 2 ;
					hit = true;
				}
				//Not a consonant, just add it to output
				if (!hit) {
					sb.append(s.charAt(i));
				}
			}
			//adds space between words
			sb.append(" ");
		}
		return sb.toString();
	}

	/**
	 * Encodes a single char to robber-language
	 * if constant it adds o and the char again
	 * Ex: a -> a, b -> bob
	 * @param c to be encoded
	 * @return char[] with encoded c
	 */
	public static char[] encodeCharToRobber(int c){
		char transformToChar = (char) c;

		if(validChar(transformToChar)){
			char[] chars = new char[3];
			chars[0] = transformToChar;
			//If constant -> adds 0 and the char again
			if(checkIfConstant(transformToChar)){
				chars[1] = 'o';
				chars[2] = transformToChar;
			}
			return chars;
		}
		return null;
	}

	/**
	 * Checks if a char is a consonant or not
	 * @param c to be checked
	 * @return boolean if char is consonant or not
	 */
	public static boolean checkIfConstant(char c){
		boolean check = false;
		//Go through all consonants
		for (int i = 0; i < CONSONANTS.length(); i++) {
			//Transforms both to lowercase, breaks loop if through
			if (Character.toLowerCase(c) == Character.toLowerCase(CONSONANTS.charAt(i))){
				check = true;
				break;
			}
		}
		return check;
	}

	/**
	 * Checks if a char is valid:
	 * Alphabetic, blank spaces, ! and ? is valid
	 * @param ch
	 * @return
	 */
	public static boolean validChar(char ch){
		boolean check = false;

		if(Character.isAlphabetic(ch) || ch == ' ' || ch == '\t' || ch == '\r' || ch == '\n' || ch == '!' || ch == '?')
			check = true;

		return check;
	}
}