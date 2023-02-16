import java.util.*;

public class GS01 {

    /**
     * Given a palindrome, find a string that is:
     * - Lowest value alphabetically after changing only one character
     * - Not a palindrome
     * @param palindromeStr The palindrome string
     * @return The new string or "IMPOSSIBLE" if no such string exists
     */
    public static String breakPalindrome(String palindromeStr) {
        // If the string is empty or has only one character, return "IMPOSSIBLE"
        if (palindromeStr.length() <= 1) {
            return "IMPOSSIBLE";
        }
        for (int i = 0; i < palindromeStr.length() / 2; i++) {
            // If the character is not 'a', then change it to 'a' and return
            if (palindromeStr.charAt(i) != 'a') {
                return palindromeStr.substring(0, i) + 'a' + palindromeStr.substring(i + 1);
            }
        }

        return "IMPOSSIBLE";
    }
}
