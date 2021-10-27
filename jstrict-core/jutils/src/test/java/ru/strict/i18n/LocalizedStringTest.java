package ru.strict.i18n;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalizedStringTest {

    @Test
    void testBuilder_equals_success() {
        var localizedString1 = LocalizedString.builder()
                .item("rus", "item1")
                .build();

        var localizedString2 = LocalizedString.builder()
                .item("rus", "item1")
                .build();

        assertEquals(localizedString1, localizedString2);
        assertEquals(localizedString1.hashCode(), localizedString2.hashCode());
    }
}
