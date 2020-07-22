package ru.strict.db.core.requests.components;

import ru.strict.db.core.common.SqlParameter;
import ru.strict.db.core.common.SqlParameters;

public class SingleWhere extends Where {

    private static final String WHERE_TEMPLATE = "%s %s ?";

    public SingleWhere(SqlItem column, String operation, SqlParameter<?> parameter) {
        super(sql(column, operation), new SqlParameters(parameter));
    }

    public static String sql(SqlItem column, String operation) {
        return String.format(WHERE_TEMPLATE, column.getSql(), operation);
    }
}
