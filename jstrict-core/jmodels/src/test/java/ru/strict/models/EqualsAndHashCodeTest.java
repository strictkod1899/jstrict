package ru.strict.models;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Date;

@RunWith(JUnit4.class)
public class EqualsAndHashCodeTest {

    @Test
    public void testCountryAndCity() {
        Country country1 = new Country(1, "country1");
        Country country2 = new Country(2, "country2");
        Country country3 = new Country(1, "country1");

        City city1 = new City(1, "city1", 1);
        city1.setCountry(country1);
        City city2 = new City(2, "city2", 1);
        city2.setCountry(country1);
        City city3 = new City(3, "city3", 2);
        city3.setCountry(country2);
        City city4 = new City(1, "city1", 1);
        city4.setCountry(country3);
        City city5 = new City(2, "city2", 1);
        city5.setCountry(country3);

        /* -------------------------------
         * Country tests
         * -------------------------------
         */
        Assert.assertEquals(country1, country3);
        Assert.assertEquals(country1.hashCode(), country3.hashCode());
        Assert.assertEquals(country1.clone(), country3.clone());
        Assert.assertEquals(country1, country1.clone());
        Assert.assertEquals(country1.hashCode(), country1.clone().hashCode());

        Assert.assertNotEquals(country1, country2);
        Assert.assertNotEquals(country3, country2);
        Assert.assertNotEquals(country1.hashCode(), country2.hashCode());
        Assert.assertNotEquals(country1.clone(), country2);
        Assert.assertNotEquals(country1.clone(), country2.clone());

        /* -------------------------------
         * City tests
         * -------------------------------
         */
        Assert.assertEquals(city1, city4);
        Assert.assertEquals(city1.hashCode(), city4.hashCode());
        Assert.assertEquals(city1.clone(), city4.clone());
        Assert.assertEquals(city1, city1.clone());
        Assert.assertEquals(city1.hashCode(), city1.clone().hashCode());

        Assert.assertNotEquals(city1, city3);
        Assert.assertNotEquals(city4, city3);
        Assert.assertNotEquals(city1.hashCode(), city3.hashCode());
        Assert.assertNotEquals(city1.clone(), city3);
        Assert.assertNotEquals(city1.clone(), city3.clone());
    }

    @Test
    public void testFileStorage() {
        Date date1 = new Date();

        FileStorage fileStorage1 = new FileStorage(1, "filename", "extension", "displayName", date1, 1, 1, "filePath",
                new byte[]{ 1, 2, 3 });
        FileStorage fileStorage2 = new FileStorage(2, "filename2", "extension2", "displayName2", date1, 2, 2, "filePath2",
                new byte[]{ 1, 2, 3, 4 });
        FileStorage fileStorage3 = new FileStorage(3, "filename", "extension", "displayName", date1, 1, 1, "filePath",
                new byte[]{ 1, 2, 3 });

        Assert.assertEquals(fileStorage1, fileStorage3);
        Assert.assertEquals(fileStorage1.hashCode(), fileStorage3.hashCode());
        Assert.assertEquals(fileStorage1.clone(), fileStorage3.clone());
        Assert.assertEquals(fileStorage1, fileStorage1.clone());
        Assert.assertEquals(fileStorage1.hashCode(), fileStorage1.clone().hashCode());

        Assert.assertNotEquals(fileStorage1, fileStorage2);
        Assert.assertNotEquals(fileStorage3, fileStorage2);
        Assert.assertNotEquals(fileStorage1.hashCode(), fileStorage2.hashCode());
        Assert.assertNotEquals(fileStorage1.clone(), fileStorage2);
        Assert.assertNotEquals(fileStorage1.clone(), fileStorage2.clone());
    }

    @Test
    public void testJWTToken() {
        Date date1 = new Date();
        User user = new User();
        user.setId(1);

        JWTToken jwtToken1 = new JWTToken(1, "accessToken", "refreshToken", date1, date1, date1, 1);
        jwtToken1.setUser(user);
        JWTToken jwtToken2 = new JWTToken(2, "accessToken2", "refreshToken2", date1, date1, date1, 1);
        jwtToken2.setUser(user);
        JWTToken jwtToken3 = new JWTToken(3, "accessToken", "refreshToken", date1, date1, date1, 1);
        jwtToken3.setUser(user);

        Assert.assertEquals(jwtToken1, jwtToken3);
        Assert.assertEquals(jwtToken1.hashCode(), jwtToken3.hashCode());
        Assert.assertEquals(jwtToken1.clone(), jwtToken3.clone());
        Assert.assertEquals(jwtToken1, jwtToken1.clone());
        Assert.assertEquals(jwtToken1.hashCode(), jwtToken1.clone().hashCode());

        Assert.assertNotEquals(jwtToken1, jwtToken2);
        Assert.assertNotEquals(jwtToken3, jwtToken2);
        Assert.assertNotEquals(jwtToken1.hashCode(), jwtToken2.hashCode());
        Assert.assertNotEquals(jwtToken1.clone(), jwtToken2);
        Assert.assertNotEquals(jwtToken1.clone(), jwtToken2.clone());
    }

