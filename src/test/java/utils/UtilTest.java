package utils;

import general.Words;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;


/**
 * Created by root on 14.02.17.
 */
class UtilTest {
    @Test
    void addToHashMap() {
        String string = new String("Строка содержит цифры01234567890и символы.,!?");
        String string1 = new String("01234567890 .,!?");
        Util.addToHashMap(string);
        assertTrue(Words.words.size()==5);
        Util.addToHashMap(string1);
        Words.words.clear();
        assertTrue(Words.words.size()==0);

    }

    @Test
    void getBufferedReaderFromPath() throws IOException{
        String validUrl = new String("http://innopolis.ru");
        String path = new String("src/main/resources/test");
        String string = new String("string");

        assertThrows(IOException.class,()->Util.getBufferedReaderFromPath(string));
        assertNotNull(Util.getBufferedReaderFromPath(validUrl));
        assertNotNull(Util.getBufferedReaderFromPath(path));
    }

}