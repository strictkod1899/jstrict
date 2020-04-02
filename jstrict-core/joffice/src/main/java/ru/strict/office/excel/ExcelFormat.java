package ru.strict.office.excel;

import ru.strict.office.IOfficeFormat;

import java.util.Arrays;

public enum ExcelFormat implements IOfficeFormat {
    XLS("xls"),
    XLSX("xlsx"),
    ODS("ods");

    private String caption;

    ExcelFormat(String caption) {
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

    public static ExcelFormat getByCaption(String caption){
        if(caption == null){
            return null;
        }

        return Arrays.stream(ExcelFormat.values())
                .filter(object -> object.caption.equals(caption))
                .findFirst()
                .orElse(null);
    }
}
