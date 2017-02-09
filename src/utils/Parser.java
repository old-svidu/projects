package utils;

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
     * @return
     */
    public static List<String> parseLinesFromUrl(String path) {
        LinkedList<String> list = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(path).openStream()))) {
            Util.addToList(br, list);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    /**
     * @param path путь к файлу
     * @return
     */
    public static List<String> parseLinesFromFile(String path) {
        LinkedList<String> list = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            Util.addToList(br, list);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }


    /**
     * @param path путь к файлу
     */
    public static void parseWords(String path) {
        if (Util.isUrl(path)) {
            for (String line : parseLinesFromUrl(path)) {
                Util.addToHashSet(line);
            }
        } else {
            for (String line : parseLinesFromFile(path)) {
                Util.addToHashSet(line);
            }
        }
    }

    /**
     * @param resources массив ресурсов(ссылок на файлы и путей к локальным файлам)
     */
    public static void parseFromResources(String[] resources) {
        for (String resource : resources) {
            new Thread(new Runnable() {
                    @Override
                    public void run() {
                        parseWords(resource);
                    }
                }).start();
            }
    }

}
