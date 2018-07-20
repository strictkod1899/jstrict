package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.DtoCity;
import ru.strict.db.core.dto.DtoProfileInfo;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.entities.EntityProfileInfo;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.jdbc.mappers.sql.MapperSqlProfileInfo;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Репозиторий таблицы "profileinfo".
 * Определяет столбцы: "name", "surname", "middlename", "userx_id", "datebirth", "phone", "city_id"
 * @param <ID> Тип идентификатора
 */
public class RepositoryProfileInfo<ID, SOURCE extends ICreateConnection>
        extends RepositoryJdbcBase<ID, SOURCE, EntityProfileInfo, DtoProfileInfo> {

    private static final String[] COLUMNS_NAME = new String[] {"name", "surname", "middlename", "userx_id", "datebirth",
            "phone", "city_id"};

    public RepositoryProfileInfo(SOURCE connectionSource, GenerateIdType isGenerateId) {
        super("profile", COLUMNS_NAME, connectionSource,
                new MapperDtoFactory().instance(MapperDtoType.PROFILE_INFO),
                new MapperSqlProfileInfo(COLUMNS_NAME),
                isGenerateId);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(EntityProfileInfo entity){
        Map<Integer, Object> valuesByColumn = new LinkedHashMap();
        valuesByColumn.put(0, entity.getName());
        valuesByColumn.put(1, entity.getSurname());
        valuesByColumn.put(2, entity.getMiddlename());
        valuesByColumn.put(3, entity.getUserId());
        valuesByColumn.put(4, entity.getDateBirth());
        valuesByColumn.put(5, entity.getPhone());
        valuesByColumn.put(6, entity.getCityId());
        return valuesByColumn;
    }

    @Override
    protected DtoProfileInfo fill(DtoProfileInfo dto){
        IRepository<ID, DtoUser> rUser =
                new RepositoryUser<>(getConnectionSource(),
                        new MapperDtoFactory().instance(MapperDtoType.USER),
                        GenerateIdType.NONE);
        dto.setUser(rUser.read((ID) dto.getUserId()));

        IRepository<ID, DtoCity> repositoryCity = new RepositoryCity(getConnectionSource(), GenerateIdType.NONE);
        dto.setCity(repositoryCity.read((ID) dto.getCityId()));
        return dto;
    }
}
