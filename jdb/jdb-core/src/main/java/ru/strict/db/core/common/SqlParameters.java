package ru.strict.db.core.common;

import java.sql.SQLType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public SqlParameter<VALUE> getByName(String name){
        return parameters.stream().filter(param -> param.getName().equals(name)).findFirst().get();
    }

    public void add(int index, String name){
        checkParameter(index, name);
        parameters.add(new SqlParameter(index, name, null));
    }

    public void add(int index, String name, VALUE value){
        checkParameter(index, name);
        parameters.add(new SqlParameter(index, name, value));
    }

    public void add(int index, String name, VALUE value, SQLType type){
        checkParameter(index, name);
        parameters.add(new SqlParameter(index, name, value, type));
    }

    public void add(SqlParameter sqlParameter){
        checkParameter(sqlParameter.getIndex(), sqlParameter.getName());
        parameters.add(sqlParameter);
    }

    public void addLast(String name){
        checkParameter(parameters.size(), name);
        parameters.add(new SqlParameter(parameters.size(), name, null));
    }

    public void addLast(String name, VALUE value){
        checkParameter(parameters.size(), name);
        parameters.add(new SqlParameter(parameters.size(), name, value));
    }

    public void addLast(String name, VALUE value, SQLType type){
        checkParameter(parameters.size(), name);
        parameters.add(new SqlParameter(parameters.size(), name, value, type));
    }

    public void addAll(SqlParameters<VALUE> parameters){
        for(SqlParameter parameter : parameters.getParameters()){
            checkParameter(parameter.getIndex(), parameter.getName());
        }
        this.parameters.addAll(parameters.getParameters());
    }

    private void checkParameter(int index, String name){
        if(parameters.stream()
                .anyMatch(p -> p.getIndex() == index || p.getName().equals(name))){
            throw new IllegalArgumentException(String.format("The index [%s] or name [%s] to column already exists", index, name));
        }
    }

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("sql-parameters. size = %s", size());
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof SqlParameters) {
            SqlParameters object = (SqlParameters) obj;
            return Objects.equals(parameters, object.parameters);
        }else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(parameters);
    }
    //</editor-fold>
}
