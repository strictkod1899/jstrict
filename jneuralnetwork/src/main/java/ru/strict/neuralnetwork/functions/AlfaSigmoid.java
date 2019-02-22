package ru.strict.neuralnetwork.functions;

/**
 * Сигмоид со смещением
 */
public class AlfaSigmoid implements IActivateFunction {

    /**
     * Смешение.
     * Для плавности (увеличения порога ненормализованных данных) задается значение меньше 1,
     * для уменьшения порога задется значение больше 1
     */
    private double alfa;

    public AlfaSigmoid(double alfa) {
        this.alfa = alfa;
    }

    @Override
    public float calc(float value) {
        return (float)((1)/(1+(Math.pow(Math.E, -alfa * (Double.parseDouble(String.valueOf(value)))))));
    }

    public double getAlfa() {
        return alfa;
    }
}
