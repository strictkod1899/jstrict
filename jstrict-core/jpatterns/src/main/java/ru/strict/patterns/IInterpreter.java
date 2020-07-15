package ru.strict.patterns;

/**
 * Pattern 'Interpreter'. Описание базовой функциональности интерпретатора
 */
public interface IInterpreter<RESULT, CONTEXT> {
    /**
     * Выполнить интерпретацию
     *
     * @param context Объект контекста
     */
    RESULT interpret(CONTEXT context);
}
