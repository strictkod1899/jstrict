package ru.strict.office.excel;

import org.apache.poi.ss.usermodel.CellStyle;

public class WriteCell extends Cell {

    private String value;
    private CellStyle style;

    public WriteCell(String value, int sheetNumber, int row, int column) {
        super(sheetNumber, row, column);
        this.value = value;
    }

    public WriteCell(String value, String sheetCaption, int row, int column) {
        super(sheetCaption, row, column);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setStyle(CellStyle style) {
        this.style = style;
    }

    public CellStyle getStyle() {
        return style;
    }
}
