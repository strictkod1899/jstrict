package ru.strict.neuralnetwork.builders;

import ru.strict.neuralnetwork.functions.ActivateFunction;
import ru.strict.neuralnetwork.networks.NeuralNetworkHidden;
import ru.strict.neuralnetwork.structures.NeuralNetworkHiddenStructure;

/**
 * Базовый класс фабрики нейронной сети со скрытыми слоями
 */
public abstract class NeuralNetworkHiddenBuilder
        <STRUCT extends NeuralNetworkHiddenStructure, NETWORK extends NeuralNetworkHidden>
        extends NeuralNetworkBuilder<STRUCT, NETWORK> {

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public NeuralNetworkHiddenBuilder(int countInputs, int countOutputs, ActivateFunction activateFunction) {
        super(countInputs, countOutputs, activateFunction);
    }

    public NeuralNetworkHiddenBuilder(int countInputs, int countHiddens, int countOutputs
            , ActivateFunction activateFunction, boolean isUseBias) {
        super(countInputs, countOutputs, activateFunction);
        setStructure((STRUCT) new NeuralNetworkHiddenStructure(countInputs, countHiddens, countOutputs, isUseBias));
    }
    //</editor-fold>

    /**
     * Добавить скрытый слой
     * @param countHiddenNeurons Количество нейронов в скрытом слое
     */
    public void addLayoutHidden(int countHiddenNeurons){
        getStructure().addLayoutHidden(countHiddenNeurons);
    }
}
