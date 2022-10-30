package ru.strict.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CodeableException extends RuntimeException {
    private final String code;
    private final String text;
}
