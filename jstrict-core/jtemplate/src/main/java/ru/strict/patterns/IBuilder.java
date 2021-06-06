package ru.strict.patterns;

/**
 * Pattern 'Builder'. Описание базовой функцональности строителя
 *
 * @param <RESULT> Тип создаваемого экземпляра
 */
public interface IBuilder<RESULT> {

    /**
     * Создать экземпляр класса
     */
    RESULT build();
}