    @Test
    public void testToken() {
        Date date1 = new Date();

        Token token1 = new Token(1, "accessToken", "refreshToken", date1, date1, date1);
        Token token2 = new Token(2, "accessToken2", "refreshToken2", date1, date1, date1);
        Token token3 = new Token(3, "accessToken", "refreshToken", date1, date1, date1);

        Assert.assertEquals(token1, token3);
        Assert.assertEquals(token1.hashCode(), token3.hashCode());
        Assert.assertEquals(token1.clone(), token3.clone());
        Assert.assertEquals(token1, token1.clone());
        Assert.assertEquals(token1.hashCode(), token1.clone().hashCode());

        Assert.assertNotEquals(token1, token2);
        Assert.assertNotEquals(token3, token2);
        Assert.assertNotEquals(token1.hashCode(), token2.hashCode());
        Assert.assertNotEquals(token1.clone(), token2);
        Assert.assertNotEquals(token1.clone(), token2.clone());
    }

    @Test
    public void testProfile() {
        DetailsUser user = new DetailsUser();
        user.setId(1);

        Profile profile1 = new Profile(1, "name", "surname", 1);
        profile1.setUser(user);
        Profile profile2 = new Profile(2, "name2", "surname2", 1);
        profile2.setUser(user);
        Profile profile3 = new Profile(3, "name", "surname", 1);
        profile3.setUser(user);

        Assert.assertEquals(profile1, profile3);
        Assert.assertEquals(profile1.hashCode(), profile3.hashCode());
        Assert.assertEquals(profile1.clone(), profile3.clone());
        Assert.assertEquals(profile1, profile1.clone());
        Assert.assertEquals(profile1.hashCode(), profile1.clone().hashCode());

        Assert.assertNotEquals(profile1, profile2);
        Assert.assertNotEquals(profile3, profile2);
        Assert.assertNotEquals(profile1.hashCode(), profile2.hashCode());
        Assert.assertNotEquals(profile1.clone(), profile2);
        Assert.assertNotEquals(profile1.clone(), profile2.clone());
    }

    @Test
    public void testProfileInfo() {
        Date date = new Date();
        DetailsUser user = new DetailsUser();
        user.setId(1);
        City city = new City();
        city.setId(1);

        DetailsProfile detailsProfile1 =
                new DetailsProfile(1, "name", "surname", "middlename", 1, true, date, "phone", 1);
        detailsProfile1.setUser(user);
        detailsProfile1.setCity(city);
        DetailsProfile detailsProfile2 =
                new DetailsProfile(2, "name2", "surname2", "middlename2", 1, false, date, "phone2", 1);
        detailsProfile2.setUser(user);
        detailsProfile2.setCity(city);
        DetailsProfile detailsProfile3 =
                new DetailsProfile(3, "name", "surname", "middlename", 1, true, date, "phone", 1);
        detailsProfile3.setUser(user);
        detailsProfile3.setCity(city);

        Assert.assertEquals(detailsProfile1, detailsProfile3);
        Assert.assertEquals(detailsProfile1.hashCode(), detailsProfile3.hashCode());
        Assert.assertEquals(detailsProfile1.clone(), detailsProfile3.clone());
        Assert.assertEquals(detailsProfile1, detailsProfile1.clone());
        Assert.assertEquals(detailsProfile1.hashCode(), detailsProfile1.clone().hashCode());

        Assert.assertNotEquals(detailsProfile1, detailsProfile2);
        Assert.assertNotEquals(detailsProfile3, detailsProfile2);
        Assert.assertNotEquals(detailsProfile1.hashCode(), detailsProfile2.hashCode());
        Assert.assertNotEquals(detailsProfile1.clone(), detailsProfile2);
        Assert.assertNotEquals(detailsProfile1.clone(), detailsProfile2.clone());
    }

    @Test
    public void testRole() {
        DetailsUser user = new DetailsUser();
        user.setId(1);

        Role role1 = new Role(1, "code", "description");
        role1.addUser(user);
        Role role2 = new Role(2, "code2", "description2");
        role2.addUser(user);
        Role role3 = new Role(3, "code", "description");
        role3.addUser(user);

        Assert.assertEquals(role1, role3);
        Assert.assertEquals(role1.hashCode(), role3.hashCode());
        Assert.assertEquals(role1.clone(), role3.clone());
        Assert.assertEquals(role1, role1.clone());
        Assert.assertEquals(role1.hashCode(), role1.clone().hashCode());

        Assert.assertNotEquals(role1, role2);
        Assert.assertNotEquals(role3, role2);
        Assert.assertNotEquals(role1.hashCode(), role2.hashCode());
        Assert.assertNotEquals(role1.clone(), role2);
        Assert.assertNotEquals(role1.clone(), role2.clone());
    }

