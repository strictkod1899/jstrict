package ru.strict.utils;

import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import ru.strict.utils.StrictUtilCollections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestStrictUtilCollections {

    private String[] arrStr;
    private Collection collStr;
    private String str;
    private String split;

    public TestStrictUtilCollections(String[] arrStr, Collection collStr,
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
                StrictUtilCollections.strTrimToArray(str, split));
    }

    @Test
    public void strTrimToCollection(){
        Assert.assertEquals(collStr,
                StrictUtilCollections.strTrimToCollection(str, split));
    }

    @Test
    public void testArrayStrToCollection(){
        Assert.assertEquals(collStr,
                StrictUtilCollections.arrayStrToCollection(arrStr));
    }

    @Test
    public void testCollectionStrToArray(){
        Assert.assertArrayEquals(arrStr,
                StrictUtilCollections.collectionStrToArray(collStr));
    }

    @Test
    public void testCollectionStrToObj(){
        Assert.assertEquals(collStr,
                StrictUtilCollections.collectionStrToObj(collStr));
    }

    @Test
    public void testCollectionObjToStr(){
        Assert.assertEquals(collStr, StrictUtilCollections.collectionObjToStr(collStr));
    }

    @Test
    public void testCollectionObjToStrTrim(){
        Assert.assertEquals(collStr, StrictUtilCollections.collectionObjToStrTrim(collStr));
    }

    @Test
    public void testCollectionsStrToObj(){
        Collection<Collection<String>> collStr = new ArrayList<>();
        Collection<Collection<Object>> collObj = new ArrayList<>();
        collStr.add(this.collStr);
        collObj.add(this.collStr);
        Assert.assertEquals(collObj, StrictUtilCollections.collectionsStrToObj(collStr));
    }
}
