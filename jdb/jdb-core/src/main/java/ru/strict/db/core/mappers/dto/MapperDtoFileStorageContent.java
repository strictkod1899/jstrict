package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.dto.DtoFileStorageBase;
import ru.strict.db.core.dto.DtoFileStorageContent;
import ru.strict.db.core.dto.DtoFileStorageContent;
import ru.strict.db.core.entities.EntityFileStorage;

/**
 * Двухсторонний маппинг объектов типа EntityFileStorage и DtoFileStorageContent
 */
public class MapperDtoFileStorageContent<ID> extends MapperDtoBase<ID, EntityFileStorage<ID>, DtoFileStorageContent<ID>> {

    private MapperDtoBase<ID, EntityFileStorage<ID>, DtoFileStorageBase<ID>> mapperBase;

    public MapperDtoFileStorageContent(MapperDtoBase<ID, EntityFileStorage<ID>, DtoFileStorageBase<ID>> mapperBase) {
        super();
        this.mapperBase = mapperBase;
    }

    @Override
    protected EntityFileStorage<ID> implementMap(DtoFileStorageContent<ID> dto) {
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
    protected DtoFileStorageContent<ID> implementMap(EntityFileStorage<ID> entity) {
        DtoFileStorageBase<ID> baseDto = mapperBase.map(entity);

        DtoFileStorageContent<ID> dto = new DtoFileStorageContent();
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
