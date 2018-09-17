package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.dto.DtoBase;
import ru.strict.db.core.mappers.MapperBase;

/**
 * Базовая реализация маппера из entity в dto
 * @param <S> Entity-класс
 * @param <T> Dto-класс
 */
public abstract class MapperDtoBase<ID, S, T extends DtoBase<ID>>
        extends MapperBase<S, T> {
}
