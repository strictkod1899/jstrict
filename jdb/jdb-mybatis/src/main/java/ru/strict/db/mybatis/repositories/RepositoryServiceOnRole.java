package ru.strict.db.mybatis.repositories;

import org.apache.ibatis.session.SqlSession;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.requests.DbTable;
import ru.strict.models.ServiceOnRole;
import ru.strict.db.core.entities.EntityServiceOnRole;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.repositories.interfaces.IRepositoryServiceOnRole;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlServiceOnRole;
import ru.strict.utils.UtilClass;

import java.util.List;
import java.util.stream.Collectors;

public class RepositoryServiceOnRole<ID, SERVICE>
        extends RepositoryMybatisBase<ID, EntityServiceOnRole<ID, SERVICE>, ServiceOnRole<ID, SERVICE>, MapperSqlServiceOnRole<ID, SERVICE>>
        implements IRepositoryServiceOnRole<ID, SERVICE> {

    private static final String[] COLUMNS_NAME = new String[] {"service_id", "roleuser_id"};

    public RepositoryServiceOnRole(CreateConnectionByMybatis connectionSource,
                                   MapperDtoBase<ID, EntityServiceOnRole<ID, SERVICE>, ServiceOnRole<ID, SERVICE>> dtoMapper,
                                   GenerateIdType generateIdType) {
        super(new DbTable("service_on_role", "sr"),
                COLUMNS_NAME,
                connectionSource,
                UtilClass.<MapperSqlServiceOnRole<ID, SERVICE>>castClass(MapperSqlServiceOnRole.class),
                dtoMapper,
                generateIdType);
    }

    @Override
    public List<ServiceOnRole<ID, SERVICE>> readByServiceId(Integer serviceId) {
        if(serviceId == null){
            throw new IllegalArgumentException("serviceId for read is NULL");
        }
        List<ServiceOnRole<ID, SERVICE>> result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlServiceOnRole<ID, SERVICE> mapperMybatis = session.getMapper(getMybatisMapperClass());
            List<EntityServiceOnRole<ID, SERVICE>> entities = mapperMybatis.readByServiceId(serviceId);
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
    public List<ServiceOnRole<ID, SERVICE>> readByRoleId(ID roleId) {
        if(roleId == null){
            throw new IllegalArgumentException("roleId for read is NULL");
        }
        List<ServiceOnRole<ID, SERVICE>> result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlServiceOnRole<ID, SERVICE> mapperMybatis = session.getMapper(getMybatisMapperClass());
            List<EntityServiceOnRole<ID, SERVICE>> entities = mapperMybatis.readByRoleId(roleId);
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
