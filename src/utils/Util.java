package utils;

import java.util.Scanner;
import static general.Words.words;

/**
 * Created by root on 07.02.17.
 */
public class Util {

    /**
     * @param string строка, которую разбиваем на слова(после разбиения добавляем слова в HashMap words)
     *
     * HashMap words содержит пары (слово, количество вхождений в ресурсы).
     * Метод разбивает строку на слова. Для каждого слова проверяется, является ли оно ключом в HashMap.
     *
     * Если слово является ключом, то мы увеличиваем количество вхождений слова  на 1 и выводим
     * данное словно на консоль с количеством его вхождений в обработанные ресурсы.
     *
     * Если слово не является ключом, то это сигнализирует о том, что мы встречаем  его первый раз и нам необходимо
     * добавить его в HashMap. После добавление в HashMap, выводим слово на консоль с количеством его вхождений
     * в обработанные ресурсы.
     */
    public static void addToHashMap(String string) {
        synchronized (words) {
            Scanner scanner = new Scanner(string);
            scanner.useDelimiter("[\\s\\p{Punct}0-9]+");
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
     * @param line
     * @return true если строка не содержит латинские символы и false  в противном случае
     * Метод является сигнализатором, допустимый ли файл
     */
    public static boolean isCorrectLine(String line) {
        return !line.matches("^.*[a-zA-Z]+.*$");
    }

    /**
     * @param line
     * @return true если строка начинается с http:// или http:// и false в противном случае
     * Метод является сигнализатором, является ли строка URL или нет.
     */
    public static boolean isUrl(String line) {
        return line.matches("^(http:\\/)|^(https:\\/).+$"); }
}
