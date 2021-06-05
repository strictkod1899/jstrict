package ru.strict.db.core.query.components;

import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.query.IParameterizedQuery;

public class Requests implements IParameterizedQuery {
    private IParameterizedQuery join;
    private Where where;
    private IParameterizedQuery groupBy;
    private Sort sort;
    private Limit limit;
    private Offset offset;

    public Requests setJoin(IParameterizedQuery join) {
        this.join = join;
        return this;
    }

    public Requests setWhere(Where where) {
        this.where = where;
        return this;
    }

    public Requests setGroupBy(IParameterizedQuery groupBy) {
        this.groupBy = groupBy;
        return this;
    }

    public Requests setSort(Sort sort) {
        this.sort = sort;
        return this;
    }

    public Requests setLimit(Limit limit) {
        this.limit = limit;
        return this;
    }

    public Requests setOffset(Offset offset) {
        this.offset = offset;
        return this;
    }

    @Override
    public String getParameterizedSql() {
        return (join == null ? "" : join.getParameterizedSql()) +
                (where == null ? "" : where.getParameterizedSql()) +
                (groupBy == null ? "" : groupBy.getParameterizedSql()) +
                (sort == null ? "" : sort.getSql()) +
                (limit == null ? "" : limit.getSql()) +
                (offset == null ? "" : offset.getSql());
    }

    @Override
    public SqlParameters getParameters() {
        SqlParameters parameters = new SqlParameters();
        if (join != null) {
            parameters.addAll(join.getParameters());
        }
        if (where != null) {
            parameters.addAll(where.getParameters());
        }
        if (groupBy != null) {
            parameters.addAll(groupBy.getParameters());
        }

        return parameters;
    }
}
