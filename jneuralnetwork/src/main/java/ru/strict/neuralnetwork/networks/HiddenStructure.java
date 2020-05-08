package ru.strict.neuralnetwork.networks;

import ru.strict.validate.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Структура нейронной сети с поддержкой скрытых слоев
 * <p><b>Пример использования (1):</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *      HiddenStructure structure = new HiddenStructure(3, 1, true);
 *      structure.addLayout(2);
 *      NeuralNetworkHidden network = new Perceptron(data, structure);
 *      network.learn(3000, 0.2f, 0.3f);
 *      ...
 * </pre></code>
 * Если планируется использовать нейронную сеть с одним скрытым слоем, тогда возможно использовать конструктор с
 * четырями параметрами,
 * где второй параметр определяет количество элементов в скрытом слое.
 * <p><b>Пример использования (2):</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *       HiddenStructure structure = new HiddenStructure(3, 2, 1, false);
 *       NeuralNetworkHidden network = new Perceptron(data, structure);
 *       network.learn(3000, 0.2f, 0.3f);
 *       ...
 *  </pre></code>
 */
class HiddenStructure extends NeuralNetworkStructure {

    /**
     * Скрытые слои нейронной сети
     */
    private List<LayoutHidden> layoutsHidden;

    //<editor-fold defaultstate="collapsed" desc="constructors">
    private HiddenStructure(int countInputs, int countOutputs) {
        super(countInputs, countOutputs);
        this.layoutsHidden = new ArrayList<>();
    }

    HiddenStructure(int countInputs, int countHiddens, int countOutputs) {
        super(countInputs, countOutputs);
        Validator.isLess(countHiddens, "countHiddens", 1)
                .details(
                        "Neural Network structure do not should have hidden neurons count is negative. [Hidden " +
                                "neurons count < 1]")
                .onThrow();

        this.layoutsHidden = new ArrayList<>();
        layoutsHidden.add(new LayoutHidden(countHiddens));
    }
    //</editor-fold>

    @Override
    public void generateSynapses() {
        int countLayouts = layoutsHidden.size();
        for (int i = 0; i < countLayouts; i++) {
            Neuron[] hiddens = layoutsHidden.get(i).getNeurons();
            for (Neuron hidden : hiddens) {
                if (i == 0) {
                    Neuron[] inputs = getInputNeurons();
                    for (Neuron input : inputs) {
                        generateSynapse(input, hidden);
                    }

                    Neuron bias = getBias();
                    if (bias.getValue() == 1) {
                        generateSynapse(bias, hidden);
                    }
                }

                if (i > 0) {
                    Neuron[] preHiddens = layoutsHidden.get(i - 1).getNeurons();
                    for (Neuron preHidden : preHiddens) {
                        generateSynapse(preHidden, hidden);
                    }

                    Neuron bias = layoutsHidden.get(i - 1).getBias();
                    if (bias.getValue() == 1) {
                        generateSynapse(bias, hidden);
                    }
                }

                if (i == countLayouts - 1) {
                    Neuron[] outputs = getOutputNeurons();
                    for (Neuron output : outputs) {
                        generateSynapse(hidden, output);

                        Neuron bias = layoutsHidden.get(i).getBias();
                        if (bias.getValue() == 1) {
                            generateSynapse(bias, output);
                        }
                    }
                }
            }
        }
    }

    private void generateSynapse(Neuron sourceNeuron, Neuron targetNeuron) {
        Synapse synapse = findSynapse(sourceNeuron, targetNeuron);
        if (synapse == null) {
            addSynapse(new Synapse(sourceNeuron, targetNeuron, generateWeight()));
        } else {
            setSynapseWeight(synapse, generateWeight());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Get/Set">
    public void setHiddenValue(int indexLayout, int indexHiddenNeuron, float value) {
        layoutsHidden.get(indexLayout).getNeurons()[indexHiddenNeuron].setValue(value);
    }

    /**
     * Добавить скрытый слой
     *
     * @param countHiddenNeurons Количество нейронов в скрытом слое
     */
    public void addLayoutHidden(int countHiddenNeurons) {
        Validator.isLess(countHiddenNeurons, "countHiddenNeurons", 1)
                .details(
                        "Neural Network structure do not should have hidden neurons count is negative. [Hidden " +
                                "neurons count < 1]")
                .onThrow();

        LayoutHidden layoutHidden = new LayoutHidden(countHiddenNeurons);
        layoutHidden.setBias(getBias().getValue() == 1 ? true : false);
        layoutsHidden.add(layoutHidden);
    }

    /**
     * @return Количество скрытых слоев
     */
    public int getSizeLayoutsHidden() {
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
    @Override
    public HiddenStructure clone() {
        HiddenStructure clone = new HiddenStructure(getCountInputs(), getCountOutputs());
        clone.setUseBias(getBias().getValue() == 1 ? true : false);
        clone.setLayoutsHidden(layoutsHidden);
        return clone;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof HiddenStructure) {
            HiddenStructure object = (HiddenStructure) obj;
            return super.equals(obj) && Objects.equals(layoutsHidden, object.getLayoutsHidden());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), layoutsHidden);
    }
    //</editor-fold>
}
