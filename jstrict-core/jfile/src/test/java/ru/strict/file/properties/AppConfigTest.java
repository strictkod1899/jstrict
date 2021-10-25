package ru.strict.file.properties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AppConfigTest {

    @Test
    void test() {
        var appConfig = new AppPropertiesConfig();
        var expectedAppName = "HelloWorld";
        Assertions.assertEquals(expectedAppName, appConfig.readValue("app.name"));
    }
}
