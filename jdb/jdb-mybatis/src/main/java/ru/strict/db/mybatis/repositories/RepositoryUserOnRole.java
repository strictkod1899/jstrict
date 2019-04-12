package ru.strict.db.mybatis.repositories;

import org.apache.ibatis.session.SqlSession;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoUserOnRole;
import ru.strict.db.core.entities.EntityUserOnRole;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.interfaces.IRepositoryUserOnRole;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlUserOnRole;
import ru.strict.utils.UtilClass;

import java.util.List;
import java.util.stream.Collectors;

public class RepositoryUserOnRole<ID>
        extends RepositoryMybatisBase<ID, EntityUserOnRole<ID>, DtoUserOnRole<ID>, MapperSqlUserOnRole<ID>>
        implements IRepositoryUserOnRole<ID> {

    private static final String[] COLUMNS_NAME = new String[] {"userx_id", "roleuser_id"};

    public RepositoryUserOnRole(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super("user_on_role",
                COLUMNS_NAME,
                connectionSource,
                UtilClass.<MapperSqlUserOnRole<ID>>castClass(MapperSqlUserOnRole.class),
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityUserOnRole.class), UtilClass.castClass(DtoUserOnRole.class)),
                generateIdType);
    }

    @Override
    public List<DtoUserOnRole<ID>> readByUserId(ID userId) {
        if(userId == null){
            throw new IllegalArgumentException("userId for read is NULL");
        }
        List<DtoUserOnRole<ID>> result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlUserOnRole<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            List<EntityUserOnRole<ID>> entities = mapperMybatis.readByUserId(userId);
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
    public List<DtoUserOnRole<ID>> readByRoleId(ID roleId) {
        if(roleId == null){
            throw new IllegalArgumentException("roleId for read is NULL");
        }
        List<DtoUserOnRole<ID>> result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlUserOnRole<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            List<EntityUserOnRole<ID>> entities = mapperMybatis.readByRoleId(roleId);
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
