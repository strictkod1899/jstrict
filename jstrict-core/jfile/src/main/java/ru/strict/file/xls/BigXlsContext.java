package ru.strict.file.xls;

import org.jxls.command.CellDataUpdater;

class BigXlsContext extends XlsContext {

    private static final CellDataUpdater COLUMN_FORMULA_UPDATER = new ColumnFormulaUpdater();

    public BigXlsContext() {
        super();

        putVar("columnFormulaUpdater", COLUMN_FORMULA_UPDATER);

        getConfig().setIsFormulaProcessingRequired(false);
    }
}
