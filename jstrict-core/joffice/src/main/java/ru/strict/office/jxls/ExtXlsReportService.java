package ru.strict.office.jxls;

import java.util.List;
import java.util.Map;

public interface ExtXlsReportService extends XlsReportService {
    /**
     * Сформировать динамический отчет в виде таблицы (без заранее заданной структуры столбцов)
     * @param headers упорядоченный список элементов заголовка и связь их с полем у объектов из {@param values}
     * @param values список dto-моделей, чьи значения будут вставлены
     * @param filePath путь до файла шаблона в ресурсах
     */
    byte[] createReport(Map<String, String> headers, List<?> values, String filePath);
}
