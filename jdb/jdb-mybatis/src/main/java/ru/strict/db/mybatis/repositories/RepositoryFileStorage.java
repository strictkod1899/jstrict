package ru.strict.db.mybatis.repositories;

import org.apache.ibatis.session.SqlSession;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoFileStorage;
import ru.strict.db.core.dto.DtoFileStorageBase;
import ru.strict.db.core.entities.EntityFileStorage;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.interfaces.IRepositoryFileStorage;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlFileStorage;
import ru.strict.utils.UtilClass;
import ru.strict.validates.ValidateBaseValue;

import java.util.List;
import java.util.stream.Collectors;

public class RepositoryFileStorage<ID, DTO extends DtoFileStorageBase<ID>>
        extends RepositoryNamedBase<ID, EntityFileStorage<ID>, DTO, MapperSqlFileStorage<ID>>
        implements IRepositoryFileStorage<ID, DTO> {

    private static final String[] COLUMNS_NAME = new String[] {"filename", "extension", "displayname", "content", "filepath",
            "create_date", "type", "status"};

    public RepositoryFileStorage(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super("file_storage",
                COLUMNS_NAME,
                connectionSource,
                UtilClass.<MapperSqlFileStorage<ID>>castClass(MapperSqlFileStorage.class),
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityFileStorage.class), UtilClass.castClass(DtoFileStorage.class)),
                generateIdType);
    }

    public RepositoryFileStorage(CreateConnectionByMybatis connectionSource,
                                 MapperDtoBase<ID, EntityFileStorage<ID>, DTO> dtoMapper,
                                 GenerateIdType generateIdType) {
        super("file_storage",
                COLUMNS_NAME,
                connectionSource,
                UtilClass.<MapperSqlFileStorage<ID>>castClass(MapperSqlFileStorage.class),
                dtoMapper,
                generateIdType);
    }

    @Override
    public List<DTO> readByDisplayName(String displayName) {
        if(ValidateBaseValue.isEmptyOrNull(displayName)){
            throw new NullPointerException("displayName for read is NULL");
        }
        List<DTO> result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlFileStorage<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            List<EntityFileStorage<ID>> entities = mapperMybatis.readByDisplayName(displayName);
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
    public List<DTO> readByFileNameAndExtension(String fileName, String extension) {
        if(ValidateBaseValue.isEmptyOrNull(fileName)){
            throw new NullPointerException("fileName for read is NULL");
        }
        if(ValidateBaseValue.isEmptyOrNull(extension)){
            throw new IllegalArgumentException("extension for read is NULL");
        }
        List<DTO> result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlFileStorage<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            List<EntityFileStorage<ID>> entities = mapperMybatis.readByFileNameAndExtension(fileName, extension);
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
    public List<DTO> readByDisplayNameAndExtension(String displayName, String extension) {
        if(ValidateBaseValue.isEmptyOrNull(displayName)){
            throw new IllegalArgumentException("displayName for read is NULL");
        }
        if(ValidateBaseValue.isEmptyOrNull(extension)){
            throw new IllegalArgumentException("extension for read is NULL");
        }
        List<DTO> result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlFileStorage<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            List<EntityFileStorage<ID>> entities = mapperMybatis.readByDisplayNameAndExtension(displayName, extension);
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
    public DTO readByFilePath(String filePath) {
        if(ValidateBaseValue.isEmptyOrNull(filePath)){
            throw new NullPointerException("filePath for read is NULL");
        }
        DTO result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlFileStorage<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            EntityFileStorage<ID> entity = mapperMybatis.readByFilePath(filePath);
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
    public List<DTO> readByType(int type) {
        List<DTO> result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlFileStorage<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            List<EntityFileStorage<ID>> entities = mapperMybatis.readByType(type);
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
    public List<DTO> readByStatus(int status) {
        List<DTO> result = null;
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlFileStorage<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            List<EntityFileStorage<ID>> entities = mapperMybatis.readByStatus(status);
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
    public String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
