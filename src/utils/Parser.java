package utils;

import general.Words;

import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by root on 07.02.17.
 */
public class Parser {

    /**
     * @param path путь к файлу
     * @return list Список строк URL ресурса, если ни одна строка ресурса не содержит латинский символ
     * Метод построчно считывает строки ресурса. Если строка корректна (не содержит латинских символов),
     * то она добавляется в список.
     * Если строка содержит латинский символ, то выбрасывается ошибка.
     */
    public static List<String> parseLinesFromUrl(String path) {
        List<String> list = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(path).openStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (Util.isCorrectLine(line)) {
                    list.add(line);
                } else {
                    throw new IOException("Ошибка в содержимом файла "+path);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

     /**
     * @param path Путь к файлу
     *
     * @return list Список строк файла, если файл не содержит латинских символов.
     *
     * Метод построчно считывает файл. Если строка корректна (не содержит латинских симовлов),
     * то она добавляется в список.
     *
     * Если строка содержит латинский символ, то значение флага Words.flag изменяется на false,
     * что будет сигнализировать о завершении обработки ресурсов.
     * После установки флага выбрасывается ошибка и на консоль выводится сообщение,
     * что содержимое файла не соотвествует требуемым ограничениям.
     */
    public static List<String> parseLinesFromFile(String path) {
        List<String> list = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (Util.isCorrectLine(line)) {
                    list.add(line);
                } else {
                    Words.flag.getAndSet(false);
                    throw new IOException("Ошибка в содержимом файла "+path);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

     /**
     * @param path путь к файлу
     *
     * @see utils.Util
     *
     * Метод построчно обрабатывает содержимое ресурса в засимости от того,
     * является ли ресурс URL или файлом.
     *
     */
    public static void parseWords(String path) {
        if (Util.isUrl(path)) {
            for (String line : parseLinesFromUrl(path)) {
                Util.addToHashMap(line);
            }
        } else {
            for (String line : parseLinesFromFile(path)) {
                Util.addToHashMap(line);
            }
        }
    }

    /**
     * @param resources массив ресурсов(ссылок на файлы и путей к локальным файлам)
     *
     * Метод считывает данные с ресурсов (для каждого ресурса создается свой поток).
     *
     * Переменная Words.flag сигнализирует, встретитли ли мы файл,
     * содержащий латинские символы. Если Words.flag является false, то
     * необходимо прекратить обработку ресурсов.
     */
    public static void parseFromResources(String[] resources) {
        for (String resource : resources) {
            if (Words.flag.get()) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        parseWords(resource);
                    }
                });
                thread.start();
            } else {
                break;
            }

        }
    }

}
