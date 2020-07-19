package ru.strict.db.core.common;

import java.sql.SQLType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Список параметров для подставновки в sql-запрос типа PreparedStatement
 */
public class SqlParameters implements Iterable<SqlParameter<?>> {
    private List<SqlParameter<?>> parameters;

    public SqlParameters() {
        parameters = new ArrayList<>(5);
    }

    public SqlParameters(SqlParameter<?> parameter) {
        this();
        add(parameter);
    }

    public List<SqlParameter<?>> getParameters() {
        return parameters;
    }

    public int size() {
        return parameters.size();
    }

    public SqlParameter<?> getByIndex(int index) {
        return parameters.stream()
                .filter(param -> param.getIndex() == index)
                .findFirst()
                .orElse(null);
    }

    public SqlParameter<?> getByName(String name) {
        return parameters.stream()
                .filter(param -> param.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void set(int index, String name) {
        checkParameter(index, name);
        parameters.add(new SqlParameter<>(index, name, null));
    }

    public void set(int index, String name, Object value) {
        checkParameter(index, name);
        parameters.add(new SqlParameter<>(index, name, value));
    }

    public void set(int index, String name, Object value, SQLType type) {
        checkParameter(index, name);
        parameters.add(new SqlParameter<>(index, name, value, type));
    }

    public void set(SqlParameter<?> sqlParameter) {
        checkParameter(sqlParameter.getIndex(), sqlParameter.getName());
        parameters.add(sqlParameter);
    }

    public void add(SqlParameter<?> sqlParameter) {
        checkParameter(sqlParameter.getName());
        parameters.add(new SqlParameter<>(parameters.size(),
                sqlParameter.getName(),
                sqlParameter.getValue(),
                sqlParameter.getSqlType())
        );
    }

    public void add(String name) {
        checkParameter(name);
        parameters.add(new SqlParameter<>(parameters.size(), name, null));
    }

    public void add(String name, Object value) {
        checkParameter(name);
        parameters.add(new SqlParameter<>(parameters.size(), name, value));
    }

    public void add(String name, Object value, SQLType type) {
        checkParameter(name);
        parameters.add(new SqlParameter<>(parameters.size(), name, value, type));
    }

    public void setAll(SqlParameters parameters) {
        for (SqlParameter<?> parameter : parameters.getParameters()) {
            checkParameter(parameter.getIndex(), parameter.getName());
        }
        this.parameters.addAll(parameters.getParameters());
    }

    public void addAll(SqlParameters parameters) {
        for (SqlParameter<?> parameter : parameters.getParameters()) {
            checkParameter(parameter.getName());
        }

        for (SqlParameter<?> parameter : parameters.getParameters()) {
            this.parameters.add(new SqlParameter<>(this.parameters.size(),
                    parameter.getName(),
                    parameter.getValue(),
                    parameter.getSqlType())
            );
        }
    }

    public Stream<SqlParameter<?>> stream() {
        return parameters.stream();
    }

    private void checkParameter(int index, String name) {
        if (parameters.stream()
                .anyMatch(p -> p.getIndex() == index || p.getName().equals(name))) {
            throw new IllegalArgumentException(String.format("The index [%s] or name [%s] to column already exists",
                    index,
                    name));
        }
    }

    private void checkParameter(String name) {
        if (parameters.stream()
                .anyMatch(p -> p.getName().equals(name))) {
            throw new IllegalArgumentException(String.format("The name [%s] to column already exists", name));
        }
    }

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString() {
        return String.format("sql-parameters. size = %s", size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SqlParameters that = (SqlParameters) o;
        return Objects.equals(parameters, that.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameters);
    }

    @Override
    public Iterator<SqlParameter<?>> iterator() {
        return parameters.iterator();
    }
    //</editor-fold>
}
