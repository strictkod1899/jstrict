package ru.strict.db.mybatis.tests.data;

import ru.strict.models.*;

import java.util.Date;
import java.util.UUID;

public class TestData {

    public static final Country<Integer> COUNTRY1 = new Country<>(111, "country1");
    public static final Country<Integer> COUNTRY1_UPDATED = new Country<>(111, "country1_updated");
    public static final Country<UUID> COUNTRY1_UUID = new Country<>(UUID.randomUUID(), "country1");
    public static final Country<Integer> COUNTRY2 = new Country<>(112, "country2");
    public static final Country<Integer> COUNTRY3 = new Country<>(113, "country3");




    public static final City<Integer> CITY1 = new City<>(111, "city1", COUNTRY1.getId());
    public static final City<Integer> CITY1_UPDATED = new City<>(111, "city1_updated", COUNTRY1.getId());
    public static final City<UUID> CITY1_UUID = new City<>(UUID.randomUUID(), "city1", COUNTRY1_UUID.getId());
    public static final City<Integer> CITY2 = new City<>(112, "city2", COUNTRY1.getId());
    public static final City<UUID> CITY2_UUID = new City<>(UUID.randomUUID(), "city2", COUNTRY1_UUID.getId());
    public static final City<Integer> CITY3 = new City<>(113, "city3", COUNTRY1.getId());




    public static final Role<Integer> ROLE1 = new Role<>(111, "role1", "description1");
    public static final Role<Integer> ROLE1_UPDATED = new Role<>(111, "role1_updated", "description1_updated");
    public static final Role<UUID> ROLE1_UUID = new Role<>(UUID.randomUUID(), "role1", "description1");
    public static final Role<Integer> ROLE2 = new Role<>(112, "role2", "description2");
    public static final Role<UUID> ROLE2_UUID = new Role<>(UUID.randomUUID(), "role2", "description2");
    public static final Role<Integer> ROLE3 = new Role<>(113, "role3", "description3");
    public static final Role<UUID> ROLE3_UUID = new Role<>(UUID.randomUUID(), "role3", "description3");




    public static final UserDetails<Integer> USER1 = new UserDetails<>(111,
            "user1",
            "user1@mail.ru",
            "password1",
            "salt1",
            "secret1");
    public static final UserDetails<Integer> USER1_UPDATED = new UserDetails<>(111,
            "user1_updated",
            "user1_updated@mail.ru",
            "password1_updated",
            "salt1_updated",
            "secret1_updated");
    public static final UserDetails<UUID> USER1_UUID = new UserDetails<>(UUID.randomUUID(),
            "user1",
            "user1@mail.ru",
            "password1",
            "salt1",
            "secret1");
    public static final UserDetails<Integer> USER2 = new UserDetails<>(112,
            "user2",
            "user2@mail.ru",
            "password2",
            "salt2",
            "secret2");
    public static final UserDetails<UUID> USER2_UUID = new UserDetails<>(UUID.randomUUID(),
            "user2",
            "user2@mail.ru",
            "password2",
            "salt2",
            "secret2");
    public static final UserDetails<Integer> USER3 = new UserDetails<>(113,
            "user3",
            "user3@mail.ru",
            "password3",
            "salt3",
            "secret3");
    public static final UserDetails<UUID> USER3_UUID = new UserDetails<>(UUID.randomUUID(),
            "user3",
            "user3@mail.ru",
            "password3",
            "salt3",
            "secret3");
    public static final UserDetails<Integer> USER4 = new UserDetails<>(114,
            "user4",
            "user4@mail.ru",
            "password4",
            "salt4",
            "secret4");
    public static final UserDetails<UUID> USER4_UUID = new UserDetails<>(UUID.randomUUID(),
            "user4",
            "user4@mail.ru",
            "password4",
            "salt4",
            "secret4");




    public static final UserOnRole<Integer> USER_ON_ROLE1 = new UserOnRole<>(111, USER1.getId(), ROLE1.getId());
    public static final UserOnRole<Integer> USER_ON_ROLE1_UPDATED = new UserOnRole<>(111, USER2.getId(), ROLE2.getId());
    public static final UserOnRole<Integer> USER_ON_ROLE2 = new UserOnRole<>(112, USER2.getId(), ROLE1.getId());
    public static final UserOnRole<Integer> USER_ON_ROLE3 = new UserOnRole<>(113, USER1.getId(), ROLE2.getId());




