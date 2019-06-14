package ru.strict.db.hibernate.mappers.dto;

import ru.strict.models.FileStorageBase;
import ru.strict.models.FileStorageContent;
import ru.strict.db.hibernate.entities.EntityFileStorage;
import ru.strict.db.core.mappers.dto.MapperDtoBase;

/**
 * Двухсторонний маппинг объектов типа EntityFileStorage и DtoFileStorageContent
 */
public class MapperDtoFileStorageContent extends MapperDtoBase<Long, EntityFileStorage, FileStorageContent<Long>> {

    private MapperDtoBase<Long, EntityFileStorage, FileStorageBase<Long>> mapperBase;

    public MapperDtoFileStorageContent(MapperDtoBase<Long, EntityFileStorage, FileStorageBase<Long>> mapperBase) {
        super();
        this.mapperBase = mapperBase;
    }

    @Override
    protected EntityFileStorage implementMap(FileStorageContent<Long> dto) {
        EntityFileStorage baseEntity = mapperBase.map(dto);

        EntityFileStorage entity = new EntityFileStorage();
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
    protected FileStorageContent<Long> implementMap(EntityFileStorage entity) {
        FileStorageBase<Long> baseDto = mapperBase.map(entity);

        FileStorageContent<Long> dto = new FileStorageContent();
        dto.setId(baseDto.getId());
        dto.setFilename(baseDto.getFilename());
        entity.setExtension(baseDto.getExtension());
        entity.setDisplayName(baseDto.getDisplayName());
        dto.setCreateDate(baseDto.getCreateDate());
        dto.setType(baseDto.getType());
        dto.setStatus(baseDto.getStatus());
        dto.setContent(entity.getContent());
        return dto;
    }
}
