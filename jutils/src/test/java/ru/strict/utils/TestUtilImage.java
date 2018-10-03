package ru.strict.utils;

import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;

@RunWith(JUnit4.class)
public class TestUtilImage {

    @Test
    public void testResizeImage(){
        Assert.assertEquals(50,
                UtilImage.resizeImage("src/test/resources/images/test.png", 50, 50).getIconWidth());
    }
}
