package ru.strict.office.excel;

public class Row {
    private String sheetCaption;
    private Integer sheetNumber;
    private int rowNumber;

    public Row(int sheetNumber, int rowNumber) {
        this.sheetNumber = sheetNumber;
        this.rowNumber = rowNumber;
    }

    public Row(String sheetCaption, int rowNumber) {
        this.sheetCaption = sheetCaption;
        this.rowNumber = rowNumber;
    }

    public String getSheetCaption() {
        return sheetCaption;
    }

    public Integer getSheetNumber() {
        return sheetNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }
}
