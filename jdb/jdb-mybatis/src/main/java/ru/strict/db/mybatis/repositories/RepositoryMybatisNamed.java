package ru.strict.db.mybatis.repositories;

import org.apache.ibatis.session.SqlSession;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.requests.DbTable;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlNamed;
import ru.strict.models.ModelBase;
import ru.strict.validate.ValidateBaseValue;

import java.util.List;

public abstract class RepositoryMybatisNamed
        <ID, T extends ModelBase<ID>, MAPPER extends MapperSqlNamed<ID, T>>
        extends RepositoryMybatisBase<ID, T, MAPPER>
        implements IRepositoryNamed<ID, T> {

    public RepositoryMybatisNamed(DbTable table,
                                  String[] columnsName,
                                  CreateConnectionByMybatis connectionSource,
                                  Class<MAPPER> mybatisMapper,
                                  GenerateIdType generateIdType) {
        super(table, columnsName, connectionSource, mybatisMapper, generateIdType);
    }

    @Override
    public T readByName(String caption) {
        if(ValidateBaseValue.isEmptyOrNull(caption)){
            throw new IllegalArgumentException("caption for read by name is NULL");
        }

        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.readByName(caption);
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
    public List<T> readAllByName(String caption) {
        if(ValidateBaseValue.isEmptyOrNull(caption)){
            throw new IllegalArgumentException("caption for read by name is NULL");
        }

        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.readAllByName(caption);
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
    public T readByNameFill(String caption) {
        if(ValidateBaseValue.isEmptyOrNull(caption)){
            throw new IllegalArgumentException("caption for read by name is NULL");
        }

        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.readByNameFill(caption);
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
    public List<T> readAllByNameFill(String caption) {
        if(ValidateBaseValue.isEmptyOrNull(caption)){
            throw new IllegalArgumentException("caption for read by name is NULL");
        }

        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.readAllByNameFill(caption);
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
}
