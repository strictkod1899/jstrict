package ru.strict.neuralnetwork.factories;

import ru.strict.neuralnetwork.functions.ActivateFunction;
import ru.strict.neuralnetwork.networks.StrictNeuralNetworkHidden;
import ru.strict.neuralnetwork.structures.StrictNeuralNetworkHiddenStructure;

/**
 * Базовый класс фабрики нейронной сети со скрытыми слоями
 */
public abstract class StrictNeuralNetworkHiddenFactory
        <STRUCT extends StrictNeuralNetworkHiddenStructure, NNETWORK extends StrictNeuralNetworkHidden>
        extends StrictNeuralNetworkFactory<STRUCT, NNETWORK> {

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public StrictNeuralNetworkHiddenFactory(int countInputs, int countOutputs, ActivateFunction activateFunction) {
        super(countInputs, countOutputs, activateFunction);
    }

    public StrictNeuralNetworkHiddenFactory(int countInputs, int countHiddens, int countOutputs
            , ActivateFunction activateFunction, boolean isUseBias) {
        super(countInputs, countOutputs, activateFunction);
        setStructure((STRUCT) new StrictNeuralNetworkHiddenStructure(countInputs, countHiddens, countOutputs, isUseBias));
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
