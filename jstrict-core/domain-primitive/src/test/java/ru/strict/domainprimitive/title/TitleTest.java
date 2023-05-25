package ru.strict.domainprimitive.title;

import org.junit.jupiter.api.Test;
import ru.strict.exception.CodeableException;
import ru.strict.test.RandomUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TitleTest {

    @Test
    void testFrom_StringIsNull_ThrowError() {
        var actualEx = assertThrows(CodeableException.class, () -> Title.from(null));

        assertTrue(actualEx.equalsByCode(TitleError.titleIsEmptyErrorCode));
    }

    @Test
    void testFrom_StringIsEmpty_ThrowError() {
        var actualEx = assertThrows(CodeableException.class, () -> Title.from(""));

        assertTrue(actualEx.equalsByCode(TitleError.titleIsEmptyErrorCode));
    }

    @Test
    void testFrom_StringIsTooLong_ThrowError() {
        var longTitle = RandomUtil.generateStr(Title.MAX_TITLE_LENGTH +1);

        var actualEx = assertThrows(CodeableException.class, () -> Title.from(longTitle));

        assertTrue(actualEx.equalsByCode(TitleError.titleTooLongErrorCode));
    }

    @Test
    void tesFrom_ValidParam_NoError() {
        var expectedTitleStr = RandomUtil.generateDefaultStr();

        var title1 = Title.from(expectedTitleStr);
        var title2 = Title.from(expectedTitleStr);

        assertEquals(title1, title2);
        assertEquals(title1.toString(), title2.toString());
    }
}
