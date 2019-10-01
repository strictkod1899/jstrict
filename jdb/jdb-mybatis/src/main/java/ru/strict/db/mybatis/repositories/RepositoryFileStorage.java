package ru.strict.db.mybatis.repositories;

import org.apache.ibatis.session.SqlSession;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.db.core.repositories.interfaces.IRepositoryFileStorage;
import ru.strict.models.FileStorage;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlFileStorage;
import ru.strict.utils.UtilClass;
import ru.strict.validate.ValidateBaseValue;

import java.util.List;

public class RepositoryFileStorage<ID>
        extends RepositoryMybatisNamed<ID, FileStorage<ID>, MapperSqlFileStorage<ID>>
        implements IRepositoryFileStorage<ID, FileStorage<ID>> {

    private static final String[] COLUMNS_NAME = DefaultColumns.FILE_STORAGE.columns();

    public RepositoryFileStorage(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super(DefaultTable.FILE_STORAGE.table(),
                COLUMNS_NAME,
                connectionSource,
                UtilClass.castClass(MapperSqlFileStorage.class),
                generateIdType);
    }

    @Override
    public List<FileStorage<ID>> readByDisplayName(String displayName) {
        if(ValidateBaseValue.isEmptyOrNull(displayName)){
            throw new IllegalArgumentException("displayName for read is NULL");
        }

        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlFileStorage<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.readByDisplayName(displayName);
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
    public List<FileStorage<ID>> readByFileNameAndExtension(String fileName, String extension) {
        if(ValidateBaseValue.isEmptyOrNull(fileName)){
            throw new IllegalArgumentException("fileName for read is NULL");
        }
        if(ValidateBaseValue.isEmptyOrNull(extension)){
            throw new IllegalArgumentException("extension for read is NULL");
        }

        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlFileStorage<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.readByFileNameAndExtension(fileName, extension);
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
    public List<FileStorage<ID>> readByDisplayNameAndExtension(String displayName, String extension) {
        if(ValidateBaseValue.isEmptyOrNull(displayName)){
            throw new IllegalArgumentException("displayName for read is NULL");
        }
        if(ValidateBaseValue.isEmptyOrNull(extension)){
            throw new IllegalArgumentException("extension for read is NULL");
        }

        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlFileStorage<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.readByDisplayNameAndExtension(displayName, extension);
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
    public FileStorage<ID> readByFilePath(String filePath) {
        if(ValidateBaseValue.isEmptyOrNull(filePath)){
            throw new IllegalArgumentException("filePath for read is NULL");
        }

        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlFileStorage<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.readByFilePath(filePath);
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
    public List<FileStorage<ID>> readByType(int type) {
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlFileStorage<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.readByType(type);
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
    public List<FileStorage<ID>> readByStatus(int status) {
        SqlSession session = null;
        try {
            session = createConnection();
            MapperSqlFileStorage<ID> mapperMybatis = session.getMapper(getMybatisMapperClass());
            return mapperMybatis.readByStatus(status);
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
    public String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
