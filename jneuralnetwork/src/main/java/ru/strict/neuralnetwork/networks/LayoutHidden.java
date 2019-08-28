package ru.strict.neuralnetwork.networks;

/**
 * Скрытый слой нейронной сети
 */
class LayoutHidden {
    private Neuron[] neurons;
    private Neuron bias;

    //<editor-fold defaultstate="collapsed" desc="constructors">
    LayoutHidden(int countHiddenNeurons) {
        neurons = new Neuron[countHiddenNeurons];
        for(int i=0; i<countHiddenNeurons; i++) {
            neurons[i] = new Neuron(0, NeuronType.HIDDEN);
        }
        this.bias = new Neuron(0, NeuronType.BIAS);
    }

    LayoutHidden(int countHiddenNeurons, boolean isUseBeas) {
        neurons = new Neuron[countHiddenNeurons];
        for(int i=0; i<countHiddenNeurons; i++) {
            neurons[i] = new Neuron(0, NeuronType.HIDDEN);
        }

        if(isUseBeas) {
            this.bias = new Neuron(1, NeuronType.BIAS);
        }else {
            this.bias = new Neuron(0, NeuronType.BIAS);
        }
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
     * Создавать объект LayoutHidden через метод addLayout класса HiddenStructure
     * </pre>
     * @param isUseBias
     */
    public void setBias(boolean isUseBias) {
        if(isUseBias)
            this.bias = new Neuron(1, NeuronType.BIAS);
        else
            this.bias = new Neuron(0, NeuronType.BIAS);
    }

    public int size(){
        return neurons.length;
    }
    //</editor-fold>
}
