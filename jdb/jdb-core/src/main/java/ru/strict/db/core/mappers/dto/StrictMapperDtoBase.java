package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.dto.StrictDtoBase;
import ru.strict.db.core.entities.StrictEntityBase;
import ru.strict.db.core.mappers.StrictMapperBase;

/**
 * Базовая реализация маппера из entity в dto
 * @param <S> Entity-класс
 * @param <T> Dto-класс
 */
public abstract class StrictMapperDtoBase<S extends StrictEntityBase, T extends StrictDtoBase>
        extends StrictMapperBase<S, T> {
}
