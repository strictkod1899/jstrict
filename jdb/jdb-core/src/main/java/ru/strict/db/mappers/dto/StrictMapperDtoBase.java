package ru.strict.db.mappers.dto;

import ru.strict.db.dto.StrictDtoBase;
import ru.strict.db.entities.StrictEntityBase;
import ru.strict.db.mappers.StrictMapperBase;

public abstract class StrictMapperDtoBase<E extends StrictEntityBase, DTO extends StrictDtoBase>
        extends StrictMapperBase<E, DTO> {
}
