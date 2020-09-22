package ru.strict.patterns.model;

import java.util.Objects;

public abstract class BaseDto<ID, T extends IModel<ID>> {
    private final T model;

    public BaseDto(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public ID getId() {
        return model == null ? null : model.getId();
    }

    @Override
    public String toString() {
        return "BaseDto{" +
                "model=" + model +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseDto<?, ?> baseDto = (BaseDto<?, ?>) o;
        return Objects.equals(model, baseDto.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }
}
