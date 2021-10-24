package ru.strict.i18n;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalizedStringTest {

    private static final LocalizedString LOCALIZED_STRING = LocalizedString.builder()
            .item("rus", "item1")
            .build();

    @Test
    void testBuild() {
        LocalizedString localizedString = LocalizedString.builder()
                .item("rus", "item1")
                .build();

        assertEquals(LOCALIZED_STRING, localizedString);
        assertEquals(LOCALIZED_STRING.hashCode(), localizedString.hashCode());
    }
}
