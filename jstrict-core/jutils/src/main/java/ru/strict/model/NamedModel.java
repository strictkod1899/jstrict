package ru.strict.model;

public interface NamedModel<ID> extends Model<ID> {
    String getName();
}
