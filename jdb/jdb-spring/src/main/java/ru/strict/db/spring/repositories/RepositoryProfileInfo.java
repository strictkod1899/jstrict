package ru.strict.db.spring.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.dto.DtoProfileInfo;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.entities.EntityProfileInfo;
import ru.strict.db.core.mappers.dto.*;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.spring.mappers.sql.MapperSqlProfileInfo;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Репозиторий таблицы "profileinfo".
 * Определяет столбцы: "name", "surname", "middlename", "user_id", "datebirth", "phone", "city_id"
 * @param <ID> Тип идентификатора
 */
public class RepositoryProfileInfo<ID>
        extends RepositorySpringBase<ID, EntityProfileInfo, DtoProfileInfo> {

    private static final String[] COLUMNS_NAME = new String[] {"name", "surname", "middlename", "userx_id", "datebirth",
            "phone", "city_id"};

    public RepositoryProfileInfo(CreateConnectionByDataSource connectionSource, GenerateIdType isGenerateId) {
        super("profile", COLUMNS_NAME, connectionSource,
                new MapperDtoFactory().instance(MapperDtoType.PROFILE_INFO),
                new MapperSqlProfileInfo(COLUMNS_NAME),
                isGenerateId);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(DtoProfileInfo dto){
        Map<Integer, Object> valuesByColumn = new LinkedHashMap();
        valuesByColumn.put(0, dto.getName());
        valuesByColumn.put(1, dto.getSurname());
        valuesByColumn.put(2, dto.getMiddlename());
        valuesByColumn.put(3, dto.getUserId());
        valuesByColumn.put(4, dto.getDateBirth());
        valuesByColumn.put(5, dto.getPhone());
        valuesByColumn.put(6, dto.getCityId());
        return valuesByColumn;
    }

    @Override
    protected DtoProfileInfo fill(DtoProfileInfo dto){
        IRepository<ID, DtoUser> rUser =
                new RepositoryUser<>(getConnectionSource(),
                        new MapperDtoFactory().instance(MapperDtoType.USER),
                        GenerateIdType.NONE);
        dto.setUser(rUser.read((ID) dto.getUserId()));
        return dto;
    }
}
