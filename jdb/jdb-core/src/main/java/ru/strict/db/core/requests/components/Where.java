package ru.strict.db.core.requests.components;

import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.requests.ParameterizedRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Where extends ParameterizedRequest {

    private static final String WHERE_TEMPLATE = " WHERE %s ";

    public Where(String where, SqlParameters parameters) {
        super(String.format(WHERE_TEMPLATE, where), parameters);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private SqlParameters parameters;
        private List<Item> items;

        public Builder() {
            parameters = new SqlParameters();
            items = new ArrayList<>(5);
        }

        public Builder addParameter(String name, Object value) {
            this.parameters.add(name, value);
            return this;
        }

        public Builder parameters(SqlParameters parameters) {
            this.parameters = parameters;
            return this;
        }

        public Builder item(WhereType type, String parameterizedSql) {
            this.items.add(new Item(type, parameterizedSql));
            return this;
        }

        public Builder item(String parameterizedSql) {
            this.items.add(new Item(parameterizedSql));
            return this;
        }

        public Where build() {
            StringBuilder sql = new StringBuilder();
            items.forEach(item -> {
                if (sql.length() > 0) {
                    sql.append(String.format("%s %s",
                            Optional.ofNullable(item.type)
                                    .map(WhereType::getSql)
                                    .orElse(null),
                            item.parameterizedSql)
                    );
                } else {
                    sql.append(item.parameterizedSql);
                }
            });
            return new Where(sql.toString(), parameters);
        }
    }

    private static class Item {
        private WhereType type;
        private String parameterizedSql;

        public Item(String parameterizedSql) {
            this.parameterizedSql = parameterizedSql;
        }

        public Item(WhereType type, String parameterizedSql) {
            this.type = type;
            this.parameterizedSql = parameterizedSql;
        }
    }
}
