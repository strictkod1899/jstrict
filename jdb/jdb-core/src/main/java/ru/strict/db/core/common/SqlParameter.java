package ru.strict.db.core.common;

import java.sql.SQLType;
import java.util.Objects;

/**
 * Параметр для подставновки в sql-запрос типа PreparedStatement
 * @param <VALUE> Тип значения параметра
 */
public class SqlParameter<VALUE> {

    private int index;
    private String name;
    private VALUE value;
    /**
     * java.sql.JDBCType
     */
    private SQLType sqlType;

    public SqlParameter(int index, String name, VALUE value) {
        this.index = index;
        this.name = name;
        this.value = value;
    }

    public SqlParameter(int index, String name, VALUE value, SQLType sqlType) {
        this.index = index;
        this.name = name;
        this.value = value;
        this.sqlType = sqlType;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VALUE getValue() {
        return value;
    }

    public SQLType getSqlType() {
        return sqlType;
    }

    public void setSqlType(SQLType sqlType) {
        this.sqlType = sqlType;
    }

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("sql-parameter [%s - %s] - %s", index, name, String.valueOf(value));
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof SqlParameter) {
            SqlParameter object = (SqlParameter) obj;
            return Objects.equals(index, object.index)
                    && Objects.equals(name, object.name)
                    && Objects.equals(value, object.value)
                    && Objects.equals(sqlType, object.sqlType);
        }else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(index, name, value, sqlType);
    }
    //</editor-fold>
}
