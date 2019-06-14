package ru.strict.db.core.mappers.dto;

import ru.strict.models.FileStorage;
import ru.strict.models.FileStoragePath;
import ru.strict.db.core.entities.EntityFileStorage;

/**
 * Двухсторонний маппинг объектов типа EntityFileStorage и DtoFileStorage
 */
public class MapperDtoFileStorage<ID> extends MapperDtoBase<ID, EntityFileStorage<ID>, FileStorage<ID>> {

    private MapperDtoBase<ID, EntityFileStorage<ID>, FileStoragePath<ID>> mapperBase;

    public MapperDtoFileStorage(MapperDtoBase<ID, EntityFileStorage<ID>, FileStoragePath<ID>> mapperBase) {
        super();
        this.mapperBase = mapperBase;
    }

    @Override
    protected EntityFileStorage<ID> implementMap(FileStorage<ID> dto) {
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
    protected FileStorage<ID> implementMap(EntityFileStorage<ID> entity) {
        FileStoragePath<ID> baseDto = mapperBase.map(entity);

        FileStorage<ID> dto = new FileStorage();
        dto.setId(baseDto.getId());
        dto.setFilename(baseDto.getFilename());
        dto.setExtension(baseDto.getExtension());
        dto.setDisplayName(baseDto.getDisplayName());
        dto.setCreateDate(baseDto.getCreateDate());
        dto.setType(baseDto.getType());
        dto.setStatus(baseDto.getStatus());
        dto.setFilePath(baseDto.getFilePath());
        dto.setContent(entity.getContent());
        return dto;
    }
}
