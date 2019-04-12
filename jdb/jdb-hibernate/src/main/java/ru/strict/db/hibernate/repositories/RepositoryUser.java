package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.dto.DtoUserBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.repositories.interfaces.IRepositoryUser;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityUser;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;
import ru.strict.utils.UtilClass;

import java.io.Serializable;

public class RepositoryUser<DTO extends DtoUserBase<Long>>
        extends RepositoryHibernateBase<Long, EntityUser, DTO>
        implements IRepositoryUser<Long, DTO> {

    private static final String[] COLUMNS_NAME = new String[] {"username", "passwordencode", "email",
            "is_blocked", "is_deleted", "is_confirm_email"};

    /**
     * Для этого конструктуора используется DtoUser
     */
    public RepositoryUser(CreateConnectionHibernate connectionSource, GenerateIdType generateIdType) {
        super("userx",
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory().instance(UtilClass.castClass(EntityUser.class), UtilClass.castClass(DtoUser.class)),
                generateIdType);
    }

    public RepositoryUser(CreateConnectionHibernate connectionSource,
                          MapperDtoBase<Long, EntityUser, DTO> dtoMapper,
                          GenerateIdType generateIdType) {
        super("userx",
                COLUMNS_NAME,
                connectionSource,
                dtoMapper,
                generateIdType);
    }

    @Override
    protected DTO fill(DTO dto){
        return dto;
    }

    @Override
    public String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected Class<EntityUser> getEntityClass() {
        return UtilClass.<EntityUser>castClass(EntityUser.class);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
