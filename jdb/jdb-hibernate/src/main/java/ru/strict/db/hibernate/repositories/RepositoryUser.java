package ru.strict.db.hibernate.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoUserBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.entities.EntityUser;

public class RepositoryUser<DTO extends DtoUserBase> extends RepositoryNamedBase<EntityUser, DTO> {

    private static final String[] COLUMNS_NAME = new String[] {"username", "passwordencode"};

    public RepositoryUser(CreateConnectionHibernate connectionSource,
                          MapperDtoBase<EntityUser, DTO> dtoMapper,
                          GenerateIdType isGenerateId) {
        super("userx",
                COLUMNS_NAME,
                connectionSource,
                dtoMapper,
                isGenerateId);
    }

    @Override
    protected DTO fill(DTO dto){
        return dto;
    }

    @Override
    protected String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected Class<EntityUser> getEntityClass() {
        return EntityUser.class;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
