package utils;

import general.Words;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import static general.Words.words;

/**
 * Created by root on 07.02.17.
 */
public class Util {

    /**
     * @param string строка, которую разбиваем на слова(после разбиения добавляем слова в HashMap)
     */
    public static void addToHashSet(String string) {
        synchronized (words) {
            Scanner scanner = new Scanner(string);
            scanner.useDelimiter("[\\s.,:;0-9\\(\\)\\[\\]]+");
            while (scanner.hasNext()) {
                String word = scanner.next();
                if (words.containsKey(word)) {
                    words.put(word, words.get(word) + 1);
                    System.out.println(word + " " + words.get(word));
                } else {
                    words.put(word, 1);
                    System.out.println(word + " " + words.get(word));
                }
            }
        }
    }

    /**
     * @param br
     * @param list список, в который будем считывать строки
     * @throws IOException
     */
    public static void addToList(BufferedReader br, LinkedList<String> list) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            if (isCorrectLine(line)) {
                list.addLast(line);
            } else {
                Words.flag = false;
                throw new IOException("Ошибка в содержимом файла");
            }
        }
    }

    /**
     * @param line
     * @return
     */
    public static boolean isCorrectLine(String line) {
        return !line.matches(".*[a-zA-Z].*");
    }

    /**
     * @param line
     * @return
     */
    public static boolean isUrl(String line) { return line.matches("^http"); }
}
