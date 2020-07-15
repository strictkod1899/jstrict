package ru.strict.patterns;

/**
 * Pattern 'Factory'. Описание базовой функцональности фабрики
 *
 * @param <RESULT> Тип создаваемого экземпляра
 * @param <PARAM> Тип передаваемого параметра
 */
public interface IFactory<RESULT, PARAM> {

    /**
     * Создать экземпляр класса
     */
    RESULT instance(PARAM parameter);
}
