package ru.strict.patterns;

/**
 * Базовый маппер
 *
 * @param <S> Источник для преобразования
 * @param <T> Цель преобразования
 */
public interface IMapper<S, T> {
    T map(S s);
}
