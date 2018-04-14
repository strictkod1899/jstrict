package ru.strict.neuralnetwork.factories;

import ru.strict.neuralnetwork.data.Neuron;
import ru.strict.neuralnetwork.data.StrictNeuralNetworkData;
import ru.strict.neuralnetwork.data.StrictNeuralNetworkDataSet;
import ru.strict.neuralnetwork.functions.ActivateFunction;
import ru.strict.neuralnetwork.networks.StrictNeuralNetwork;
import ru.strict.neuralnetwork.structures.StrictNeuralNetworkStructure;

/**
 * Базовый класс фабрики нейронной сети
 * @param <STRUCT> Структура нейронной сети
 * @param <NNETWORK> Нейронная сеть
 */
public abstract class StrictNeuralNetworkFactory<STRUCT extends StrictNeuralNetworkStructure, NNETWORK extends StrictNeuralNetwork> {

    /**
     * Количесво входных нейронов
     */
    private int countInputs;
    /**
     * Количесво выходных нейронов
     */
    private int countOutputs;
    /**
     * Структура нейронной сети
     */
    private STRUCT structure;
    /**
     * Данные, заданные нейронной сети (обучающие и тестовые)
     */
    private StrictNeuralNetworkData data;
    /**
     * Функция активации
     */
    private ActivateFunction activateFunction;

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public StrictNeuralNetworkFactory(int countInputs, int countOutputs, ActivateFunction activateFunction) {
        this.countInputs = countInputs;
        this.countOutputs = countOutputs;
        data = new StrictNeuralNetworkData(countInputs, countOutputs);
        this.activateFunction = activateFunction;
    }
    //</editor-fold>

    /**
     * Создать нейронную сеть
     * @return
     */
    public abstract NNETWORK createNeuralNetwork();

    //<editor-fold defaultstate="collapsed" desc="Get/set">
    public void addTrainingSet(StrictNeuralNetworkDataSet set){
        data.addTrainingSet(set);
    }

    public void addTestSet(StrictNeuralNetworkDataSet set){
        data.addTestSet(set);
    }

    public void addTrainingSet(Neuron[] inputSet, Neuron[] outputSet){
        data.addTrainingSet(new StrictNeuralNetworkDataSet(inputSet, outputSet));
    }

    public void addTestSet(Neuron[] inputSet, Neuron[] outputSet){
        data.addTestSet(new StrictNeuralNetworkDataSet(inputSet, outputSet));
    }

    public StrictNeuralNetworkData getData() {
        return data;
    }

    public STRUCT getStructure() {
        return structure;
    }

    protected void setStructure(STRUCT structure) {
        this.structure = structure;
    }

    public int getCountInputs() {
        return countInputs;
    }

    public int getCountOutputs() {
        return countOutputs;
    }

    public ActivateFunction getActivateFunction() {
        return activateFunction;
    }

    public void setUseBias(boolean isUseBias){
        structure.setUseBias(isUseBias);
    }
    //</editor-fold>
}
