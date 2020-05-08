package ru.strict.db;

import ru.strict.models.City;
import ru.strict.models.Country;
import ru.strict.models.DetailsUser;
import ru.strict.models.FileStorage;
import ru.strict.models.JWTToken;
import ru.strict.models.PermissionOnRole;
import ru.strict.models.Profile;
import ru.strict.models.DetailsProfile;
import ru.strict.models.Role;
import ru.strict.models.UserOnRole;

import java.util.Date;

public final class TestData {

    public static final Country<Long> COUNTRY1 = new Country<>(1L, "country1");
    public static final Country<Long> COUNTRY2 = new Country<>(2L, "country2");
    public static final Country<Long> COUNTRY3 = new Country<>(3L, "country3");
    public static final Country<Long> UPDATED_COUNTRY1 = new Country<>(1L, "country1-2");

    public static final Country<Long> FILL_COUNTRY1 = new Country<>(1L, "country4");
    public static final Country<Long> UPDATED_FILL_COUNTRY1 = new Country<>(1L, "country4-2");



    public static final City<Long> CITY1 = new City<>(1L, "city1", COUNTRY1.getId());
    public static final City<Long> CITY2 = new City<>(2L, "city2", COUNTRY1.getId());
    public static final City<Long> CITY3 = new City<>(3L, "city3", COUNTRY2.getId());
    public static final City<Long> UPDATED_CITY1 = new City<>(1L, "city1-2", COUNTRY2.getId());

    public static final City<Long> FILL_CITY1 = new City<>(1L, "city4", COUNTRY1.getId());
    public static final City<Long> UPDATED_FILL_CITY1 = new City<>(1L, "city4-2", COUNTRY1.getId());



    public static final Role<Long> ROLE1 = new Role<>(1L, "role1", "description1");
    public static final Role<Long> ROLE2 = new Role<>(2L, "role2", "description2");
    public static final Role<Long> ROLE3 = new Role<>(3L, "role3", "description3");
    public static final Role<Long> UPDATED_ROLE1 = new Role<>(1L, "role1-2", "description1-2");

    public static final Role<Long> FILL_ROLE1 = new Role<>(1L, "role4", "description4");
    public static final Role<Long> UPDATED_FILL_ROLE1 = new Role<>(1L, "role4-2", "description4-2");



    public static final DetailsUser<Long> USER1 = new DetailsUser<>(1L,
            "user1",
            "user1@mail.ru",
            true,
            true,
            true,
            "password1",
            "salt1",
            "secret1");
    public static final DetailsUser<Long> USER2 = new DetailsUser<>(2L,
            "user2",
            "user2@mail.ru",
            true,
            true,
            true,
            "password2",
            "salt2",
            "secret2");
    public static final DetailsUser<Long> USER3 = new DetailsUser<>(3L,
            "user3",
            "user3@mail.ru",
            true,
            true,
            true,
            "password3",
            "salt3",
            "secret3");
    public static final DetailsUser<Long> UPDATED_USER1 = new DetailsUser<>(1L,
            "user1-2",
            "user1-2@mail.ru",
            false,
            false,
            false,
            "password1-2",
            "salt1-2",
            "secret1-2");

    public static final DetailsUser<Long> FILL_USER1 = new DetailsUser<>(1L,
            "user4",
            "user4@mail.ru",
            true,
            true,
            true,
            "password4",
            "salt4",
            "secret4");
    public static final DetailsUser<Long> UPDATED_FILL_USER1 = new DetailsUser<>(1L,
            "user4-2",
            "user4-2@mail.ru",
            false,
            false,
            false,
            "password4-2",
            "salt4-2",
            "secret4-2");



    public static final UserOnRole<Long> USER_ON_ROLE1 = new UserOnRole<>(1L, USER1.getId(), ROLE1.getId());
    public static final UserOnRole<Long> USER_ON_ROLE2 = new UserOnRole<>(2L, USER1.getId(), ROLE2.getId());
    public static final UserOnRole<Long> USER_ON_ROLE3 = new UserOnRole<>(3L, USER2.getId(), ROLE1.getId());
    public static final UserOnRole<Long> UPDATED_USER_ON_ROLE1 = new UserOnRole<>(1L, USER1.getId(), ROLE3.getId());

    public static final UserOnRole<Long> FILL_USER_ON_ROLE1 = new UserOnRole<>(1L, USER3.getId(), ROLE1.getId());
    public static final UserOnRole<Long> UPDATED_FILL_USER_ON_ROLE1 =
            new UserOnRole<>(1L, USER3.getId(), ROLE2.getId());



