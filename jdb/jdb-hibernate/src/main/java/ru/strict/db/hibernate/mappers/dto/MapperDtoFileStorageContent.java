package ru.strict.db.hibernate.mappers.dto;

import ru.strict.db.core.dto.DtoFileStorageBase;
import ru.strict.db.core.dto.DtoFileStorageContent;
import ru.strict.db.hibernate.entities.EntityFileStorage;
import ru.strict.db.core.mappers.dto.MapperDtoBase;

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
        entity.setCreateDate(baseEntity.getCreateDate());
        entity.setType(baseEntity.getType());
        entity.setContent(dto.getContent());
        return entity;
    }

    @Override
    protected DtoFileStorageContent<ID> implementMap(EntityFileStorage<ID> entity) {
        DtoFileStorageBase<ID> baseDto = mapperBase.map(entity);

        DtoFileStorageContent<ID> dto = new DtoFileStorageContent();
        dto.setId(baseDto.getId());
        dto.setFilename(baseDto.getFilename());
        dto.setCreateDate(baseDto.getCreateDate());
        dto.setType(baseDto.getType());
        dto.setContent(entity.getContent());
        return dto;
    }
}
