package ru.strict.file;

import org.junit.jupiter.api.Test;
import ru.strict.file.txt.TxtFile;
import ru.strict.util.ClassUtil;
import ru.strict.util.ResourcesUtil;

import static org.junit.jupiter.api.Assertions.*;

class TxtFileTest {

    @Test
    void testWriteAndRead_success() {
        var fileName = "test1.txt";
        var filePath = ClassUtil.getFilePathByClass(this.getClass(), fileName);
        var expectedReadContent = "mytest1";

        var txtFile = new TxtFile(filePath);
        txtFile.write(expectedReadContent);
        assertEquals(txtFile.read(), expectedReadContent);
    }

    @Test
    void testWriteAndRead_manyProcessors_success() {
        var fileName = "test2.txt";
        var filePath = ClassUtil.getFilePathByClass(this.getClass(), fileName);
        var expectedReadContent = "mytest2";

        var fileForWrite = new TxtFile(filePath);
        var fileForRead = new TxtFile(filePath);
        fileForWrite.write(expectedReadContent);
        assertEquals(fileForRead.read(), expectedReadContent);
    }

    @Test
    void testRead_byInputStream_success() {
        var file = new TxtFile(() -> ResourcesUtil.getResourceStream("test.txt"));
        var actualContent = file.read();

        assertEquals("Hello", actualContent);
    }
}
