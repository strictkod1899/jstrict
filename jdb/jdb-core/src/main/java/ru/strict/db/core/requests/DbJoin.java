package ru.strict.db.core.requests;

import ru.strict.validate.ValidateBaseValue;

import java.util.Objects;

/**
 * Конструкция join sql-запроса
 */
public class DbJoin extends DbRequestBase {
    private JoinType joinType;
    private String table1Column;
    private DbTable table2;
    private String table2Column;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public DbJoin(JoinType joinType, DbTable table1, String table1Column, DbTable table2, String table2Column) {
        super(table1);
        if(joinType == null){
            throw new IllegalArgumentException("joinType is NULL");
        }
        if(ValidateBaseValue.isEmptyOrNull(table1Column)){
            throw new IllegalArgumentException("table1Column is NULL");
        }
        if(table2 == null){
            throw new IllegalArgumentException("table2 is NULL");
        }
        if(ValidateBaseValue.isEmptyOrNull(table2Column)){
            throw new IllegalArgumentException("table2Column is NULL");
        }
        this.joinType = joinType;
        this.table1Column = table1Column;
        this.table2 = table2;
        this.table2Column = table2Column;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public JoinType getJoinType() {
        return joinType;
    }

    public DbTable getTable1(){
        return getTable();
    }

    public String getTable1Column() {
        return table1Column;
    }

    public DbTable getTable2() {
        return table2;
    }

    public String getTable2Column() {
        return table2Column;
    }
    //</editor-fold>

    @Override
    public String getSql(){
        String result;
        result = String.format("%s JOIN %s ON %s.%s = %s.%s",
                joinType.getCaption(),
                getTable1().getSql(),
                getTable1().getRequiredName(),
                table1Column,
                table2.getRequiredName(),
                table2Column);
        return result.trim();
    }

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return getSql();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DbJoin dbJoin = (DbJoin) o;
        return joinType == dbJoin.joinType &&
                Objects.equals(table1Column, dbJoin.table1Column) &&
                Objects.equals(table2, dbJoin.table2) &&
                Objects.equals(table2Column, dbJoin.table2Column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), joinType, table1Column, table2, table2Column);
    }

    //</editor-fold>
}
