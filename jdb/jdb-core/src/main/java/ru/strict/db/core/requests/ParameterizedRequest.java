package ru.strict.db.core.requests;

import ru.strict.db.core.common.SqlParameters;
import ru.strict.validate.Validator;

import java.util.Objects;

public class ParameterizedRequest implements IParameterizedRequest {

    private final String parameterizedSql;
    private final SqlParameters parameters;

    public ParameterizedRequest(String parameterizedSql, SqlParameters parameters) {
        Validator.isEmptyOrNull(parameterizedSql, "parameterizedSql");

        this.parameterizedSql = parameterizedSql;
        this.parameters = parameters;
    }

    @Override
    public String getParameterizedSql() {
        return parameterizedSql;
    }

    @Override
    public SqlParameters getParameters() {
        return parameters;
    }

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParameterizedRequest that = (ParameterizedRequest) o;
        return Objects.equals(parameterizedSql, that.parameterizedSql) &&
                Objects.equals(parameters, that.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameterizedSql, parameters);
    }
    //</editor-fold>
}
