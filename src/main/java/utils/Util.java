package utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Pattern;

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
     * @param path
     * @return BufferedReader для посторочного считывания файла
     * @throws IOException
     * @throws IllegalArgumentException
     *
     * Функция возвращает BufferedReader, если path является Url или путем к существующему файлу.
     * В противном случае выбрасывается ошибка.
     */
    public static BufferedReader getBufferedReaderFromPath(String path) throws IOException, IllegalArgumentException {
        BufferedReader br;
        if(path.matches("^(http:\\/|https:\\/|ftp:\\/).+$")){
            br = new BufferedReader(new InputStreamReader(new URL(path).openStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        }
        return br;
    }
}
