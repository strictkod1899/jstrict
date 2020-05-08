package ru.strict.neuralnetwork.networks;

import ru.strict.neuralnetwork.functions.IActivateFunction;

import java.util.List;

/**
 * Нейронная сеть с поддержкой скрытых слоев
 */
public abstract class NeuralNetworkHidden<DATA extends NeuralNetworkData, STRUCT extends HiddenStructure>
        extends NeuralNetwork<DATA, STRUCT> {

    //<editor-fold defaultstate="collapsed" desc="constructors">
    NeuralNetworkHidden(DATA data, STRUCT structure, IActivateFunction activateFunction) {
        super(data, structure, activateFunction);
    }
    //</editor-fold>

    protected abstract Neuron[] feedforward(Neuron[] inputs);

    //<editor-fold defaultstate="collapsed" desc="Get/Set">
    public LayoutHidden getLayoutHidden(int i) {
        return getStructure().getLayoutsHidden().get(i);
    }

    public List<LayoutHidden> getLayoutsHidden() {
        return getStructure().getLayoutsHidden();
    }

    public void setHiddenValue(int indexLayout, int indexHiddenNeuron, float value) {
        getStructure().setHiddenValue(indexLayout, indexHiddenNeuron, value);
    }

    /**
     * Добавить скрытый слой
     *
     * @param countHiddenNeurons Количество нейронов в скрытом слое
     */
    public void addLayoutHidden(int countHiddenNeurons) {
        getStructure().addLayoutHidden(countHiddenNeurons);
    }

    /**
     * @return Количество скрытых слоев
     */
    public int getCountLayoutsHidden() {
        return getStructure().getSizeLayoutsHidden();
    }
    //</editor-fold>
}
