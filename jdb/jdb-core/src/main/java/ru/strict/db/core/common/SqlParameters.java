package ru.strict.db.core.common;

import java.sql.SQLType;
import java.util.ArrayList;
import java.util.List;

/**
 * Список параметров для подставновки в sql-запрос типа PreparedStatement
 */
public class SqlParameters<VALUE> {

    private List<SqlParameter<VALUE>> parameters;

    public SqlParameters() {
        parameters = new ArrayList<>();
    }

    public List<SqlParameter<VALUE>> getParameters() {
        return parameters;
    }

    public int size(){
        return parameters.size();
    }

    public SqlParameter<VALUE> getByIndex(int index){
        return parameters.stream().filter(param -> param.getIndex() == index).findFirst().get();
    }

    public SqlParameter<VALUE> getByName(String columnName){
        return parameters.stream().filter(param -> param.getColumnName().equals(columnName)).findFirst().get();
    }

    public void add(int index, String columnName){
        checkParameter(index, columnName);
        parameters.add(new SqlParameter(index, columnName, null));
    }

    public void add(int index, String columnName, VALUE value){
        checkParameter(index, columnName);
        parameters.add(new SqlParameter(index, columnName, value));
    }

    public void add(int index, String columnName, VALUE value, SQLType type){
        checkParameter(index, columnName);
        parameters.add(new SqlParameter(index, columnName, value, type));
    }

    public void add(SqlParameter sqlParameter){
        checkParameter(sqlParameter.getIndex(), sqlParameter.getColumnName());
        parameters.add(sqlParameter);
    }

    public void addLast(String columnName){
        checkParameter(parameters.size(), columnName);
        parameters.add(new SqlParameter(parameters.size(), columnName, null));
    }

    public void addLast(String columnName, VALUE value){
        checkParameter(parameters.size(), columnName);
        parameters.add(new SqlParameter(parameters.size(), columnName, value));
    }

    public void addLast(String columnName, VALUE value, SQLType type){
        checkParameter(parameters.size(), columnName);
        parameters.add(new SqlParameter(parameters.size(), columnName, value, type));
    }

    public void addAll(SqlParameters<VALUE> parameters){
        for(SqlParameter parameter : parameters.getParameters()){
            checkParameter(parameter.getIndex(), parameter.getColumnName());
        }
        this.parameters.addAll(parameters.getParameters());
    }

    private void checkParameter(int index, String columnName){
        if(parameters.stream()
                .anyMatch(p -> p.getIndex() == index || p.getColumnName().equals(columnName))){
            throw new IllegalArgumentException(String.format("The index [%s] or name [%s] to column already exists", index, columnName));
        }
    }
}
