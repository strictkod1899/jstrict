package ru.strict.db.mybatis.repositories;

import org.apache.ibatis.session.SqlSession;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.db.core.repositories.interfaces.IRepositoryProfileDetails;
import ru.strict.models.DetailsProfile;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlProfileDetails;
import ru.strict.utils.UtilClass;

import java.util.List;

public class RepositoryProfileDetails<ID>
        extends RepositoryMybatisBase<ID, DetailsProfile<ID>, MapperSqlProfileDetails<ID>>
        implements IRepositoryProfileDetails<ID, DetailsProfile<ID>> {

    private static final String[] COLUMNS_NAME = DefaultColumns.PROFILE_DETAILS.columns();

    public RepositoryProfileDetails(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super(DefaultTable.PROFILE.table(),
                COLUMNS_NAME,
                connectionSource,
                UtilClass.castClass(MapperSqlProfileDetails.class),
                generateIdType);
    }

    @Override
    public List<DetailsProfile<ID>> readByFio(String name, String surname, String middlename) {
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlProfileDetails<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.readByFio(name, surname, middlename);
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
    public List<DetailsProfile<ID>> readBySurname(String name, String surname) {
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlProfileDetails<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.readBySurname(name, surname);
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
    public List<DetailsProfile<ID>> readByUserId(ID userId) {
        if(userId == null){
            throw new IllegalArgumentException("userId for read is NULL");
        }
        List<DetailsProfile<ID>> result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlProfileDetails<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.readByUserId(userId);
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
}
