package ru.strict.db.core.migration.components;

import ru.strict.utils.UtilHashCode;

/**
 * Первичный ключ таблицы для миграции в базу данных
 */
public class MigrationPrimaryKey implements MigrationComponent {

    /**
     * Наименование первичного ключа в конструкци CONSTRAINTS
     */
    private String name;
    /**
     * Столбец, который представляет значения первичного ключа
     */
    private String column;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public MigrationPrimaryKey(String name, String column) {
        if(name == null){
            throw new NullPointerException("name is NULL");
        } else if(column == null){
            throw new NullPointerException("column is NULL");
        }

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
        if(obj!=null && obj instanceof MigrationPrimaryKey) {
            MigrationPrimaryKey object = (MigrationPrimaryKey) obj;
            return name.equals(object.getName()) && column.equals(object.getColumn());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        return UtilHashCode.createHashCode(name, column);
    }
    //</editor-fold>
}
