package ru.strict.db.mybatis.repositories;

import org.apache.ibatis.session.SqlSession;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.requests.DbTable;
import ru.strict.models.DtoBase;
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

    public RepositoryMybatisBase(DbTable table,
                                 String[] columnsName,
                                 CreateConnectionByMybatis connectionSource,
                                 Class<MAPPER> mybatisMapper,
                                 MapperDtoBase<ID, E, DTO> dtoMapper,
                                 GenerateIdType generateIdType) {
        super(table, columnsName, connectionSource, dtoMapper, generateIdType);
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
                    MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
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
                    MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
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
            MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
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
            MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
            List<E> entities = mapperMybatis.readAll();
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
            MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
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
    public DTO readFill(ID id) {
        DTO result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
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
            MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
            List<E> entities = mapperMybatis.readAllFill();
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
            MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
            result = mapperMybatis.readCount();
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
    public boolean isRowExists(ID id) {
        if(id == null){
            return false;
        }

        boolean result = false;
        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
            result = mapperMybatis.readCountById(id) > 0 ? true : false;
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

    public Class<MAPPER> getMybatisMapperClass() {
        return mybatisMapper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RepositoryMybatisBase<?, ?, ?, ?> that = (RepositoryMybatisBase<?, ?, ?, ?>) o;
        return Objects.equals(mybatisMapper, that.mybatisMapper);
    }

    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), mybatisMapper);
    }
}
