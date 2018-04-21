package ru.strict.neuralnetwork.networks;

import ru.strict.neuralnetwork.data.*;
import ru.strict.neuralnetwork.functions.ActivateFunction;
import ru.strict.neuralnetwork.structures.StrictNeuralNetworkStructure;

import java.util.List;

/**
 * Основа реализации нейронной сети
 *
 * @param <DATA> Данные для обучения и тестирования нейронной сети
 * @param <STRUCT> Структура нейронной сети
 */
public abstract class StrictNeuralNetwork<DATA extends StrictNeuralNetworkData, STRUCT extends StrictNeuralNetworkStructure>
        implements StrictNeuralNetworkAny {

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
    private ActivateFunction activateFunction;

    //<editor-fold defaultstate="collapsed" desc="constructors">
    private void ensureCreateInstance(DATA data, STRUCT structure, ActivateFunction activateFunction){
        if(data==null)
            throw new NullPointerException("Neural Network do not supported null value. [StrictNeuralNetworkData is null]");
        if(structure==null)
            throw new NullPointerException("Neural Network do not supported null value. [StrictNeuralNetworkStructure is null]");
        if(activateFunction==null)
            throw new NullPointerException("Neural Network do not supported null value. [ActivateFunction is null]");
    }

    public StrictNeuralNetwork(DATA data, STRUCT structure, ActivateFunction activateFunction) {
        try{
            ensureCreateInstance(data, structure, activateFunction);
        }catch(Exception ex){throw ex;}

        this.data = data;
        this.structure = structure;
        this.activateFunction = activateFunction;
    }
    //</editor-fold>

    @Override
    public void learn(int epochs, float learnRate, float moment) {
        if(epochs<=0)
            throw new IllegalArgumentException("Learning exception: epoch count should not be is negative. [Epochs <= 0]");

        for(int epoch=0; epoch<epochs; epoch++) {
            List<StrictNeuralNetworkDataSet> trainingSets = getData().getRandomTrainingSets();
            for (StrictNeuralNetworkDataSet trainingSet : trainingSets) {
                implementLearn(trainingSet, learnRate, moment);
            }
        }
    }

    protected abstract void implementLearn(StrictNeuralNetworkDataSet trainingSet, float learnRate, float moment);

    /**
     * Сгенерировать синапсы - связи между нейронами
     */
    public void generateSynapses(){
        structure.generateSynapses();
    }

    //<editor-fold defaultstate="collapsed" desc="Get/Set">
    public Neuron getBias(){
        return structure.getBias();
    }
    public void setSynapseWeight(Synapse synapse, float newWeight){
        structure.setSynapseWeight(synapse, newWeight);
    }

    public int findIndexSynapse(Synapse synapse){
        return structure.findIndexSynapse(synapse);
    }

    public Synapse findSynapse(Neuron sourceNeuron, Neuron targetNeuron){
        return getStructure().findSynapse(sourceNeuron, targetNeuron);
    }

    public Synapse[] findSynapses(Neuron targetNeuron){
        return getStructure().findSynapses(targetNeuron);
    }

    public Neuron[] getInputNeurons(){
        return getStructure().getInputNeurons();
    }

    public Neuron[] getOutputNeurons(){
        return getStructure().getOutputNeurons();
    }

    public void setOutputValue(int i, float value){
        getStructure().setOutputValue(i, value);
    }

    public void setInputValue(int i, float value){
        getStructure().setInputValue(i, value);
    }

    public Neuron getInputNeuron(int i){
        return getStructure().getInputNeurons()[i];
    }

    public Neuron getOutputNeuron(int i){
        return getStructure().getOutputNeurons()[i];
    }

    public List<Synapse> getSynapses(){
        return structure.getSynapses();
    }

    public DATA getData() {
        return data;
    }

    public STRUCT getStructure() {
        return structure;
    }

    public ActivateFunction getActivateFunction() {
        return activateFunction;
    }
    //</editor-fold>
}