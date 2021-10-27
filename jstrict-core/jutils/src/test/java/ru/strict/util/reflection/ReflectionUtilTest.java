package ru.strict.util.reflection;

import org.junit.jupiter.api.Test;
import ru.strict.exception.ValidateException;
import ru.strict.util.ReflectionUtil;

import static org.junit.jupiter.api.Assertions.*;

class ReflectionUtilTest {

    @Test
    void testCreateInstance_byPublicConstructor_success() {
        var expectedStudent = new Student("name1", 18);

        var actualStudent =
                ReflectionUtil.createInstance(Student.class, expectedStudent.getName(), expectedStudent.getAge());
        assertEquals(expectedStudent, actualStudent);
    }

    @Test
    void testCreateInstance_byPrivateConstructor_nullResult() {
        var actualStudent =
                ReflectionUtil.createInstance(Student.class, "name");
        assertNull(actualStudent);
    }

    @Test
    void testCreateDeclaredInstance_byPublicConstructor_success() {
        var expectedStudent = new Student("name1", -1);

        var actualStudent = ReflectionUtil.createDeclaredInstance(Student.class, expectedStudent.getName());
        assertEquals(expectedStudent, actualStudent);
    }

    @Test
    void testGetInterfaces_success() {
        var actualInterfaces = ReflectionUtil.getInterfaces(Student.class);

        assertTrue(actualInterfaces.contains(StudentName.class));
        assertTrue(actualInterfaces.contains(StudentAge.class));
    }

    @Test
    void testIsPrimitive_success() {
        assertFalse(ReflectionUtil.isPrimitive(Boolean.class, Student.class));

        assertTrue(ReflectionUtil.isPrimitive(Byte.class, byte.class));
        assertTrue(ReflectionUtil.isPrimitive(Short.class, short.class));
        assertTrue(ReflectionUtil.isPrimitive(Integer.class, int.class));
        assertTrue(ReflectionUtil.isPrimitive(Long.class, long.class));
        assertTrue(ReflectionUtil.isPrimitive(Float.class, float.class));
        assertTrue(ReflectionUtil.isPrimitive(Double.class, double.class));
        assertTrue(ReflectionUtil.isPrimitive(Character.class, char.class));
        assertTrue(ReflectionUtil.isPrimitive(Boolean.class, boolean.class));
    }

    @Test
    void testIsSuperClass() {
        assertFalse(ReflectionUtil.isSuperClass(Student.class, Student.class));
        assertFalse(ReflectionUtil.isSuperClass(Robot.class, Student.class));
        assertTrue(ReflectionUtil.isSuperClass(StudentAge.class, Student.class));
        assertTrue(ReflectionUtil.isSuperClass(BaseStudent.class, Student.class));
    }

    @Test
    void testIsInterface() {
        assertFalse(ReflectionUtil.isInterface(Student.class, Student.class));
        assertFalse(ReflectionUtil.isInterface(Robot.class, Student.class));
        assertTrue(ReflectionUtil.isInterface(StudentAge.class, Student.class));
        assertFalse(ReflectionUtil.isInterface(BaseStudent.class, Student.class));
    }

    @Test
    void testIsInstanceOf() {
        assertTrue(ReflectionUtil.isInstanceOf(Student.class, Student.class));
        assertFalse(ReflectionUtil.isInstanceOf(Robot.class, Student.class));
        assertTrue(ReflectionUtil.isInstanceOf(StudentAge.class, Student.class));
        assertTrue(ReflectionUtil.isInstanceOf(BaseStudent.class, Student.class));
    }

    @Test
    void testInvokeMethodByAnnotation_oneMethodWithAnnotation_success() {
        var student = new Student("myname", 18);
        var expectedUpperName = "MYNAME";

        var actualUpperName = ReflectionUtil.invokeMethodByAnnotation(student, TestAnnotation.class);
        assertEquals(expectedUpperName, actualUpperName);
    }

    @Test
    void testInvokeMethodByAnnotation_manyMethodsWithAnnotation_fail() {
        var robot = new Robot("alisa");

        assertThrows(ValidateException.class,
                () -> ReflectionUtil.invokeMethodByAnnotation(robot, TestAnnotation.class));
    }

    @Test
    void testInvokeVoidMethodsByAnnotation_manyMethodsWithAnnotation_success() {
        var robot = new Robot("alisa");
        var expectedUpperName = "robot-alisa-test";

        ReflectionUtil.invokeMethodsByAnnotation(robot, TestAnnotation.class);
        assertEquals(expectedUpperName, robot.getName());
    }

    @Test
    void testSetField_privateField_success() {
        var robot = new Robot();
        var expectedName = "alisa";

        assertNull(robot.getName());

        ReflectionUtil.setField(robot, "name", expectedName);
        assertEquals(expectedName, robot.getName());
    }

    @Test
    void testGetAllFields_success() {
        var fields = ReflectionUtil.getAllFields(Robot.class);

        assertEquals(2, fields.size());
    }

    @Test
    void testGetFieldValue_success() {
        var robot = new Robot(1, "alisa");

        assertEquals(Integer.valueOf(1), ReflectionUtil.getFieldValue(robot, "id"));
        assertEquals("alisa", ReflectionUtil.getFieldValue(robot, "name"));
    }
}
