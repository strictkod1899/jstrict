package ru.strict.patterns.model;

import ru.strict.patterns.mapper.MapTarget;

public interface IModel<ID> extends MapTarget {
    ID getId();
}
