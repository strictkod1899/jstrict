package ru.strict.office.excel;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import ru.strict.office.IOfficeFile;

public interface IExcelFile extends IOfficeFile<Workbook> {

    /**
     * Получить лист excel-документа. Если он не создан, тогда он будет создан
     * @return
     */
    Sheet getSheet(int sheetNumber);

    /**
     * Получить лист excel-документа. Если он не создан, тогда он будет создан
     * @return
     */
    Sheet getSheet(String sheetCaption);

    /**
     * Получить строку excel-документа. Если она не создана, тогда она будет создана
     * @param row
     * @return
     */
    org.apache.poi.ss.usermodel.Row getRow(Row row);

    /**
     * Получить строку excel-документа. Если она не создана, тогда она будет создана
     * @return
     */
    org.apache.poi.ss.usermodel.Row getRow(String sheetCaption, int rowNumber);

    /**
     * Получить строку excel-документа. Если она не создана, тогда она будет создана
     * @return
     */
    org.apache.poi.ss.usermodel.Row getRow(int sheetNumber, int rowNumber);

    /**
     * Получить ячейку excel-документа. Если она не создана, тогда она будет создана
     * @param cell
     * @return
     */
    org.apache.poi.ss.usermodel.Cell getCell(Cell cell);

    /**
     * Получить ячейку excel-документа. Если она не создана, тогда она будет создана
     * @return
     */
    org.apache.poi.ss.usermodel.Cell getCell(String sheetCaption, int rowNumber, int column);

    /**
     * Получить ячейку excel-документа. Если она не создана, тогда она будет создана
     * @return
     */
    org.apache.poi.ss.usermodel.Cell getCell(int sheetNumber, int rowNumber, int column);

    boolean isSheetExists(int sheetNumber);
    boolean isSheetExists(String sheetCaption);
    boolean isRowExists(Row row);
    boolean isRowExists(int sheetNumber, int rowNumber);
    boolean isRowExists(String sheetCaption, int rowNumber);
    boolean isCellExists(Cell cell);
    boolean isCellExists(String sheetCaption, int rowNumber, int column);
    boolean isCellExists(int sheetNumber, int rowNumber, int column);

    /**
     * Получить значение ячейки
     * @param cell
     * @return
     */
    String getValue(Cell cell);
    String getValue(String sheetCaption, int rowNumber, int column);
    String getValue(int sheetNumber, int rowNumber, int column);
    void write(WriteCell cell);

    CellStyle createStyle(CellStyleType styleType);
}
