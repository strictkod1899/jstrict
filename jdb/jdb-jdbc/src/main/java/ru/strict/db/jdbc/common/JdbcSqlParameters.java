package ru.strict.db.jdbc.common;

import java.util.ArrayList;

/**
 * Список параметров для подставновки в sql-запрос типа PreparedStatement. Не требует явного использования
 */
public class JdbcSqlParameters extends ArrayList<JdbcSqlParameter> {

    public JdbcSqlParameter findByIndex(int index){
        return this.stream().filter(param -> param.getIndex() == index).findFirst().get();
    }

    public JdbcSqlParameter findByName(String name){
        return this.stream().filter(param -> param.getName().equals(name)).findFirst().get();
    }

    public void add(int index, String name){
        add(new JdbcSqlParameter(index, name, null));
    }

    public void add(int index, String name, Object value){
        add(new JdbcSqlParameter(index, name, value));
    }

    public void addLast(String name){
        add(new JdbcSqlParameter(size(), name, null));
    }

    public void addLast(String name, Object value){
        add(new JdbcSqlParameter(size(), name, value));
    }
}
