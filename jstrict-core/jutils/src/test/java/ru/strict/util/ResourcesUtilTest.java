package ru.strict.util;

import org.junit.jupiter.api.Test;
import ru.strict.exception.ResourceNotFoundException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.strict.util.ResourcesUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class ResourcesUtilTest {

    @Test
    void testGetResourceAsFile() {
        var appPath = ClassUtil.getPathByClass(ResourcesUtil.class);
        var targetFilePath = appPath + File.separator + "empty.txt";
        getResourceAsFile("sources/empty.txt", targetFilePath);

        assertTrue(Files.exists(Paths.get(targetFilePath)));
    }

    @Test
    void testGetResourceAsTempFile() {
        var file = getResourceAsTempFile("sources/empty.txt");

        assertTrue(file.exists());
    }

    @Test
    void testGetResourceOrThrow() {
        var file = getResourceOrThrow("sources/empty.txt");

        assertNotNull(file);
    }

    @Test
    void testGetResourceOrThrow_fail() {
        assertThrows(ResourceNotFoundException.class, () -> getResourceOrThrow("not_exists.txt"));
    }

    @Test
    void testGetResourceStream() {
        var inputStream = getResourceStream("sources/empty.txt");

        assertNotNull(inputStream);
    }
}
