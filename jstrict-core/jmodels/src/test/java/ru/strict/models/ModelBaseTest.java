package com.cdek.cfdocs.dto;

import org.junit.Assert;
import org.junit.Test;

/**
 * Базовый класс для тестирования модели на equals и hashCode
 * @param <T> Тип модели
 */
public abstract class ModelBaseTest<T> {

    /**
     * Тестируем модели согласно свойствам equals:
     * 1. Рефлексивность – объект равен самому себе, a == a;
     * 2. Симметричность – если a == b, то и b == a;
     * 3. Транзитивность – если a == b, и b == c, то a == c; (это условие пропустим в тесте, чтбы не создавать 3 идентичных объекта)
     * 4. Постоянность – при одинаковых данных, результат должен быть одинаковым;
     * 5. Неравенство null – если a == null, то false.
     *
     * ВАЖНО: id-объекта не должен учитываться в equals и hashCode
     */
    @Test
    public void test() {
        T object1 = createObject1();
        T object2 = createObject2();
        T object3 = createObject3();

        Assert.assertEquals(object1, object1);
        Assert.assertEquals(object1, object2);

        Assert.assertNotEquals(object1, null);
        Assert.assertNotEquals(object1, object3);
        Assert.assertNotEquals(object2, object3);

        Assert.assertEquals(object1.hashCode(), object1.hashCode());
        Assert.assertEquals(object1.hashCode(), object2.hashCode());

        Assert.assertNotEquals(object1.hashCode(), object3.hashCode());
        Assert.assertNotEquals(object2.hashCode(), object3.hashCode());
    }

    protected abstract T createObject1();
    /**
     * Этот объект должен быть идентичен по полям объекту 1 {@link #createObject1()}
     */
    protected abstract T createObject2();
    protected abstract T createObject3();
}