    public static final PermissionProvider PERMISSION_PROVIDER = new PermissionProvider();
    public static final PermissionOnRole<Long, Permission> PERMISSION_ON_ROLE1 =
            new PermissionOnRole<>(1L, Permission.PERMISSION1.getId(), ROLE1.getId(), PERMISSION_PROVIDER);
    public static final PermissionOnRole<Long, Permission> PERMISSION_ON_ROLE2 =
            new PermissionOnRole<>(2L, Permission.PERMISSION1.getId(), ROLE2.getId(), PERMISSION_PROVIDER);
    public static final PermissionOnRole<Long, Permission> PERMISSION_ON_ROLE3 =
            new PermissionOnRole<>(3L, Permission.PERMISSION2.getId(), ROLE1.getId(), PERMISSION_PROVIDER);
    public static final PermissionOnRole<Long, Permission> UPDATED_PERMISSION_ON_ROLE1 =
            new PermissionOnRole<>(1L, Permission.PERMISSION1.getId(), ROLE3.getId(), PERMISSION_PROVIDER);

    public static final PermissionOnRole<Long, Permission> FILL_PERMISSION_ON_ROLE1 =
            new PermissionOnRole<>(1L, Permission.PERMISSION3.getId(), ROLE1.getId(), PERMISSION_PROVIDER);
    public static final PermissionOnRole<Long, Permission> UPDATED_FILL_PERMISSION_ON_ROLE1 =
            new PermissionOnRole<>(1L, Permission.PERMISSION3.getId(), ROLE2.getId(), PERMISSION_PROVIDER);



    public static final Profile<Long> PROFILE1 = new Profile<>(1L, "name1", "surname1", USER1.getId());
    public static final Profile<Long> PROFILE2 = new Profile<>(2L, "name2", "surname2", USER1.getId());
    public static final Profile<Long> PROFILE3 = new Profile<>(3L, "name3", "surname3", USER2.getId());
    public static final Profile<Long> UPDATED_PROFILE1 = new Profile<>(1L, "name1-2", "surname1-2", USER2.getId());

    public static final Profile<Long> FILL_PROFILE1 = new Profile<>(1L, "name4", "surname4", USER3.getId());
    public static final Profile<Long> UPDATED_FILL_PROFILE1 = new Profile<>(1L, "name4-2", "surname4-2", USER2.getId());



    public static final DetailsProfile<Long> DETAILS_PROFILE1 = new DetailsProfile<>(1L,
            "name1",
            "surname1",
            "middlename1",
            USER1.getId(),
            true,
            new Date(),
            "phone1",
            CITY1.getId());
    public static final DetailsProfile<Long> DETAILS_PROFILE2 = new DetailsProfile<>(2L,
            "name2",
            "surname2",
            "middlename2",
            USER1.getId(),
            true,
            new Date(),
            "phone2",
            CITY1.getId());
    public static final DetailsProfile<Long> DETAILS_PROFILE3 = new DetailsProfile<>(3L,
            "name3",
            "surname3",
            "middlename3",
            USER2.getId(),
            false,
            new Date(),
            "phone3",
            CITY2.getId());
    public static final DetailsProfile<Long> UPDATED_DETAILS_PROFILE1 = new DetailsProfile<>(1L,
            "name1-2",
            "surname1-2",
            "middlename1-2",
            USER2.getId(),
            false,
            new Date(),
            "phone1-2",
            CITY2.getId());

    public static final DetailsProfile<Long> FILL_DETAILS_PROFILE1 = new DetailsProfile<>(1L,
            "name4",
            "surname4",
            "middlename4",
            USER3.getId(),
            true,
            new Date(),
            "phone4",
            CITY3.getId());
    public static final DetailsProfile<Long> UPDATED_FILL_DETAILS_PROFILE1 = new DetailsProfile<>(1L,
            "name4-2",
            "surname4-2",
            "middlename4-2",
            USER2.getId(),
            true,
            new Date(),
            "phone4-2",
            CITY2.getId());



    public static final FileStorage<Long> FILE_STORAGE1 = new FileStorage<>(1L,
            "filename1",
            "extension1",
            "displayname1",
            new Date(),
            1,
            1,
            "filepath1",
            new byte[]{ 1, 2, 3, 4, 5, 6, 7, 8 });
    public static final FileStorage<Long> FILE_STORAGE2 = new FileStorage<>(2L,
            "filename2",
            "extension2",
            "displayname2",
            new Date(),
            2,
            2,
            "filepath2",
            new byte[]{ 1, 2, 3, 4 });
    public static final FileStorage<Long> FILE_STORAGE3 = new FileStorage<>(3L,
            "filename3",
            "extension3",
            "displayname3",
            new Date(),
            3,
            3,
            "filepath3",
            new byte[]{ 1, 2 });
    public static final FileStorage<Long> UPDATED_FILE_STORAGE1 = new FileStorage<>(1L,
            "filename1-2",
            "extension1-2",
            "displayname1-2",
            new Date(),
            2,
            2,
            "filepath1-2",
            new byte[]{ 1, 2, 3, 4, 5, 6 });

