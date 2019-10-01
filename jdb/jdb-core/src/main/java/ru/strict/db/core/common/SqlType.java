package ru.strict.db.core.common;

import java.sql.JDBCType;
import java.sql.SQLType;
import java.util.Arrays;
import java.util.UUID;

/**
 * Для получения стандартных типов, используйте java.sql.JDBCType
 */
public enum SqlType implements SQLType {
    UUID(1),
    TEXT(2);

    private Integer type;

    SqlType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name();
    }

    public String getVendor() {
        return "ru.strict";
    }

    public Integer getVendorTypeNumber() {
        return type;
    }

    public static <T> T mapValue(Object value, SQLType sqlType){
        if(sqlType == null){
            throw new IllegalArgumentException("sqlType is NULL");
        }
        if(value == null){
            return null;
        }

        if (sqlType.equals(SqlType.UUID)) {
            return (T) java.util.UUID.fromString(String.valueOf(value));
        } else if (sqlType.equals(JDBCType.BIGINT)) {
            return (T) Long.valueOf(String.valueOf(value));
        } else if (sqlType.equals(SqlType.TEXT)) {
            return (T) String.valueOf(value);
        } else {
            return (T) value;
        }
    }
}
