package ru.strict.neuralnetwork.structures;

import ru.strict.neuralnetwork.data.LayoutHidden;
import ru.strict.neuralnetwork.data.Neuron;
import ru.strict.neuralnetwork.data.Synapse;

import java.util.LinkedList;
import java.util.List;

/**
 * Структура нейронной сети с поддержкой скрытых слоев
 * <p><b>Пример использования (1):</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *      NeuralNetworkHiddenStructure structure = new NeuralNetworkHiddenStructure(3, 1, true);
 *      structure.addLayoutHidden(2);
 *      NeuralNetworkHidden network = new Perceptron(data, structure);
 *      network.generateSynapses();
 *      ...
 * </pre></code>
 * Если планируется использовать нейронную сеть с одним скрытым слоем, тогда возможно использовать конструктор с четырями параметрами,
 * где второй параметр определяет количество элементов в скрытом слое.
 * <p><b>Пример использования (2):</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *       NeuralNetworkHiddenStructure structure = new NeuralNetworkHiddenStructure(3, 2, 1, false);
 *       NeuralNetworkHidden network = new Perceptron(data, structure);
 *       network.generateSynapses();
 *       ...
 *  </pre></code>
 */
public class NeuralNetworkHiddenStructure extends NeuralNetworkStructure {

    /**
     * Скрытые слои нейронной сети
     */
    private List<LayoutHidden> layoutsHidden;

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public NeuralNetworkHiddenStructure(int countInput, int countOutput, boolean isUseBias) {
        super(countInput, countOutput, isUseBias);
        this.layoutsHidden = new LinkedList<>();
    }

    public NeuralNetworkHiddenStructure(int countInputs, int countHiddens, int countOutputs, boolean isUseBias) {
        super(countInputs, countOutputs, isUseBias);
        if(countHiddens < 1)
            throw new IllegalArgumentException("Neural Network structure do not should have hidden neurons count is negative. [Hidden neurons count < 1]");

        this.layoutsHidden = new LinkedList<>();
        layoutsHidden.add(new LayoutHidden(countHiddens));
        layoutsHidden.get(0).setBias(isUseBias);
    }
    //</editor-fold>

    @Override
    public void generateSynapses() {
        int countLayouts = layoutsHidden.size();
        for(int i=0; i<countLayouts; i++){
            Neuron[] hiddens = layoutsHidden.get(i).getNeurons();
            for(Neuron hidden : hiddens){
                if(i==0) {
                    Neuron[] inputs = getInputNeurons();
                    for(Neuron input : inputs)
                        generateSynapse(input, hidden);

                    Neuron bias = getBias();
                    if(bias.getValue()==1)
                        generateSynapse(bias, hidden);
                }

                if(i>0){
                    Neuron[] preHiddens = layoutsHidden.get(i-1).getNeurons();
                    for(Neuron preHidden : preHiddens)
                        generateSynapse(preHidden, hidden);

                    Neuron bias = layoutsHidden.get(i-1).getBias();
                    if(bias.getValue()==1)
                        generateSynapse(bias, hidden);
                }

                if(i==countLayouts-1){
                    Neuron[] outputs = getOutputNeurons();
                    for(Neuron output : outputs) {
                        generateSynapse(hidden, output);

                        Neuron bias = layoutsHidden.get(i).getBias();
                        if(bias.getValue()==1)
                            generateSynapse(bias, output);
                    }
                }
            }
        }
    }

    private void generateSynapse(Neuron sourceNeuron, Neuron targetNeuron){
        Synapse synapse = findSynapse(sourceNeuron, targetNeuron);
        if(synapse==null)
            addSynapse(new Synapse(sourceNeuron, targetNeuron, generateWeight()));
        else
            setSynapseWeight(synapse, generateWeight());
    }

    //<editor-fold defaultstate="collapsed" desc="Get/Set">
    public void setHiddenValue(int indexLayout, int indexHiddenNeuron, float value){
        layoutsHidden.get(indexLayout).getNeurons()[indexHiddenNeuron].setValue(value);
    }

    /**
     * Добавить скрытый слой
     * @param countHiddenNeurons Количество нейронов в скрытом слое
     */
    public void addLayoutHidden(int countHiddenNeurons){
        LayoutHidden layoutHidden = new LayoutHidden(countHiddenNeurons);
        layoutHidden.setBias(getBias().getValue()==1? true: false);
        layoutsHidden.add(layoutHidden);
    }

    /**
     * @return Количество скрытых слоев
     */
    public int getSizeLayoutsHidden(){
        return layoutsHidden.size();
    }

    public List<LayoutHidden> getLayoutsHidden() {
        return layoutsHidden;
    }

    private void setLayoutsHidden(List<LayoutHidden> layoutsHidden) {
        this.layoutsHidden = layoutsHidden;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Base override">
    public NeuralNetworkHiddenStructure clone(){
        NeuralNetworkHiddenStructure clone =
                new NeuralNetworkHiddenStructure(getCountInputs(),getCountOutputs(), getBias().getValue()==0?false:true);
        clone.setLayoutsHidden(layoutsHidden);
        return clone;
    }
    //</editor-fold>
}
