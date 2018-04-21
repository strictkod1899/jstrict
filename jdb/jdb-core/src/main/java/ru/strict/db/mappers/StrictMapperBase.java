package ru.strict.db.mappers;

public abstract class StrictMapperBase<E extends MapSource, DTO extends MapTarget>
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
