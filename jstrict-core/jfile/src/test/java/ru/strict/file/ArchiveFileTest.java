package ru.strict.file;

import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.strict.file.archive.ArchiveFile;
import ru.strict.util.ClassUtil;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArchiveFileTest {

    @Test
    void testRead_success() {
        var archiveFilePath = ClassUtil.getFilePathByClass(this.getClass(), "test.zip");

        var archiveFile = new ArchiveFile(new File(archiveFilePath));
        var archiveItems = archiveFile.read();

        assertTrue(containsFile(archiveItems, "file1.txt"));
        assertTrue(containsFile(archiveItems, "file2.txt"));
    }

    @Test
    @Disabled
    void testExtract_success() {
        var extractDirectoryPath = ClassUtil.getPathByClass(this.getClass()) + File.separator + "archive_extract";
        var archiveFilePath = ClassUtil.getFilePathByClass(this.getClass(), "test.zip");
        var archiveFile = new ArchiveFile(new File(archiveFilePath));

        archiveFile.extract(extractDirectoryPath);

        var file1Path = Paths.get(extractDirectoryPath + "file1.txt");
        var file2Path = Paths.get(extractDirectoryPath + "file1.txt");

        assertTrue(Files.exists(file1Path));
        assertTrue(Files.exists(file2Path));
    }

    private boolean containsFile(List<ISimpleInArchiveItem> archiveItems, String expectedFileName) {
        try {
            for (var archiveItem : archiveItems) {
                if (archiveItem.getPath().contains(expectedFileName)) {
                    return true;
                }
            }
            return false;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
