package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.dto.DtoFileStorageBase;
import ru.strict.db.core.dto.DtoFileStoragePath;
import ru.strict.db.core.dto.DtoFileStoragePath;
import ru.strict.db.core.entities.EntityFileStorage;

/**
 * Двухсторонний маппинг объектов типа EntityFileStorage и DtoFileStoragePath
 */
public class MapperDtoFileStoragePath<ID> extends MapperDtoBase<ID, EntityFileStorage<ID>, DtoFileStoragePath<ID>> {

    private MapperDtoBase<ID, EntityFileStorage<ID>, DtoFileStorageBase<ID>> mapperBase;

    public MapperDtoFileStoragePath(MapperDtoBase<ID, EntityFileStorage<ID>, DtoFileStorageBase<ID>> mapperBase) {
        super();
        this.mapperBase = mapperBase;
    }

    @Override
    protected EntityFileStorage<ID> implementMap(DtoFileStoragePath<ID> dto) {
        EntityFileStorage<ID> baseEntity = mapperBase.map(dto);

        EntityFileStorage<ID> entity = new EntityFileStorage();
        entity.setId(baseEntity.getId());
        entity.setFilename(baseEntity.getFilename());
        entity.setCreateDate(baseEntity.getCreateDate());
        entity.setType(baseEntity.getType());
        entity.setFilePath(dto.getFilePath());
        return entity;
    }

    @Override
    protected DtoFileStoragePath<ID> implementMap(EntityFileStorage<ID> entity) {
        DtoFileStorageBase<ID> baseDto = mapperBase.map(entity);

        DtoFileStoragePath<ID> dto = new DtoFileStoragePath();
        dto.setId(baseDto.getId());
        dto.setFilename(baseDto.getFilename());
        dto.setCreateDate(baseDto.getCreateDate());
        dto.setType(baseDto.getType());
        dto.setFilePath(entity.getFilePath());
        return dto;
    }
}
