package ru.strict.domainprimitive.id;

import org.junit.jupiter.api.Test;
import ru.strict.exception.CodeableException;
import ru.strict.test.FailTestException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EntityIdTest {

    @Test
    void tesNewId_NoError() {
        var entityId1 = new EntityId();
        var entityId2 = new EntityId();

        assertNotEquals(entityId1, entityId2);
    }

    @Test
    void tesIdFrom_StringIsNull_ThrowError() {
        try {
            new EntityId(null);
        } catch (CodeableException ex) {
            assertTrue(CodeableException.equalsByCode(ex, EntityIdError.idIsEmptyErrorCode));
            return;
        }

        throw new FailTestException();
    }

    @Test
    void tesIdFrom_EmptyString_ThrowError() {
        try {
            new EntityId("");
        } catch (CodeableException ex) {
            assertTrue(CodeableException.equalsByCode(ex, EntityIdError.idIsEmptyErrorCode));
            return;
        }

        throw new FailTestException();
    }

    @Test
    void tesIdFrom_NotUUID_ThrowError() {
        try {
            new EntityId("123");
        } catch (CodeableException ex) {
            assertTrue(CodeableException.equalsByCode(ex, EntityIdError.invalidIdFormatErrorCode));
            return;
        }

        throw new FailTestException();
    }

    @Test
    void tesIdFrom_ValidParam_NoError() {
        var expectedIdStr = UUID.randomUUID().toString();

        var entityId1 = new EntityId(expectedIdStr);
        var entityId2 = new EntityId(expectedIdStr);

        assertEquals(entityId1, entityId2);
        assertEquals(entityId1.toString(), entityId2.toString());
    }
}
