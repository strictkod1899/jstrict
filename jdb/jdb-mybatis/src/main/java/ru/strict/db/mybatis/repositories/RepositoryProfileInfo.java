package ru.strict.db.mybatis.repositories;

import org.apache.ibatis.session.SqlSession;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.models.ProfileDetails;
import ru.strict.db.core.entities.EntityProfileInfo;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.interfaces.IRepositoryProfile;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlProfileInfo;
import ru.strict.utils.UtilClass;

import java.util.List;
import java.util.stream.Collectors;

public class RepositoryProfileInfo<ID>
        extends RepositoryMybatisBase<ID, EntityProfileInfo<ID>, ProfileDetails<ID>, MapperSqlProfileInfo<ID>>
        implements IRepositoryProfile<ID, ProfileDetails<ID>> {

    private static final String[] COLUMNS_NAME = new String[] {"name", "surname", "middlename", "userx_id", "datebirth",
            "phone", "city_id"};

    public RepositoryProfileInfo(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super("profile",
                COLUMNS_NAME,
                connectionSource,
                UtilClass.<MapperSqlProfileInfo<ID>>castClass(MapperSqlProfileInfo.class),
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityProfileInfo.class), UtilClass.castClass(ProfileDetails.class)),
                generateIdType);
    }

    @Override
    public List<ProfileDetails<ID>> readByFio(String name, String surname, String middlename) {
        List<ProfileDetails<ID>> result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlProfileInfo<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            List<EntityProfileInfo<ID>> entities = mapperMybatis.readByFio(name, surname, middlename);
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
    public List<ProfileDetails<ID>> readByUserId(ID userId) {
        if(userId == null){
            throw new IllegalArgumentException("userId for read is NULL");
        }
        List<ProfileDetails<ID>> result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlProfileInfo<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            List<EntityProfileInfo<ID>> entities = mapperMybatis.readByUserId(userId);
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
