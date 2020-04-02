package ru.strict.patterns;

/**
 * Описание базового маппера
 * @param <S> Источник для преобразования
 * @param <T> Цель преобразования
 */
public interface IMapper<S, T extends MapTarget> {
    S map(T t);
    T map(S s);
}
