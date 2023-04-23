package ru.strict.domainprimitive.id;

import org.junit.jupiter.api.Test;
import ru.strict.exception.CodeableException;

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
        var actualEx = assertThrows(CodeableException.class, () -> new EntityId(null));

        assertTrue(actualEx.equalsByCode(EntityIdError.idIsEmptyErrorCode));
    }

    @Test
    void tesIdFrom_EmptyString_ThrowError() {
        var actualEx = assertThrows(CodeableException.class, () -> new EntityId(""));

        assertTrue(actualEx.equalsByCode(EntityIdError.idIsEmptyErrorCode));
    }

    @Test
    void tesIdFrom_NotUUID_ThrowError() {
        var actualEx = assertThrows(CodeableException.class, () -> new EntityId("123"));

        assertTrue(actualEx.equalsByCode(EntityIdError.invalidIdFormatErrorCode));
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
