package ru.strict.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.exceptions.ResourceNotFoundException;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.strict.utils.ResourcesUtil.*;

@RunWith(JUnit4.class)
public class ResourcesUtilTest {

    @Test
    public void testGetResourceAsFile() {
        String appPath = ClassUtil.getPathByClass(ResourcesUtil.class);
        String targetFilePath = appPath + File.separator + "empty.txt";
        getResourceAsFile("sources/empty.txt", targetFilePath);

        Assert.assertTrue(Files.exists(Paths.get(targetFilePath)));
    }

    @Test
    public void testGetResourceAsTempFile() {
        File file = getResourceAsTempFile("sources/empty.txt");

        Assert.assertTrue(file.exists());
    }

    @Test
    public void testGetResourceOrThrow() {
        File file = getResourceOrThrow("sources/empty.txt");

        Assert.assertNotNull(file);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetResourceOrThrow_fail() {
        getResourceOrThrow("not_exists.txt");
    }

    @Test
    public void testGetResourceStream() {
        InputStream in = getResourceStream("sources/empty.txt");

        Assert.assertNotNull(in);
    }
}
