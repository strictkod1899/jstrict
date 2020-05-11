package ru.strict.db.core.configuration.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sql")
public class Sql {
    private String groupName;
    private List<Query> queries;

    public String getGroupName() {
        return groupName;
    }

    @XmlAttribute(name = "groupName")
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Query> getQueries() {
        return queries;
    }

    @XmlElement(name = "query")
    public void setQueries(List<Query> queries) {
        this.queries = queries;
    }
}
