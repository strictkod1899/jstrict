package ru.strict.patterns.mapper;

/**
 * Двухсторонний маппер
 *
 * @param <S> Источник для преобразования
 * @param <T> Цель преобразования
 */
public interface IBiMapper<S, T extends MapTarget> extends IMapper<S, T> {
    S map(T t);
}
