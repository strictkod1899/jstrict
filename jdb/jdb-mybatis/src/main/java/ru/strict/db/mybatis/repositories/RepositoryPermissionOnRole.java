package ru.strict.db.mybatis.repositories;

import org.apache.ibatis.session.SqlSession;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.models.PermissionOnRole;
import ru.strict.db.core.repositories.interfaces.IRepositoryPermissionOnRole;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlPermissionOnRole;
import ru.strict.utils.UtilClass;

import java.util.List;
import java.util.stream.Collectors;

public class RepositoryPermissionOnRole<ID, PERMISSION>
        extends RepositoryMybatisBase<ID, PermissionOnRole<ID, PERMISSION>, MapperSqlPermissionOnRole<ID, PERMISSION>>
        implements IRepositoryPermissionOnRole<ID, PERMISSION> {

    private static final String[] COLUMNS_NAME = DefaultColumns.PERMISSION_ON_ROLE.columns();

    public RepositoryPermissionOnRole(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super(DefaultTable.PERMISSION_ON_ROLE.table(),
                COLUMNS_NAME,
                connectionSource,
                UtilClass.castClass(MapperSqlPermissionOnRole.class),
                generateIdType);
    }

    @Override
    public List<PermissionOnRole<ID, PERMISSION>> readByPermissionId(Integer permissionId) {
        if(permissionId == null){
            throw new IllegalArgumentException("permissionId for read is NULL");
        }

        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlPermissionOnRole<ID, PERMISSION> mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.readByPermissionId(permissionId);
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
    public List<PermissionOnRole<ID, PERMISSION>> readByRoleId(ID roleId) {
        if(roleId == null){
            throw new IllegalArgumentException("roleId for read is NULL");
        }

        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlPermissionOnRole<ID, PERMISSION> mapperMybatis = session.getMapper(getMybatisMapperClass());
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
