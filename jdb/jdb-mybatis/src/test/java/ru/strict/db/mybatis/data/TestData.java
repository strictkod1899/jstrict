package ru.strict.db.mybatis.data;

import ru.strict.models.*;

import java.util.Date;
import java.util.UUID;

public class TestData {
    public static final Country<Integer> COUNTRY1 = new Country<>(111, "country1");
    public static final Country<Integer> COUNTRY1_UPDATED = new Country<>(111, "country1_updated");
    public static final Country<UUID> COUNTRY1_UUID = new Country<>(UUID.randomUUID(), "country1");

    public static final City<Integer> CITY1 = new City<>(111, "city1", COUNTRY1.getId());
    public static final City<Integer> CITY1_UPDATED = new City<>(111, "city1_updated", COUNTRY1.getId());
    public static final City<UUID> CITY1_UUID = new City<>(UUID.randomUUID(), "city1", COUNTRY1_UUID.getId());

    public static final City<Integer> CITY2 = new City<>(112, "city2", COUNTRY1.getId());
    public static final City<UUID> CITY2_UUID = new City<>(UUID.randomUUID(), "city2", COUNTRY1_UUID.getId());

    public static final Roleuser<Integer> ROLEUSER1 = new Roleuser<>(111, "role1", "description1");
    public static final Roleuser<Integer> ROLEUSER1_UPDATED = new Roleuser<>(111, "role1_updated", "description1_updated");
    public static final Roleuser<UUID> ROLEUSER1_UUID = new Roleuser<>(UUID.randomUUID(), "role1", "description1");

    public static final Roleuser<Integer> ROLEUSER2 = new Roleuser<>(112, "role2", "description2");
    public static final Roleuser<Integer> ROLEUSER2_UPDATED = new Roleuser<>(112, "role2_updated", "description2_updated");
    public static final Roleuser<UUID> ROLEUSER2_UUID = new Roleuser<>(UUID.randomUUID(), "role2", "description2");

    public static final User<Integer> USER1 = new User<>(111, "user1", "user1@mail.ru", "password1");
    public static final User<Integer> USER1_UPDATED = new User<>(111, "user1_updated", "user1_updated@mail.ru", "password1_updated");
    public static final User<UUID> USER1_UUID = new User<>(UUID.randomUUID(), "user1", "user1@mail.ru", "password1");

    public static final User<Integer> USER2 = new User<>(112, "user2", "user2@mail.ru", "password2");
    public static final User<Integer> USER2_UPDATED = new User<>(112, "user2_updated", "user2_updated@mail.ru", "password2_updated");
    public static final User<UUID> USER2_UUID = new User<>(UUID.randomUUID(), "user2", "user2@mail.ru", "password2");

    public static final User<Integer> USER3 = new User<>(113, "user3", "user3@mail.ru", "password3");
    public static final User<UUID> USER3_UUID = new User<>(UUID.randomUUID(), "user3", "user3@mail.ru", "password3");

    public static final User<Integer> USER4 = new User<>(114, "user4", "user4@mail.ru", "password4");
    public static final User<UUID> USER4_UUID = new User<>(UUID.randomUUID(), "user4", "user4@mail.ru", "password4");

    public static final UserOnRole<Integer> USER_ON_ROLE1 = new UserOnRole<>(111, USER1.getId(), ROLEUSER1.getId());
    public static final UserOnRole<Integer> USER_ON_ROLE1_UPDATED = new UserOnRole<>(111, USER2.getId(), ROLEUSER2.getId());
    public static final UserOnRole<UUID> USER_ON_ROLE1_UUID = new UserOnRole<>(UUID.randomUUID(), USER1_UUID.getId(), ROLEUSER1_UUID.getId());

    public static final UserOnRole<Integer> USER_ON_ROLE2 = new UserOnRole<>(USER2.getId(), ROLEUSER1.getId());
    public static final UserOnRole<UUID> USER_ON_ROLE2_UUID = new UserOnRole<>(USER2_UUID.getId(), ROLEUSER1_UUID.getId());

    public static final UserOnRole<Integer> USER_ON_ROLE3 = new UserOnRole<>(113, USER1.getId(), ROLEUSER2.getId());
    public static final UserOnRole<UUID> USER_ON_ROLE3_UUID = new UserOnRole<>(UUID.randomUUID(), USER1_UUID.getId(), ROLEUSER2_UUID.getId());

    public static final UserOnRole<Integer> USER_ON_ROLE4 = new UserOnRole<>(114, USER3.getId(), ROLEUSER1.getId());
    public static final UserOnRole<UUID> USER_ON_ROLE4_UUID = new UserOnRole<>(UUID.randomUUID(), USER3_UUID.getId(), ROLEUSER1_UUID.getId());

    public static final Profile<Integer> PROFILE1 = new Profile<>(111, "name1", "surname1", "middlename2", USER1.getId());
    public static final Profile<Integer> PROFILE1_UPDATED = new Profile<>(111, "name1_updated", "surname1_updated", "middlename2_updated", USER1.getId());
    public static final Profile<UUID> PROFILE1_UUID = new Profile<>(UUID.randomUUID(), "name1", "surname1", "middlename2", USER1_UUID.getId());

