package ru.strict.db.mybatis.repositories;

import org.apache.ibatis.session.SqlSession;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.requests.DbTable;
import ru.strict.db.core.repositories.RepositoryBase;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.mybatis.components.IntegerId;
import ru.strict.db.mybatis.components.LongId;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlExtension;
import ru.strict.models.ModelBase;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public abstract class RepositoryMybatisBase
        <ID, T extends ModelBase<ID>, MAPPER extends MapperSqlExtension<ID, T>>
        extends RepositoryBase<ID, SqlSession, CreateConnectionByMybatis, T> {

    private Class<MAPPER> mybatisMapper;

    public RepositoryMybatisBase(DbTable table,
                                 String[] columnsName,
                                 CreateConnectionByMybatis connectionSource,
                                 Class<MAPPER> mybatisMapper,
                                 GenerateIdType generateIdType) {
        super(table, columnsName, connectionSource, generateIdType);
        if(mybatisMapper == null){
            throw new IllegalArgumentException("mybatisMapper is NULL");
        }
        this.mybatisMapper = mybatisMapper;
    }

    @Override
    public ID create(T model) {
        SqlSession session = null;
        GenerateIdType usingGenerateIdType = getGenerateIdType();

        ID generatedId = null;
        if(model.getId() != null){
            usingGenerateIdType = GenerateIdType.NONE;
            generatedId = model.getId();
        }

        switch(usingGenerateIdType){
            case INT:
                try {
                    session = createConnection();
                    MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
                    IntegerId generateId = new IntegerId();
                    mapperMybatis.createGenerateId(model, generateId);
                    session.commit();
                    generatedId = (ID) generateId.getValue();
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
                break;
            case LONG:
                try {
                    session = createConnection();
                    MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
                    LongId generateId = new LongId();
                    mapperMybatis.createGenerateId(model, generateId);
                    session.commit();
                    generatedId = (ID) generateId.getValue();
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
                break;
            case UUID:
                try {
                    generatedId = (ID)UUID.randomUUID();
                    model.setId(generatedId);

                    session = createConnection();
                    MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
                    mapperMybatis.create(model);
                    session.commit();

                    model.setId(null);
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
                break;
            case NONE:
                try {
                    session = createConnection();
                    MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
                    mapperMybatis.create(model);
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
                break;
            default:
                throw new IllegalArgumentException("Type for generate id is not determine. Entity was not created into");
        };

        return generatedId;
    }

    @Override
    public T read(ID id) {
        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.read(id);
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
    public List<T> readAll(DbRequests requests) {
        if(requests != null){
            throw new UnsupportedOperationException("This repository not supported an query by DbRequests");
        }
        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.readAll();
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
    public void update(T model) {
        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
            mapperMybatis.update(model);
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
    }

    @Override
    public void delete(ID id) {
        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
            mapperMybatis.delete(id);
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
    }

    @Override
    public T readFill(ID id) {
        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.readFill(id);
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
    public List<T> readAllFill(DbRequests requests) {
        if(requests != null){
            throw new UnsupportedOperationException("This repository not supported a query by DbRequests");
        }
        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.readAllFill();
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
    public int readCount(DbRequests requests) {
        if(requests != null){
            throw new UnsupportedOperationException("This repository not supported a query by DbRequests");
        }
        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.readCount();
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
    public boolean isRowExists(ID id) {
        if(id == null){
            return false;
        }

        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.readCountById(id) > 0 ? true : false;
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
    public void executeSql(String sql) {
        SqlSession session = null;
        try {
            session = createConnection();
            session.getConnection().prepareStatement(sql).executeUpdate();
            session.commit();
        }catch(Exception ex){
            if(session != null){
                session.rollback();
            }
            throw new RuntimeException(ex);
        }finally {
            if(session != null) {
                session.close();
            }
        }
    }

    @Override
    protected T fill(T model) {
        throw new UnsupportedOperationException("Implementation to this method not required");
    }

    public Class<MAPPER> getMybatisMapperClass() {
        return mybatisMapper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RepositoryMybatisBase<?, ?, ?> that = (RepositoryMybatisBase<?, ?, ?>) o;
        return Objects.equals(mybatisMapper, that.mybatisMapper);
    }

    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), mybatisMapper);
    }
}
