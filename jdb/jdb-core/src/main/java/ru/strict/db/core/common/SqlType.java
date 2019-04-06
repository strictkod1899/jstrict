package ru.strict.db.core.common;

import java.sql.SQLType;
import java.util.Arrays;

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

    public static SqlType getByType(int type) {
        return Arrays.stream(SqlType.values())
                .filter(t -> t.type == type)
                .findFirst()
                .orElse(null);
    }
}