    public static final Profile<Integer> PROFILE2 = new Profile<>(112, "name2", "surname2", "middlename2", USER1.getId());

    public static final ProfileDetails<Integer> PROFILE_INFO1 = new ProfileDetails<>(111, "name1", "surname1", "middlename2", USER1.getId(), new Date(), "phone1", CITY1.getId());
    public static final ProfileDetails<Integer> PROFILE_INFO1_UPDATED = new ProfileDetails<>(111, "name1_updated", "surname1_updated", "middlename2_updated", USER1.getId(), new Date(), "phone1_updated", CITY2.getId());
    public static final ProfileDetails<UUID> PROFILE_INFO1_UUID = new ProfileDetails<>(UUID.randomUUID(), "name1", "surname1", "middlename2", USER1_UUID.getId(), new Date(), "phone1", CITY1_UUID.getId());

    public static final ProfileDetails<Integer> PROFILE_INFO2 = new ProfileDetails<>(112, "name2", "surname2", "middlename2", USER1.getId(), new Date(), "phone1", CITY1.getId());

    public static final FileStorage<Integer> FILE_STORAGE1 = new FileStorage<>(111, "filename1", "extension1", "displayname1", "filepath1", new byte[]{ 1, 2, 3, 4, 5, 6, 7, 8 }, new Date(), 1, 1);
    public static final FileStorage<Integer> FILE_STORAGE1_UPDATED = new FileStorage<>(111, "filename1_updated", "extension1_updated", "displayname1_updated", "filepath1_updated", new byte[]{ 1,2, 3, 4, 5, 6}, new Date(), 3, 3);
    public static final FileStorage<UUID> FILE_STORAGE1_UUID = new FileStorage<>(UUID.randomUUID(), "filename1", "extension1", "displayname1", "filepath1", new byte[]{ 1, 2, 3, 4, 5, 6, 7, 8 }, new Date(), 1, 1);

    public static final FileStorage<Integer> FILE_STORAGE2 = new FileStorage<>(112, "filename2", "extension2", "displayname2", "filepath2", new byte[]{ 1, 2, 3, 4, 5, 6, 7, 8 }, new Date(), 2, 2);

    public static final JWTToken<Integer> JWT_TOKEN1 = new JWTToken<>(111, "accessToken1", "refreshToken1", new Date(), new Date(), new Date(), USER1.getId());
    public static final JWTToken<Integer> JWT_TOKEN1_UPDATED = new JWTToken<>(111, "accessToken1_updated", "refreshToken1_updated", new Date(), new Date(), new Date(), USER2.getId());
    public static final JWTToken<UUID> JWT_TOKEN1_UUID = new JWTToken<>(UUID.randomUUID(), "accessToken1", "refreshToken1", new Date(), new Date(), new Date(), USER1_UUID.getId());

    public static final JWTToken<Integer> JWT_TOKEN2 = new JWTToken<>(112, "accessToken2", "refreshToken2", new Date(), new Date(), new Date(), USER1.getId());

    public static final ServiceOnRole<Integer, ServiceModel> SERVICE_ON_ROLE1 = new ServiceOnRole<>(111, ServiceModel.SERVICE1.getId(), ROLEUSER1.getId());
    public static final ServiceOnRole<Integer, ServiceModel> SERVICE_ON_ROLE1_UPDATED = new ServiceOnRole<>(111, ServiceModel.SERVICE2.getId(), ROLEUSER2.getId());
    public static final ServiceOnRole<UUID, ServiceModel> SERVICE_ON_ROLE1_UUID = new ServiceOnRole<>(UUID.randomUUID(), ServiceModel.SERVICE1.getId(), ROLEUSER1_UUID.getId());

    public static final ServiceOnRole<Integer, ServiceModel> SERVICE_ON_ROLE2 = new ServiceOnRole<>(ServiceModel.SERVICE2.getId(), ROLEUSER1.getId());
    public static final ServiceOnRole<UUID, ServiceModel> SERVICE_ON_ROLE2_UUID = new ServiceOnRole<>(ServiceModel.SERVICE2.getId(), ROLEUSER1_UUID.getId());

    public static final ServiceOnRole<Integer, ServiceModel> SERVICE_ON_ROLE3 = new ServiceOnRole<>(113, ServiceModel.SERVICE1.getId(), ROLEUSER2.getId());
    public static final ServiceOnRole<UUID, ServiceModel> SERVICE_ON_ROLE3_UUID = new ServiceOnRole<>(UUID.randomUUID(), ServiceModel.SERVICE1.getId(), ROLEUSER2_UUID.getId());

    public static final ServiceOnRole<Integer, ServiceModel> SERVICE_ON_ROLE4 = new ServiceOnRole<>(114, ServiceModel.SERVICE3.getId(), ROLEUSER1.getId());
    public static final ServiceOnRole<UUID, ServiceModel> SERVICE_ON_ROLE4_UUID = new ServiceOnRole<>(UUID.randomUUID(), ServiceModel.SERVICE3.getId(), ROLEUSER1_UUID.getId());

}
