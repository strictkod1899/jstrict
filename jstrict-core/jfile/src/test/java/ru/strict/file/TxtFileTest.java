package ru.strict.file;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.strict.file.txt.TxtFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TxtFileTest {

    private static final String TEST_FILE_NAME = "test.txt";
    private static final String TEST_FILE_CONTENT_1 = "mytest_mytest1";
    private static final String TEST_FILE_CONTENT_2 = "mytest_mytest2";

    @AfterEach
    public void post() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_FILE_NAME));
    }

    @Test
    public void testWriteBySource() {
        TxtFile fileForWrite = new TxtFile(TEST_FILE_NAME);
        TxtFile fileForRead = new TxtFile(TEST_FILE_NAME);
        fileForWrite.write(TEST_FILE_CONTENT_1);
        Assertions.assertEquals(fileForRead.read(), TEST_FILE_CONTENT_1);
    }

    @Test
    public void testWrite() {
        TxtFile fileForWrite = new TxtFile(TEST_FILE_NAME);
        TxtFile fileForRead = new TxtFile(TEST_FILE_NAME);
        fileForWrite.setContent(TEST_FILE_CONTENT_2);
        fileForWrite.write();
        Assertions.assertEquals(fileForRead.read(), TEST_FILE_CONTENT_2);
    }
}
