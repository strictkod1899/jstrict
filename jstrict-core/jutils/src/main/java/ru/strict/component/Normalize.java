package ru.strict.component;

/**
 * Функция нормализации значений
 */
public final class Normalize {
    private float min;
    private float max;
    private float targetMin;
    private float targetMax;

    /**
     * Нормализация значений в пределах от 0 до 1
     *
     * @param min минимальное значение исходной метрики
     * @param max максимальное значение исходной метрики
     */
    public Normalize(float min, float max) {
        this(min, max, 0, 1);
    }

    /**
     * Нормализация значений в пределах от {@param targetMin} до {@param targetMax}
     *
     * @param min минимальное значение исходной метрики
     * @param max максимальное значение исходной метрики
     */
    public Normalize(float min, float max, float targetMin, float targetMax) {
        this.min = min;
        this.max = max;
        this.targetMin = targetMin;
        this.targetMax = targetMax;
    }

    public final float calc(float value) {
        return (((value - min) * (targetMax - targetMin)) / (max - min)) + targetMin;
    }
}
