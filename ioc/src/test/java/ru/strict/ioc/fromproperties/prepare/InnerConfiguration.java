package ru.strict.ioc.fromproperties.prepare;

import ru.strict.ioc.annotation.Component;
import ru.strict.ioc.annotation.FromProperties;

public class InnerConfiguration {

    @Component
    @FromProperties(prefix = "test.inner1")
    public TestInnerConfig inner1() {
        return new TestInnerConfig();
    }

    @Component
    @FromProperties(prefix = "test.inner2")
    public TestInnerConfig inner2() {
        return new TestInnerConfig();
    }
}
