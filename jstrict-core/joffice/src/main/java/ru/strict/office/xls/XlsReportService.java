package ru.strict.office.xls;

public interface XlsReportService {
    byte[] createReport(TemplateData templateData);
    byte[] createReport(TemplateData templateData, String lang);
}
