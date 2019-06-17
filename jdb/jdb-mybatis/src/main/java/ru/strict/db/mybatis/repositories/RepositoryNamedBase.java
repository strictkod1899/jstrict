package ru.strict.db.mybatis.repositories;

import org.apache.ibatis.session.SqlSession;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.requests.DbTable;
import ru.strict.models.DtoBase;
import ru.strict.db.core.entities.EntityBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlNamed;
import ru.strict.validates.ValidateBaseValue;

import java.util.List;
import java.util.stream.Collectors;

public abstract class RepositoryNamedBase
        <ID, E extends EntityBase<ID>, DTO extends DtoBase<ID>, MAPPER extends MapperSqlNamed<ID, E>>
        extends RepositoryMybatisBase<ID, E, DTO, MAPPER>
        implements IRepositoryNamed<ID, DTO> {

    public RepositoryNamedBase(DbTable table,
                               String[] columnsName,
                               CreateConnectionByMybatis connectionSource,
                               Class<MAPPER> mybatisMapper,
                               MapperDtoBase<ID, E, DTO> dtoMapper,
                               GenerateIdType generateIdType) {
        super(table, columnsName, connectionSource, mybatisMapper, dtoMapper, generateIdType);
    }

    @Override
    public DTO readByName(String caption) {
        if(ValidateBaseValue.isEmptyOrNull(caption)){
            throw new IllegalArgumentException("caption for read by name is NULL");
        }
        DTO result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
            E entity = mapperMybatis.readByName(caption);
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
    public List<DTO> readAllByName(String caption) {
        if(ValidateBaseValue.isEmptyOrNull(caption)){
            throw new IllegalArgumentException("caption for read by name is NULL");
        }
        List<DTO> result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MAPPER mapperMybatis = session.getMapper(getMybatisMapperClass());
            List<E> entities = mapperMybatis.readAllByName(caption);
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
}
