package ru.strict.utils;

import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestUtilCollections {

    private String[] arrStr;
    private Collection collStr;
    private String str;
    private String split;

    public TestUtilCollections(String[] arrStr, Collection collStr,
                               String str, String split) {
        this.arrStr = arrStr;
        this.collStr = collStr;
        this.str = str;
        this.split = split;
    }

    @Parameters
    public static Collection setUp(){
        return Arrays.asList(new Object[][]{
                {new String[]{"Hello","World!","Hello","People!"},
                        Arrays.asList(new String[]{"Hello","World!","Hello","People!"}),
                        "Hello World! Hello People!",
                        " "}
        });
    }

    @Test
    public void testStrTrimToArray(){
        Assert.assertArrayEquals(arrStr,
                UtilCollections.strTrimToArray(str, split));
    }

    @Test
    public void strTrimToCollection(){
        Assert.assertEquals(collStr,
                UtilCollections.strTrimToCollection(str, split));
    }

    @Test
    public void testArrayStrToCollection(){
        Assert.assertEquals(collStr,
                UtilCollections.arrayStrToCollection(arrStr));
    }

    @Test
    public void testCollectionStrToArray(){
        Assert.assertArrayEquals(arrStr,
                UtilCollections.collectionStrToArray(collStr));
    }

    @Test
    public void testCollectionStrToObj(){
        Assert.assertEquals(collStr,
                UtilCollections.collectionStrToObj(collStr));
    }

    @Test
    public void testCollectionObjToStr(){
        Assert.assertEquals(collStr, UtilCollections.collectionObjToStr(collStr));
    }

    @Test
    public void testCollectionObjToStrTrim(){
        Assert.assertEquals(collStr, UtilCollections.collectionObjToStrTrim(collStr));
    }

    @Test
    public void testCollectionsStrToObj(){
        Collection<Collection<String>> collStr = new ArrayList<>();
        Collection<Collection<Object>> collObj = new ArrayList<>();
        collStr.add(this.collStr);
        collObj.add(this.collStr);
        Assert.assertEquals(collObj, UtilCollections.collectionsStrToObj(collStr));
    }
}
