package ru.strict.patterns.model;

public interface INamedModel<ID> extends IModel<ID> {
    String getName();
}
