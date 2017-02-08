package main;


import utils.Parser;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 07.02.17.
 */
public class Main {
    public static void main(String[] args) {
        String fileLocation = "/home/sa/MyFolder/test.txt";
        String fileLocation1 = "/home/sa/MyFolder/test1.txt";
        String fileLocation2 = "/home/sa/MyFolder/test2.txt";
        String fileLocation3 = "/home/sa/MyFolder/test3.txt";
        String fileLocation4 = "/home/sa/MyFolder/test32.txt";
        List<String> resources = new ArrayList<>();
        resources.add(fileLocation);
        resources.add(fileLocation1);
        resources.add(fileLocation2);
        resources.add(fileLocation3);
        resources.add(fileLocation4);

        Parser.parseFromResources(resources);
    }

}
