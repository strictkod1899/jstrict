package ru.strict.db.mybatis.data;

import ru.strict.db.core.dto.DtoCity;
import ru.strict.db.core.dto.DtoCountry;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoUser;

import java.util.UUID;

public class TestData {
    public static final DtoCountry<Integer> COUNTRY1 = new DtoCountry<>(111, "country1");
    public static final DtoCountry<Integer> COUNTRY1_UPDATED = new DtoCountry<>(111, "country1_updated");
    public static final DtoCountry<UUID> COUNTRY1_UUID = new DtoCountry<>(UUID.randomUUID(), "country1");

    public static final DtoCity<Integer> CITY1 = new DtoCity<>(121, "city1", COUNTRY1.getId());
    public static final DtoCity<Integer> CITY1_UPDATED = new DtoCity<>(121, "city1_updated", COUNTRY1.getId());
    public static final DtoCity<UUID> CITY1_UUID = new DtoCity<>(UUID.randomUUID(), "city1", COUNTRY1_UUID.getId());

    public static final DtoRoleuser<Integer> ROLEUSER1 = new DtoRoleuser<>(131, "role1", "description1");
    public static final DtoRoleuser<Integer> ROLEUSER1_UPDATED = new DtoRoleuser<>(131, "role1_updated", "description1_updated");
    public static final DtoRoleuser<UUID> ROLEUSER1_UUID = new DtoRoleuser<>(UUID.randomUUID(), "role1", "description1");

    public static final DtoUser<Integer> USER1 = new DtoUser<>(141, "user1", "user1@mail.ru", "password1");
    public static final DtoUser<Integer> USER1_UPDATED = new DtoUser<>(141, "user1_updated", "user1_updated@mail.ru", "password1_updated");
    public static final DtoUser<UUID> USER1_UUID = new DtoUser<>(UUID.randomUUID(), "user1", "user1@mail.ru", "password1");
}
