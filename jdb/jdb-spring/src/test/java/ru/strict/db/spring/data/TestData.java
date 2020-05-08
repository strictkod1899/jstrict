package ru.strict.db.spring.data;

import ru.strict.models.*;

import java.util.Date;
import java.util.UUID;

public class TestData {

    public static final Country<Long> COUNTRY1 = new Country<>(111L, "country1");
    public static final Country<Long> COUNTRY1_UPDATED = new Country<>(111L, "country1_updated");
    public static final Country<UUID> COUNTRY1_UUID = new Country<>(UUID.randomUUID(), "country1");
    public static final Country<Long> COUNTRY2 = new Country<>(112L, "country2");
    public static final Country<Long> COUNTRY3 = new Country<>(113L, "country3");




    public static final City<Long> CITY1 = new City<>(111L, "city1", COUNTRY1.getId());
    public static final City<Long> CITY1_UPDATED = new City<>(111L, "city1_updated", COUNTRY1.getId());
    public static final City<UUID> CITY1_UUID = new City<>(UUID.randomUUID(), "city1", COUNTRY1_UUID.getId());
    public static final City<Long> CITY2 = new City<>(112L, "city2", COUNTRY1.getId());
    public static final City<UUID> CITY2_UUID = new City<>(UUID.randomUUID(), "city2", COUNTRY1_UUID.getId());
    public static final City<Long> CITY3 = new City<>(113L, "city3", COUNTRY1.getId());




    public static final Role<Long> ROLE1 = new Role<>(111L, "role1", "description1");
    public static final Role<Long> ROLE1_UPDATED = new Role<>(111L, "role1_updated", "description1_updated");
    public static final Role<UUID> ROLE1_UUID = new Role<>(UUID.randomUUID(), "role1", "description1");
    public static final Role<Long> ROLE2 = new Role<>(112L, "role2", "description2");
    public static final Role<UUID> ROLE2_UUID = new Role<>(UUID.randomUUID(), "role2", "description2");
    public static final Role<Long> ROLE3 = new Role<>(113L, "role3", "description3");
    public static final Role<UUID> ROLE3_UUID = new Role<>(UUID.randomUUID(), "role3", "description3");




    public static final DetailsUser<Long> USER1 = new DetailsUser<>(111L,
            "user1",
            "user1@mail.ru",
            "password1",
            "salt1",
            "secret1");
    public static final DetailsUser<Long> USER1_UPDATED = new DetailsUser<>(111L,
            "user1_updated",
            "user1_updated@mail.ru",
            "password1_updated",
            "salt1_updated",
            "secret1_updated");
    public static final DetailsUser<UUID> USER1_UUID = new DetailsUser<>(UUID.randomUUID(),
            "user1",
            "user1@mail.ru",
            "password1",
            "salt1",
            "secret1");
    public static final DetailsUser<Long> USER2 = new DetailsUser<>(112L,
            "user2",
            "user2@mail.ru",
            "password2",
            "salt2",
            "secret2");
    public static final DetailsUser<UUID> USER2_UUID = new DetailsUser<>(UUID.randomUUID(),
            "user2",
            "user2@mail.ru",
            "password2",
            "salt2",
            "secret2");
    public static final DetailsUser<Long> USER3 = new DetailsUser<>(113L,
            "user3",
            "user3@mail.ru",
            "password3",
            "salt3",
            "secret3");
    public static final DetailsUser<UUID> USER3_UUID = new DetailsUser<>(UUID.randomUUID(),
            "user3",
            "user3@mail.ru",
            "password3",
            "salt3",
            "secret3");
    public static final DetailsUser<Long> USER4 = new DetailsUser<>(114L,
            "user4",
            "user4@mail.ru",
            "password4",
            "salt4",
            "secret4");
    public static final DetailsUser<UUID> USER4_UUID = new DetailsUser<>(UUID.randomUUID(),
            "user4",
            "user4@mail.ru",
            "password4",
            "salt4",
            "secret4");




    public static final UserOnRole<Long> USER_ON_ROLE1 = new UserOnRole<>(111L, USER1.getId(), ROLE1.getId());
    public static final UserOnRole<Long> USER_ON_ROLE1_UPDATED = new UserOnRole<>(111L, USER2.getId(), ROLE2.getId());
    public static final UserOnRole<Long> USER_ON_ROLE2 = new UserOnRole<>(112L, USER2.getId(), ROLE1.getId());
    public static final UserOnRole<Long> USER_ON_ROLE3 = new UserOnRole<>(113L, USER1.getId(), ROLE2.getId());




