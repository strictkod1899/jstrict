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
            new Title(null);
        } catch (CodeableException ex) {
            assertTrue(CodeableException.equalsByCode(ex, TitleError.titleIsEmptyErrorCode));
            return;
        }

        throw new FailTestException();
    }

    @Test
    void testFrom_StringIsEmpty_ThrowError() {
        try {
            new Title("");
        } catch (CodeableException ex) {
            assertTrue(CodeableException.equalsByCode(ex, TitleError.titleIsEmptyErrorCode));
            return;
        }

        throw new FailTestException();
    }

    @Test
    void testFrom_StringIsTooLong_ThrowError() {
        try {
            new Title("testtitletesttitletesttitletesttitletesttitletesttitletesttitletesttitletesttitletesttitletesttitletesttitletesttitletesttitletesttitletesttitletesttitletesttitletesttitletesttitletesttitle");
        } catch (CodeableException ex) {
            assertTrue(CodeableException.equalsByCode(ex, TitleError.titleTooLongErrorCode));
            return;
        }

        throw new FailTestException();
    }

    @Test
    void tesFrom_ValidParam_NoError() {
        var expectedTitleStr = RandomUtil.generateDefaultStr();

        var title1 = new Title(expectedTitleStr);
        var title2 = new Title(expectedTitleStr);

        assertEquals(title1, title2);
        assertEquals(title1.toString(), title2.toString());
    }
}
