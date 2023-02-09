package ru.strict.domainprimitive.title;

import org.junit.jupiter.api.Test;
import ru.strict.exception.CodeableException;
import ru.strict.test.FailTestException;
import ru.strict.test.RandomUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TitleTest {

    @Test
    void testFrom_StringIsNull_ThrowError() {
        try {
            Title.from(null);
        } catch (CodeableException ex) {
            assertTrue(CodeableException.equalsByCode(ex, TitleError.titleIsEmptyErrorCode));
            return;
        }

        throw new FailTestException();
    }

    @Test
    void testFrom_StringIsEmpty_ThrowError() {
        try {
            Title.from("");
        } catch (CodeableException ex) {
            assertTrue(CodeableException.equalsByCode(ex, TitleError.titleIsEmptyErrorCode));
            return;
        }

        throw new FailTestException();
    }

    @Test
    void testFrom_StringIsTooLong_ThrowError() {
        try {
            Title.from("testtitletesttitletesttitletesttitletesttitletesttitletesttitletesttitletesttitletesttitletesttitletesttitletesttitletesttitletesttitletesttitletesttitletesttitletesttitletesttitletesttitle");
        } catch (CodeableException ex) {
            assertTrue(CodeableException.equalsByCode(ex, TitleError.titleTooLongErrorCode));
            return;
        }

        throw new FailTestException();
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
