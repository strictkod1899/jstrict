package ru.strict.db.core.query.components;

import ru.strict.validate.Validator;

import java.util.Objects;

/**
 * Базовое определение условия sql-запроса
 */
public abstract class TableRequest {
    /**
     * Наименование таблицы
     */
    private Table table;

    public TableRequest(Table table) {
        Validator.isNull(table, "table");

        this.table = table;
    }

    public Table getTable() {
        return table;
    }

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TableRequest that = (TableRequest) o;
        return Objects.equals(table, that.table);
    }

    @Override
    public int hashCode() {
        return Objects.hash(table);
    }
    //</editor-fold>
}
