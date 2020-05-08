package ru.strict.neuralnetwork.functions;

public class HyperbolicTan implements IActivateFunction {

    @Override
    public float calc(float value) {
        return (float) ((((Math.pow(Math.E, (Double.parseDouble(String.valueOf(2 * value))))) - 1))
                / ((Math.pow(Math.E, (Double.parseDouble(String.valueOf(2 * value))))) + 1));
    }
}
