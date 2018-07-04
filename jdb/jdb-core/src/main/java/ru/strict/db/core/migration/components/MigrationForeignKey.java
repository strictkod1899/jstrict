package ru.strict.db.core.migration.components;

import ru.strict.utils.UtilHashCode;

/**
 * Внешний ключ таблицы для миграции в базу данных
 */
public class MigrationForeignKey implements MigrationComponent {

    /**
     * Наименование внешнего ключа в конструкци CONSTRAINTS
     */
    private String name;
    /**
     * Столбцев в текущей таблицы, который ссылается на внешнюю таблицу
     */
    private String column;
    /**
     * Наименование внешней таблицы
     */
    private String tableRef;
    /**
     * Наименовани столбца внешней таблицы
     */
    private String columnRef;
    /**
     * Поведение при обновлении записи внешней таблицы
     */
    private MigrationForeignBehavior updateBehavior;
    /**
     * Поведение при удалении записи внешней таблицы
     */
    private MigrationForeignBehavior deleteBehavior;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public MigrationForeignKey(String name, String column, String tableRef, String columnRef
            , MigrationForeignBehavior updateBehavior, MigrationForeignBehavior deleteBehavior) {
        if(name == null){
            throw new NullPointerException("name is NULL");
        } else if(column == null){
            throw new NullPointerException("column is NULL");
        } else if(tableRef == null){
            throw new NullPointerException("tableRef is NULL");
        } else if(columnRef == null){
            throw new NullPointerException("columnRef is NULL");
        } else if(updateBehavior == null){
            throw new NullPointerException("updateBehavior is NULL");
        } else if(deleteBehavior == null){
            throw new NullPointerException("deleteBehavior is NULL");
        }

        this.name = name;
        this.column = column;
        this.tableRef = tableRef;
        this.columnRef = columnRef;
        this.updateBehavior = updateBehavior;
        this.deleteBehavior = deleteBehavior;
    }
    //</editor-fold>

    @Override
    public String getSql(){
        return String.format("CONSTRAINT %s FOREIGN KEY (%s) REFERENCES %s (%s) ON UPDATE %s ON DELETE %s"
                , name, column, tableRef, columnRef, updateBehavior.getSql(), deleteBehavior.getSql());
    }

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getName() {
        return name;
    }

    public String getColumn() {
        return column;
    }

    public String getTableRef() {
        return tableRef;
    }

    public String getColumnRef() {
        return columnRef;
    }

    public MigrationForeignBehavior getUpdateBehavior() {
        return updateBehavior;
    }

    public MigrationForeignBehavior getDeleteBehavior() {
        return deleteBehavior;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return getSql();
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof MigrationForeignKey) {
            MigrationForeignKey object = (MigrationForeignKey) obj;
            return name.equals(object.getName()) && column.equals(object.getColumn())
                    && tableRef.equals(object.getTableRef())
                    && columnRef.equals(object.getColumnRef())
                    && updateBehavior.equals(object.getUpdateBehavior())
                    && deleteBehavior.equals(object.getDeleteBehavior());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        return UtilHashCode.createHashCode(name, column, tableRef, columnRef, updateBehavior, deleteBehavior);
    }
    //</editor-fold>
}
