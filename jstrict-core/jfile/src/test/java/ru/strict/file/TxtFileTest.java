package ru.strict.file;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.file.txt.TxtFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RunWith(JUnit4.class)
public class TxtFileTest {

    private static final String TEST_FILE_NAME = "test.txt";
    private static final String TEST_FILE_CONTENT_1 = "mytest_mytest1";
    private static final String TEST_FILE_CONTENT_2 = "mytest_mytest2";

    @After
    public void post() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_FILE_NAME));
    }

    @Test
    public void testWriteBySource() {
        TxtFile fileForWrite = new TxtFile(TEST_FILE_NAME);
        TxtFile fileForRead = new TxtFile(TEST_FILE_NAME);
        fileForWrite.write(TEST_FILE_CONTENT_1);
        Assert.assertEquals(fileForRead.read(), TEST_FILE_CONTENT_1);
    }

    @Test
    public void testWrite() {
        TxtFile fileForWrite = new TxtFile(TEST_FILE_NAME);
        TxtFile fileForRead = new TxtFile(TEST_FILE_NAME);
        fileForWrite.setContent(TEST_FILE_CONTENT_2);
        fileForWrite.write();
        Assert.assertEquals(fileForRead.read(), TEST_FILE_CONTENT_2);
    }
}
