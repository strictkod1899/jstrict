package ru.strict.db.mybatis.repositories;

import org.apache.ibatis.session.SqlSession;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.models.City;
import ru.strict.db.core.repositories.interfaces.IRepositoryCity;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlCity;
import ru.strict.utils.UtilClass;

import java.util.List;

public class RepositoryCity<ID>
        extends RepositoryMybatisNamed<ID, City<ID>, MapperSqlCity<ID>>
        implements IRepositoryCity<ID> {

    private static final String[] COLUMNS_NAME = DefaultColumns.CITY.columns();

    public RepositoryCity(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super(DefaultTable.CITY.table(),
                COLUMNS_NAME,
                connectionSource,
                UtilClass.castClass(MapperSqlCity.class),
                generateIdType);
    }

    @Override
    public List<City<ID>> readByCountryId(ID countryId) {
        if(countryId == null){
            throw new IllegalArgumentException("countryId for read is NULL");
        }
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlCity<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.readByCountryId(countryId);
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
