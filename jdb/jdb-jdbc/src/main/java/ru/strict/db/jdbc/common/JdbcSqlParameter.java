package ru.strict.db.jdbc.common;

public class JdbcSqlParameter<VALUE> {

    private int index;
    private String name;
    private VALUE value;

    public JdbcSqlParameter(int index, String name, VALUE value) {
        this.index = index;
        this.name = name;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public VALUE getValue() {
        return value;
    }
}
