package ru.strict.db.hibernate.entities;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.db.core.entities.EntityCity;
import ru.strict.db.core.entities.EntityCountry;
import ru.strict.db.core.entities.EntityFileStorage;
import ru.strict.db.core.entities.EntityJWTToken;
import ru.strict.db.core.entities.EntityProfile;
import ru.strict.db.core.entities.EntityProfileInfo;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.entities.EntityServiceOnRole;
import ru.strict.db.core.entities.EntityToken;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.entities.EntityUserOnRole;

import java.util.Date;

@RunWith(JUnit4.class)
public class TestBaseMethods {

    @Test
    public void testCountryAndCity(){
        EntityCountry country1 = new EntityCountry(1, "country1");
        EntityCountry country2 = new EntityCountry(2, "country2");
        EntityCountry country3 = new EntityCountry(1, "country1");

        EntityCity city1 = new EntityCity(1, "city1", 1);
        city1.setCountry(country1);
        EntityCity city2 = new EntityCity(2, "city2", 1);
        city2.setCountry(country1);
        EntityCity city3 = new EntityCity(3, "city3", 2);
        city3.setCountry(country2);
        EntityCity city4 = new EntityCity(1, "city1", 1);
        city4.setCountry(country3);
        EntityCity city5 = new EntityCity(2, "city2", 1);
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

        EntityFileStorage fileStorage1 = new EntityFileStorage(1, "filename", "extension", "displayName", "filePath",
                new byte[]{1, 2, 3}, date1, 1, 1);
        EntityFileStorage fileStorage2 = new EntityFileStorage(2, "filename", "extension", "displayName", "filePath",
                new byte[]{1, 2, 3}, date1, 1, 1);
        EntityFileStorage fileStorage3 = new EntityFileStorage(1, "filename", "extension", "displayName", "filePath",
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
        EntityUser user = new EntityUser();
        user.setId(1);

        EntityJWTToken jwtToken1 = new EntityJWTToken(1, "accessToken", "refreshToken", date1, date1, date1, 1);
        jwtToken1.setUser(user);
        EntityJWTToken jwtToken2 = new EntityJWTToken(2, "accessToken", "refreshToken", date1, date1, date1, 1);
        jwtToken2.setUser(user);
        EntityJWTToken jwtToken3 = new EntityJWTToken(1, "accessToken", "refreshToken", date1, date1, date1, 1);
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

        EntityToken token1 = new EntityToken(1, "accessToken", "refreshToken", date1, date1, date1);
        EntityToken token2 = new EntityToken(2, "accessToken", "refreshToken", date1, date1, date1);
        EntityToken token3 = new EntityToken(1, "accessToken", "refreshToken", date1, date1, date1);

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
        EntityUser user = new EntityUser();
        user.setId(1);

        EntityProfile profile1 = new EntityProfile(1, "name", "surname", "middlename", 1);
        profile1.setUser(user);
        EntityProfile profile2 = new EntityProfile(2, "name", "surname", "middlename", 1);
        profile2.setUser(user);
        EntityProfile profile3 = new EntityProfile(1, "name", "surname", "middlename", 1);
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
        EntityUser user = new EntityUser();
        user.setId(1);
        EntityCity city = new EntityCity();
        city.setId(1);

        EntityProfileInfo profileInfo1 = new EntityProfileInfo(1, "name", "surname", "middlename", 1, date, "phone", 1);
        profileInfo1.setUser(user);
        profileInfo1.setCity(city);
        EntityProfileInfo profileInfo2 = new EntityProfileInfo(2, "name", "surname", "middlename", 1, date, "phone", 1);
        profileInfo2.setUser(user);
        profileInfo2.setCity(city);
        EntityProfileInfo profileInfo3 = new EntityProfileInfo(1, "name", "surname", "middlename", 1, date, "phone", 1);
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
        EntityUser user = new EntityUser();
        user.setId(1);

        EntityRoleuser role1 = new EntityRoleuser(1, "code", "description");
        role1.addUser(user);
        EntityRoleuser role2 = new EntityRoleuser(2, "code", "description");
        role2.addUser(user);
        EntityRoleuser role3 = new EntityRoleuser(1, "code", "description");
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
        EntityProfile profile1 = new EntityProfile(1, "name", "surname", "middlename", 1);
        EntityRoleuser role1 = new EntityRoleuser(1, "code", "description");
        EntityJWTToken jwtToken1 = new EntityJWTToken(1, "accessToken", "refreshToken", date1, date1, date1, 1);

        EntityUser user1 = new EntityUser(1, "username", "password", "email");
        user1.addProfile(profile1);
        user1.addRole(role1);
        user1.addToken(jwtToken1);
        EntityUser user2 = new EntityUser(2, "username", "password", "email");
        user2.addProfile(profile1);
        user2.addRole(role1);
        user2.addToken(jwtToken1);
        EntityUser user3 = new EntityUser(1, "username", "password", "email");
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
        EntityRoleuser role = new EntityRoleuser(1, "code", "description");

        EntityServiceOnRole permissionOnRole1 = new EntityServiceOnRole(1, 1, 1);
        permissionOnRole1.setRole(role);
        EntityServiceOnRole permissionOnRole2 = new EntityServiceOnRole(2, 1, 1);
        permissionOnRole2.setRole(role);
        EntityServiceOnRole permissionOnRole3 = new EntityServiceOnRole(1, 1, 1);
        permissionOnRole3.setRole(role);

        Assert.assertTrue(permissionOnRole1.equals(permissionOnRole3));
        Assert.assertEquals(permissionOnRole1, permissionOnRole3);
        Assert.assertEquals(permissionOnRole1.hashCode(), permissionOnRole3.hashCode());
        Assert.assertEquals(permissionOnRole1.clone(), permissionOnRole3.clone());
        Assert.assertEquals(permissionOnRole1, permissionOnRole1.clone());
        Assert.assertEquals(permissionOnRole1.hashCode(), permissionOnRole1.clone().hashCode());

        Assert.assertFalse(permissionOnRole1.equals(permissionOnRole2));
        Assert.assertNotEquals(permissionOnRole1, permissionOnRole2);
        Assert.assertNotEquals(permissionOnRole3, permissionOnRole2);
        Assert.assertNotEquals(permissionOnRole1.hashCode(), permissionOnRole2.hashCode());
        Assert.assertNotEquals(permissionOnRole1.clone(), permissionOnRole2);
        Assert.assertNotEquals(permissionOnRole1.clone(), permissionOnRole2.clone());
    }

    @Test
    public void testUserOnRole(){
        EntityRoleuser role = new EntityRoleuser(1, "code", "description");
        EntityUser user = new EntityUser(1, "username", "password", "email");

        EntityUserOnRole userOnRole1 = new EntityUserOnRole(1, 1, 1);
        userOnRole1.setRole(role);
        userOnRole1.setUser(user);
        EntityUserOnRole userOnRole2 = new EntityUserOnRole(2, 1, 1);
        userOnRole2.setRole(role);
        userOnRole2.setUser(user);
        EntityUserOnRole userOnRole3 = new EntityUserOnRole(1, 1, 1);
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
