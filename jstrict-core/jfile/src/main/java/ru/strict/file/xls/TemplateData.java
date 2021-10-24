package ru.strict.file.xls;

import lombok.Getter;
import lombok.NonNull;

import java.util.Map;

@Getter
public class TemplateData extends ReportData {
    @NonNull
    private final String templateName;

    public TemplateData(@NonNull String templateName) {
        this.templateName = templateName;
    }

    public TemplateData(@NonNull String templateName, @NonNull Map<String, Object> data) {
        super(data);
        this.templateName = templateName;
    }
}
