package ru.strict.db.mybatis.repositories;

import org.apache.ibatis.session.SqlSession;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.models.UserOnRole;
import ru.strict.db.core.repositories.interfaces.IRepositoryUserOnRole;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlUserOnRole;
import ru.strict.utils.UtilClass;

import java.util.List;

public class RepositoryUserOnRole<ID>
        extends RepositoryMybatisBase<ID, UserOnRole<ID>, MapperSqlUserOnRole<ID>>
        implements IRepositoryUserOnRole<ID> {

    private static final String[] COLUMNS_NAME = DefaultColumns.USER_ON_ROLE.columns();

    public RepositoryUserOnRole(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super(DefaultTable.USER_ON_ROLE.table(),
                COLUMNS_NAME,
                connectionSource,
                UtilClass.castClass(MapperSqlUserOnRole.class),
                generateIdType);
    }

    @Override
    public List<UserOnRole<ID>> readByUserId(ID userId) {
        if(userId == null){
            throw new IllegalArgumentException("userId for read is NULL");
        }

        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlUserOnRole<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
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
    public List<UserOnRole<ID>> readByRoleId(ID roleId) {
        if(roleId == null){
            throw new IllegalArgumentException("roleId for read is NULL");
        }

        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlUserOnRole<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.readByRoleId(roleId);
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
