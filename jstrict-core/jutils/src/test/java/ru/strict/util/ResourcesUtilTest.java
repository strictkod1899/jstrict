package ru.strict.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.strict.exception.ResourceNotFoundException;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.strict.util.ResourcesUtil.*;

public class ResourcesUtilTest {

    @Test
    public void testGetResourceAsFile() {
        String appPath = ClassUtil.getPathByClass(ResourcesUtil.class);
        String targetFilePath = appPath + File.separator + "empty.txt";
        getResourceAsFile("sources/empty.txt", targetFilePath);

        Assertions.assertTrue(Files.exists(Paths.get(targetFilePath)));
    }

    @Test
    public void testGetResourceAsTempFile() {
        File file = getResourceAsTempFile("sources/empty.txt");

        Assertions.assertTrue(file.exists());
    }

    @Test
    public void testGetResourceOrThrow() {
        File file = getResourceOrThrow("sources/empty.txt");

        Assertions.assertNotNull(file);
    }

    @Test
    public void testGetResourceOrThrow_fail() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> getResourceOrThrow("not_exists.txt"));
    }

    @Test
    public void testGetResourceStream() {
        InputStream in = getResourceStream("sources/empty.txt");

        Assertions.assertNotNull(in);
    }
}