    public static final FileStorage<Long> FILL_FILE_STORAGE1 = new FileStorage<>(1L,
            "filename1",
            "extension1",
            "displayname1",
            new Date(),
            1,
            1,
            "filepath1",
            new byte[]{ 1, 2, 3, 4, 5, 6, 7, 8 });
    public static final FileStorage<Long> UPDATED_FILL_FILE_STORAGE1 = new FileStorage<>(1L,
            "filename1-2",
            "extension1-2",
            "displayname1-2",
            new Date(),
            2,
            2,
            "filepath1-2",
            new byte[]{ 1, 2, 3, 4, 5, 6 });



    public static final JWTToken<Long> JWT_TOKEN1 =
            new JWTToken<>(1L,
                    "accessToken1",
                    "refreshToken1",
                    new Date(),
                    new Date(),
                    new Date(),
                    USER1.getId()
            );
    public static final JWTToken<Long> JWT_TOKEN2 =
            new JWTToken<>(2L,
                    "accessToken2",
                    "refreshToken2",
                    new Date(),
                    new Date(),
                    new Date(),
                    USER1.getId()
            );
    public static final JWTToken<Long> JWT_TOKEN3 =
            new JWTToken<>(3L,
                    "accessToken3",
                    "refreshToken3",
                    new Date(),
                    new Date(),
                    new Date(),
                    USER2.getId()
            );
    public static final JWTToken<Long> UPDATED_JWT_TOKEN1 =
            new JWTToken<>(1L,
                    "accessToken1-2",
                    "refreshToken1-2",
                    new Date(),
                    new Date(),
                    new Date(),
                    USER2.getId()
            );

    public static final JWTToken<Long> FILL_JWT_TOKEN1 =
            new JWTToken<>(1L,
                    "accessToken4",
                    "refreshToken4",
                    new Date(),
                    new Date(),
                    new Date(),
                    USER3.getId()
            );
    public static final JWTToken<Long> UPDATED_FILL_JWT_TOKEN1 =
            new JWTToken<>(1L,
                    "accessToken4-2",
                    "refreshToken4-2",
                    new Date(),
                    new Date(),
                    new Date(),
                    USER2.getId()
            );



    static {
        FILL_COUNTRY1.addCity(CITY1);
        FILL_COUNTRY1.addCity(CITY2);
        UPDATED_FILL_COUNTRY1.addCity(CITY1);
        UPDATED_FILL_COUNTRY1.addCity(CITY2);

        FILL_CITY1.setCountry(COUNTRY1);
        UPDATED_FILL_CITY1.setCountry(COUNTRY1);

        FILL_PROFILE1.setUser(USER3);
        UPDATED_FILL_PROFILE1.setUser(USER2);

        FILL_DETAILS_PROFILE1.setUser(USER3);
        FILL_DETAILS_PROFILE1.setCity(CITY3);
        UPDATED_FILL_DETAILS_PROFILE1.setUser(USER2);
        UPDATED_FILL_DETAILS_PROFILE1.setCity(CITY2);

        FILL_USER1.addProfile(PROFILE1);
        FILL_USER1.addProfile(PROFILE2);
        FILL_USER1.addRole(ROLE1);
        FILL_USER1.addRole(ROLE2);
        FILL_USER1.addToken(JWT_TOKEN1);
        FILL_USER1.addToken(JWT_TOKEN2);
        UPDATED_FILL_USER1.addProfile(PROFILE1);
        UPDATED_FILL_USER1.addProfile(PROFILE2);
        UPDATED_FILL_USER1.addRole(ROLE1);
        UPDATED_FILL_USER1.addRole(ROLE2);
        UPDATED_FILL_USER1.addToken(JWT_TOKEN1);
        UPDATED_FILL_USER1.addToken(JWT_TOKEN2);

        FILL_USER_ON_ROLE1.setUser(USER3);
        FILL_USER_ON_ROLE1.setRole(ROLE1);
        UPDATED_FILL_USER_ON_ROLE1.setUser(USER3);
        UPDATED_FILL_USER_ON_ROLE1.setRole(ROLE2);

        FILL_PERMISSION_ON_ROLE1.setRole(ROLE1);
        UPDATED_FILL_PERMISSION_ON_ROLE1.setRole(ROLE2);

        FILL_JWT_TOKEN1.setUser(USER3);
        UPDATED_FILL_JWT_TOKEN1.setUser(USER2);
    }

    private TestData() {
    }
}
