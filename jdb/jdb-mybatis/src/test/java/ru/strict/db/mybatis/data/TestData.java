package ru.strict.db.mybatis.data;

import ru.strict.db.core.dto.*;

import java.util.UUID;

public class TestData {
    public static final DtoCountry<Integer> COUNTRY1 = new DtoCountry<>(111, "country1");
    public static final DtoCountry<Integer> COUNTRY1_UPDATED = new DtoCountry<>(111, "country1_updated");
    public static final DtoCountry<UUID> COUNTRY1_UUID = new DtoCountry<>(UUID.randomUUID(), "country1");

    public static final DtoCity<Integer> CITY1 = new DtoCity<>(111, "city1", COUNTRY1.getId());
    public static final DtoCity<Integer> CITY1_UPDATED = new DtoCity<>(111, "city1_updated", COUNTRY1.getId());
    public static final DtoCity<UUID> CITY1_UUID = new DtoCity<>(UUID.randomUUID(), "city1", COUNTRY1_UUID.getId());

    public static final DtoRoleuser<Integer> ROLEUSER1 = new DtoRoleuser<>(111, "role1", "description1");
    public static final DtoRoleuser<Integer> ROLEUSER1_UPDATED = new DtoRoleuser<>(111, "role1_updated", "description1_updated");
    public static final DtoRoleuser<UUID> ROLEUSER1_UUID = new DtoRoleuser<>(UUID.randomUUID(), "role1", "description1");

    public static final DtoRoleuser<Integer> ROLEUSER2 = new DtoRoleuser<>(112, "role2", "description2");
    public static final DtoRoleuser<Integer> ROLEUSER2_UPDATED = new DtoRoleuser<>(112, "role2_updated", "description2_updated");
    public static final DtoRoleuser<UUID> ROLEUSER2_UUID = new DtoRoleuser<>(UUID.randomUUID(), "role2", "description2");

    public static final DtoUser<Integer> USER1 = new DtoUser<>(111, "user1", "user1@mail.ru", "password1");
    public static final DtoUser<Integer> USER1_UPDATED = new DtoUser<>(111, "user1_updated", "user1_updated@mail.ru", "password1_updated");
    public static final DtoUser<UUID> USER1_UUID = new DtoUser<>(UUID.randomUUID(), "user1", "user1@mail.ru", "password1");

    public static final DtoUser<Integer> USER2 = new DtoUser<>(112, "user2", "user2@mail.ru", "password2");
    public static final DtoUser<Integer> USER2_UPDATED = new DtoUser<>(112, "user2_updated", "user2_updated@mail.ru", "password2_updated");
    public static final DtoUser<UUID> USER2_UUID = new DtoUser<>(UUID.randomUUID(), "user2", "user2@mail.ru", "password2");

    public static final DtoUser<Integer> USER3 = new DtoUser<>(113, "user3", "user3@mail.ru", "password3");
    public static final DtoUser<UUID> USER3_UUID = new DtoUser<>(UUID.randomUUID(), "user3", "user3@mail.ru", "password3");

    public static final DtoUser<Integer> USER4 = new DtoUser<>(114, "user4", "user4@mail.ru", "password4");
    public static final DtoUser<UUID> USER4_UUID = new DtoUser<>(UUID.randomUUID(), "user4", "user4@mail.ru", "password4");

    public static final DtoUserOnRole<Integer> USER_ON_ROLE1 = new DtoUserOnRole<>(111, USER1.getId(), ROLEUSER1.getId());
    public static final DtoUserOnRole<Integer> USER_ON_ROLE1_UPDATED = new DtoUserOnRole<>(111, USER2.getId(), ROLEUSER2.getId());
    public static final DtoUserOnRole<UUID> USER_ON_ROLE1_UUID = new DtoUserOnRole<>(UUID.randomUUID(), USER1_UUID.getId(), ROLEUSER1_UUID.getId());

    public static final DtoUserOnRole<Integer> USER_ON_ROLE2 = new DtoUserOnRole<>(USER2.getId(), ROLEUSER1.getId());
    public static final DtoUserOnRole<UUID> USER_ON_ROLE2_UUID = new DtoUserOnRole<>(USER2_UUID.getId(), ROLEUSER1_UUID.getId());

    public static final DtoUserOnRole<Integer> USER_ON_ROLE3 = new DtoUserOnRole<>(113, USER1.getId(), ROLEUSER2.getId());
    public static final DtoUserOnRole<UUID> USER_ON_ROLE3_UUID = new DtoUserOnRole<>(UUID.randomUUID(), USER1_UUID.getId(), ROLEUSER2_UUID.getId());

    public static final DtoUserOnRole<Integer> USER_ON_ROLE4 = new DtoUserOnRole<>(114, USER3.getId(), ROLEUSER1.getId());
    public static final DtoUserOnRole<UUID> USER_ON_ROLE4_UUID = new DtoUserOnRole<>(UUID.randomUUID(), USER3_UUID.getId(), ROLEUSER1_UUID.getId());

    public static final DtoProfile<Integer> PROFILE1 = new DtoProfile<>(111, "name1", "surname1", "middlename2", USER1.getId());
    public static final DtoProfile<Integer> PROFILE1_UPDATED = new DtoProfile<>(111, "name1_updated", "surname1_updated", "middlename2_updated", USER1.getId());
    public static final DtoProfile<UUID> PROFILE1_UUID = new DtoProfile<>(UUID.randomUUID(), "name1", "surname1", "middlename2", USER1_UUID.getId());



}
