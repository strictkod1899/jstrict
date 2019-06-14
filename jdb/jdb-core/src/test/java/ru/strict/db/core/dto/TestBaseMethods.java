package ru.strict.db.core.dto;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.models.*;

import java.util.Date;

@RunWith(JUnit4.class)
public class TestBaseMethods {

    @Test
    public void testCountryAndCity(){
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
        Assert.assertTrue(country1.equals(country3));
        Assert.assertEquals(country1, country3);
        Assert.assertEquals(country1.hashCode(), country3.hashCode());
        Assert.assertEquals(country1.clone(), country3.clone());
        Assert.assertEquals(country1, country1.clone());
        Assert.assertEquals(country1.hashCode(), country1.clone().hashCode());

        Assert.assertFalse(country1.equals(country2));
        Assert.assertNotEquals(country1, country2);
        Assert.assertNotEquals(country3, country2);
        Assert.assertNotEquals(country1.hashCode(), country2.hashCode());
        Assert.assertNotEquals(country1.clone(), country2);
        Assert.assertNotEquals(country1.clone(), country2.clone());

        /* -------------------------------
         * City tests
         * -------------------------------
         */
        Assert.assertTrue(city1.equals(city4));
        Assert.assertEquals(city1, city4);
        Assert.assertEquals(city1.hashCode(), city4.hashCode());
        Assert.assertEquals(city1.clone(), city4.clone());
        Assert.assertEquals(city1, city1.clone());
        Assert.assertEquals(city1.hashCode(), city1.clone().hashCode());

        Assert.assertFalse(city1.equals(city3));
        Assert.assertNotEquals(city1, city3);
        Assert.assertNotEquals(city4, city3);
        Assert.assertNotEquals(city1.hashCode(), city3.hashCode());
        Assert.assertNotEquals(city1.clone(), city3);
        Assert.assertNotEquals(city1.clone(), city3.clone());
    }

    @Test
    public void testFileStorage(){
        Date date1 = new Date();

        FileStorage fileStorage1 = new FileStorage(1, "filename", "extension", "displayName", "filePath",
                new byte[]{1, 2, 3}, date1, 1, 1);
        FileStorage fileStorage2 = new FileStorage(2, "filename", "extension", "displayName", "filePath",
                new byte[]{1, 2, 3}, date1, 1, 1);
        FileStorage fileStorage3 = new FileStorage(1, "filename", "extension", "displayName", "filePath",
                new byte[]{1, 2, 3}, date1, 1, 1);

        Assert.assertTrue(fileStorage1.equals(fileStorage3));
        Assert.assertEquals(fileStorage1, fileStorage3);
        Assert.assertEquals(fileStorage1.hashCode(), fileStorage3.hashCode());
        Assert.assertEquals(fileStorage1.clone(), fileStorage3.clone());
        Assert.assertEquals(fileStorage1, fileStorage1.clone());
        Assert.assertEquals(fileStorage1.hashCode(), fileStorage1.clone().hashCode());

        Assert.assertFalse(fileStorage1.equals(fileStorage2));
        Assert.assertNotEquals(fileStorage1, fileStorage2);
        Assert.assertNotEquals(fileStorage3, fileStorage2);
        Assert.assertNotEquals(fileStorage1.hashCode(), fileStorage2.hashCode());
        Assert.assertNotEquals(fileStorage1.clone(), fileStorage2);
        Assert.assertNotEquals(fileStorage1.clone(), fileStorage2.clone());
    }

    @Test
    public void testJWTToken(){
        Date date1 = new Date();
        UserWithToken user = new UserWithToken();
        user.setId(1);

        JWTToken jwtToken1 = new JWTToken(1, "accessToken", "refreshToken", date1, date1, date1, 1);
        jwtToken1.setUser(user);
        JWTToken jwtToken2 = new JWTToken(2, "accessToken", "refreshToken", date1, date1, date1, 1);
        jwtToken2.setUser(user);
        JWTToken jwtToken3 = new JWTToken(1, "accessToken", "refreshToken", date1, date1, date1, 1);
        jwtToken3.setUser(user);

        Assert.assertTrue(jwtToken1.equals(jwtToken3));
        Assert.assertEquals(jwtToken1, jwtToken3);
        Assert.assertEquals(jwtToken1.hashCode(), jwtToken3.hashCode());
        Assert.assertEquals(jwtToken1.clone(), jwtToken3.clone());
        Assert.assertEquals(jwtToken1, jwtToken1.clone());
        Assert.assertEquals(jwtToken1.hashCode(), jwtToken1.clone().hashCode());

        Assert.assertFalse(jwtToken1.equals(jwtToken2));
        Assert.assertNotEquals(jwtToken1, jwtToken2);
        Assert.assertNotEquals(jwtToken3, jwtToken2);
        Assert.assertNotEquals(jwtToken1.hashCode(), jwtToken2.hashCode());
        Assert.assertNotEquals(jwtToken1.clone(), jwtToken2);
        Assert.assertNotEquals(jwtToken1.clone(), jwtToken2.clone());
    }

