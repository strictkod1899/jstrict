package ru.strict.components;

/**
 * Функция нормализации значений
 */
public class Normalize {

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
}
