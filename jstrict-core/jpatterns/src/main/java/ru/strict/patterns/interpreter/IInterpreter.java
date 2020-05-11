package ru.strict.patterns.interpreter;

/**
 * Pattern 'Interpreter'. Описание базовой функциональности интерпретатора
 */
public interface IInterpreter<RESULT, CONTEXT> {
    /**
     * Выполнить интерпретацию
     *
     * @param context Объект контекста
     * @return
     */
    RESULT interpret(CONTEXT context);
}
