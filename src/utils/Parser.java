package utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static general.Words.words;

/**
 * Created by root on 07.02.17.
 */
public class Parser extends Thread {

    public static List<String> parseLines(String fileLocation) throws IOException {
        return Files.readAllLines(Paths.get(fileLocation), StandardCharsets.UTF_8);
    }


    public static void addWords(String string) throws IOException {
        synchronized (words) {
            StringTokenizer stringTokenizer = new StringTokenizer(string, " \t\n,:-;");
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
    }


    public static void parseWords(String fileLocation) throws IOException {
        for (String line : parseLines(fileLocation)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        addWords(line);
                    } catch (IOException e) {
                        System.out.println("ошибка в parseWords");
                    }
                }
            }).start();
        }
    }


    public static void parseFromResources(List<String> resources) throws IOException{
        for (String resource : resources) {
            parseWords(resource);
        }
    }


}
