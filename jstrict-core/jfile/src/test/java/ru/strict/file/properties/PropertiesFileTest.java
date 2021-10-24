package ru.strict.file.properties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.strict.util.ResourcesUtil;

class PropertiesFileTest {

    @Test
    void testReadValue_withoutSuffix_success() {
        var filePath = "test.properties";
        var expected1 = "hello";
        var expected2 = "2";

        var file = new PropertiesFile(() -> ResourcesUtil.getResourceStream(filePath));

        Assertions.assertEquals(expected1, file.readValue("var1"));
        Assertions.assertEquals(expected2, file.readValue("var2"));
    }

    @Test
    void testReadValue_withSuffix_success() {
        var filePath = "test.properties";
        var expected1 = "privet";
        var expected2 = "2";

        var file = PropertiesFile.fromResource(filePath, "rus");

        Assertions.assertEquals(expected1, file.readValue("var1"));
        Assertions.assertEquals(expected2, file.readValue("var2"));
    }
}
