package ru.strict.file;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.strict.file.properties.AppConfig;

public class AppConfigTest {

    @Test
    public void test() {
        AppConfig appConfig = new AppConfig();
        String expectedAppName = "HelloWorld";
        Assertions.assertEquals(expectedAppName, appConfig.readValue("app.name"));
    }
}
