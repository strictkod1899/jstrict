package ru.strict.db.core.requests.components;

import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.requests.IParameterizedRequest;
import ru.strict.utils.StringUtil;
import ru.strict.validate.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Select extends TableRequest implements IParameterizedRequest {

    private static final String SELECT_TEMPLATE = "SELECT %s FROM %s %s";

    private List<SqlItem> selectItems;
    private IParameterizedRequest requests;

    public Select(Table table, SqlItem selectItem) {
        this(table, Collections.singletonList(selectItem), null);
    }

    public Select(Table table, List<SqlItem> selectItems) {
        this(table, selectItems, null);
    }

    public Select(Table table, SqlItem selectItem, IParameterizedRequest requests) {
        this(table, Collections.singletonList(selectItem), requests);
    }

    public Select(Table table, List<SqlItem> selectItems, IParameterizedRequest requests) {
        super(table);
        Validator.isNull(selectItems, "selectItems");

        this.selectItems = selectItems;
        this.requests = requests;
    }

    public void addSelectItem(SqlItem selectItem) {
        selectItems.add(selectItem);
    }

    public void addSelectItem(Table table, String column) {
        selectItems.add(new SqlItem(table, column));
    }

    public List<SqlItem> getSelectItems() {
        return selectItems;
    }

    public void setRequests(IParameterizedRequest requests) {
        this.requests = requests;
    }

    @Override
    public String getParameterizedSql() {
        return String.format(SELECT_TEMPLATE,
                formatSelectItemsAsString(),
                getTable().getSql(),
                requests == null ? "" : requests.getParameterizedSql()
        ).trim();
    }

    @Override
    public SqlParameters getParameters() {
        return requests == null ? new SqlParameters() : requests.getParameters();
    }

    private String formatSelectItemsAsString() {
        return StringUtil.join(",", selectItems.stream().map(SqlItem::getSql).collect(Collectors.toList()));
    }

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString() {
        return getParameterizedSql();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Select dbSelect = (Select) o;
        return Objects.equals(selectItems, dbSelect.selectItems) &&
                Objects.equals(requests, dbSelect.requests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), selectItems, requests);
    }
    //</editor-fold>
}
