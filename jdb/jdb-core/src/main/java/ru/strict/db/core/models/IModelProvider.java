package ru.strict.db.core.models;

public interface IModelProvider<MODEL> {
    MODEL getById(Integer id);
    MODEL getByCaption(String caption);
}
