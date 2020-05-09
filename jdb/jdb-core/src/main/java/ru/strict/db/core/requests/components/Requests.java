package ru.strict.db.core.requests.components;

import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.requests.IParameterizedRequest;

public class Requests implements IParameterizedRequest {
    private IParameterizedRequest join;
    private Where where;
    private IParameterizedRequest groupBy;
    private Sort sort;
    private Limit limit;
    private Offset offset;

    public Requests setJoin(IParameterizedRequest join) {
        this.join = join;
        return this;
    }

    public Requests setWhere(Where where) {
        this.where = where;
        return this;
    }

    public Requests setGroupBy(IParameterizedRequest groupBy) {
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
        parameters.addAll(join.getParameters());
        parameters.addAll(where.getParameters());
        parameters.addAll(groupBy.getParameters());

        return parameters;
    }
}
