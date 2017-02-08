package utils;

import java.util.StringTokenizer;
import static general.Words.words;

/**
 * Created by root on 07.02.17.
 */
public class Util {

    synchronized public static void addWords(String string) {
        StringTokenizer stringTokenizer = new StringTokenizer(string, " \t\n,:-;.0123456789");
        while (stringTokenizer.hasMoreTokens()) {
            String word = stringTokenizer.nextToken();
            if (words.containsKey(word)) {
                words.put(word, words.get(word) + 1);
                System.out.println(word + " " + words.get(word));
            } else {
                words.put(word, 1);
                System.out.println(word + " " + words.get(word));
            }

        }
    }

    public static boolean isCorrectLine(String line) {
        return !line.matches(".*[a-zA-Z].*");
    }
}
