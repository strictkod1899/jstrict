package ru.strict.neuralnetwork.networks;

import java.util.*;

/**
 * Базовая структура нейронной сети
 */
abstract class NeuralNetworkStructure implements Cloneable {

    private int countInputs;
    private int countOutputs;
    private Neuron[] inputNeurons;
    private Neuron[] outputNeurons;
    private Neuron bias;

    /**
     * Связи между нейронами
     */
    private List<Synapse> synapses;
    private final Random random = new Random();

    //<editor-fold defaultstate="collapsed" desc="constructors">
    NeuralNetworkStructure(int countInputs, int countOutputs) {
        if (countInputs < 1) {
            throw new IllegalArgumentException(
                    "Neural Network structure do not should have input neurons count is negative. [Input neurons " +
                            "count < 1]");
        }
        if (countOutputs < 1) {
            throw new IllegalArgumentException(
                    "Neural Network structure do not should have output neurons count is negative. [Output neurons " +
                            "count < 1]");
        }

        this.countInputs = countInputs;
        this.countOutputs = countOutputs;
        inputNeurons = new Neuron[countInputs];
        outputNeurons = new Neuron[countOutputs];
        synapses = new ArrayList<>();

        for (int i = 0; i < countInputs; i++) {
            inputNeurons[i] = new Neuron(0, NeuronType.INPUT);
        }
        for (int i = 0; i < countOutputs; i++) {
            outputNeurons[i] = new Neuron(0, NeuronType.OUTPUT);
        }
    }
    //</editor-fold>

    /**
     * Сгенерировать синапсы - связи между нейронами
     */
    public abstract void generateSynapses();

    /**
     * Генерация случайного веса для синапса
     *
     * @return вес синапса
     */
    protected float generateWeight() {
        float randomValue = random.nextFloat();
        return randomValue;
    }

    //<editor-fold defaultstate="collapsed" desc="Get/Set">
    public boolean isSynapsesExists() {
        return synapses != null && synapses.size() > 0;
    }

    /**
     * Добавить синапс - связь между двумя нейронами
     *
     * @param synapse Скрытый слой
     */
    protected void addSynapse(Synapse synapse) {
        synapses.add(synapse);
    }

    public void setSynapseWeight(Synapse synapse, float newWeight) {
        synapses.get(findIndexSynapse(synapse)).setWeight(newWeight);
    }

    public int findIndexSynapse(Synapse synapse) {
        for (int i = 0; i < synapses.size(); i++) {
            if (synapses.get(i).equals(synapse)) {
                return i;
            }
        }
        return -1;
    }

    public Synapse findSynapse(Neuron sourceNeuron, Neuron targetNeuron) {
        return synapses.stream()
                .filter((s) -> s.getSourceNeuron().equals(sourceNeuron) && s.getTargetNeuron().equals(targetNeuron))
                .findFirst()
                .orElse(null);
    }

    public Synapse[] findSynapses(Neuron targetNeuron) {
        return synapses.stream().filter((s) -> s.getTargetNeuron().equals(targetNeuron)).toArray(Synapse[]::new);
    }

    public void setInputValue(int i, float value) {
        inputNeurons[i].setValue(value);
    }

    public void setOutputValue(int i, float value) {
        outputNeurons[i].setValue(value);
    }

    public int getCountInputs() {
        return countInputs;
    }

    public int getCountOutputs() {
        return countOutputs;
    }

    public Neuron[] getInputNeurons() {
        return inputNeurons;
    }

    public Neuron[] getOutputNeurons() {
        return outputNeurons;
    }

    public List<Synapse> getSynapses() {
        return synapses;
    }

    public Neuron getBias() {
        return bias;
    }

    public void setUseBias(boolean isUseBias) {
        if (isUseBias) {
            bias = new Neuron(1, NeuronType.BIAS);
        } else {
            bias = new Neuron(0, NeuronType.BIAS);
        }
    }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Base override">
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof NeuralNetworkStructure) {
            NeuralNetworkStructure object = (NeuralNetworkStructure) obj;
            return countInputs == object.countInputs &&
                    countOutputs == object.countOutputs &&
                    Arrays.equals(inputNeurons, object.inputNeurons) &&
                    Arrays.equals(outputNeurons, object.outputNeurons) &&
                    Objects.equals(bias, object.bias) &&
                    Objects.equals(synapses, object.synapses) &&
                    Objects.equals(random, object.random);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(countInputs, countOutputs, bias, synapses, random);
        result = 31 * result + Arrays.hashCode(inputNeurons);
        result = 31 * result + Arrays.hashCode(outputNeurons);
        return result;
    }
    //</editor-fold>
}
