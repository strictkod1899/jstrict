package ru.strict.db.core.configuration;

import java.util.HashMap;
import java.util.Map;

class SqlGroup {
    private Map<String, String> queries;

    public SqlGroup() {
        queries = new HashMap<>();
    }

    public void setQuery(String queryName, String sql) {
        queries.put(queryName, sql);
    }

    public String getQuery(String queryName) {
        return queries.get(queryName);
    }
}
