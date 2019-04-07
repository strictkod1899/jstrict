package ru.strict.db.mybatis.repositories;

import org.apache.ibatis.session.SqlSession;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoBase;
import ru.strict.db.core.entities.EntityBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.repositories.RepositoryBase;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlExtension;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
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
    public DTO create(DTO dto) {
        SqlSession session = null;

        GenerateIdType usingGenerateIdType = getGenerateIdType();
        if(dto.getId() != null){
            usingGenerateIdType = GenerateIdType.NONE;
        }

        switch(usingGenerateIdType){
            case NUMBER:
                try {
                    session = createConnection();
                    MAPPER mapperMybatis = session.getMapper(getMybatisMapper());
                    E entity = getDtoMapper().map(dto);
                    mapperMybatis.createGenerateId(entity);
                    session.commit();
                    dto.setId(entity.getId());
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
                dto.setId((ID)UUID.randomUUID());
            case NONE:
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
                break;
            default:
                throw new IllegalArgumentException("Type for generate id is not determine. Entity was not created into");
        };

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
        if(requests != null){
            throw new UnsupportedOperationException("This repository not supported a query by DbRequests");
        }
        List<DTO> result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapper());
            List<E> entities = mapperMybatis.readAll("");
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
        if(requests != null){
            throw new UnsupportedOperationException("This repository not supported a query by DbRequests");
        }
        List<DTO> result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapper());
            String requestsString = requests != null ? " " + requests.getSql() : "";
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
    public int readCount(DbRequests requests) {
        if(requests != null){
            throw new UnsupportedOperationException("This repository not supported a query by DbRequests");
        }
        int result = -1;
        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapper());
            result = mapperMybatis.readCount("");
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
    protected DTO fill(DTO dto) {
        throw new UnsupportedOperationException("Implementation to this method not required");
    }

    public Class<MAPPER> getMybatisMapper() {
        return mybatisMapper;
    }

    @Override
    public int hashCode(){
        return Objects.hash(getConnectionSource(), getDtoMapper(), getTableName(), getColumnsName(),
                getGenerateIdType(), mybatisMapper);
    }

    @Override
    public void close(){
        mybatisMapper = null;
        super.close();
    }
}