    @Test
    public void testToken(){
        Date date1 = new Date();

        Token token1 = new Token(1, "accessToken", "refreshToken", date1, date1, date1);
        Token token2 = new Token(2, "accessToken", "refreshToken", date1, date1, date1);
        Token token3 = new Token(1, "accessToken", "refreshToken", date1, date1, date1);

        Assert.assertTrue(token1.equals(token3));
        Assert.assertEquals(token1, token3);
        Assert.assertEquals(token1.hashCode(), token3.hashCode());
        Assert.assertEquals(token1.clone(), token3.clone());
        Assert.assertEquals(token1, token1.clone());
        Assert.assertEquals(token1.hashCode(), token1.clone().hashCode());

        Assert.assertFalse(token1.equals(token2));
        Assert.assertNotEquals(token1, token2);
        Assert.assertNotEquals(token3, token2);
        Assert.assertNotEquals(token1.hashCode(), token2.hashCode());
        Assert.assertNotEquals(token1.clone(), token2);
        Assert.assertNotEquals(token1.clone(), token2.clone());
    }

    @Test
    public void testProfile(){
        User user = new User();
        user.setId(1);

        Profile profile1 = new Profile(1, "name", "surname", "middlename", 1);
        profile1.setUser(user);
        Profile profile2 = new Profile(2, "name", "surname", "middlename", 1);
        profile2.setUser(user);
        Profile profile3 = new Profile(1, "name", "surname", "middlename", 1);
        profile3.setUser(user);

        Assert.assertTrue(profile1.equals(profile3));
        Assert.assertEquals(profile1, profile3);
        Assert.assertEquals(profile1.hashCode(), profile3.hashCode());
        Assert.assertEquals(profile1.clone(), profile3.clone());
        Assert.assertEquals(profile1, profile1.clone());
        Assert.assertEquals(profile1.hashCode(), profile1.clone().hashCode());

        Assert.assertFalse(profile1.equals(profile2));
        Assert.assertNotEquals(profile1, profile2);
        Assert.assertNotEquals(profile3, profile2);
        Assert.assertNotEquals(profile1.hashCode(), profile2.hashCode());
        Assert.assertNotEquals(profile1.clone(), profile2);
        Assert.assertNotEquals(profile1.clone(), profile2.clone());
    }

    @Test
    public void testProfileInfo(){
        Date date = new Date();
        User user = new User();
        user.setId(1);
        City city = new City();
        city.setId(1);

        ProfileDetails profileDetails1 = new ProfileDetails(1, "name", "surname", "middlename", 1, date, "phone", 1);
        profileDetails1.setUser(user);
        profileDetails1.setCity(city);
        ProfileDetails profileDetails2 = new ProfileDetails(2, "name", "surname", "middlename", 1, date, "phone", 1);
        profileDetails2.setUser(user);
        profileDetails2.setCity(city);
        ProfileDetails profileDetails3 = new ProfileDetails(1, "name", "surname", "middlename", 1, date, "phone", 1);
        profileDetails3.setUser(user);
        profileDetails3.setCity(city);

        Assert.assertTrue(profileDetails1.equals(profileDetails3));
        Assert.assertEquals(profileDetails1, profileDetails3);
        Assert.assertEquals(profileDetails1.hashCode(), profileDetails3.hashCode());
        Assert.assertEquals(profileDetails1.clone(), profileDetails3.clone());
        Assert.assertEquals(profileDetails1, profileDetails1.clone());
        Assert.assertEquals(profileDetails1.hashCode(), profileDetails1.clone().hashCode());

        Assert.assertFalse(profileDetails1.equals(profileDetails2));
        Assert.assertNotEquals(profileDetails1, profileDetails2);
        Assert.assertNotEquals(profileDetails3, profileDetails2);
        Assert.assertNotEquals(profileDetails1.hashCode(), profileDetails2.hashCode());
        Assert.assertNotEquals(profileDetails1.clone(), profileDetails2);
        Assert.assertNotEquals(profileDetails1.clone(), profileDetails2.clone());
    }

    @Test
    public void testRoleuser(){
        User user = new User();
        user.setId(1);

        Roleuser role1 = new Roleuser(1, "code", "description");
        role1.addUser(user);
        Roleuser role2 = new Roleuser(2, "code", "description");
        role2.addUser(user);
        Roleuser role3 = new Roleuser(1, "code", "description");
        role3.addUser(user);

        Assert.assertTrue(role1.equals(role3));
        Assert.assertEquals(role1, role3);
        Assert.assertEquals(role1.hashCode(), role3.hashCode());
        Assert.assertEquals(role1.clone(), role3.clone());
        Assert.assertEquals(role1, role1.clone());
        Assert.assertEquals(role1.hashCode(), role1.clone().hashCode());

        Assert.assertFalse(role1.equals(role2));
        Assert.assertNotEquals(role1, role2);
        Assert.assertNotEquals(role3, role2);
        Assert.assertNotEquals(role1.hashCode(), role2.hashCode());
        Assert.assertNotEquals(role1.clone(), role2);
        Assert.assertNotEquals(role1.clone(), role2.clone());
    }