    @Test
    public void testUser() {
        Date date1 = new Date();
        Profile profile1 = new Profile(1, "name", "surname", 1);
        Role role1 = new Role(1, "code", "description");
        JWTToken jwtToken1 = new JWTToken(1, "accessToken", "refreshToken", date1, date1, date1, 1);

        DetailsUser user1 = new DetailsUser(1, "username", "password", "email", "salt", "secret");
        user1.addProfile(profile1);
        user1.addRole(role1);
        user1.addToken(jwtToken1);
        DetailsUser user2 = new DetailsUser(2, "username2", "password2", "email2", "salt2", "secret2");
        user2.addProfile(profile1);
        user2.addRole(role1);
        user2.addToken(jwtToken1);
        DetailsUser user3 = new DetailsUser(3, "username", "password", "email", "salt", "secret");
        user3.addProfile(profile1);
        user3.addRole(role1);
        user3.addToken(jwtToken1);

        Assert.assertEquals(user1, user3);
        Assert.assertEquals(user1.hashCode(), user3.hashCode());
        Assert.assertEquals(user1.clone(), user3.clone());
        Assert.assertEquals(user1, user1.clone());
        Assert.assertEquals(user1.hashCode(), user1.clone().hashCode());

        Assert.assertNotEquals(user1, user2);
        Assert.assertNotEquals(user3, user2);
        Assert.assertNotEquals(user1.hashCode(), user2.hashCode());
        Assert.assertNotEquals(user1.clone(), user2);
        Assert.assertNotEquals(user1.clone(), user2.clone());
    }

    @Test
    public void testPermissionOnRole() {
        Role role = new Role(1, "code", "description");

        PermissionOnRole permissionOnRole1 = new PermissionOnRole(1, 1, 1);
        permissionOnRole1.setRole(role);
        PermissionOnRole permissionOnRole2 = new PermissionOnRole(2, 2, 1);
        permissionOnRole2.setRole(role);
        PermissionOnRole permissionOnRole3 = new PermissionOnRole(3, 1, 1);
        permissionOnRole3.setRole(role);

        Assert.assertEquals(permissionOnRole1, permissionOnRole3);
        Assert.assertEquals(permissionOnRole1.hashCode(), permissionOnRole3.hashCode());
        Assert.assertEquals(permissionOnRole1.clone(), permissionOnRole3.clone());
        Assert.assertEquals(permissionOnRole1, permissionOnRole1.clone());
        Assert.assertEquals(permissionOnRole1.hashCode(), permissionOnRole1.clone().hashCode());

        Assert.assertNotEquals(permissionOnRole1, permissionOnRole2);
        Assert.assertNotEquals(permissionOnRole3, permissionOnRole2);
        Assert.assertNotEquals(permissionOnRole1.hashCode(), permissionOnRole2.hashCode());
        Assert.assertNotEquals(permissionOnRole1.clone(), permissionOnRole2);
        Assert.assertNotEquals(permissionOnRole1.clone(), permissionOnRole2.clone());
    }

    @Test
    public void testUserOnRole() {
        Role role = new Role(1, "code", "description");
        DetailsUser user = new DetailsUser(1, "username", "password", "email", "salt", "secret");

        UserOnRole userOnRole1 = new UserOnRole(1, 1, 1);
        userOnRole1.setRole(role);
        userOnRole1.setUser(user);
        UserOnRole userOnRole2 = new UserOnRole(2, 2, 2);
        userOnRole2.setRole(role);
        userOnRole2.setUser(user);
        UserOnRole userOnRole3 = new UserOnRole(3, 1, 1);
        userOnRole3.setRole(role);
        userOnRole3.setUser(user);

        Assert.assertEquals(userOnRole1, userOnRole3);
        Assert.assertEquals(userOnRole1.hashCode(), userOnRole3.hashCode());
        Assert.assertEquals(userOnRole1.clone(), userOnRole3.clone());
        Assert.assertEquals(userOnRole1, userOnRole1.clone());
        Assert.assertEquals(userOnRole1.hashCode(), userOnRole1.clone().hashCode());

        Assert.assertNotEquals(userOnRole1, userOnRole2);
        Assert.assertNotEquals(userOnRole3, userOnRole2);
        Assert.assertNotEquals(userOnRole1.hashCode(), userOnRole2.hashCode());
        Assert.assertNotEquals(userOnRole1.clone(), userOnRole2);
        Assert.assertNotEquals(userOnRole1.clone(), userOnRole2.clone());
    }
}
