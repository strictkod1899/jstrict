package ru.strict.office.xls;

import lombok.RequiredArgsConstructor;
import org.jxls.command.CellDataUpdater;
import org.jxls.common.CellData;
import org.jxls.common.CellRef;
import org.jxls.common.Context;

/**
 * Если в ячейке записана формула, то в файле-шаблоне она имеет вид SUM(D3).
 * После обработки шаблона, необходимо формулу расширить на диапазон, полученной таблицы.
 * Итого получаем формулу вида SUM(D3:D15)
 * Данный класс обрабатывает только формулы по вертикали
 */
@RequiredArgsConstructor
class ColumnFormulaUpdater implements CellDataUpdater {

    @Override
    public void updateCellData(CellData cellData, CellRef targetCell, Context context) {
        if (cellData.isFormulaCell()) {
            String formula = createRangeFormula(cellData.getFormula(), targetCell.getRow());
            cellData.setEvaluationResult(formula);
        }
    }

    /**
     * Должна получиться строка подобного вида SUM(D3:D15)
     */
    private String createRangeFormula(String sourceFormula, int lastRowNumber) {
        String startFormula = sourceFormula.substring(0, sourceFormula.indexOf("("));
        String startCell = sourceFormula.substring(sourceFormula.indexOf("(") + 1, sourceFormula.indexOf(")"));
        String firstCellSymbol = startCell.substring(0, 1);
        return String.format("%s(%s:%s%d)", startFormula, startCell, firstCellSymbol, lastRowNumber);
    }
}
