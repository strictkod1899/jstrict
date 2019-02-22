package ru.strict.neuralnetwork.networks;

public class TestResult {
    private Neuron[] expectedNeurons;
    private Neuron[] actualNeurons;

    //<editor-fold defaultstate="collapsed" desc="constructors">
    TestResult(Neuron[] expectedNeurons, Neuron[] actualNeurons) {
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
}
