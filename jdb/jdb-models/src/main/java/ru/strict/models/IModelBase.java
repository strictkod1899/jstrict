package ru.strict.models;

import ru.strict.patterns.MapTarget;

public interface IModelBase<ID> extends MapTarget {
    ID getId();
}
