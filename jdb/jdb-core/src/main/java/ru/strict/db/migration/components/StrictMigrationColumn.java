package ru.strict.db.migration.components;

public class StrictMigrationColumn {

    private String name;
    private String type;
    private boolean isNotNull;
    private String defaultValue;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictMigrationColumn(String name, String type, boolean isNotNull) {
        this.name = name;
        this.type = type;
        this.isNotNull = isNotNull;
        defaultValue = null;
    }

    public StrictMigrationColumn(String name, String type, boolean isNotNull, String defaultValue) {
        this.name = name;
        this.type = type;
        this.isNotNull = isNotNull;
        this.defaultValue = defaultValue;
    }
    //</editor-fold>

    /**
     * Получить sql-строку описания столбца
     * @return
     */
    public String getSql(){
        // TODO: добавить поддержку Default value
        return String.format("%s %s %s", name, type, (isNotNull? "NOT NULL": ""));
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
}
