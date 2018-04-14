package ru.strict.neuralnetwork.data;

/**
 * Синапс - связь между двумя нейронами
 */
public class Synapse {
    /**
     * Нейрон от которого поступают входные значения
     */
    private Neuron sourceNeuron;
    /**
     * Нейрон к которому поступают значения
     */
    private Neuron targetNeuron;
    /**
     * Вес связи
     */
    private float weight;

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public Synapse(Neuron sourceNeuron, Neuron targetNeuron, float weight) {
        this.sourceNeuron = sourceNeuron;
        this.targetNeuron = targetNeuron;
        this.weight = weight;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Get/Set">
    public Neuron getSourceNeuron() {
        return sourceNeuron;
    }

    public void setSourceNeuron(Neuron sourceNeuron) {
        this.sourceNeuron = sourceNeuron;
    }

    public Neuron getTargetNeuron() {
        return targetNeuron;
    }

    public void setTargetNeuron(Neuron targetNeuron) {
        this.targetNeuron = targetNeuron;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Base override">
    public String toString(){
        return String.format("Synapse [weight=%s] from %s to %s", weight, sourceNeuron, targetNeuron);
    }

    public boolean equals(Object obj){
        if(obj == null)
            return false;
        Synapse synapse = (Synapse) obj;
        return synapse.getSourceNeuron().equals(sourceNeuron) && synapse.getTargetNeuron().equals(targetNeuron)
                && synapse.weight == weight;
    }
    //</editor-fold>
}
