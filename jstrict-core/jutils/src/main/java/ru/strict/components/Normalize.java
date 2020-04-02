package ru.strict.components;

import java.util.Objects;

/**
 * Функция нормализации значений
 */
public class Normalize implements Cloneable{

    private float min;
    private float max;
    private float targetMin;
    private float targetMax;

    public Normalize(float min, float max) {
        this(min, max, 0, 1);
    }

    public Normalize(float min, float max, float targetMin, float targetMax) {
        this.min = min;
        this.max = max;
        this.targetMin = targetMin;
        this.targetMax = targetMax;
    }

    public float calc(float value) {
        return (((value - min)*(targetMax-targetMin))/(max-min))+targetMin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Normalize object = (Normalize) o;
        return Float.compare(object.min, min) == 0 &&
                Float.compare(object.max, max) == 0 &&
                Float.compare(object.targetMin, targetMin) == 0 &&
                Float.compare(object.targetMax, targetMax) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max, targetMin, targetMax);
    }

    @Override
    public Normalize clone() {
        try {
            return (Normalize)super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
