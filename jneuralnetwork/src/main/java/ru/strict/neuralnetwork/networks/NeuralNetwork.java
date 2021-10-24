package ru.strict.neuralnetwork.networks;

import ru.strict.exception.ValidateException;
import ru.strict.neuralnetwork.functions.IActivateFunction;
import ru.strict.validate.Validator;

import java.util.List;

/**
 * Основа реализации нейронной сети
 *
 * @param <DATA> Данные для обучения и тестирования нейронной сети
 * @param <STRUCT> Структура нейронной сети
 */
public abstract class NeuralNetwork<DATA extends NeuralNetworkData, STRUCT extends NeuralNetworkStructure>
        implements INeuralNetwork {
    /**
     * Данные для обучения и тестирования нейронной сети
     */
    private DATA data;
    /**
     * Структура нейронной сети
     */
    private STRUCT structure;
    /**
     * Функция активации
     */
    private IActivateFunction activateFunction;

    //<editor-fold defaultstate="collapsed" desc="constructors">
    NeuralNetwork(DATA data, STRUCT structure, IActivateFunction activateFunction) {
        Validator.isNull(data, "data");
        Validator.isNull(structure, "structure");
        Validator.isNull(activateFunction, "activateFunction");

        this.data = data;
        this.structure = structure;
        this.activateFunction = activateFunction;
    }
    //</editor-fold>

    @Override
    public void learn(int epochs, float speed, float moment) {
        if (!structure.isSynapsesExists()) {
            generateSynapses();
        }

        if (epochs <= 0) {
            throw new ValidateException("epochs", "epochs <= 0", "Learning exception: epoch count cann't be is negative");
        }

        for (int epoch = 0; epoch < epochs; epoch++) {
            List<NeuralNetworkDataSet> trainingSets = getData().getRandomTrainingSets();
            for (NeuralNetworkDataSet trainingSet : trainingSets) {
                implementLearn(trainingSet, speed, moment);
            }
        }
    }

    protected abstract void implementLearn(NeuralNetworkDataSet trainingSet, float speed, float moment);

    /**
     * Сгенерировать синапсы - связи между нейронами
     */
    public void generateSynapses() {
        structure.generateSynapses();
    }

    //<editor-fold defaultstate="collapsed" desc="Get/Set">
    public Neuron getBias() {
        return structure.getBias();
    }

    public void setSynapseWeight(Synapse synapse, float newWeight) {
        structure.setSynapseWeight(synapse, newWeight);
    }

    public int findIndexSynapse(Synapse synapse) {
        return structure.findIndexSynapse(synapse);
    }

    public Synapse findSynapse(Neuron sourceNeuron, Neuron targetNeuron) {
        return getStructure().findSynapse(sourceNeuron, targetNeuron);
    }

    public Synapse[] findSynapses(Neuron targetNeuron) {
        return getStructure().findSynapses(targetNeuron);
    }

    public Neuron[] getInputNeurons() {
        return getStructure().getInputNeurons();
    }

    public Neuron[] getOutputNeurons() {
        return getStructure().getOutputNeurons();
    }

    public void setOutputValue(int i, float value) {
        getStructure().setOutputValue(i, value);
    }

    public void setInputValue(int i, float value) {
        getStructure().setInputValue(i, value);
    }

    public Neuron getInputNeuron(int i) {
        return getStructure().getInputNeurons()[i];
    }

    public Neuron getOutputNeuron(int i) {
        return getStructure().getOutputNeurons()[i];
    }

    public List<Synapse> getSynapses() {
        return structure.getSynapses();
    }

    public DATA getData() {
        return data;
    }

    public STRUCT getStructure() {
        return structure;
    }

    public IActivateFunction getActivateFunction() {
        return activateFunction;
    }
    //</editor-fold>
}
