package ru.strict.db.core.common;

import java.sql.JDBCType;
import java.sql.SQLType;

/**
 * Тип генерируемого значения для id записи в базе данных.
 * Используется в классах производных от BaseRepository
 */
public enum GenerateIdType {
    /**
     * Генерация числового значения типа Long. <br/>
     * <i><b>Примечание: </b> при использовании данного типа, в базе данных должен быть настроен auto increment</i>
     */
    LONG,
    /**
     * Генерация числового значения типа Integer. <br/>
     * <i><b>Примечание: </b> при использовании данного типа, в базе данных должен быть настроен auto increment</i>
     */
    INT,
    /**
     * Генерация значений UUID
     */
    UUID,
    /**
     * Не генерировать значение
     */
    NONE;

    public static SQLType getSqlType(GenerateIdType generateIdType) {
        if (generateIdType == null) {
            return null;
        }

        switch (generateIdType) {
            case LONG:
                return JDBCType.BIGINT;
            case INT:
                return JDBCType.INTEGER;
            case UUID:
                return JDBCType.OTHER;
            default:
                return null;
        }
    }
}
