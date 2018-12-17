package ru.strict.neuralnetwork.builders;

import ru.strict.neuralnetwork.data.NeuralNetworkDataSet;
import ru.strict.neuralnetwork.data.Neuron;
import ru.strict.neuralnetwork.data.NeuralNetworkData;
import ru.strict.neuralnetwork.functions.ActivateFunction;
import ru.strict.neuralnetwork.networks.NeuralNetwork;
import ru.strict.neuralnetwork.structures.NeuralNetworkStructure;

/**
 * Базовый класс фабрики нейронной сети
 * @param <STRUCT> Структура нейронной сети
 * @param <NETWORK> Нейронная сеть
 */
public abstract class NeuralNetworkBuilder<STRUCT extends NeuralNetworkStructure, NETWORK extends NeuralNetwork> {

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
    private NeuralNetworkData data;
    /**
     * Функция активации
     */
    private ActivateFunction activateFunction;

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public NeuralNetworkBuilder(int countInputs, int countOutputs, ActivateFunction activateFunction) {
        this.countInputs = countInputs;
        this.countOutputs = countOutputs;
        data = new NeuralNetworkData(countInputs, countOutputs);
        this.activateFunction = activateFunction;
    }
    //</editor-fold>

    /**
     * Создать нейронную сеть
     * @return
     */
    public abstract NETWORK createNeuralNetwork();

    //<editor-fold defaultstate="collapsed" desc="Get/set">
    public void addTrainingSet(NeuralNetworkDataSet set){
        data.addTrainingSet(set);
    }

    public void addTestSet(NeuralNetworkDataSet set){
        data.addTestSet(set);
    }

    public void addTrainingSet(Neuron[] inputSet, Neuron[] outputSet){
        data.addTrainingSet(new NeuralNetworkDataSet(inputSet, outputSet));
    }

    public void addTestSet(Neuron[] inputSet, Neuron[] outputSet){
        data.addTestSet(new NeuralNetworkDataSet(inputSet, outputSet));
    }

    public NeuralNetworkData getData() {
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