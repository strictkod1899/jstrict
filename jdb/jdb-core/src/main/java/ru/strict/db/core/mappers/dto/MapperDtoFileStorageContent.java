package ru.strict.db.core.mappers.dto;

import ru.strict.models.FileStorageBase;
import ru.strict.models.FileStorageContent;
import ru.strict.db.core.entities.EntityFileStorage;

/**
 * Двухсторонний маппинг объектов типа EntityFileStorage и DtoFileStorageContent
 */
public class MapperDtoFileStorageContent<ID> extends MapperDtoBase<ID, EntityFileStorage<ID>, FileStorageContent<ID>> {

    private MapperDtoBase<ID, EntityFileStorage<ID>, FileStorageBase<ID>> mapperBase;

    public MapperDtoFileStorageContent(MapperDtoBase<ID, EntityFileStorage<ID>, FileStorageBase<ID>> mapperBase) {
        super();
        this.mapperBase = mapperBase;
    }

    @Override
    protected EntityFileStorage<ID> implementMap(FileStorageContent<ID> dto) {
        EntityFileStorage<ID> baseEntity = mapperBase.map(dto);

        EntityFileStorage<ID> entity = new EntityFileStorage();
        entity.setId(baseEntity.getId());
        entity.setFilename(baseEntity.getFilename());
        entity.setExtension(baseEntity.getExtension());
        entity.setDisplayName(baseEntity.getDisplayName());
        entity.setCreateDate(baseEntity.getCreateDate());
        entity.setType(baseEntity.getType());
        entity.setStatus(baseEntity.getStatus());
        entity.setContent(dto.getContent());
        return entity;
    }

    @Override
    protected FileStorageContent<ID> implementMap(EntityFileStorage<ID> entity) {
        FileStorageBase<ID> baseDto = mapperBase.map(entity);

        FileStorageContent<ID> dto = new FileStorageContent();
        dto.setId(baseDto.getId());
        dto.setFilename(baseDto.getFilename());
        dto.setExtension(baseDto.getExtension());
        dto.setDisplayName(baseDto.getDisplayName());
        dto.setCreateDate(baseDto.getCreateDate());
        dto.setType(baseDto.getType());
        dto.setStatus(baseDto.getStatus());
        dto.setContent(entity.getContent());
        return dto;
    }
}
