package ru.strict.db.migration.components;

public class StrictMigrationColumn implements StrictMigrationComponent{

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
        if(obj instanceof StrictMigrationColumn) {
            StrictMigrationColumn column = (StrictMigrationColumn) obj;
            return name.equals(column.getName()) && type.equals(column.getType()) && isNotNull == column.isNotNull()
                    && defaultValue.equals(column.getDefaultValue());
        }else
            return false;
    }
    //</editor-fold>
}
