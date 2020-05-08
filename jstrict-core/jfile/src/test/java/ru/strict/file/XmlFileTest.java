package ru.strict.file;

import org.jdom2.Element;
import org.junit.After;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.file.txt.TxtFile;
import ru.strict.file.xml.XmlFile;
import ru.strict.file.xml.XmlNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class XmlFileTest {

    private static final String TEST_FILE_NAME = "test.xml";

    @After
    public void post() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_FILE_NAME));
    }

    public void testWriteBySource() throws IOException {
        Element root = new Element("root");
        root.addContent("\n ");
        Element element1 = new Element("var1");
        Element element1_1 = new Element("var1_1");
        element1.getChildren().add(element1_1);
        Element element2 = new Element("var2");
        root.getChildren().add(element1);
        root.addContent("\n ");
        root.getChildren().add(element2);
        root.addContent("\n" );

        Element element10 = new Element("var1");
        Element element10_1 = new Element("var1_1");
        element1.getChildren().add(element10_1);
        Element element20 = new Element("var2");

        XmlFile fileForWrite = new XmlFile(TEST_FILE_NAME, "root");
        fileForWrite.addElements(element10, element20);
        fileForWrite.write();
        XmlFile fileForRead = new XmlFile(TEST_FILE_NAME);
        Assert.assertEquals(fileForRead.read(new XmlNode(new Element("root"))), root);
    }
}
