package ru.strict.ioc.fromproperties;

import org.junit.jupiter.api.Test;
import ru.strict.ioc.fromproperties.prepare.BoomConfig;
import ru.strict.ioc.fromproperties.prepare.IoC;
import ru.strict.ioc.fromproperties.prepare.TestConfig;
import ru.strict.ioc.fromproperties.prepare.TestInnerConfig;

import static org.junit.jupiter.api.Assertions.*;

public class FromPropertiesTest {

    @Test
    public void testFromProperties_appConfig() {
        var expectedTestConfig = createExpectedTestConfig();

        var testConfig = IoC.instance().getComponent(TestConfig.class);

        assertEquals(expectedTestConfig, testConfig);
    }

    @Test
    public void testFromProperties_boomConfig() {
        var expectedBoomConfig = new BoomConfig();
        expectedBoomConfig.setBoom1("boom-1");
        expectedBoomConfig.setBoom2("boom-2");

        var boomConfig = IoC.instance().getComponent(BoomConfig.class);

        assertEquals(expectedBoomConfig, boomConfig);
    }

    private TestConfig createExpectedTestConfig() {
        var inner1 = new TestInnerConfig();
        inner1.setField1("field1-1");
        inner1.setField2("field1-2");
        var inner2 = new TestInnerConfig();
        inner2.setField1("field2-1");
        inner2.setField2("field2-2");

        var testConfig = new TestConfig(inner1, inner2);
        testConfig.setName("testName");
        testConfig.setAge(18);
        testConfig.setSum(10.01f);

        return testConfig;
    }

}
