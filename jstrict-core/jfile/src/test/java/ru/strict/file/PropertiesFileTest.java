package ru.strict.file;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.file.properties.PropertiesFile;
import ru.strict.utils.ResourcesUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RunWith(JUnit4.class)
public class PropertiesFileTest {

    private static final String TEST_FILE_NAME = "test.properties";

    @After
    public void post() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_FILE_NAME));
    }

    @Test
    public void test() {
        String expected1 = "hello";
        String expected2 = "2";
        ResourcesUtil.getResourceAsFile(TEST_FILE_NAME);
        PropertiesFile file = new PropertiesFile(TEST_FILE_NAME);
        Assert.assertEquals(file.readValue("var1"), expected1);
        Assert.assertEquals(file.readValue("var2"), expected2);
    }
}
