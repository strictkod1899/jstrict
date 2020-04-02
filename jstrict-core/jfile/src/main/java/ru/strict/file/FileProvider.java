package ru.strict.file;

import com.itextpdf.text.pdf.PdfReader;
import com.kursx.parser.fb2.FictionBook;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class FileProvider {

    public static PdfReader getPdfFile(String filePath) throws IOException {
        return new PdfReader(filePath);
    }

    public static AudioFile getMp3File(String filePath)
            throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {
        return AudioFileIO.read(new File(filePath));
    }

    public static FictionBook getFb2File(String filePath) throws IOException, ParserConfigurationException, SAXException {
        return new FictionBook(new File(filePath));
    }
}
