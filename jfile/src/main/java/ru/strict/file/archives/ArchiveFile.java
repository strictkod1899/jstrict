package ru.strict.file.archives;

import net.sf.sevenzipjbinding.*;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import net.sf.sevenzipjbinding.simple.ISimpleInArchive;
import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;
import ru.strict.file.IFileReader;
import ru.strict.utils.UtilFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchiveFile implements AutoCloseable, IFileReader<List<ISimpleInArchiveItem>> {

    private ISimpleInArchive archive;
    private RandomAccessFileInStream inStream;
    private List<ISimpleInArchiveItem> archiveItems;

    public ArchiveFile(File archiveFile) throws FileNotFoundException, SevenZipException, SevenZipNativeInitializationException {
        this(archiveFile, determineArchiveFormat(archiveFile));
    }

    public ArchiveFile(File archiveFile, ArchiveFormat archiveFormat) throws FileNotFoundException, SevenZipException, SevenZipNativeInitializationException{
        if(archiveFormat == null){
            throw new IllegalArgumentException("ArchiveFormat is NULL");
        }

        SevenZip.initSevenZipFromPlatformJAR();
        final RandomAccessFile randomAccessFile = new RandomAccessFile(archiveFile, "r");
        inStream = new RandomAccessFileInStream(randomAccessFile);
        archive = SevenZip.openInArchive(archiveFormat, inStream).getSimpleInterface();

        archiveItems = new ArrayList<>(archive.getNumberOfItems());

        for (ISimpleInArchiveItem item : archive.getArchiveItems()) {
            archiveItems.add(item);
        }
    }

    /**
     * Разархивировать архив
     * @param destinationPath путь до каталога, куда необходмо разархивировать
     */
    public boolean extract(String destinationPath) throws FileNotFoundException, SevenZipException {
        boolean result = true;
        for(ISimpleInArchiveItem item : archiveItems){
            File fileItem = new File(item.getPath());
            final OutputFileStream fileStream = new OutputFileStream(
                    new File(
                                String.format("%s%s%s", destinationPath + File.separator + fileItem.getName()
                            )
                    )
            );
            ExtractOperationResult extractionResult = item.extractSlow(fileStream);
            if (extractionResult != ExtractOperationResult.OK) {
                result = false;
            }
        }

        return result;
    }

    @Override
    public void close() throws IOException {
        if(inStream != null) {
            inStream.close();
        }
        if(archive != null) {
            archive.close();
        }
    }

    @Override
    public List<ISimpleInArchiveItem> read() {
        return archiveItems;
    }

    /**
     * Определить формат архива по расширению файла
     */
    private static ArchiveFormat determineArchiveFormat(File archiveFile){
        ArchiveFormat archiveFormat = null;
        String fileExtension = UtilFile.getFileExtension(archiveFile);

        ArchiveFormat[] availableFormats = ArchiveFormat.values();
        for(ArchiveFormat format : availableFormats){
            if(fileExtension.equalsIgnoreCase(format.getMethodName())){
                archiveFormat = format;
                break;
            }
        }

        return archiveFormat;
    }

    private class OutputFileStream implements ISequentialOutStream {
        private FileOutputStream fos;

        public OutputFileStream(File file) throws FileNotFoundException {
            fos = new FileOutputStream(file);
        }

        @Override
        public int write(byte[] data) throws SevenZipException {
            try {
                fos.write(data);
            } catch (IOException ex) {
                throw new SevenZipException(ex);
            } finally {
                if(fos != null){
                    try {
                        fos.flush();
                        fos.close();
                    }catch (IOException ex){
                        throw new SevenZipException(ex);
                    }
                }
            }
            return data.length;
        }
    }
}
