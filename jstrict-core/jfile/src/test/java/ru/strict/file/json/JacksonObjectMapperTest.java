package ru.strict.file.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JacksonObjectMapperTest {

    @Test
    void testReadFromResource_ValidParams_ReturnObject() {
        var jacksonObjectMapper = new JacksonObjectMapper();

        var usersJSON = jacksonObjectMapper.readFromResource("users.json", UsersJson.class);

        assertNotNull(usersJSON);
        assertEquals(2, usersJSON.users.size());

        var actualUser1 = usersJSON.users.get(0);
        var actualUser2 = usersJSON.users.get(1);

        assertEquals("user1", actualUser1.name);
        assertNotNull(actualUser1.attributes);
        assertEquals(17, actualUser1.attributes.age);
        assertEquals(65.4f, actualUser1.attributes.weight);

        assertEquals("user2", actualUser2.name);
        assertNotNull(actualUser2.attributes);
        assertEquals(16, actualUser2.attributes.age);
        assertNull(actualUser2.attributes.weight);
    }

    static class UsersJson {
        @JsonProperty("users")
        List<UserJson> users;
    }

    static class UserJson {
        @JsonProperty("name")
        String name;
        @JsonProperty("attributes")
        UserAttributes attributes;
    }

    static class UserAttributes {
        @JsonProperty("age")
        Integer age;
        @JsonProperty("weight")
        Float weight;
    }
}
