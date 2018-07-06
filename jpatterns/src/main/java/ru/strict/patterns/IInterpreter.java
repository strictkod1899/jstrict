package ru.strict.patterns;

/**
 * Паттерн интерпретатор
 */
public interface IInterpreter<RESULT, CONTEXT> {
    RESULT interpret(CONTEXT context);
}
