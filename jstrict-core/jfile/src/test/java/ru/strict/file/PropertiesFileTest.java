package ru.strict.file;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.strict.file.properties.PropertiesFile;
import ru.strict.utils.ResourcesUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PropertiesFileTest {

    private static final String TEST_FILE_NAME = "test.properties";

    @AfterEach
    public void post() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_FILE_NAME));
    }

    @Test
    public void test() {
        String expected1 = "hello";
        String expected2 = "2";

        ResourcesUtil.getResourceAsFile(TEST_FILE_NAME);
        PropertiesFile file = new PropertiesFile(TEST_FILE_NAME);

        Assertions.assertEquals(expected1, file.readValue("var1"));
        Assertions.assertEquals(expected2, file.readValue("var2"));
    }
}
