package ru.strict.db.core.configuration;

import ru.strict.db.core.configuration.models.Sql;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
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

    }

    public void loadFile(String filePath) {

    }

    public String getSql(String groupName, String queryName) {
        return Optional.ofNullable(groups.get(groupName))
                .map(group -> group.getQuery(queryName))
                .orElse(null);
    }
}
