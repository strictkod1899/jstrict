package ru.strict.office.excel;

public class Cell {
    private Row row;
    private int column;

    public Cell(int sheetNumber, int row, int column) {
        this.row = new Row(sheetNumber, row);
        this.column = column;
    }

    public Cell(String sheetCaption, int row, int column) {
        this.row = new Row(sheetCaption, row);
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    public String getSheetCaption() {
        return row.getSheetCaption();
    }

    public Integer getSheetNumber() {
        return row.getSheetNumber();
    }

    public int getRow() {
        return row.getRowNumber();
    }
}
