package ru.strict.test;

import lombok.experimental.UtilityClass;
import ru.strict.exception.CodeableException;

@UtilityClass
public class ExceptionStub {

    public RuntimeException getException() {
        var randomErrorCode = RandomUtil.generateDefaultStr();
        var randomErrorText = RandomUtil.generateDefaultStr();
        return new CodeableException(randomErrorCode, randomErrorText);
    }
}
