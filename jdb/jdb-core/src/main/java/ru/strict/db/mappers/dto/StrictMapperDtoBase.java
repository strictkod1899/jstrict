package ru.strict.db.mappers.dto;

import ru.strict.db.dto.StrictDtoBase;
import ru.strict.db.entities.StrictEntityBase;
import ru.strict.db.mappers.StrictMapperBase;

/**
 * Базовая реализация маппера из entity в dto
 * @param <S> Entity-класс
 * @param <T> Dto-класс
 */
public abstract class StrictMapperDtoBase<S extends StrictEntityBase, T extends StrictDtoBase>
        extends StrictMapperBase<S, T> {
}
