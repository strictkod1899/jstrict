package ru.strict.db.core.requests;

/**
 * Конструкция join sql-запроса
 */
public class DbJoin extends DbRequestBase {
    private JoinType joinType;
    private String table1Column;
    private String table2Name;
    private String table2Column;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public DbJoin(JoinType joinType, String table1Name, String table1Column, String table2Name, String table2Column) {
        super(table1Name);
        this.joinType = joinType;
        this.table1Column = table1Column;
        this.table2Name = table2Name;
        this.table2Column = table2Column;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public JoinType getJoinType() {
        return joinType;
    }

    public String getTable1Name() {
        return getTableName();
    }

    public String getTable1Column() {
        return table1Column;
    }

    public String getTable2Name() {
        return table2Name;
    }

    public String getTable2Column() {
        return table2Column;
    }
    //</editor-fold>

    @Override
    public String getSql(){
        String result;
        result = String.format("%s JOIN %s ON %s.%s = %s.%s", joinType.getCaption(), getTable1Name(), getTable1Name(), table1Column,
                table2Name, table2Column);
        return result.trim();
    }

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return getSql();
    }
    //</editor-fold>
}
