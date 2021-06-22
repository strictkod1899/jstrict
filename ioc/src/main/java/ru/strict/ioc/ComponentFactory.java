package ru.strict.ioc;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ComponentFactory<T> implements IComponentFactory<T> {
    private final Class<T> clazz;
    private final IoC ioc;

    @Override
    public T getComponent() {
        return ioc.getComponent(clazz);
    }
}
