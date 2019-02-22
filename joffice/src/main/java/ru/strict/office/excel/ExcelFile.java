package ru.strict.office.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.strict.office.OfficeFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExcelFile extends OfficeFile<Workbook, ExcelFormat> implements IExcelFile {

    private CellStyleFactory styleFactory;

    public ExcelFile(String filePath) {
        super(filePath);
        styleFactory = new CellStyleFactory(source);
    }

    public ExcelFile(String filePath, ExcelFormat format) {
        super(filePath, format);
        styleFactory = new CellStyleFactory(source);
    }

    @Override
    protected Workbook initializeSource(){
        try {
            Workbook source = null;
            if(!Files.exists(Paths.get(getFilePath()))){
                source = createWorkbook(null);
                source.createSheet();
                source.write(new FileOutputStream(getFilePath()));
            }else{
                source = createWorkbook(getFilePath());
            }

            return source;
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    private Workbook createWorkbook(String filePath){
        Workbook source = null;
        try {
            switch (getFormat()) {
                case XLSX:
                    if (filePath == null) {
                        source = new XSSFWorkbook();
                    } else {
                        source = new XSSFWorkbook(new FileInputStream(filePath));
                    }
                    break;
                case XLS:
                default:
                    if (filePath == null) {
                        source = new HSSFWorkbook();
                    } else {
                        source = new HSSFWorkbook(new FileInputStream(filePath));
                    }
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }

        return source;
    }

    @Override
    protected ExcelFormat getFormatByCaption(String format) {
        ExcelFormat resultFormat = ExcelFormat.getByCaption(format);
        if(resultFormat == null){
            resultFormat = ExcelFormat.XLS;
        }
        return resultFormat;
    }

    @Override
    public void recreateFile(){
        try {
            Files.deleteIfExists(Paths.get(getFilePath()));
            initializeSource().close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void write(){
        try {
            source.write(new FileOutputStream(getFilePath()));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Sheet getSheet(int sheetNumber) {
        Sheet sheet = source.getSheetAt(sheetNumber);
        if(sheet == null){
            int sheetsCount = source.getNumberOfSheets();
            if(sheetsCount < sheetNumber) {
                for (int i = sheetsCount; i <= sheetNumber; i++) {
                    source.createSheet();
                }
                sheet = source.getSheetAt(sheetNumber);
            }
        }
        return sheet;
    }

    @Override
    public Sheet getSheet(String sheetCaption) {
        if(sheetCaption == null){
            throw new NullPointerException("sheetCaption is NULL");
        }

        Sheet sheet = source.getSheet(sheetCaption);
        if(sheet == null){
            sheet = source.createSheet(sheetCaption);
        }

        return sheet;
    }

    @Override
    public org.apache.poi.ss.usermodel.Row getRow(Row row) {
        if(row == null){
            throw new NullPointerException("row is NULL");
        }

        Sheet sheet = null;
        if(row.getSheetNumber() != null) {
            sheet = getSheet(row.getSheetNumber());
        } else {
            sheet = getSheet(row.getSheetCaption());
        }

        org.apache.poi.ss.usermodel.Row createdRow = sheet.getRow(row.getRowNumber());
        if(createdRow == null){
            createdRow = sheet.createRow(row.getRowNumber());
        }

        return createdRow;
    }

    @Override
    public org.apache.poi.ss.usermodel.Row getRow(String sheetCaption, int rowNumber) {
        return getRow(new Row(sheetCaption, rowNumber));
    }

    @Override
    public org.apache.poi.ss.usermodel.Row getRow(int sheetNumber, int rowNumber) {
        return getRow(new Row(sheetNumber, rowNumber));
    }

    @Override
    public org.apache.poi.ss.usermodel.Cell getCell(Cell cell){
        if(cell == null){
            throw new NullPointerException("cell is NULL");
        }

        org.apache.poi.ss.usermodel.Row row = null;
        if(cell.getSheetNumber() == null) {
            row = getRow(new Row(cell.getSheetCaption(), cell.getRow()));
        }else{
            row = getRow(new Row(cell.getSheetNumber(), cell.getRow()));
        }

        org.apache.poi.ss.usermodel.Cell createCell = row.getCell(cell.getColumn());
        if(createCell == null){
            createCell = row.createCell(cell.getColumn());
        }

        return createCell;
    }

    @Override
    public org.apache.poi.ss.usermodel.Cell getCell(String sheetCaption, int rowNumber, int column) {
        return getCell(new Cell(sheetCaption, rowNumber, column));
    }

    @Override
    public org.apache.poi.ss.usermodel.Cell getCell(int sheetNumber, int rowNumber, int column) {
        return getCell(new Cell(sheetNumber, rowNumber, column));
    }

    @Override
    public boolean isSheetExists(int sheetNumber) {
        boolean result = source.getSheetAt(sheetNumber) != null;
        return result;
    }

    @Override
    public boolean isSheetExists(String sheetCaption) {
        if(sheetCaption == null){
            throw new NullPointerException("sheetCaption is NULL");
        }

        boolean result = source.getSheet(sheetCaption) != null;
        return result;
    }

    @Override
    public boolean isRowExists(int sheetNumber, int rowNumber) {
        return isRowExists(new Row(sheetNumber, rowNumber));
    }

    @Override
    public boolean isRowExists(String sheetCaption, int rowNumber) {
        return isRowExists(new Row(sheetCaption, rowNumber));
    }

    @Override
    public boolean isRowExists(Row row) {
        if(row == null){
            throw new NullPointerException("row is NULL");
        }

        boolean result = false;

        Sheet sheet = null;
        if(row.getSheetNumber() != null) {
            sheet = source.getSheetAt(row.getSheetNumber());
        } else {
            sheet = source.getSheet(row.getSheetCaption());
        }

        if(sheet == null){
            result = false;
        } else {
            result = sheet.getRow(row.getRowNumber()) != null;
        }

        return result;
    }

    @Override
    public boolean isCellExists(String sheetCaption, int rowNumber, int column) {
        return isCellExists(new Cell(sheetCaption, rowNumber, column));
    }

    @Override
    public boolean isCellExists(int sheetNumber, int rowNumber, int column) {
        return isCellExists(new Cell(sheetNumber, rowNumber, column));
    }

    @Override
    public boolean isCellExists(Cell cell) {
        if(cell == null){
            throw new NullPointerException("cell is NULL");
        }

        boolean result = false;

        Sheet sheet = null;
        if(cell.getSheetNumber() != null) {
            sheet = source.getSheetAt(cell.getSheetNumber());
        } else {
            sheet = source.getSheet(cell.getSheetCaption());
        }

        if(sheet == null){
            result = false;
        } else {
            org.apache.poi.ss.usermodel.Row row = sheet.getRow(cell.getRow());
            if(row == null){
                result = false;
            }else{
                result = row.getCell(cell.getColumn()) != null;
            }
        }

        return result;
    }

    @Override
    public String getValue(String sheetCaption, int rowNumber, int column) {
        return getValue(new Cell(sheetCaption, rowNumber, column));
    }

    @Override
    public String getValue(int sheetNumber, int rowNumber, int column) {
        return getValue(new Cell(sheetNumber, rowNumber, column));
    }

    @Override
    public String getValue(Cell cell) {
        if(cell == null){
            throw new NullPointerException("cell is NULL");
        }
        String result = null;
        org.apache.poi.ss.usermodel.Cell foundedCell = getCell(cell);
        if(foundedCell != null){
            result = foundedCell.getStringCellValue();
        }
        return result;
    }

    @Override
    public void write(WriteCell cell){
        if(cell == null){
            throw new NullPointerException("cell is NULL");
        }
        org.apache.poi.ss.usermodel.Cell createCell = getCell(cell);
        createCell.setCellValue(cell.getValue());
        if(cell.getStyle() != null){
            createCell.setCellStyle(cell.getStyle());
        }
        write();
    }

    @Override
    public CellStyle createStyle(CellStyleType styleType) {
        if(styleType == null){
            throw new NullPointerException("styleType is NULL");
        }
        CellStyle result = null;
        switch(styleType){
            case DEFAULT:
                result = styleFactory.createDefaultStyle();
                break;
            case GOOD:
                result = styleFactory.createGoodStyle();
                break;
            case NEUTRAL:
                result = styleFactory.createNeutralStyle();
                break;
            case BAD:
                result = styleFactory.createBadStyle();
                break;
        }
        return result;
    }

    @Override
    public void close() throws IOException {
        source.close();
        source = null;
        styleFactory = null;
    }
}
