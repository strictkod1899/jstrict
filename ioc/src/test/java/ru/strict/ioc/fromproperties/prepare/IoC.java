package ru.strict.ioc.fromproperties.prepare;

import ru.strict.ioc.InstanceType;
import ru.strict.ioc.SingletonIoC;

public class IoC extends SingletonIoC {

    public static IoC instance() {
        if (getInstance() == null || !(getInstance() instanceof IoC)) {
            setInstance(new IoC());
        }
        return getInstance();
    }

    protected void configure() {
        addComponent(InnerConfiguration.class, InstanceType.CONFIGURATION);

        addComponent(TestConfig.class);
        addComponent(BoomConfig.class);
        addComponent(TestConfig.SubConfig.class);
    }
}
