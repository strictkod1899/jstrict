package ru.strict.db.core.query.components;

import ru.strict.db.core.common.SqlParameter;
import ru.strict.db.core.common.SqlParameters;

public class SingleWhere extends Where {

    private static final String WHERE_TEMPLATE = "%s %s ?";
    private static final String WHERE_WITHOUT_PARAM_TEMPLATE = "%s %s";

    public SingleWhere(SqlItem column, String operation, SqlParameter<?> parameter) {
        super(sql(column, operation), new SqlParameters(parameter));
    }

    public static String sql(SqlItem column, String operation) {
        return String.format(WHERE_TEMPLATE, column.getSql(), operation);
    }

    /**
     * Сгенерировать sql без подстановки параметра
     */
    public static String operationSql(SqlItem column, String operation) {
        return String.format(WHERE_WITHOUT_PARAM_TEMPLATE, column.getSql(), operation);
    }
}
