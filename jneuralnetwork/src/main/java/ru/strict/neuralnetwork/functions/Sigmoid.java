package ru.strict.neuralnetwork.functions;

public class Sigmoid extends ActivateFunction {

    @Override
    public float calc(float value) {
        return (float)((1)/(1+(Math.pow(Math.E, -(Double.parseDouble(String.valueOf(value)))))));
    }
}
