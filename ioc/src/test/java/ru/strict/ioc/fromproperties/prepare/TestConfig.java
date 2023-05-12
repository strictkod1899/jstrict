package ru.strict.ioc.fromproperties.prepare;

import lombok.Data;
import ru.strict.ioc.annotation.Component;
import ru.strict.ioc.annotation.FromProperties;

@Data
@FromProperties(prefix = "test")
public class TestConfig {
    @Component(value = "inner1", constructorParam = 0)
    private final TestInnerConfig inner1;
    @Component(value = "inner2", constructorParam = 1)
    private final TestInnerConfig inner2;

    private String name;
    private Integer age;
    private Float sum;

    public static class SubConfig {

    }
}
