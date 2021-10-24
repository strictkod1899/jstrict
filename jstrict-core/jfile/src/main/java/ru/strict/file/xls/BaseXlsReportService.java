package ru.strict.file.xls;

import org.jxls.common.Context;

public abstract class BaseXlsReportService implements XlsReportService {

    abstract byte[] outputXlsReport(String filePath, Context context);
    abstract Context createXlsContext();

    @Override
    public byte[] createReport(TemplateData templateData) {
        Context xlsContext = createXlsContext(templateData);
        return outputXlsReport(templateData.getTemplateName(), xlsContext);
    }

    @Override
    public byte[] createReport(TemplateData templateData, String lang) {
        Context xlsContext = createXlsContext(templateData);
        return outputXlsReport(templateData.getTemplateName(), lang, xlsContext);
    }

    private byte[] outputXlsReport(String filePath, String lang, Context context) {
        String fullFilePath = formatFilePath(filePath, lang);
        return outputXlsReport(fullFilePath, context);
    }

    private String formatFilePath(String filePath, String lang) {
        String filePathWithoutExtension = filePath.substring(0, filePath.lastIndexOf("."));
        String fileExtension = filePath.substring(filePath.lastIndexOf(".") + 1);

        return String.format("%s_%s.%s", filePathWithoutExtension, lang, fileExtension);
    }

    private Context createXlsContext(TemplateData templateData) {
        Context context = createXlsContext();

        templateData.getData().keySet()
                .forEach(key -> context.putVar(key, templateData.getItem(key)));

        return context;
    }
}
