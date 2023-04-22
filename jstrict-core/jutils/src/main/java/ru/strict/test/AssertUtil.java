package ru.strict.test;

import lombok.experimental.UtilityClass;
import ru.strict.exception.CodeableException;
import ru.strict.exception.Errors;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@UtilityClass
public class AssertUtil {

    public static void assertExceptionByCodes(RuntimeException ex, List<String> expectedCodes) {
        assertInstanceOf(Errors.ErrorsException.class, ex);

        var errorsException = (Errors.ErrorsException) ex;
        var actualExceptions = errorsException.toList();

        assertEquals(expectedCodes.size(), actualExceptions.size());

        var actualExceptionsMap = new HashMap<String, CodeableException>();
        actualExceptions.forEach(actualEx -> actualExceptionsMap.put(actualEx.getCode(), actualEx));

        for (var expectedCode : expectedCodes) {
            assertTrue(actualExceptionsMap.containsKey(expectedCode));
        }
    }
}
