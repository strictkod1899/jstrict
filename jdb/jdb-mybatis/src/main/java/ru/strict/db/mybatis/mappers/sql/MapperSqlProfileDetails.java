package ru.strict.db.mybatis.mappers.sql;

import org.apache.ibatis.annotations.Param;
import ru.strict.models.DetailsProfile;

import java.util.List;

public interface MapperSqlProfileDetails<ID> extends MapperSqlProfile<ID, DetailsProfile<ID>> {
    List<DetailsProfile<ID>> readByFio(@Param("name") String name, @Param("surname") String surname, @Param("middlename") String middlename);
    List<DetailsProfile<ID>> readByUserId(@Param("userId") ID userId);
}
