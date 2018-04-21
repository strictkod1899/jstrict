package ru.strict.db.mappers;

public interface StrictMapperAny<E extends MapSource, DTO extends MapTarget> {
    E map(DTO dto);
    DTO map(E entity);
}
