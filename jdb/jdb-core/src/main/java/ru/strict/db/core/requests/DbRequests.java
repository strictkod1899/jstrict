package ru.strict.db.core.requests;

import java.util.*;
import ru.strict.utils.UtilHashCode;

/**
 * Набор условий для добавления к sql-запросу
 */
public class DbRequests extends LinkedList<DbRequestBase> implements IDbRequest {

    /**
     * Наименование таблицы из основной конструкции select
     */
    private String selectTableName;
    /**
     * Добавлять AND между условиями
     */
    private boolean isAnd;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public DbRequests(String selectTableName, boolean isAnd) {
        this.selectTableName = selectTableName;
        this.isAnd = isAnd;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getSelectTableName() {
        return selectTableName;
    }

    public boolean isAnd() {
        return isAnd;
    }
    //</editor-fold>

    @Override
    public String getSql(){
        String result = "";

        Collection<String> tableNames = new LinkedList<>();

        for(DbRequestBase request : this){
            if(!tableNames.contains(request.getTableName()) && !request.getTableName().equals(selectTableName))
                tableNames.add(request.getTableName());
        }

        for(String tableName : tableNames)
            result+=", " + tableName;

        if(!isEmpty())
            result+=" WHERE ";
        else
            return "";

        String symbol;
        if(isAnd)
            symbol = "AND";
        else
            symbol = "OR";

        result += get(0).getSql() + " ";

        for(int i=1; i<size(); i++)
            result += symbol + " " + get(i).getSql() + " ";

        return result;
    }

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return getSql();
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof DbRequests) {
            DbRequests object = (DbRequests) obj;
            return super.equals(object) && selectTableName.equals(object.getSelectTableName())
                    && isAnd == object.isAnd()
                    && (size() == object.size() && this.containsAll(object));
        }else
            return false;
    }

    @Override
    public int hashCode(){
        return UtilHashCode.createHashCode(selectTableName, isAnd, this);
    }
    //</editor-fold>
}
