package utils;

import general.Words;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.*;

/**
 * Created by root on 07.02.17.
 */
public class Parser {
    public static Logger logger = Logger.getLogger(Parser.class);
    static {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
    }

    /**
     * @param path путь к файлу
     * @return list Список строк URL ресурса, если ни одна строка ресурса не содержит латинский символ
     * @see utils.Util
     * @throws IOException
     *
     * Метод построчно считывает строки ресурса. Если строка корректна (не содержит латинских символов),
     * то она разбивается на слова, который добавляются в HashMap
     * Если строка содержит латинский символ, то выбрасывается ошибка.
     */
    public static void parseFromResource(String path) throws IOException {
        try (BufferedReader br = Util.getBufferedReaderFromPath(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.matches("^.*[a-zA-Z]+.*$")) {
                    if (!line.equals("")) {
                        Util.addToHashMap(line);
                    }
                } else {
                    Words.flag.set(false);
                    throw new IOException();
                }
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
                        try {
                            parseFromResource(resource);
                            logger.trace("Ресурс "+resource+" обработан.");
                        } catch (IOException e) {
                            logger.error(e);
                        } catch (IllegalArgumentException e){
                            logger.error(e);
                        }
                    }
                });
                thread.start();
            } else {
                break;
            }

        }
    }

}