    @Test
    public void testUser(){
        Date date1 = new Date();
        Profile profile1 = new Profile(1, "name", "surname", "middlename", 1);
        Roleuser role1 = new Roleuser(1, "code", "description");
        JWTToken jwtToken1 = new JWTToken(1, "accessToken", "refreshToken", date1, date1, date1, 1);

        UserWithToken user1 = new UserWithToken(1, "username", "password", "email");
        user1.addProfile(profile1);
        user1.addRole(role1);
        user1.addToken(jwtToken1);
        UserWithToken user2 = new UserWithToken(2, "username", "password", "email");
        user2.addProfile(profile1);
        user2.addRole(role1);
        user2.addToken(jwtToken1);
        UserWithToken user3 = new UserWithToken(1, "username", "password", "email");
        user3.addProfile(profile1);
        user3.addRole(role1);
        user3.addToken(jwtToken1);

        Assert.assertTrue(user1.equals(user3));
        Assert.assertEquals(user1, user3);
        Assert.assertEquals(user1.hashCode(), user3.hashCode());
        Assert.assertEquals(user1.clone(), user3.clone());
        Assert.assertEquals(user1, user1.clone());
        Assert.assertEquals(user1.hashCode(), user1.clone().hashCode());

        Assert.assertFalse(user1.equals(user2));
        Assert.assertNotEquals(user1, user2);
        Assert.assertNotEquals(user3, user2);
        Assert.assertNotEquals(user1.hashCode(), user2.hashCode());
        Assert.assertNotEquals(user1.clone(), user2);
        Assert.assertNotEquals(user1.clone(), user2.clone());
    }

    @Test
    public void testServiceOnRole(){
        Roleuser role = new Roleuser(1, "code", "description");

        ServiceOnRole serviceOnRole1 = new ServiceOnRole(1, 1, 1);
        serviceOnRole1.setRole(role);
        ServiceOnRole serviceOnRole2 = new ServiceOnRole(2, 1, 1);
        serviceOnRole2.setRole(role);
        ServiceOnRole serviceOnRole3 = new ServiceOnRole(1, 1, 1);
        serviceOnRole3.setRole(role);

        Assert.assertTrue(serviceOnRole1.equals(serviceOnRole3));
        Assert.assertEquals(serviceOnRole1, serviceOnRole3);
        Assert.assertEquals(serviceOnRole1.hashCode(), serviceOnRole3.hashCode());
        Assert.assertEquals(serviceOnRole1.clone(), serviceOnRole3.clone());
        Assert.assertEquals(serviceOnRole1, serviceOnRole1.clone());
        Assert.assertEquals(serviceOnRole1.hashCode(), serviceOnRole1.clone().hashCode());

        Assert.assertFalse(serviceOnRole1.equals(serviceOnRole2));
        Assert.assertNotEquals(serviceOnRole1, serviceOnRole2);
        Assert.assertNotEquals(serviceOnRole3, serviceOnRole2);
        Assert.assertNotEquals(serviceOnRole1.hashCode(), serviceOnRole2.hashCode());
        Assert.assertNotEquals(serviceOnRole1.clone(), serviceOnRole2);
        Assert.assertNotEquals(serviceOnRole1.clone(), serviceOnRole2.clone());
    }

    @Test
    public void testUserOnRole(){
        Roleuser role = new Roleuser(1, "code", "description");
        User user = new User(1, "username", "password", "email");

        UserOnRole userOnRole1 = new UserOnRole(1, 1, 1);
        userOnRole1.setRole(role);
        userOnRole1.setUser(user);
        UserOnRole userOnRole2 = new UserOnRole(2, 1, 1);
        userOnRole2.setRole(role);
        userOnRole2.setUser(user);
        UserOnRole userOnRole3 = new UserOnRole(1, 1, 1);
        userOnRole3.setRole(role);
        userOnRole3.setUser(user);

        Assert.assertTrue(userOnRole1.equals(userOnRole3));
        Assert.assertEquals(userOnRole1, userOnRole3);
        Assert.assertEquals(userOnRole1.hashCode(), userOnRole3.hashCode());
        Assert.assertEquals(userOnRole1.clone(), userOnRole3.clone());
        Assert.assertEquals(userOnRole1, userOnRole1.clone());
        Assert.assertEquals(userOnRole1.hashCode(), userOnRole1.clone().hashCode());

        Assert.assertFalse(userOnRole1.equals(userOnRole2));
        Assert.assertNotEquals(userOnRole1, userOnRole2);
        Assert.assertNotEquals(userOnRole3, userOnRole2);
        Assert.assertNotEquals(userOnRole1.hashCode(), userOnRole2.hashCode());
        Assert.assertNotEquals(userOnRole1.clone(), userOnRole2);
        Assert.assertNotEquals(userOnRole1.clone(), userOnRole2.clone());
    }
}
