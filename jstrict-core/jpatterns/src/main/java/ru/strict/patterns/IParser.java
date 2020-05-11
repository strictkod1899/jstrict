package ru.strict.patterns;

/**
 * Определение исполнителя парсинга
 *
 * @param <INPUT> Элемент с входными данными
 * @param <RESULT> Выходной элемент
 */
public interface IParser<INPUT, RESULT> {
    RESULT parse(INPUT input) throws Exception;
}
