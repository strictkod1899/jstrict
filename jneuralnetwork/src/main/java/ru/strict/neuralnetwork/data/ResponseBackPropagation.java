package ru.strict.neuralnetwork.data;

public class ResponseBackPropagation {
    private Synapse synapse;
    private float newWeight;

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public ResponseBackPropagation(Synapse synapse, float newWeight) {
        this.synapse = synapse;
        this.newWeight = newWeight;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Get/Set">
    public Synapse getSynapse() {
        return synapse;
    }

    public float getNewWeight() {
        return newWeight;
    }
    //</editor-fold>
}
