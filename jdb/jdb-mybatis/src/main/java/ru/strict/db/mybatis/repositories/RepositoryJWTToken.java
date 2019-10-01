package ru.strict.db.mybatis.repositories;

import org.apache.ibatis.session.SqlSession;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.models.JWTToken;
import ru.strict.db.core.repositories.interfaces.IRepositoryJWTToken;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlJWTToken;
import ru.strict.utils.UtilClass;
import ru.strict.validate.ValidateBaseValue;

import java.util.List;

public class RepositoryJWTToken<ID>
        extends RepositoryMybatisBase<ID, JWTToken<ID>, MapperSqlJWTToken<ID>>
        implements IRepositoryJWTToken<ID> {

    private static final String[] COLUMNS_NAME = DefaultColumns.JWT_TOKEN.columns();

    public RepositoryJWTToken(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super(DefaultTable.JWT_TOKEN.table(),
                COLUMNS_NAME,
                connectionSource,
                UtilClass.castClass(MapperSqlJWTToken.class),
                generateIdType);
    }

    @Override
    public JWTToken<ID> readByAccessToken(String accessToken) {
        if(ValidateBaseValue.isEmptyOrNull(accessToken)){
            throw new IllegalArgumentException("accessToken for read is NULL");
        }

        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlJWTToken<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.readByAccessToken(accessToken);
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
    public JWTToken<ID> readByRefreshToken(String refreshToken) {
        if(ValidateBaseValue.isEmptyOrNull(refreshToken)){
            throw new IllegalArgumentException("refreshToken for read is NULL");
        }

        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlJWTToken<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.readByRefreshToken(refreshToken);
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
    public List<JWTToken<ID>> readByUserId(ID userId) {
        if(userId == null){
            throw new IllegalArgumentException("userId for read is NULL");
        }

        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlJWTToken<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
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
