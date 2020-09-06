package ru.strict.db.core.configuration;

import ru.strict.db.core.configuration.models.Query;
import ru.strict.db.core.configuration.models.Sql;
import ru.strict.db.core.exceptions.ConfigurationQueryNotFoundException;
import ru.strict.db.core.exceptions.ConfigurationWhereNotFoundException;
import ru.strict.db.core.exceptions.UncorrectedQueryFormatException;
import ru.strict.validate.Validator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static ru.strict.utils.ResourcesUtil.*;

public class SqlConfiguration {

    private static JAXBContext jaxb;
    private static Unmarshaller jaxbUnmarshaller;

    static {
        try {
            jaxb = JAXBContext.newInstance(Sql.class);
            jaxbUnmarshaller = jaxb.createUnmarshaller();
        } catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Map<String, SqlGroup> groups;

    public SqlConfiguration() {
        groups = new HashMap<>();
    }

    public void loadResource(String resourcePath) {
        try (InputStream fileInputStream = getResourceStream(resourcePath)) {
            loadFile(fileInputStream);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void loadFile(InputStream fileInputStream) {
        try {
            Sql sql = (Sql) jaxbUnmarshaller.unmarshal(fileInputStream);

            SqlGroup sqlGroup = createOrGetGroup(sql.getGroupName());

            fillWhere(sqlGroup, Optional.ofNullable(sql.getWhere()).orElse(Collections.emptyList()));
            fillQueries(sqlGroup, Optional.ofNullable(sql.getQueries()).orElse(Collections.emptyList()));
        } catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String getQuery(String groupName, String queryName) {
        return Optional.ofNullable(groups.get(groupName))
                .map(group -> group.getQuery(queryName))
                .orElse(null);
    }

    public String getQueryOrThrow(String groupName, String queryName) {
        String query = getQuery(groupName, queryName);

        if (query == null) {
            throw new ConfigurationQueryNotFoundException(groupName, queryName);
        }

        return query;
    }

    public String getWhere(String groupName, String whereName) {
        return Optional.ofNullable(groups.get(groupName))
                .map(group -> group.getWhere(whereName))
                .orElse(null);
    }

    public String getWhereOrThrow(String groupName, String whereName) {
        String where = getWhere(groupName, whereName);

        if (where == null) {
            throw new ConfigurationWhereNotFoundException(groupName, whereName);
        }

        return where;
    }

    private SqlGroup createOrGetGroup(String groupName) {
        SqlGroup sqlGroup = groups.get(groupName);
        boolean groupExists = true;
        if (sqlGroup == null) {
            groupExists = false;
            sqlGroup = new SqlGroup();
        }

        if (!groupExists) {
            groups.put(groupName, sqlGroup);
        }

        return sqlGroup;
    }

    private void fillWhere(SqlGroup sqlGroup, List<Query> whereList) {
        for (Query where : whereList) {
            String query = where.getQuery().trim();

            if (!query.startsWith("WHERE")) {
                throw new UncorrectedQueryFormatException(
                        where.getName(),
                        "query with type 'WHERE' should starts from text 'WHERE'");
            }

            sqlGroup.setWhere(where.getName(), query);
        }
    }

    private void fillQueries(SqlGroup sqlGroup, List<Query> queries) {
        for (Query query : queries) {
            sqlGroup.setQuery(query.getName(), query.getQuery().trim());
        }
    }

    public static SqlConfiguration fromResources(String requiredFile, String...files) {
        Validator.isNull(requiredFile, "file");
        
        SqlConfiguration configuration = new SqlConfiguration();
        configuration.loadResource(requiredFile);
        if (files != null) {
            for (String file : files) {
                configuration.loadResource(file);
            }
        }

        return configuration;
    }

    public static SqlConfiguration fromFiles(String requiredFile, String...files) {
        Validator.isNull(requiredFile, "file");

        SqlConfiguration configuration = new SqlConfiguration();

        try {
            try (InputStream fileInputStream = new FileInputStream(requiredFile)) {
                configuration.loadFile(fileInputStream);
            }
            if (files != null) {
                for (String file : files) {
                    try (InputStream fileInputStream = new FileInputStream(file)) {
                        configuration.loadFile(fileInputStream);
                    }
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return configuration;
    }
}
