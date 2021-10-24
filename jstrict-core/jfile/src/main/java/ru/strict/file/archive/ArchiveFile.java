package ru.strict.file.archive;

import lombok.RequiredArgsConstructor;
import net.sf.sevenzipjbinding.*;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import net.sf.sevenzipjbinding.impl.RandomAccessFileOutStream;
import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;
import ru.strict.file.FileProcessingException;
import ru.strict.util.FileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class ArchiveFile {
    private final File archiveFile;
    private final ArchiveFormat archiveFormat;

    public ArchiveFile(File archiveFile) {
        this(archiveFile, getArchiveFormat(archiveFile));
    }

    /**
     * Разархивировать архив
     *
     * @param destinationPath путь до каталога, куда необходмо разархивировать
     */
    public void extract(String destinationPath) {
        var archiveItems = read();

        FileUtil.createDirectoryIfNotExists(destinationPath);

        try {
            for (var archiveItem : archiveItems) {
                var fileItem = new File(archiveItem.getPath());
                var filePath = destinationPath + File.separator + fileItem.getName();
                try (var randomAccessFile = new RandomAccessFile(new File(filePath), "rws");
                        var outputFileStream = new OutputFileStream(randomAccessFile)) {
                    var extractionResult = archiveItem.extractSlow(outputFileStream);
                    if (extractionResult != ExtractOperationResult.OK) {
                        throw new ExtractArchiveException(archiveFile.getAbsolutePath(),
                                archiveItem.getPath(),
                                extractionResult.name());
                    }
                }
            }
        } catch (Exception ex) {
            throw new FileProcessingException(archiveFile.getAbsolutePath(), ex);
        }
    }

    public List<ISimpleInArchiveItem> read() {
        try (var randomAccessFile = new RandomAccessFile(archiveFile, "r");
                var inputStream = new RandomAccessFileInStream(randomAccessFile)) {
            SevenZip.initSevenZipFromPlatformJAR();
            var archive = SevenZip.openInArchive(archiveFormat, inputStream).getSimpleInterface();

            var archiveItems = new ArrayList<ISimpleInArchiveItem>(archive.getNumberOfItems());
            Collections.addAll(archiveItems, archive.getArchiveItems());

            return archiveItems;
        } catch (Exception ex) {
            throw new FileProcessingException(archiveFile.getAbsolutePath(), ex);
        }
    }

    /**
     * Определить формат архива по расширению файла
     */
    private static ArchiveFormat getArchiveFormat(File archiveFile) {
        var fileExtension = FileUtil.getFileExtension(archiveFile);

        var availableFormats = ArchiveFormat.values();
        for (var format : availableFormats) {
            if (fileExtension.equalsIgnoreCase(format.getMethodName())) {
                return format;
            }
        }

        return null;
    }

    private static class OutputFileStream extends RandomAccessFileOutStream implements AutoCloseable {

        public OutputFileStream(RandomAccessFile randomAccessFile) {
            super(randomAccessFile);
        }

        @Override
        public void close() throws IOException {
            super.close();
        }
    }
}
