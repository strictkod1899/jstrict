package ru.strict.db.mybatis.repositories;

import org.apache.ibatis.session.SqlSession;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.*;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.interfaces.IRepositoryUser;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlUser;
import ru.strict.utils.UtilClass;
import ru.strict.validates.ValidateBaseValue;

public class RepositoryUser<ID, DTO extends DtoUserBase<ID>>
        extends RepositoryNamedBase<ID, EntityUser<ID>, DTO, MapperSqlUser<ID>>
        implements IRepositoryUser<ID, DTO> {

    private static final String[] COLUMNS_NAME = new String[] {"username", "passwordencode", "email",
            "is_blocked", "is_deleted", "is_confirm_email"};

    /**
     * Для этого конструктуора используется DtoUser
     */
    public RepositoryUser(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super("userx",
                COLUMNS_NAME,
                connectionSource,
                UtilClass.<MapperSqlUser<ID>>castClass(MapperSqlUser.class),
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityUser.class), UtilClass.castClass(DtoUser.class)),
                generateIdType);
    }

    public RepositoryUser(CreateConnectionByMybatis connectionSource,
                              MapperDtoBase<ID, EntityUser<ID>, DTO> dtoMapper,
                              GenerateIdType generateIdType) {
        super("userx",
                COLUMNS_NAME,
                connectionSource,
                UtilClass.castClass(MapperSqlUser.class),
                dtoMapper,
                generateIdType);
    }

    @Override
    public DTO readByEmail(String email) {
        if(ValidateBaseValue.isEmptyOrNull(email)){
            throw new IllegalArgumentException("email for read is NULL");
        }
        DTO result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlUser<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            EntityUser<ID> entity = mapperMybatis.readByEmail(email);
            result = getDtoMapper().map(entity);
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
    public boolean isDeleted(ID id) {
        if(id == null){
            throw new IllegalArgumentException("id for read is NULL");
        }
        boolean result = false;
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlUser<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            result = mapperMybatis.isDeleted(id);
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
    public boolean isBlocked(ID id) {
        if(id == null){
            throw new IllegalArgumentException("id for read is NULL");
        }
        boolean result = false;
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlUser<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            result = mapperMybatis.isBlocked(id);
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
    public boolean isConfirmEmail(ID id) {
        if(id == null){
            throw new IllegalArgumentException("id for read is NULL");
        }
        boolean result = false;
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlUser<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            result = mapperMybatis.isConfirmEmail(id);
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
    public String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
