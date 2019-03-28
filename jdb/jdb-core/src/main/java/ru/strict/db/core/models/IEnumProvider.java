package ru.strict.db.core.models;

public interface IEnumProvider<ID, MODEL> extends INamedModel<ID> {
    MODEL getById(ID id);
    MODEL getByCaption(String caption);
}
