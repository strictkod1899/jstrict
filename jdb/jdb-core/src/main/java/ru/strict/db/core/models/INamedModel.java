package ru.strict.db.core.models;

public interface INamedModel<ID> extends IModelBase<ID> {
    String getCaption();
}
