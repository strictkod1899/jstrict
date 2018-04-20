package ru.strict.db.mappers;

import ru.strict.db.dto.StrictDtoBase;
import ru.strict.db.entities.StrictEntityBase;

public interface StrictMapperAny<E extends StrictEntityBase, DTO extends StrictDtoBase> {
    public E map(DTO dto);
    public DTO map(E entity);
}
