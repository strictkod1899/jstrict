package ru.strict.office.jxls;

public interface XlsReportService {
    byte[] createReport(TemplateData templateData);
    byte[] createReport(TemplateData templateData, String lang);
}
