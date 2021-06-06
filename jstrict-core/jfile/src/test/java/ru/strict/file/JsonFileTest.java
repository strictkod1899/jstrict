package ru.strict.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.strict.file.json.JacksonObjectMapper;
import ru.strict.file.json.JsonFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class JsonFileTest {

    private static final ObjectMapper OBJECT_MAPPER = new JacksonObjectMapper();

    private static final String TEST_FILE_NAME = "test.json";
    private static final String FILE_CONTENT = "{\n" +
            "  \"name\" : \"Ronaldo\",\n" +
            "  \"age\" : 25,\n" +
            "  \"lastScores\" : [ 2, 1, 3, 5, 0, 0, 1, 1 ]\n" +
            "}";

    @AfterEach
    public void post() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_FILE_NAME));
    }

    @Test
    public void testWriteBySource() {
        LinkedHashMap<String, Object> expected = new LinkedHashMap<>();
        expected.put("name", "Ronaldo");
        expected.put("age", 25);
        expected.put("lastScores", new ArrayList<>(Arrays.asList(2, 1, 3, 5, 0, 0, 1, 1)));

        JsonFile fileForWrite = new JsonFile(TEST_FILE_NAME, OBJECT_MAPPER);
        fileForWrite.write(FILE_CONTENT);
        JsonFile fileForRead = new JsonFile(TEST_FILE_NAME, OBJECT_MAPPER);
        Assertions.assertEquals(fileForRead.read(), expected);
    }

    @Disabled
    @Test
    public void testWrite() {
        LinkedHashMap<String, Object> expected = new LinkedHashMap<>();
        expected.put("name", "Ronaldo");
        expected.put("age", 25);
        expected.put("lastScores", new ArrayList<>(Arrays.asList(2, 1, 3, 5, 0, 0, 1, 1)));

        JsonFile fileForWrite = new JsonFile(TEST_FILE_NAME, OBJECT_MAPPER);
        fileForWrite.setContent(expected);
        fileForWrite.write();
        JsonFile fileForRead = new JsonFile(TEST_FILE_NAME, OBJECT_MAPPER);
        Assertions.assertEquals(fileForRead.read(), expected);
    }

    @Test
    public void testWriteToTargetClass() {
        TestObject object = new TestObject();
        object.name = "Ronaldo";
        object.age = 25;
        object.lastScores = new ArrayList<>(Arrays.asList(2, 1, 3, 5, 0, 0, 1, 1));

        JsonFile fileForWrite = new JsonFile(TEST_FILE_NAME, OBJECT_MAPPER);
        fileForWrite.write(object);
        JsonFile<TestObject> fileForRead = new JsonFile<>(TEST_FILE_NAME, TestObject.class, OBJECT_MAPPER);
        Assertions.assertEquals(fileForRead.readToTargetClass(), object);
    }

    private static class TestObject {
        private String name;
        private int age;
        private List<Integer> lastScores;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public List<Integer> getLastScores() {
            return lastScores;
        }

        public void setLastScores(List<Integer> lastScores) {
            this.lastScores = lastScores;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            TestObject that = (TestObject) o;
            return age == that.age &&
                    Objects.equals(name, that.name) &&
                    Objects.equals(lastScores, that.lastScores);
        }

        @Override
        public int hashCode() {

            return Objects.hash(name, age, lastScores);
        }
    }
}
