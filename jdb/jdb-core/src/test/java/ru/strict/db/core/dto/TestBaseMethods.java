package ru.strict.db.core.dto;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.db.core.dto.*;

import java.util.Date;

@RunWith(JUnit4.class)
public class TestBaseMethods {

    @Test
    public void testCountryAndCity(){
        DtoCountry country1 = new DtoCountry(1, "country1");
        DtoCountry country2 = new DtoCountry(2, "country2");
        DtoCountry country3 = new DtoCountry(1, "country1");

        DtoCity city1 = new DtoCity(1, "city1", 1);
        city1.setCountry(country1);
        DtoCity city2 = new DtoCity(2, "city2", 1);
        city2.setCountry(country1);
        DtoCity city3 = new DtoCity(3, "city3", 2);
        city3.setCountry(country2);
        DtoCity city4 = new DtoCity(1, "city1", 1);
        city4.setCountry(country3);
        DtoCity city5 = new DtoCity(2, "city2", 1);
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

        DtoFileStorage fileStorage1 = new DtoFileStorage(1, "filename", "extension", "displayName", "filePath",
                new byte[]{1, 2, 3}, date1, 1, 1);
        DtoFileStorage fileStorage2 = new DtoFileStorage(2, "filename", "extension", "displayName", "filePath",
                new byte[]{1, 2, 3}, date1, 1, 1);
        DtoFileStorage fileStorage3 = new DtoFileStorage(1, "filename", "extension", "displayName", "filePath",
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
        DtoUserWithToken user = new DtoUserWithToken();
        user.setId(1);

        DtoJWTToken jwtToken1 = new DtoJWTToken(1, "accessToken", "refreshToken", date1, date1, date1, 1);
        jwtToken1.setUser(user);
        DtoJWTToken jwtToken2 = new DtoJWTToken(2, "accessToken", "refreshToken", date1, date1, date1, 1);
        jwtToken2.setUser(user);
        DtoJWTToken jwtToken3 = new DtoJWTToken(1, "accessToken", "refreshToken", date1, date1, date1, 1);
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

        DtoToken token1 = new DtoToken(1, "accessToken", "refreshToken", date1, date1, date1);
        DtoToken token2 = new DtoToken(2, "accessToken", "refreshToken", date1, date1, date1);
        DtoToken token3 = new DtoToken(1, "accessToken", "refreshToken", date1, date1, date1);

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
        DtoUser user = new DtoUser();
        user.setId(1);

        DtoProfile profile1 = new DtoProfile(1, "name", "surname", "middlename", 1);
        profile1.setUser(user);
        DtoProfile profile2 = new DtoProfile(2, "name", "surname", "middlename", 1);
        profile2.setUser(user);
        DtoProfile profile3 = new DtoProfile(1, "name", "surname", "middlename", 1);
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
        DtoUser user = new DtoUser();
        user.setId(1);
        DtoCity city = new DtoCity();
        city.setId(1);

        DtoProfileInfo profileInfo1 = new DtoProfileInfo(1, "name", "surname", "middlename", 1, date, "phone", 1);
        profileInfo1.setUser(user);
        profileInfo1.setCity(city);
        DtoProfileInfo profileInfo2 = new DtoProfileInfo(2, "name", "surname", "middlename", 1, date, "phone", 1);
        profileInfo2.setUser(user);
        profileInfo2.setCity(city);
        DtoProfileInfo profileInfo3 = new DtoProfileInfo(1, "name", "surname", "middlename", 1, date, "phone", 1);
        profileInfo3.setUser(user);
        profileInfo3.setCity(city);

        Assert.assertTrue(profileInfo1.equals(profileInfo3));
        Assert.assertEquals(profileInfo1, profileInfo3);
        Assert.assertEquals(profileInfo1.hashCode(), profileInfo3.hashCode());
        Assert.assertEquals(profileInfo1.clone(), profileInfo3.clone());
        Assert.assertEquals(profileInfo1, profileInfo1.clone());
        Assert.assertEquals(profileInfo1.hashCode(), profileInfo1.clone().hashCode());

        Assert.assertFalse(profileInfo1.equals(profileInfo2));
        Assert.assertNotEquals(profileInfo1, profileInfo2);
        Assert.assertNotEquals(profileInfo3, profileInfo2);
        Assert.assertNotEquals(profileInfo1.hashCode(), profileInfo2.hashCode());
        Assert.assertNotEquals(profileInfo1.clone(), profileInfo2);
        Assert.assertNotEquals(profileInfo1.clone(), profileInfo2.clone());
    }

    @Test
    public void testRoleuser(){
        DtoUser user = new DtoUser();
        user.setId(1);

        DtoRoleuser role1 = new DtoRoleuser(1, "code", "description");
        role1.addUser(user);
        DtoRoleuser role2 = new DtoRoleuser(2, "code", "description");
        role2.addUser(user);
        DtoRoleuser role3 = new DtoRoleuser(1, "code", "description");
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
        DtoProfile profile1 = new DtoProfile(1, "name", "surname", "middlename", 1);
        DtoRoleuser role1 = new DtoRoleuser(1, "code", "description");
        DtoJWTToken jwtToken1 = new DtoJWTToken(1, "accessToken", "refreshToken", date1, date1, date1, 1);

        DtoUserWithToken user1 = new DtoUserWithToken(1, "username", "password", "email");
        user1.addProfile(profile1);
        user1.addRole(role1);
        user1.addToken(jwtToken1);
        DtoUserWithToken user2 = new DtoUserWithToken(2, "username", "password", "email");
        user2.addProfile(profile1);
        user2.addRole(role1);
        user2.addToken(jwtToken1);
        DtoUserWithToken user3 = new DtoUserWithToken(1, "username", "password", "email");
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
        DtoRoleuser role = new DtoRoleuser(1, "code", "description");

        DtoServiceOnRole serviceOnRole1 = new DtoServiceOnRole(1, 1, 1);
        serviceOnRole1.setRole(role);
        DtoServiceOnRole serviceOnRole2 = new DtoServiceOnRole(2, 1, 1);
        serviceOnRole2.setRole(role);
        DtoServiceOnRole serviceOnRole3 = new DtoServiceOnRole(1, 1, 1);
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
        DtoRoleuser role = new DtoRoleuser(1, "code", "description");
        DtoUser user = new DtoUser(1, "username", "password", "email");

        DtoUserOnRole userOnRole1 = new DtoUserOnRole(1, 1, 1);
        userOnRole1.setRole(role);
        userOnRole1.setUser(user);
        DtoUserOnRole userOnRole2 = new DtoUserOnRole(2, 1, 1);
        userOnRole2.setRole(role);
        userOnRole2.setUser(user);
        DtoUserOnRole userOnRole3 = new DtoUserOnRole(1, 1, 1);
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
