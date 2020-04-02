package ru.strict.db.mybatis.mappers.sql;

import org.apache.ibatis.annotations.Param;
import ru.strict.models.ProfileDetails;

import java.util.List;

public interface MapperSqlProfileDetails<ID> extends MapperSqlProfile<ID, ProfileDetails<ID>> {
    List<ProfileDetails<ID>> readByFio(@Param("name") String name, @Param("surname") String surname, @Param("middlename") String middlename);
    List<ProfileDetails<ID>> readByUserId(@Param("userId") ID userId);
}
