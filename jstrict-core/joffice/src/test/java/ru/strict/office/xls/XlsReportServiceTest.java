package ru.strict.office.xls;

import lombok.Data;
import org.junit.jupiter.api.Disabled;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

class XlsReportServiceTest {

    private ExtXlsReportService xlsReportService = new XlsReportServiceImpl();

    @Test
    void testCreateReport_xls_success() throws IOException {
        TemplateData journalTemplateData = new TemplateData("xls/templates/journal.xls");

        List<Journal> journal = new ArrayList<>(2);
        journal.add(new Journal("Алексей", "Москва", LocalDate.of(2020, 1, 1), new BigDecimal("312.12")));
        journal.add(new Journal("Иван", "Иваново", LocalDate.of(2019, 1, 1), new BigDecimal("542.12")));

        journalTemplateData.addItem("items", journal);

        byte[] content = xlsReportService.createReport(journalTemplateData, "rus");
        write(content, "journal_rus_output.xls");
    }

    @Test
    void testCreateReport_xlsx_success() throws IOException {
        TemplateData journalTemplateData = new TemplateData("xls/templates/journal.xlsx");

        List<Journal> journal = new ArrayList<>(2);
        journal.add(new Journal("Алексей", "Москва", LocalDate.of(2020, 1, 1), new BigDecimal("312.12")));
        journal.add(new Journal("Иван", "Иваново", LocalDate.of(2019, 1, 1), new BigDecimal("542.12")));

        journalTemplateData.addItem("items", journal);

        byte[] content = xlsReportService.createReport(journalTemplateData, "rus");
        write(content, "journal_rus_output.xlsx");
    }

    @Test
    void testCreateReport_success() throws IOException {
        TemplateData namesTemplateData = new TemplateData("xls/templates/names.xls");

        List<String> names = new ArrayList<>(3);
        names.add("Алексей");
        names.add("Иван");
        names.add("Максим");

        namesTemplateData.addItem("items", names);

        byte[] content = xlsReportService.createReport(namesTemplateData);
        write(content, "names_output.xls");
    }

    @Test
    @Disabled
    void testCreateReport_dynamic_success() throws IOException {
        Map<String, String> headers = new LinkedHashMap<>(4);
        headers.put("Имя клиента", "name");
        headers.put("Город", "city");
        headers.put("Дата", "date");
        headers.put("Сумма", "sum");

        List<Journal> journal = new ArrayList<>(3);
        journal.add(new Journal("Алексей", "Москва", LocalDate.of(2020, 1, 1), new BigDecimal("312.12")));
        journal.add(new Journal("Иван", "Иваново", LocalDate.of(2019, 1, 1), new BigDecimal("542.12")));

        byte[] content = xlsReportService.createReport(headers, journal, "xls/templates/dynamic_template.xls");
        write(content, "dynamic_output.xls");
    }

    @Test
    void testBigReport_unsupportedExtension_fail() {
        assertThrows(XlsReportException.class,
                () -> xlsReportService.createReport(new TemplateData("unsupported.xls")));
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
