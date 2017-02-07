package main;


import utils.Parser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by root on 07.02.17.
 */
public class Main {



    public static void main(String[] args) {
        String fileLocation = "/home/sa/MyFolder/test.txt";
        String fileLocation1 = "/home/sa/MyFolder/test1.txt";
        String fileLocation2 = "/home/sa/MyFolder/test2.txt";
        List<String> resources = new ArrayList<>();
        resources.add(fileLocation);
        resources.add(fileLocation1);
        resources.add(fileLocation2);


        //hello, world!

        try {
            Parser.parseFromResources(resources);
        } catch (IOException e) {
            System.out.println("ошибка в main");
        }





    }

}
