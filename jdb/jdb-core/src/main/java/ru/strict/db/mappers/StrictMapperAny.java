package ru.strict.db.mappers;

/**
 * Описание базового маппера
 * @param <S> Источник для преобразования
 * @param <T> Цель преобразования
 */
public interface StrictMapperAny<S, T extends MapTarget> {
    S map(T t);
    T map(S entity);
}
