package ru.strict.db.core.migration.components;

/**
 * Типы поведения внешних ключей при удалении/обновлении связанной записи
 */
public enum MigrationForeignBehavior implements MigrationComponent {
    /**
     * Не выполнять никаких действий при удалении/обновлении связанных записей
     */
    NO_ACTION("NO ACTION"),
    /**
     * Запрещается удаление/обновление связанных записей
     */
    RESTRICT("RESTRICT"),
    /**
     * Устанавливать значение null при удалении/обновлении связанных записей
     */
    SET_NULL("SET NULL"),
    /**
     * Устанавливать значение по-умолчанию при удалении/обновлении связанных записей
     */
    SET_DEFAULT("SET DEFAULT"),
    /**
     * Выполнять удаление/обновление текущей записи при удалении/обновлении связанных записей
     */
    CASCADE("CASCADE");

    private String sql;

    MigrationForeignBehavior(String sql) {
        if(sql == null){
            throw new NullPointerException("sql is NULL");
        }

        this.sql = sql;
    }

    @Override
    public String getSql() {
        return sql;
    }

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("Foreign behaviour: %s", sql);
    }
    //</editor-fold>
}
