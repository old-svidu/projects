package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Util;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by root on 09.02.17.
 */
class UtilTest {
    @Test
    void addToHashSet() {


    }

    @Test
    void addToList() {


        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("home/sa/MyFolder/Test/test.txt")))) {
            LinkedList<String> list = new LinkedList<>();
            list.add("мама мыла раму");
            Assertions.assertEquals(list,Util.addToList(br,list));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @Test
    void isCorrectLine() {
        String string = new String("a sd");
        Assertions.assertEquals(false,Util.isCorrectLine(string));

    }

    @Test
    void isUrl() {
        String string = new String("http://innopolis.ru");
        boolean flag = Util.isUrl(string);
        Assertions.assertEquals(true,flag);

    }

}