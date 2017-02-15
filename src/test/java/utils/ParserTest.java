package utils;

import general.Words;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by root on 14.02.17.
 */
class ParserTest {
    @Test
    void parseLinesFromResource() throws IOException, IllegalArgumentException {

        String path = new String("src/main/resources/test");
        String badPath = new String("src/main/resources/test12");
        String badFile = new String("src/main/resources/badTest");
        String pathUrl = new String("https://raw.githubusercontent.com/jaowl/innopolis-labs/" +
                "faa7299addaea64c0de6512e08fefe0f63448a03/lab1/src/main/resources/validtext");
        String badPathUrl = new String("http:/sd");

        Parser.parseFromResource(path);
        assertTrue(Words.words.size()==9);
        assertThrows(IOException.class,()->Parser.parseFromResource(badPath));
        assertThrows(IOException.class,()->Parser.parseFromResource(badFile));
        assertThrows(IllegalArgumentException.class,()->Parser.parseFromResource(badPathUrl));
    }



    @Test
    void parseFromResources() {
        String path = new String("src/main/resources/test");
        String badFilePath = new String("src/main/resources/badTest");
        String badPath = new String("src/main/resources/test12");

        String[] resources = new String[2];
        resources[0] = path;
        resources[1] = badFilePath;

        Parser.parseFromResources(resources);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(Words.words.size()==9);
    }

}