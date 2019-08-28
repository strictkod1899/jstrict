package ru.strict.db.core.requests;

import ru.strict.db.core.common.SqlParameters;

import java.util.Objects;

/**
 * Условие Where sql-запроса в виде подзапроса
 */
public class DbWhereInnerSql extends DbWhereBase {
    /**
     * Левая часть условия
     */
    private IDbRequest leftWhereItem;
    /**
     * Оператор
     */
    private String operator;

    private DbSelect innerSelect;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public DbWhereInnerSql(IDbRequest leftWhereItem, String operator, DbSelect innerSelect) {
        this.leftWhereItem = leftWhereItem;
        this.operator = operator;
        this.innerSelect = innerSelect;
    }

    public DbWhereInnerSql(Object leftWhereItem, String operator, DbSelect innerSelect) {
        this.leftWhereItem = new DbObject<>(leftWhereItem);
        this.operator = operator;
        this.innerSelect = innerSelect;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    @Override
    public String getSql() {
        return String.format("(%s) %s (%s)", leftWhereItem, operator, innerSelect.getSql());
    }

    @Override
    public String getParametrizedSql() {
        return String.format("(%s) %s (%s)", leftWhereItem, operator, innerSelect.getParametrizedSql());
    }

    @Override
    public SqlParameters getParameters() {
        return innerSelect.getParameters();
    }
    //</editor-fold>

    @Override
    public String toString(){
        return getSql();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbWhereInnerSql that = (DbWhereInnerSql) o;
        return Objects.equals(leftWhereItem, that.leftWhereItem) &&
                Objects.equals(operator, that.operator) &&
                Objects.equals(innerSelect, that.innerSelect);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftWhereItem, operator, innerSelect);
    }
}
