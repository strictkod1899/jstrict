package ru.strict.neuralnetwork.data;

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
}
