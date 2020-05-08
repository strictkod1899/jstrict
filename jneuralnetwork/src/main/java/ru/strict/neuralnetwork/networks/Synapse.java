package ru.strict.neuralnetwork.networks;

import java.util.Objects;

/**
 * Синапс - связь между двумя нейронами
 */
class Synapse {
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
    Synapse(Neuron sourceNeuron, Neuron targetNeuron, float weight) {
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
    @Override
    public String toString() {
        return String.format("Synapse [weight=%s] from %s to %s", weight, sourceNeuron, targetNeuron);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Synapse) {
            Synapse object = (Synapse) obj;
            return Objects.equals(sourceNeuron, object.getSourceNeuron())
                    && Objects.equals(targetNeuron, object.getTargetNeuron())
                    && Objects.equals(weight, object.getWeight());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceNeuron, targetNeuron, weight);
    }
    //</editor-fold>
}
