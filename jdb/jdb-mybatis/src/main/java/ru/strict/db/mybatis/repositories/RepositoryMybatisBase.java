package ru.strict.db.mybatis.repositories;

import org.apache.ibatis.session.SqlSession;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoBase;
import ru.strict.db.core.entities.EntityBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.repositories.RepositoryBase;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlBase;
import ru.strict.db.mybatis.mappers.sql.MapperSqlExtension;

import java.util.List;
import java.util.stream.Collectors;

public abstract class RepositoryMybatisBase
        <ID, E extends EntityBase<ID>, DTO extends DtoBase<ID>, MAPPER extends MapperSqlExtension<ID, E>>
        extends RepositoryBase<ID, SqlSession, CreateConnectionByMybatis, E, DTO> {

    private Class<MAPPER> mybatisMapper;

    public RepositoryMybatisBase(String tableName,
                                 String[] columnsName,
                                 CreateConnectionByMybatis connectionSource,
                                 Class<MAPPER> mybatisMapper,
                                 MapperDtoBase<ID, E, DTO> dtoMapper,
                                 GenerateIdType generateIdType) {
        super(tableName, columnsName, connectionSource, dtoMapper, generateIdType);
        this.mybatisMapper = mybatisMapper;
    }

    @Override
    public int readCount(DbRequests requests) {
        int result = -1;
        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapper());
            String requestsString = requests != null ? requests.getSql() : "";
            result = mapperMybatis.readCount(requestsString);
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
    public DTO create(DTO dto) {
        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapper());
            E entity = getDtoMapper().map(dto);
            mapperMybatis.create(entity);
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
        return dto;
    }

    @Override
    public DTO read(ID id) {
        DTO result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapper());
            E entity = mapperMybatis.read(id);
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
    public List<DTO> readAll(DbRequests requests) {
        List<DTO> result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapper());
            String requestsString = requests != null ? requests.getSql() : "";
            List<E> entities = mapperMybatis.readAll(requestsString);
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
    public DTO update(DTO dto) {
        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapper());
            E entity = getDtoMapper().map(dto);
            mapperMybatis.update(entity);
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

        return dto;
    }

    @Override
    public void delete(ID id) {
        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapper());
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
    public DTO readFill(ID id) {
        DTO result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapper());
            E entity = mapperMybatis.readFill(id);
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
    public List<DTO> readAllFill(DbRequests requests) {
        List<DTO> result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapper());
            String requestsString = requests != null ? requests.getSql() : "";
            List<E> entities = mapperMybatis.readAllFill(requestsString);
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
    protected DTO fill(DTO dto) {
        throw new UnsupportedOperationException("Implementation to this method not required");
    }

    public Class<MAPPER> getMybatisMapper() {
        return mybatisMapper;
    }
}