package ru.strict.test;

import lombok.experimental.UtilityClass;
import org.mockito.stubbing.Answer;

@UtilityClass
public class MockitoUtil {

    public static final Answer<Object> STRICT_BEHAVIOUR = (invocationOnMock) -> {
        throw new RuntimeException(String.format("Behaviour for mock method = '%s' didn't set", invocationOnMock.getMethod().getName()));
    };
}
