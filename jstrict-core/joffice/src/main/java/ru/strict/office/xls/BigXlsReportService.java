package ru.strict.office.xls;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFFormulaEvaluator;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.transform.poi.PoiTransformer;
import ru.strict.util.ResourcesUtil;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Формирование большого excel-документа по шаблону jxls.
 *
 * Описание проблемы:
 * Если формировать большой документ стандартными средствами jxls, то может закончится оперативная память.
 * Для избежания этого, требуется использовать SXSSF-подход - это подход для обработки файла небольшими частями.
 * Работает только с .xlsx файлами.
 *
 * Тонкости использования:
 * 1. Обрабатывается только первая страница;
 * 2. В результате обработки формируется новый лист 'Result', на котором сохраняется результат обработки;
 * 3. Первый лист с шаблоном удаляется из конечного файла;
 * 4. Обрабатывает порциями по 1000 строк. Это значение вшито, чтобы не усложнять методы лишними параметрами.
 *    Если очень потребуется изменить это значение, то делаем доработку;
 * 5. Некоторые формулы могут не работать, т.к. на момент реализации выполнялась только поддержка формулы SUM.
 */
public class BigXlsReportService extends BaseXlsReportService {

    private static final int ROW_ACCESS_WINDOW_SIZE = 1000;

    @Override
    Context createXlsContext() {
        return new BigXlsContext();
    }

    @Override
    byte[] outputXlsReport(String filePath, Context context) {
        if (!filePath.endsWith(".xlsx")) {
            throw new XlsReportException(filePath, "Unsupported file extension. Expected .xlsx");
        }

        try (InputStream inputStream = ResourcesUtil.getResourceStream(filePath);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            workbook.setForceFormulaRecalculation(true);
            PoiTransformer transformer = PoiTransformer.createSxssfTransformer(workbook, ROW_ACCESS_WINDOW_SIZE, false);

            AreaBuilder areaBuilder = new XlsCommentAreaBuilder(transformer);
            List<Area> xlsAreaList = areaBuilder.build();
            Area xlsArea = xlsAreaList.get(0);
            xlsArea.applyAt(new CellRef("Result!A1"), context);

            transformer.getWorkbook().removeSheetAt(0);
            evaluateFormulas((SXSSFWorkbook)transformer.getWorkbook());
            transformer.getWorkbook().write(outputStream);

            return outputStream.toByteArray();
        } catch (Exception ex) {
            throw new XlsReportException(filePath, ex);
        }
    }

    public void evaluateFormulas(SXSSFWorkbook workbook) {
        Sheet mainSheet = workbook.getSheet("Result");

        /*
            Если кол-во строк в конечном файле меньше чем окно обработки, тогда можно расчитать формулы,
            иначе будет ошибка, т.к. эти данные не загружены в оперативную память (обрабатывается другая часть данных)
         */
        if (mainSheet.getLastRowNum() <= ROW_ACCESS_WINDOW_SIZE) {
            SXSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);
        }
    }
}
