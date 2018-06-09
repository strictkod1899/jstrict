package ru.strict.db.core.migration.components;

import ru.strict.utils.StrictUtilHashCode;

/**
 * Первичный ключ таблицы для миграции в базу данных
 */
public class StrictMigrationPrimaryKey implements StrictMigrationComponent {

    /**
     * Наименование первичного ключа в конструкци CONSTRAINTS
     */
    private String name;
    /**
     * Столбец, который представляет значения первичного ключа
     */
    private String column;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictMigrationPrimaryKey(String name, String column) {
        this.name = name;
        this.column = column;
    }
    //</editor-fold>

    @Override
    public String getSql(){
        return String.format("CONSTRAINT %s PRIMARY KEY (%s)", name, column);
    }

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getName() {
        return name;
    }

    public String getColumn() {
        return column;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("Primary key: %s to column %s", name, column);
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof StrictMigrationPrimaryKey) {
            StrictMigrationPrimaryKey object = (StrictMigrationPrimaryKey) obj;
            return name.equals(object.getName()) && column.equals(object.getColumn());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        return StrictUtilHashCode.createHashCode(name, column);
    }
    //</editor-fold>
}
