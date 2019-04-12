package ru.strict.db.hibernate.mappers.dto;

import ru.strict.db.core.dto.DtoFileStorageBase;
import ru.strict.db.core.dto.DtoFileStorageContent;
import ru.strict.db.hibernate.entities.EntityFileStorage;
import ru.strict.db.core.mappers.dto.MapperDtoBase;

/**
 * Двухсторонний маппинг объектов типа EntityFileStorage и DtoFileStorageContent
 */
public class MapperDtoFileStorageContent extends MapperDtoBase<Long, EntityFileStorage, DtoFileStorageContent<Long>> {

    private MapperDtoBase<Long, EntityFileStorage, DtoFileStorageBase<Long>> mapperBase;

    public MapperDtoFileStorageContent(MapperDtoBase<Long, EntityFileStorage, DtoFileStorageBase<Long>> mapperBase) {
        super();
        this.mapperBase = mapperBase;
    }

    @Override
    protected EntityFileStorage implementMap(DtoFileStorageContent<Long> dto) {
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
    protected DtoFileStorageContent<Long> implementMap(EntityFileStorage entity) {
        DtoFileStorageBase<Long> baseDto = mapperBase.map(entity);

        DtoFileStorageContent<Long> dto = new DtoFileStorageContent();
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
