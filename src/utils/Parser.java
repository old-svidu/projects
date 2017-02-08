package utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;


import static utils.Util.addWords;
import static utils.Util.isCorrectLine;

/**
 * Created by root on 07.02.17.
 */
public class Parser extends Thread {


    public static List<String> parseLines(String fileLocation) {
        LinkedList<String> list = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileLocation), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (isCorrectLine(line)) {
                    list.addLast(line);
                } else {
                    throw new IOException("Ошибка в содержимом файла");
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
        return list;
    }


    public static void parseWords(String fileLocation) {
        for (String line : parseLines(fileLocation)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    addWords(line);
                }
            }).start();
        }
    }


    public static void parseFromResources(List<String> resources) {
        for (String resource : resources) {
            parseWords(resource);
        }
    }


}