    public static final Profile<Long> PROFILE1 = new Profile<>(111L, "name1", "surname1", USER1.getId());
    public static final Profile<Long> PROFILE1_UPDATED = new Profile<>(111L, "name1_updated", "surname1_updated", USER2.getId());
    public static final Profile<Long> PROFILE2 = new Profile<>(112L, "name2", "surname2", USER1.getId());
    public static final Profile<Long> PROFILE3 = new Profile<>(113L, "name3", "surname3", USER1.getId());




    public static final DetailsProfile<Long> PROFILE_DETAILS1 = new DetailsProfile<>(111L,
            "name1",
            "surname1",
            "middlename1",
            USER1.getId(),
            true,
            new Date(),
            "phone1",
            CITY1.getId());
    public static final DetailsProfile<Long> PROFILE_DETAILS1_UPDATED = new DetailsProfile<>(111L,
            "name1_updated",
            "surname1_updated",
            "middlename1_updated",
            USER1.getId(),
            true,
            new Date(),
            "phone1_updated",
            CITY2.getId());
    public static final DetailsProfile<Long> PROFILE_DETAILS2 = new DetailsProfile<>(112L,
            "name2",
            "surname2",
            "middlename2",
            USER1.getId(),
            true,
            new Date(),
            "phone2",
            CITY1.getId());
    public static final DetailsProfile<Long> PROFILE_DETAILS3 = new DetailsProfile<>(113L,
            "name3",
            "surname3",
            "middlename3",
            USER2.getId(),
            true,
            new Date(),
            "phone3",
            CITY2.getId());




    public static final FileStorage<Long> FILE_STORAGE1 = new FileStorage<>(111L,
            "filename1",
            "extension1",
            "displayname1",
            "filepath1",
            new byte[]{ 1, 2, 3, 4, 5, 6, 7, 8 },
            new Date(),
            1,
            1);
    public static final FileStorage<Long> FILE_STORAGE1_UPDATED = new FileStorage<>(111L,
            "filename1_updated",
            "extension1_updated",
            "displayname1_updated",
            "filepath1_updated",
            new byte[]{ 1, 2, 3, 4, 5, 6},
            new Date(),
            3,
            3);
    public static final FileStorage<Long> FILE_STORAGE2 = new FileStorage<>(112L,
            "filename2",
            "extension2",
            "displayname2",
            "filepath2",
            new byte[]{ 1, 2, 3, 4, 5, 6, 7, 8 },
            new Date(),
            2,
            2);
    public static final FileStorage<Long> FILE_STORAGE3 = new FileStorage<>(113L,
            "filename3",
            "extension3",
            "displayname3",
            "filepath3",
            new byte[]{ 1, 2, 3, 4, 5, 6, 7, 8 },
            new Date(),
            1,
            1);




    public static final JWTToken<Long> JWT_TOKEN1 = new JWTToken<>(111L, "accessToken1", "refreshToken1", new Date(), new Date(), new Date(), USER1.getId());
    public static final JWTToken<Long> JWT_TOKEN1_UPDATED = new JWTToken<>(111L, "accessToken1_updated", "refreshToken1_updated", new Date(), new Date(), new Date(), USER2.getId());
    public static final JWTToken<Long> JWT_TOKEN2 = new JWTToken<>(112L, "accessToken2", "refreshToken2", new Date(), new Date(), new Date(), USER1.getId());
    public static final JWTToken<Long> JWT_TOKEN3 = new JWTToken<>(113L, "accessToken3", "refreshToken3", new Date(), new Date(), new Date(), USER2.getId());




    public static final PermissionOnRole<Long, Permission> PERMISSION_ON_ROLE1 = new PermissionOnRole<>(111L, Permission.PERMISSION1.getId(), ROLE1.getId());
    public static final PermissionOnRole<Long, Permission> PERMISSION_ON_ROLE1_UPDATED = new PermissionOnRole<>(111L, Permission.PERMISSION2.getId(), ROLE2.getId());
    public static final PermissionOnRole<Long, Permission> PERMISSION_ON_ROLE2 = new PermissionOnRole<>(112L, Permission.PERMISSION2.getId(), ROLE1.getId());
    public static final PermissionOnRole<Long, Permission> PERMISSION_ON_ROLE3 = new PermissionOnRole<>(113L, Permission.PERMISSION1.getId(), ROLE2.getId());
}
