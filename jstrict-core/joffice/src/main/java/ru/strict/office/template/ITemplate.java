package ru.strict.office.template;

import java.util.Map;

public interface ITemplate {
    void addItem(String name, Object item);
    void removeItem(String name);
    <T> T getItem(String name);
    Map<String, Object> getItems();
    TemplateConfiguration getConfiguration();
    void fillTemplate();
    void toPdf();
}
