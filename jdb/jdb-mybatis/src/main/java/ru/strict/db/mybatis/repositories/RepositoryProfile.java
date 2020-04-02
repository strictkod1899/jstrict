package ru.strict.db.mybatis.repositories;

import org.apache.ibatis.session.SqlSession;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.models.Profile;
import ru.strict.db.core.repositories.interfaces.IRepositoryProfile;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlProfile;
import ru.strict.utils.UtilClass;

import java.util.List;

public class RepositoryProfile<ID>
        extends RepositoryMybatisBase<ID, Profile<ID>, MapperSqlProfile<ID, Profile<ID>>>
        implements IRepositoryProfile<ID, Profile<ID>> {

    private static final String[] COLUMNS_NAME = DefaultColumns.PROFILE.columns();

    public RepositoryProfile(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super(DefaultTable.PROFILE.table(),
                COLUMNS_NAME,
                connectionSource,
                UtilClass.castClass(MapperSqlProfile.class),
                generateIdType);
    }

    @Override
    public List<Profile<ID>> readBySurname(String name, String surname) {
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlProfile<ID, Profile<ID>> mapperMybatis = session.getMapper(getMybatisMapperClass());
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
    public List<Profile<ID>> readByUserId(ID userId) {
        if(userId == null){
            throw new IllegalArgumentException("userId for read is NULL");
        }

        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlProfile<ID, Profile<ID>> mapperMybatis = session.getMapper(getMybatisMapperClass());
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
