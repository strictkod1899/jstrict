package ru.strict.office.template;

import ru.strict.office.IOfficeFormat;

import java.util.Arrays;

public enum TemplateFormat implements IOfficeFormat {
    XLS("xls"),
    XLSX("xlsx"),
    ODS("ods"),
    ODT("odt"),
    DOC("doc"),
    DOCX("docx");

    private String caption;

    TemplateFormat(String caption) {
        this.caption = caption;
    }

    @Override
    public String getCaption() {
        return caption;
    }

    @Override
    public String toString() {
        return caption;
    }

    public static TemplateFormat getByCaption(String caption){
        if(caption == null){
            return null;
        }

        return Arrays.stream(TemplateFormat.values())
                .filter(object -> object.caption.equals(caption))
                .findFirst()
                .orElse(null);
    }
}
