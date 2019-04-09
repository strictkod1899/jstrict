package ru.strict.db.mybatis.repositories;

import org.apache.ibatis.session.SqlSession;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoCity;
import ru.strict.db.core.entities.EntityCity;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.interfaces.IRepositoryCity;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlCity;
import ru.strict.utils.UtilClass;

import java.util.List;
import java.util.stream.Collectors;

public class RepositoryCity<ID>
        extends RepositoryNamedBase<ID, EntityCity<ID>, DtoCity<ID>, MapperSqlCity<ID>>
        implements IRepositoryCity<ID> {

    private static final String[] COLUMNS_NAME = new String[] {"caption", "country_id"};

    public RepositoryCity(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super("city",
                COLUMNS_NAME,
                connectionSource,
                UtilClass.<MapperSqlCity<ID>>castClass(MapperSqlCity.class),
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityCity.class), UtilClass.castClass(DtoCity.class)),
                generateIdType);
    }

    @Override
    public List<DtoCity<ID>> readByCountryId(ID countryId) {
        if(countryId == null){
            throw new NullPointerException("countryId for read is NULL");
        }
        List<DtoCity<ID>> result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlCity<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            List<EntityCity<ID>> entities = mapperMybatis.readByCountryId(countryId);
            result = entities.stream().map(e -> getDtoMapper().map(e)).collect(Collectors.toList());
            session.commit();
        }catch(Exception ex){
            if(session != null){
                session.rollback();
            }
            throw ex;
        }finally {
            if(session != null) {
                session.close();
            }
        }

        return result;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }

    @Override
    public String getColumnWithName() {
        return COLUMNS_NAME[0];
    }
}
