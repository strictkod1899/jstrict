package ru.strict.test;

import lombok.experimental.UtilityClass;
import ru.strict.exception.CodeableException;
import ru.strict.exception.ErrorsException;
import ru.strict.util.ReflectionUtil;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@UtilityClass
public class AssertUtil {

    public static void assertExceptionByCodes(RuntimeException ex, List<String> expectedCodes) {
        assertInstanceOf(ErrorsException.class, ex);

        var errorsException = (ErrorsException) ex;
        var actualExceptions = errorsException.toList();

        assertEquals(expectedCodes.size(), actualExceptions.size());

        var actualExceptionsMap = new HashMap<String, CodeableException>();
        actualExceptions.forEach(actualEx -> actualExceptionsMap.put(actualEx.getCode(), actualEx));

        for (var expectedCode : expectedCodes) {
            assertTrue(actualExceptionsMap.containsKey(expectedCode));
        }
    }

    public static <T> void assertFieldsNotNull(T obj) {
        var reflectFields = ReflectionUtil.getAllFields(obj.getClass());

        reflectFields.forEach(reflectField -> {
            var fieldValue = ReflectionUtil.getFieldValue(obj, reflectField.getName());
            assertNotNull(fieldValue, String.format("Field '%s' is null", reflectField.getName()));
        });
    }
}
