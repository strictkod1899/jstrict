package ru.strict.models;

public interface INamedModel<ID> extends IModelBase<ID> {
    String getCaption();
}
