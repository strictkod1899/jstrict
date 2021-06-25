package ru.strict.ioc.box;

import lombok.RequiredArgsConstructor;
import ru.strict.ioc.IoC;

@RequiredArgsConstructor
public class ComponentFactory<T> implements IComponentFactory<T> {
    private final Class<T> clazz;
    private final IoC ioc;

    @Override
    public T getComponent() {
        return ioc.getComponent(clazz);
    }
}
