package ru.strict.db.mybatis.repositories;

import org.apache.ibatis.session.SqlSession;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.db.core.repositories.interfaces.IRepositoryUser;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlUser;
import ru.strict.models.DetailsUser;
import ru.strict.utils.UtilClass;
import ru.strict.validate.ValidateBaseValue;

public class RepositoryUser<ID>
        extends RepositoryMybatisNamed<ID, DetailsUser<ID>, MapperSqlUser<ID>>
        implements IRepositoryUser<ID, DetailsUser<ID>> {

    private static final String[] COLUMNS_NAME = DefaultColumns.USER.columns();

    public RepositoryUser(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super(DefaultTable.USER.table(),
                COLUMNS_NAME,
                connectionSource,
                UtilClass.castClass(MapperSqlUser.class),
                generateIdType);
    }

    @Override
    public DetailsUser<ID> readByEmail(String email) {
        if(ValidateBaseValue.isEmptyOrNull(email)){
            throw new IllegalArgumentException("email for read is NULL");
        }
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlUser<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.readByEmail(email);
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
    public boolean isDeleted(ID id) {
        if(id == null){
            throw new IllegalArgumentException("id for read is NULL");
        }

        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlUser<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.isDeleted(id);
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
    public boolean isBlocked(ID id) {
        if(id == null){
            throw new IllegalArgumentException("id for read is NULL");
        }

        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlUser<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.isBlocked(id);
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
    public boolean isConfirmEmail(ID id) {
        if(id == null){
            throw new IllegalArgumentException("id for read is NULL");
        }

        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlUser<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.isConfirmEmail(id);
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
    public String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
