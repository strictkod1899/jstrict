package ru.strict.db.core.requests;

import ru.strict.db.core.common.SqlParameter;
import ru.strict.db.core.common.SqlParameters;

import java.util.ArrayList;
import java.util.List;

public class DbWhere extends DbWhereBase<List<DbWhereBase>, DbWhereBase> {
    
    private List<DbWhereBase> childs;
    private WhereType whereType;

    public DbWhere(WhereType whereType){
        super();
        this.whereType = whereType;
        childs = new ArrayList<>();
    }

    @Override
    public void add(DbWhereBase component){
        childs.add(component);
    }

    @Override
    public void remove(DbWhereBase component){
        childs.remove(component);
    }

    @Override
    public void set(int i, DbWhereBase component){
        childs.set(i, component);
    }

    @Override
    public DbWhereBase getChild(int i){
        return childs.get(i);
    }

    @Override
    public List<DbWhereBase> getChilds(){
        return childs;
    }

    @Override
    public int size() {
        return childs.size();
    }

    @Override
    public boolean isEmpty() {
        return childs.isEmpty();
    }

    @Override
    public boolean contains(DbWhereBase component) {
        return childs.contains(component);
    }

    @Override
    public void clear() {
        childs.clear();
    }

    public WhereType getType() {
        return whereType;
    }

    @Override
    public String getSql() {
        if(childs.isEmpty()) {
            return "";
        }

        String result = String.format("(%s) ", childs.get(0).getSql());

        for(int i = 1; i< childs.size(); i++) {
            result += String.format("%s (%s) ", whereType.getCaption(), childs.get(i).getSql());
        }

        return result.trim();
    }

    @Override
    public String getParametrizedSql() {
        if(childs.isEmpty()) {
            return "";
        }

        String result = String.format("(%s) ", childs.get(0).getParametrizedSql());

        for(int i = 1; i< childs.size(); i++) {
            result += String.format("%s (%s) ", whereType.getCaption(), childs.get(i).getParametrizedSql());
        }

        return result.trim();
    }

    @Override
    public SqlParameters getParameters() {
        SqlParameters result = new SqlParameters();
        for(DbWhereBase where : childs){
            SqlParameters<?> parameters = where.getParameters();
            int i = result.size();
            for(SqlParameter parameter : parameters.getParameters()){
                parameter.setIndex(i);
                i++;
            }
            result.addAll(parameters);
        }

        return result;
    }

    @Override
    public String toString() {
        return getSql();
    }
}