    public static final Profile<Integer> PROFILE1 = new Profile<>(111, "name1", "surname1", USER1.getId());
    public static final Profile<Integer> PROFILE1_UPDATED = new Profile<>(111, "name1_updated", "surname1_updated", USER2.getId());
    public static final Profile<Integer> PROFILE2 = new Profile<>(112, "name2", "surname2", USER1.getId());
    public static final Profile<Integer> PROFILE3 = new Profile<>(113, "name3", "surname3", USER1.getId());




    public static final ProfileDetails<Integer> PROFILE_DETAILS1 = new ProfileDetails<>(111,
            "name1",
            "surname1",
            "middlename1",
            USER1.getId(),
            true,
            new Date(),
            "phone1",
            CITY1.getId());
    public static final ProfileDetails<Integer> PROFILE_DETAILS1_UPDATED = new ProfileDetails<>(111,
            "name1_updated",
            "surname1_updated",
            "middlename1_updated",
            USER1.getId(),
            true,
            new Date(),
            "phone1_updated",
            CITY2.getId());
    public static final ProfileDetails<Integer> PROFILE_DETAILS2 = new ProfileDetails<>(112,
            "name2",
            "surname2",
            "middlename2",
            USER1.getId(),
            true,
            new Date(),
            "phone2",
            CITY1.getId());
    public static final ProfileDetails<Integer> PROFILE_DETAILS3 = new ProfileDetails<>(113,
            "name3",
            "surname3",
            "middlename3",
            USER2.getId(),
            true,
            new Date(),
            "phone3",
            CITY2.getId());




    public static final FileStorage<Integer> FILE_STORAGE1 = new FileStorage<>(111,
            "filename1",
            "extension1",
            "displayname1",
            "filepath1",
            new byte[]{ 1, 2, 3, 4, 5, 6, 7, 8 },
            new Date(),
            1,
            1);
    public static final FileStorage<Integer> FILE_STORAGE1_UPDATED = new FileStorage<>(111,
            "filename1_updated",
            "extension1_updated",
            "displayname1_updated",
            "filepath1_updated",
            new byte[]{ 1, 2, 3, 4, 5, 6},
            new Date(),
            3,
            3);
    public static final FileStorage<Integer> FILE_STORAGE2 = new FileStorage<>(112,
            "filename2",
            "extension2",
            "displayname2",
            "filepath2",
            new byte[]{ 1, 2, 3, 4, 5, 6, 7, 8 },
            new Date(),
            2,
            2);
    public static final FileStorage<Integer> FILE_STORAGE3 = new FileStorage<>(113,
            "filename3",
            "extension3",
            "displayname3",
            "filepath3",
            new byte[]{ 1, 2, 3, 4, 5, 6, 7, 8 },
            new Date(),
            1,
            1);




    public static final JWTToken<Integer> JWT_TOKEN1 = new JWTToken<>(111, "accessToken1", "refreshToken1", new Date(), new Date(), new Date(), USER1.getId());
    public static final JWTToken<Integer> JWT_TOKEN1_UPDATED = new JWTToken<>(111, "accessToken1_updated", "refreshToken1_updated", new Date(), new Date(), new Date(), USER2.getId());
    public static final JWTToken<Integer> JWT_TOKEN2 = new JWTToken<>(112, "accessToken2", "refreshToken2", new Date(), new Date(), new Date(), USER1.getId());
    public static final JWTToken<Integer> JWT_TOKEN3 = new JWTToken<>(113, "accessToken3", "refreshToken3", new Date(), new Date(), new Date(), USER2.getId());




    public static final PermissionOnRole<Integer, Permission> PERMISSION_ON_ROLE1 = new PermissionOnRole<>(111, Permission.PERMISSION1.getId(), ROLE1.getId());
    public static final PermissionOnRole<Integer, Permission> PERMISSION_ON_ROLE1_UPDATED = new PermissionOnRole<>(111, Permission.PERMISSION2.getId(), ROLE2.getId());
    public static final PermissionOnRole<Integer, Permission> PERMISSION_ON_ROLE2 = new PermissionOnRole<>(112, Permission.PERMISSION2.getId(), ROLE1.getId());
    public static final PermissionOnRole<Integer, Permission> PERMISSION_ON_ROLE3 = new PermissionOnRole<>(113, Permission.PERMISSION1.getId(), ROLE2.getId());
}
