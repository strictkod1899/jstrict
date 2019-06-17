package ru.strict.db.mybatis.repositories;

import org.apache.ibatis.session.SqlSession;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.requests.DbTable;
import ru.strict.models.JWTToken;
import ru.strict.db.core.entities.EntityJWTToken;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.interfaces.IRepositoryJWTToken;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlJWTToken;
import ru.strict.utils.UtilClass;
import ru.strict.validates.ValidateBaseValue;

import java.util.List;
import java.util.stream.Collectors;

public class RepositoryJWTToken<ID>
        extends RepositoryMybatisBase<ID, EntityJWTToken<ID>, JWTToken<ID>, MapperSqlJWTToken<ID>>
        implements IRepositoryJWTToken<ID> {

    private static final String[] COLUMNS_NAME = new String[] {"access_token", "refresh_token", "expire_time_access",
            "expire_time_refresh", "issued_at", "issuer", "subject", "not_before", "audience", "secret",
            "algorithm", "type", "userx_id"};

    public RepositoryJWTToken(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super(new DbTable("token", "tkn"),
                COLUMNS_NAME,
                connectionSource,
                UtilClass.<MapperSqlJWTToken<ID>>castClass(MapperSqlJWTToken.class),
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityJWTToken.class), UtilClass.castClass(JWTToken.class)),
                generateIdType);
    }

    @Override
    public JWTToken<ID> readByAccessToken(String accessToken) {
        if(ValidateBaseValue.isEmptyOrNull(accessToken)){
            throw new IllegalArgumentException("accessToken for read is NULL");
        }
        JWTToken<ID> result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlJWTToken<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            EntityJWTToken<ID> entity = mapperMybatis.readByAccessToken(accessToken);
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
    public JWTToken<ID> readByRefreshToken(String refreshToken) {
        if(ValidateBaseValue.isEmptyOrNull(refreshToken)){
            throw new IllegalArgumentException("refreshToken for read is NULL");
        }
        JWTToken<ID> result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlJWTToken<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            EntityJWTToken<ID> entity = mapperMybatis.readByRefreshToken(refreshToken);
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
    public List<JWTToken<ID>> readByUserId(ID userId) {
        if(userId == null){
            throw new IllegalArgumentException("userId for read is NULL");
        }
        List<JWTToken<ID>> result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlJWTToken<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            List<EntityJWTToken<ID>> entities = mapperMybatis.readByUserId(userId);
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
}
