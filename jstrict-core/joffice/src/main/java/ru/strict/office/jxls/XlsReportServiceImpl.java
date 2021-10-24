package ru.strict.office.jxls;

import org.jxls.common.Context;
import org.jxls.template.SimpleExporter;
import org.jxls.util.JxlsHelper;
import ru.strict.util.ResourcesUtil;
import ru.strict.util.StringUtil;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class XlsReportServiceImpl extends BaseXlsReportService implements ExtXlsReportService {

    @Override
    Context createXlsContext() {
        return new XlsContext();
    }

    @Override
    byte[] outputXlsReport(String filePath, Context context) {
        try (InputStream inputStream = ResourcesUtil.getResourceStream(filePath);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            JxlsHelper.getInstance().processTemplate(inputStream, outputStream, context);

            return outputStream.toByteArray();
        } catch (Exception ex) {
            throw new XlsReportException(filePath, ex);
        }
    }

    @Override
    public byte[] createReport(Map<String, String> headers, List<?> values, String filePath) {
        SimpleExporter exporter = new SimpleExporter();

        try (InputStream inputStream = ResourcesUtil.getResourceStream(filePath);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            exporter.registerGridTemplate(inputStream);

            String fields = StringUtil.join(",", headers.values());
            exporter.gridExport(headers.keySet(), values, fields, outputStream);
            return outputStream.toByteArray();
        } catch (Exception ex) {
            throw new XlsReportException(filePath, ex);
        }
    }
}
