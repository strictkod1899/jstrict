package ru.strict.neuralnetwork.networks;

import ru.strict.neuralnetwork.data.*;
import ru.strict.neuralnetwork.functions.ActivateFunction;
import ru.strict.neuralnetwork.structures.StrictNeuralNetworkHiddenStructure;

import java.util.List;

/**
 * Нейронная сеть с поддержкой скрытых слоев
 */
public abstract class StrictNeuralNetworkHidden<DATA extends StrictNeuralNetworkData, STRUCT extends StrictNeuralNetworkHiddenStructure>
        extends StrictNeuralNetwork<DATA, STRUCT> {

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public StrictNeuralNetworkHidden(DATA data, STRUCT structure, ActivateFunction activateFunction) {
        super(data, structure, activateFunction);
    }
    //</editor-fold>

    protected abstract Neuron[] feedforward(Neuron[] inputs);

    //<editor-fold defaultstate="collapsed" desc="Get/Set">
    public LayoutHidden getLayoutHidden(int i){
        return getStructure().getLayoutsHidden().get(i);
    }

    public List<LayoutHidden> getLayoutsHidden(){
        return getStructure().getLayoutsHidden();
    }

    public void setHiddenValue(int indexLayout, int indexHiddenNeuron, float value){
        getStructure().setHiddenValue(indexLayout, indexHiddenNeuron, value);
    }

    /**
     * Добавить скрытый слой
     * @param countHiddenNeurons Количество нейронов в скрытом слое
     */
    public void addLayoutHidden(int countHiddenNeurons){
        getStructure().addLayoutHidden(countHiddenNeurons);
    }

    /**
     * @return Количество скрытых слоев
     */
    public int getCountLayoutsHidden(){
        return getStructure().getSizeLayoutsHidden();
    }

    @Override
    public DATA getData() {
        return super.getData();
    }

    @Override
    public STRUCT getStructure() {
        return super.getStructure();
    }
    //</editor-fold>
}
