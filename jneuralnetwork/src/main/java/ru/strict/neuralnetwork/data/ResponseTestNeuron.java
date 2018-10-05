package ru.strict.neuralnetwork.data;

import java.util.Arrays;

public class ResponseTestNeuron {
    private Neuron[] expectedNeurons;
    private Neuron[] actualNeurons;

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public ResponseTestNeuron(Neuron[] expectedNeurons, Neuron[] actualNeurons) {
        this.expectedNeurons = expectedNeurons;
        this.actualNeurons = actualNeurons;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Get/Set">
    public Neuron[] getExpectedNeurons() {
        return expectedNeurons;
    }

    public Neuron[] getActualNeurons() {
        return actualNeurons;
    }
    //</editor-fold>

    @Override
    public String toString(){
        String expected = "";
        String actually = "";
        for(Neuron neuron : getExpectedNeurons()) {
            expected += neuron.getValue() + " | ";
        }
        for(Neuron neuron : getActualNeurons()) {
            actually += neuron.getValue() + " | ";
        }
        String result = String.format("Expected: %s\t Actually: %s", expected, actually);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj!=null && obj instanceof ResponseTestNeuron) {
            ResponseTestNeuron object = (ResponseTestNeuron) obj;
            return Arrays.equals(expectedNeurons, object.expectedNeurons) &&
                    Arrays.equals(actualNeurons, object.actualNeurons);
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(expectedNeurons);
        result = 31 * result + Arrays.hashCode(actualNeurons);
        return result;
    }
}
