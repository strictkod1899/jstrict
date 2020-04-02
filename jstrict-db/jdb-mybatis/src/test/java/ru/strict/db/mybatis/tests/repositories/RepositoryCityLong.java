package ru.strict.db.mybatis.tests.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.repositories.RepositoryCity;

public class RepositoryCityLong extends RepositoryCity<Long> {

    public RepositoryCityLong(CreateConnectionByMybatis connectionSource, GenerateIdType generateIdType) {
        super(connectionSource, generateIdType);
    }
}
