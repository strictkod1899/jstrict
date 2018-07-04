package ru.strict.db.core.migration.components;

import ru.strict.utils.UtilHashCode;

/**
 * Столбец таблицы для миграции в базу данных
 */
public class MigrationColumn implements MigrationComponent {

    /**
     * Наименование столбца
     */
    private String name;
    /**
     * Тип столбца
     */
    private String type;
    /**
     * Подержка нулевого значения
     */
    private boolean isNotNull;
    /**
     * Значение по-умолчанию
     */
    private String defaultValue;

    private void initialize(String name, String type, boolean isNotNull, String defaultValue){
        if(name == null){
            throw new NullPointerException("name is NULL");
        } else if(type == null){
            throw new NullPointerException("type is NULL");
        }

        this.name = name;
        this.type = type;
        this.isNotNull = isNotNull;
        this.defaultValue = defaultValue;
    }

    //<editor-fold defaultState="collapsed" desc="constructors">
    public MigrationColumn(String name, String type, boolean isNotNull) {
        initialize(name, type, isNotNull, null);
    }

    public MigrationColumn(String name, String type, boolean isNotNull, String defaultValue) {
        initialize(name, type, isNotNull, defaultValue);
    }
    //</editor-fold>

    @Override
    public String getSql(){
        // TODO: добавить поддержку Default value
        return String.format("%s %s %s", name, type, (isNotNull? "NOT NULL": "Nullable"));
    }

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isNotNull() {
        return isNotNull;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("%s %s %s", name, type, (isNotNull? "NOT NULL": "Nullable"));
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof MigrationColumn) {
            MigrationColumn object = (MigrationColumn) obj;
            return name.equals(object.getName()) && type.equals(object.getType()) && isNotNull == object.isNotNull()
                    && defaultValue.equals(object.getDefaultValue());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        return UtilHashCode.createHashCode(name, type, isNotNull, defaultValue);
    }
    //</editor-fold>
}
