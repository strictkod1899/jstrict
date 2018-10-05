package ru.strict.neuralnetwork.data;

import java.util.Arrays;
import java.util.Objects;

/**
 * Скрытый слой нейронной сети
 */
public class LayoutHidden {
    private Neuron[] neurons;
    private Neuron bias;

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public LayoutHidden(int countHiddenNeurons) {
        neurons = new Neuron[countHiddenNeurons];
        for(int i=0; i<countHiddenNeurons; i++)
            neurons[i] = new Neuron(0, NeuronType.HIDDEN);
        this.bias = new Neuron(0, NeuronType.BIAS);
    }

    public LayoutHidden(int countHiddenNeurons, boolean isUseBeas) {
        neurons = new Neuron[countHiddenNeurons];
        for(int i=0; i<countHiddenNeurons; i++)
            neurons[i] = new Neuron(0, NeuronType.HIDDEN);

        if(isUseBeas)
            this.bias = new Neuron(1, NeuronType.BIAS);
        else
            this.bias = new Neuron(0, NeuronType.BIAS);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Get/Set">
    public Neuron[] getNeurons() {
        return neurons;
    }

    public Neuron getNeuron(int i){
        return neurons[i];
    }

    public Neuron getBias() {
        return bias;
    }

    /**
     * <pre>
     * Установить нейрон смещения
     * <b>ВАЖНО:</b> Не использовать метод вручную. Требуемая область видимости: protected.
     * Создавать объект LayoutHidden через метод addLayoutHidden класса NeuralNetworkHiddenStructure
     * </pre>
     * @param isUseBeas
     */
    public void setBias(boolean isUseBeas) {
        if(isUseBeas)
            this.bias = new Neuron(1, NeuronType.BIAS);
        else
            this.bias = new Neuron(0, NeuronType.BIAS);
    }

    public int size(){
        return neurons.length;
    }
    //</editor-fold>


    @Override
    public boolean equals(Object obj) {
        if(obj!=null && obj instanceof LayoutHidden) {
            LayoutHidden object = (LayoutHidden) obj;
            return Arrays.equals(neurons, object.getNeurons()) &&
                    Objects.equals(bias, object.getBias());
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(bias);
        result = 31 * result + Arrays.hashCode(neurons);
        return result;
    }
}
