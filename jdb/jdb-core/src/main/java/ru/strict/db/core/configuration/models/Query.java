package ru.strict.db.core.configuration.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class Query {
    private String name;
    private String query;

    public String getName() {
        return name;
    }

    @XmlAttribute(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public String getQuery() {
        return query;
    }

    @XmlValue
    public void setQuery(String query) {
        this.query = query;
    }
}
