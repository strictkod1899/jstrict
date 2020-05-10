package ru.strict.db.core.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static ru.strict.utils.ResourcesUtil.*;

public class SqlConfiguration {
    private Map<String, SqlGroup> groups;

    public SqlConfiguration() {
        groups = new HashMap<>();
    }

    public void loadResource(String resourcePath) {

    }

    public void loadFile(String filePath) {

    }

    public String getSql(String groupName, String queryName) {
        return Optional.ofNullable(groups.get(groupName))
                .map(group -> group.getQuery(queryName))
                .orElse(null);
    }
}
