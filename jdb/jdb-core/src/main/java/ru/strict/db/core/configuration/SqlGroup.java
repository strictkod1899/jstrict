package ru.strict.db.core.configuration;

import java.util.HashMap;
import java.util.Map;

class SqlGroup {
    private Map<String, String> queries;
    private Map<String, String> where;

    public SqlGroup() {
        queries = new HashMap<>();
        where = new HashMap<>();
    }

    public void setQuery(String queryName, String sql) {
        queries.put(queryName, sql);
    }

    public String getQuery(String queryName) {
        return queries.get(queryName);
    }

    public void setWhere(String whereName, String sql) {
        where.put(whereName, sql);
    }

    public String getWhere(String whereName) {
        return where.get(whereName);
    }
}
