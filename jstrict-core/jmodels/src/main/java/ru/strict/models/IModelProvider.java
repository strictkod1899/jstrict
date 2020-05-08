package ru.strict.models;

public interface IModelProvider<MODEL> {
    MODEL getById(Integer id);
}
