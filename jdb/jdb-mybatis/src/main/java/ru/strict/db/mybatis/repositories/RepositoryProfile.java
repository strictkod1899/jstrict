package ru.strict.db.mybatis.repositories;

import org.apache.ibatis.session.SqlSession;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoProfile;
import ru.strict.db.core.entities.EntityProfile;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.interfaces.IRepositoryProfile;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlProfile;
import ru.strict.utils.UtilClass;

import java.util.List;
import java.util.stream.Collectors;

public class RepositoryProfile<ID>
        extends RepositoryMybatisBase<ID, EntityProfile<ID>, DtoProfile<ID>, MapperSqlProfile<ID>>
        implements IRepositoryProfile<ID, DtoProfile<ID>> {

    private static final String[] COLUMNS_NAME = new String[] {"name", "surname", "middlename", "userx_id"};

    public RepositoryProfile(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super("profile",
                COLUMNS_NAME,
                connectionSource,
                UtilClass.<MapperSqlProfile<ID>>castClass(MapperSqlProfile.class),
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityProfile.class), UtilClass.castClass(DtoProfile.class)),
                generateIdType);
    }

    @Override
    public List<DtoProfile<ID>> readByFio(String name, String surname, String middlename) {
        List<DtoProfile<ID>> result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlProfile<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            List<EntityProfile<ID>> entities = mapperMybatis.readByFio(name, surname, middlename);
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
    public List<DtoProfile<ID>> readByUserId(ID userId) {
        if(userId == null){
            throw new NullPointerException("userId for read is NULL");
        }
        List<DtoProfile<ID>> result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlProfile<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            List<EntityProfile<ID>> entities = mapperMybatis.readByUserId(userId);
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
