package ru.strict.office.xls;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BigXlsReportServiceTest {

    private XlsReportService bigXlsReportService = new BigXlsReportService();

    @Test
    void testBigReport_oneWindow_success() throws IOException {
        byte[] content = generateDataAndCreateXlsx(499);
        write(content, "big_journal_rus_output_1.xlsx");
    }

    @Test
    void testBigReport_severalWindows_success() throws IOException {
        byte[] content = generateDataAndCreateXlsx(1999);
        write(content, "big_journal_rus_output_2.xlsx");
    }

    @Test
    void testBigReport_unsupportedExtension_fail() {
        assertThrows(XlsReportException.class,
                () -> bigXlsReportService.createReport(new TemplateData("unsupported.xls")));
    }

    @Test
    void testBigReport_unsupportedExtensionWithLang_fail() {
        assertThrows(XlsReportException.class,
                () -> bigXlsReportService.createReport(new TemplateData("unsupported.xls"), "rus"));
    }

    private byte[] generateDataAndCreateXlsx(int countIterations) {
        TemplateData journalTemplateData = new TemplateData("xls/templates/big_journal.xlsx");

        List<Journal> journal = new ArrayList<>(countIterations * 2);
        for (int i = 0; i < countIterations; ++i) {
            journal.add(new Journal("Алексей", "Москва", LocalDate.of(2020, 1, 1), new BigDecimal("312.12")));
            journal.add(new Journal("Иван", "Иваново", LocalDate.of(2019, 1, 1), new BigDecimal("542.12")));
        }
        journalTemplateData.addItem("items", journal);

        return bigXlsReportService.createReport(journalTemplateData, "rus");
    }

    /**
     * Записать байты в файл в /target/classes директории, чтобы можно было открыть и посмотртеь результат работы
     */
    private void write(byte[] content, String expectedFileName) throws IOException {
        String outputFilePath = getPathByClass() + "/" + expectedFileName;
        Files.deleteIfExists(Paths.get(outputFilePath));

        FileOutputStream fos = new FileOutputStream(outputFilePath);
        fos.write(content);
        fos.close();
    }

    /**
     * Получить путь до директории класса
     */
    private static String getPathByClass() {
        try {
            File file = new File(XlsReportService.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            if (file.isDirectory()) {
                return file.getPath();
            } else {
                return file.getParentFile().getPath();
            }
        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Data
    public static class Journal {
        private final String name;
        private final String city;
        private final LocalDate date;
        private final BigDecimal sum;
    }
}
