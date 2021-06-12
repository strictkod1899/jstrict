package ru.strict.template.model;

import ru.strict.template.mapper.MapTarget;

public interface IModel<ID> extends MapTarget {
    ID getId();
}
