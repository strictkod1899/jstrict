package ru.strict.office.template;

import com.itextpdf.text.Font;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import ru.strict.util.FileUtil;
import ru.strict.office.template.TemplateConfiguration.*;
import ru.strict.validate.Validator;

import java.io.*;
import java.util.*;
import java.util.List;

final class PdfConverter {

    private final OfficeTemplate template;
    private final BaseFont baseFont;

    PdfConverter(OfficeTemplate template) {
        Validator.isNull(template, "template");
        this.template = template;
        try {
            baseFont = BaseFont.createFont(
                    template.getConfiguration().getFontFilePath(),
                    template.getConfiguration().getEncoding(),
                    BaseFont.EMBEDDED
            );
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    void toPdf() {
        try {
            FileUtil.recreateFile(template.outputPdf);

            switch (template.templateExtension) {
                case XLS:
                case XLSX:
                    excelToPdf();
                    break;
                case ODT:
                case DOC:
                case DOCX:
                    docToPdf();
                    break;
                default:
                    throw new UnsupportedOperationException(String.format("Unsupported templateExtension [%s]", template.templateExtension));
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void docToPdf() throws Exception {
        ConverterTypeVia via;
        switch (template.templateExtension) {
            case ODT:
                via = ConverterTypeVia.ODFDOM;
                break;
            case DOC:
            case DOCX:
                via = ConverterTypeVia.XWPF;
                break;
            default:
                throw new UnsupportedOperationException(String.format("Unsupported templateExtension [%s]", template.templateExtension));
        }

        try (InputStream templateInput = new FileInputStream(template.outputTemplate);
             OutputStream output = new FileOutputStream(new File(template.outputPdf))) {

            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(templateInput, TemplateEngineKind.Velocity, false);
            IContext context = report.createContext();
            context.putMap(template.getItems());
            Options options = Options.getTo(ConverterTypeTo.PDF).via(via);

            report.convert(context, options, output);
            output.flush();
        }
    }

    private void excelToPdf() throws Exception {
        Document document = null;
        try (FileInputStream templateInput = new FileInputStream(template.outputTemplate);
             FileOutputStream pdfOutput = new FileOutputStream(new File(template.outputPdf))) {
            document = new Document(PageSize.ARCH_E,
                    template.getConfiguration().getMarginLeft(),
                    template.getConfiguration().getMarginRight(),
                    template.getConfiguration().getMarginTop(),
                    template.getConfiguration().getMarginBottom()
            );
            PdfWriter writer = PdfWriter.getInstance(document, pdfOutput);
            document.open();

            PdfDestination magnify = new PdfDestination(PdfDestination.XYZ, -1f, -1f, 0f);
            int pageNumberToOpenTo = 1;
            PdfAction zoom = PdfAction.gotoLocalPage(pageNumberToOpenTo, magnify, writer);
            writer.setOpenAction(zoom);
            document.addDocListener(writer);

            Workbook excelWorkbook = createWorkbook(templateInput, template.templateExtension);
            FormulaEvaluator formulaEvaluator = createFormulaEvaluator(excelWorkbook, template.templateExtension);
            int usageSheetsCount = getUsageSheetsCount(template.getConfiguration().getGetSheetType(), excelWorkbook);

            for (int sheetNumber = 0; sheetNumber < usageSheetsCount; sheetNumber++) {
                document.add(createPdfTable(excelWorkbook, sheetNumber, formulaEvaluator, template.getConfiguration()));
            }

            document.close();
        }
    }

    private int getUsageSheetsCount(GetSheetType getSheetType, Workbook excelWorkbook) {
        switch (getSheetType) {
            case AUTO:
                return excelWorkbook.getNumberOfSheets();
            case FIX:
                return template.getConfiguration().getUsageSheetsCount();
            default:
                throw new UnsupportedOperationException(String.format("Unknown GetSheetType [%s]", template.getConfiguration().getGetSheetType()));
        }
    }

    private Workbook createWorkbook(FileInputStream templateInput, TemplateFormat templateExtension) throws IOException {
        switch (templateExtension) {
            case XLS:
                return new HSSFWorkbook(templateInput);
            case XLSX:
                return new XSSFWorkbook(templateInput);
            default:
                throw new UnsupportedOperationException(String.format("Unknown excel format [%s]", templateExtension));
        }
    }

    private PdfPTable createPdfTable(Workbook excelWorkbook,
                                     int sheetNumber,
                                     FormulaEvaluator formulaEvaluator,
                                     TemplateConfiguration configuration) throws IOException, BadElementException {
        Sheet sheet = excelWorkbook.getSheetAt(sheetNumber);
        Iterator<Row> rowIterator = sheet.iterator();
        int maxColumn = sheet.getRow(0).getLastCellNum();

        PdfPTable pdfTable = new PdfPTable(maxColumn);
        pdfTable.setHorizontalAlignment(Element.ALIGN_LEFT);
        pdfTable.setWidthPercentage(configuration.getWidthPercentage());
        pdfTable.setSpacingBefore(0f);
        pdfTable.setSpacingAfter(0f);

        Map<CellPosition, PictureData> pictures = getPictures(sheet, template.templateExtension);
        List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();

        while (rowIterator.hasNext()) {
            org.apache.poi.ss.usermodel.Row row = rowIterator.next();
            for (int i = 0; i < maxColumn; i++) {
                org.apache.poi.ss.usermodel.Cell poiCell = row.getCell(i, MissingCellPolicy.CREATE_NULL_AS_BLANK);
                PdfPCell pdfCell = createPdfCell(poiCell, formulaEvaluator, pictures);
                pdfTable.addCell(pdfCell);
            }
        }
        ensureMergeCells(mergedRegions, pdfTable);

        return pdfTable;
    }

    private PdfPCell createPdfCell(org.apache.poi.ss.usermodel.Cell poiCell,
                               FormulaEvaluator formulaEvaluator,
                               Map<CellPosition, PictureData> pictures) throws IOException, BadElementException {
        PictureData picture = pictures.get(new CellPosition(poiCell.getRowIndex(), poiCell.getColumnIndex()));
        PdfPCell pdfCell = null;
        if (picture != null) {
            pdfCell = createPdfImageCell(Image.getInstance(picture.getData()));
        } else {
            switch (poiCell.getCellType()) {
                case BLANK:
                    pdfCell = createPdfTextCell("", poiCell);
                    break;
                case STRING:
                    pdfCell = createPdfTextCell(poiCell.getStringCellValue(), poiCell);
                    break;
                case NUMERIC:
                    pdfCell = createPdfTextCell(String.valueOf(poiCell.getNumericCellValue()), poiCell);
                    break;
                case BOOLEAN:
                    pdfCell = createPdfTextCell(String.valueOf(poiCell.getBooleanCellValue()), poiCell);
                    break;
                case FORMULA:
                    CellValue value = formulaEvaluator.evaluate(poiCell);
                    pdfCell = createPdfCellByValue(value, poiCell);
                    break;
            }
        }

        if (pdfCell != null) {
            if (template.getConfiguration().getBorderConfigType() == BorderConfigType.AUTO) {
                ensureSetBorder(pdfCell, poiCell);
            }

            BaseColor backgroundColor = getPdfColor(poiCell, poiCell.getCellStyle().getFillBackgroundColorColor());
            pdfCell.setBackgroundColor(backgroundColor);
            formatText(pdfCell, poiCell);
            pdfCell.setMinimumHeight(poiCell.getRow().getHeightInPoints());
            pdfCell.setPadding(template.getConfiguration().getMinPadding());
        }

        return pdfCell;
    }

    private PdfPCell createPdfCellByValue(org.apache.poi.ss.usermodel.CellValue cellValue, Cell poiCell) {
        switch (cellValue.getCellType()) {
            case BLANK:
                return createPdfTextCell("", poiCell);
            case STRING:
                return createPdfTextCell(cellValue.getStringValue(), poiCell);
            case NUMERIC:
                return createPdfTextCell(String.valueOf(cellValue.getNumberValue()), poiCell);
            case BOOLEAN:
                return createPdfTextCell(String.valueOf(cellValue.getBooleanValue()), poiCell);
            case FORMULA:
                throw new UnsupportedOperationException(String.format("Illegal excelCellType [%s]", cellValue.getCellType()));
            default:
                return null;
        }
    }

    private PdfPCell createPdfTextCell(String text, Cell poiCell) {
        BaseColor textColor = Optional
                .ofNullable(template.getConfiguration().getFontColor())
                .orElse(getPdfColor(poiCell, getPoiFont(poiCell).getColor()));

        org.apache.poi.ss.usermodel.Font poiFont = getPoiFont(poiCell);
        int fontStyle = template.getConfiguration().getFontStyle() == Font.UNDEFINED ?
                getPdfFontStyle(poiFont) :
                template.getConfiguration().getFontStyle();

        float fontSize = template.getConfiguration().getFontSize() > 0 ?
                template.getConfiguration().getFontSize() :
                getPoiFont(poiCell).getFontHeightInPoints();
        fontSize *= template.getConfiguration().getFontWeight();

        Font font = new Font(baseFont, fontSize, fontStyle, textColor);

        Phrase phrase = new Phrase(text, font);
        PdfPCell pdfCell = new PdfPCell(phrase);
        pdfCell.setBorderWidth(template.getConfiguration().getBorderWidth());

        return pdfCell;
    }

    private void formatText(PdfPCell pdfCell, org.apache.poi.ss.usermodel.Cell cell) {
        CellStyle style = cell.getCellStyle();
        pdfCell.setNoWrap(!style.getWrapText());

        HorizontalAlignment horizontalAlignment = style.getAlignmentEnum();
        switch (horizontalAlignment) {
            case LEFT:
                pdfCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                break;
            case CENTER:
                pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                break;
            case RIGHT:
                pdfCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                break;
        }

        VerticalAlignment verticalAlignment = style.getVerticalAlignment();
        switch (verticalAlignment) {
            case TOP:
                pdfCell.setVerticalAlignment(Element.ALIGN_TOP);
                break;
            case CENTER:
                pdfCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                break;
            case BOTTOM:
                pdfCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                break;
        }
    }

    private PdfPCell createPdfImageCell(Image image) {
        PdfPCell pdfCell = new PdfPCell(image);
        pdfCell.setBorderWidth(0);

        return pdfCell;
    }

    private float getBorderWidth(BorderStyle poiBorderStyle) {
        switch (poiBorderStyle) {
            case THIN:
                return BorderWidth.THIN.getWidth();
            case HAIR:
                return BorderWidth.HAIR.getWidth();
            case THICK:
                return BorderWidth.THICK.getWidth();
            case NONE:
            default:
                return 0;
        }
    }

    private Map<CellPosition, PictureData> getPictures(Sheet sheet, TemplateFormat extension) {
        Map<CellPosition, PictureData> pictures = new HashMap<>();

        switch (extension) {
            case XLS: {
                HSSFPatriarch drawing = (HSSFPatriarch) sheet.createDrawingPatriarch();
                List<HSSFShape> shapes = drawing.getChildren();
                shapes.forEach(shape -> {
                    if (shape instanceof HSSFPicture) {
                        HSSFPicture picture = (HSSFPicture) shape;
                        HSSFClientAnchor clientAnchor = picture.getClientAnchor();
                        CellPosition cellPosition = new CellPosition(clientAnchor.getRow1(), clientAnchor.getCol1());
                        pictures.put(cellPosition, picture.getPictureData());
                    }
                });
                break;
            }
            case XLSX: {
                XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
                List<XSSFShape> shapes = drawing.getShapes();
                shapes.forEach(shape -> {
                    if (shape instanceof XSSFPicture) {
                        XSSFPicture picture = (XSSFPicture) shape;
                        XSSFClientAnchor clientAnchor = picture.getClientAnchor();
                        CellPosition cellPosition = new CellPosition(clientAnchor.getRow1(), clientAnchor.getCol1());
                        pictures.put(cellPosition, picture.getPictureData());
                    }
                });
                break;
            }
            default:
                throw new UnsupportedOperationException(String.format("Unsupported excelType [%s]", extension));
        }

        return pictures;
    }

    private FormulaEvaluator createFormulaEvaluator(Workbook workbook, TemplateFormat templateExtension) {
        switch (templateExtension) {
            case XLS:
                return new HSSFFormulaEvaluator((HSSFWorkbook) workbook);
            case XLSX:
                return new XSSFFormulaEvaluator((XSSFWorkbook) workbook);
            default:
                throw new UnsupportedOperationException(String.format("Unknown excel format [%s]", templateExtension));
        }
    }

    private int getPdfFontStyle(org.apache.poi.ss.usermodel.Font poiFont) {
        if (poiFont.getBold() || poiFont.getItalic() || poiFont.getUnderline() > 0) {
            if (poiFont.getBold() && !poiFont.getItalic() && poiFont.getUnderline() <= 0) {
                return Font.BOLD;
            } else if (!poiFont.getBold() && poiFont.getItalic() && poiFont.getUnderline() <= 0) {
                return Font.ITALIC;
            } else if (!poiFont.getBold() && !poiFont.getItalic() && poiFont.getUnderline() > 0) {
                return Font.UNDERLINE;
            } else if (poiFont.getBold() && poiFont.getItalic() && poiFont.getUnderline() <= 0) {
                return Font.BOLDITALIC;
            } else {
                return Font.UNDEFINED;
            }
        } else {
            return Font.UNDEFINED;
        }
    }

    private org.apache.poi.ss.usermodel.Font getPoiFont(org.apache.poi.ss.usermodel.Cell cell) {
        Validator.isNull(cell, "cell");

        if (cell instanceof HSSFCell) {
            HSSFCell hssfCell = (HSSFCell) cell;
            return hssfCell.getCellStyle().getFont(hssfCell.getRow().getSheet().getWorkbook());
        } if (cell instanceof XSSFCell) {
            XSSFCell xssfCell = (XSSFCell) cell;
            return xssfCell.getCellStyle().getFont();
        } else {
            throw new UnsupportedOperationException(String.format("Unknown cell type [%s]", cell.getClass()));
        }
    }

    private void ensureSetBorder(PdfPCell pdfCell, org.apache.poi.ss.usermodel.Cell cell) {
        CellStyle cellStyle = cell.getCellStyle();

        pdfCell.setBorderColorBottom(getPdfColor(cell, cellStyle.getBottomBorderColor()));
        pdfCell.setBorderWidthBottom(getBorderWidth(cellStyle.getBorderBottom()));
        pdfCell.setBorderColorTop(getPdfColor(cell, cellStyle.getTopBorderColor()));
        pdfCell.setBorderWidthTop(getBorderWidth(cellStyle.getBorderTop()));
        pdfCell.setBorderColorLeft(getPdfColor(cell, cellStyle.getLeftBorderColor()));
        pdfCell.setBorderWidthLeft(getBorderWidth(cellStyle.getBorderLeft()));
        pdfCell.setBorderColorRight(getPdfColor(cell, cellStyle.getRightBorderColor()));
        pdfCell.setBorderWidthRight(getBorderWidth(cellStyle.getBorderRight()));
    }

    private BaseColor getPdfColor(org.apache.poi.ss.usermodel.Cell cell, short poiColorCode) {
        Validator.isNull(cell, "cell");

        if (cell instanceof HSSFCell) {
            HSSFWorkbook workbook = ((HSSFCell) cell).getRow().getSheet().getWorkbook();
            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(poiColorCode);
            return getPdfColor(cell, style.getFillForegroundColorColor());
        }
        if (cell instanceof XSSFCell) {
            XSSFWorkbook workbook = ((XSSFCell) cell).getRow().getSheet().getWorkbook();
            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(poiColorCode);
            return getPdfColor(cell, style.getFillForegroundColorColor());
        } else {
            throw new UnsupportedOperationException(String.format("Unknown cell type [%s]", cell.getClass()));
        }
    }

    private BaseColor getPdfColor(org.apache.poi.ss.usermodel.Cell cell, Color poiColor) {
        Validator.isNull(cell, "cell");
        if (poiColor == null) {
            return BaseColor.BLACK;
        }

        if (poiColor instanceof HSSFColor) {
            HSSFColor color = (HSSFColor) poiColor;
            if (color.getIndex() == 64) {
                return BaseColor.WHITE;
            }

            short[] rgb = color.getTriplet();
            java.awt.Color c = new java.awt.Color(rgb[0], rgb[1], rgb[2]);
            return rgb == null ? BaseColor.BLACK : new BaseColor(c.getRed(), c.getGreen(), c.getBlue());
        } else if (poiColor instanceof XSSFColor) {
            XSSFColor color = (XSSFColor) poiColor;
            if (color.getIndex() == 64) {
                return BaseColor.WHITE;
            }

            byte[] rgb = color.getRGB();
            return rgb == null ? BaseColor.BLACK : new BaseColor(rgb[0], rgb[1], rgb[2]);
        } else {
            throw new UnsupportedOperationException(String.format("Unknown color type [%s]", poiColor.getClass()));
        }
    }

    private void ensureMergeCells(List<CellRangeAddress> mergedRegions, PdfPTable pdfTable) {
        ArrayList<PdfPRow> rows = pdfTable.getRows();
        for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
            PdfPRow row = rows.get(rowIndex);
            PdfPCell[] cells = row.getCells();
            for (int columnIndex = 0; columnIndex < cells.length; columnIndex++) {
                ensureMergeCell(mergedRegions, new CellPosition(rowIndex, columnIndex), cells[columnIndex]);
            }
        }
    }

    private void ensureMergeCell(List<CellRangeAddress> mergedRegions, CellPosition poiCell, PdfPCell pdfCell) {
        for (CellRangeAddress region : mergedRegions) {
            int firstColumn = region.getFirstColumn();
            int lastColumn = region.getLastColumn();
            int firstRow = region.getFirstRow();

            if (poiCell.getColumn() == firstColumn && poiCell.getRow() == firstRow) {
                pdfCell.setColspan(lastColumn - firstColumn+1);
                return;
            }
        }
    }

    private class CellPosition {
        private int row;
        private int column;

        public CellPosition(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }

        @Override
        public String toString() {
            return String.format("Cell [%s - %s]", row, column);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            CellPosition that = (CellPosition) o;
            return row == that.row &&
                    column == that.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }
    }
}
