package ru.strict.db.mappers;

import ru.strict.db.dto.StrictDtoBase;
import ru.strict.db.entities.StrictEntityBase;

public abstract class StrictBaseMapper<E extends StrictEntityBase, DTO extends StrictDtoBase>
        implements StrictMapperAny<E, DTO> {
    protected abstract E implementMap(DTO dto);
    protected abstract DTO implementMap(E entity);

    @Override
    public E map(DTO dto){
        return dto==null ? null : implementMap(dto);
    }

    @Override
    public DTO map(E entity){
        return entity==null ? null : implementMap(entity);
    }
}
