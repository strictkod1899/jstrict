package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.mappers.MapperBase;
import ru.strict.models.DtoBase;

/**
 * Базовая реализация маппера из entity в dto
 * @param <S> Entity-класс
 * @param <T> Dto-класс
 */
public abstract class MapperDtoBase<ID, S, T extends DtoBase<ID>>
        extends MapperBase<S, T> {
}
