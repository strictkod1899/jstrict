package ru.strict.db.hibernate.mappers.dto;

import ru.strict.db.core.dto.DtoFileStorage;
import ru.strict.db.core.dto.DtoFileStoragePath;
import ru.strict.db.hibernate.entities.EntityFileStorage;
import ru.strict.db.core.mappers.dto.MapperDtoBase;

/**
 * Двухсторонний маппинг объектов типа EntityFileStorage и DtoFileStorage
 */
public class MapperDtoFileStorage<ID> extends MapperDtoBase<ID, EntityFileStorage<ID>, DtoFileStorage<ID>> {

    private MapperDtoBase<ID, EntityFileStorage<ID>, DtoFileStoragePath<ID>> mapperBase;

    public MapperDtoFileStorage(MapperDtoBase<ID, EntityFileStorage<ID>, DtoFileStoragePath<ID>> mapperBase) {
        super();
        this.mapperBase = mapperBase;
    }

    @Override
    protected EntityFileStorage<ID> implementMap(DtoFileStorage<ID> dto) {
        EntityFileStorage<ID> baseEntity = mapperBase.map(dto);

        EntityFileStorage<ID> entity = new EntityFileStorage();
        entity.setId(baseEntity.getId());
        entity.setFilename(baseEntity.getFilename());
        entity.setExtension(baseEntity.getExtension());
        entity.setDisplayName(baseEntity.getDisplayName());
        entity.setCreateDate(baseEntity.getCreateDate());
        entity.setType(baseEntity.getType());
        entity.setStatus(baseEntity.getStatus());
        entity.setFilePath(baseEntity.getFilePath());
        entity.setContent(dto.getContent());
        return entity;
    }

    @Override
    protected DtoFileStorage<ID> implementMap(EntityFileStorage<ID> entity) {
        DtoFileStoragePath<ID> baseDto = mapperBase.map(entity);

        DtoFileStorage<ID> dto = new DtoFileStorage();
        dto.setId(baseDto.getId());
        dto.setFilename(baseDto.getFilename());
        entity.setExtension(baseDto.getExtension());
        entity.setDisplayName(baseDto.getDisplayName());
        dto.setCreateDate(baseDto.getCreateDate());
        dto.setType(baseDto.getType());
        dto.setStatus(baseDto.getStatus());
        dto.setFilePath(baseDto.getFilePath());
        dto.setContent(entity.getContent());
        return dto;
    }
}
