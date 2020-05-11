package ru.strict.db.core.configuration;

import ru.strict.db.core.configuration.models.Query;
import ru.strict.db.core.configuration.models.Sql;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.HashMap;
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
        loadFile(getResourceStream(resourcePath));
    }

    public void loadFile(InputStream fileInputStream) {
        try {
            Sql sql = (Sql) jaxbUnmarshaller.unmarshal(fileInputStream);

            SqlGroup sqlGroup = groups.get(sql.getGroupName());
            boolean groupExists = true;
            if (sqlGroup == null) {
                groupExists = false;
                sqlGroup = new SqlGroup();
            }

            for (Query query : sql.getQueries()) {
                sqlGroup.setQuery(query.getName(), query.getQuery().trim());
            }

            if (!groupExists) {
                groups.put(sql.getGroupName(), sqlGroup);
            }
        } catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String getSql(String groupName, String queryName) {
        return Optional.ofNullable(groups.get(groupName))
                .map(group -> group.getQuery(queryName))
                .orElse(null);
    }
}
