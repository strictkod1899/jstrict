package ru.strict.db.requests;

/**
 * Базовое определние условия sql-запроса
 */
public abstract class StrictDbRequestBase implements StrictDbRequestAny{

    /**
     * Наименование таблицы
     */
    private String tableName;

    public StrictDbRequestBase(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
}
