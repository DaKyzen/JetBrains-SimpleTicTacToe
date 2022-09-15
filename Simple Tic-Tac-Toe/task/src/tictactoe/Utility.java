package tictactoe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {
    public static int tryParseInt(String userInput, int defaultValue) {
        try {
            return Integer.parseInt(userInput);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static int countMatches(Pattern pattern, String input) {
        Matcher matcher = pattern.matcher(input);
        int count = 0;
        while (matcher.find())
            count++;
        return count;
    }
}
