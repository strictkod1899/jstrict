package ru.strict.ioc;

import ru.strict.ioc.components.*;

public class IoCImpl extends IoC {
    @Override
    protected void configure() {
        addComponent(Service1.class);
        addComponent(Service2.class);

        addComponent(Controller1.class);
    }
}
