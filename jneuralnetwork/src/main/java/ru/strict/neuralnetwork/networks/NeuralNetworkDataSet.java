package ru.strict.neuralnetwork.networks;

import java.util.Arrays;
import java.util.Objects;

/**
 * Единичный набор данных для использования в нейронной сети.
 * <p><b>Пример использования (операция xor):</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *      NeuralNetworkDataSet dataSet = new NeuralNetworkDataSet(2, 1);
 *      dataSet.setInputNeurons(new Neuron[]{new Neuron(1), new Neuron(1)});
 *      dataSet.setOutputNeurons(new Neuron[]{new Neuron(1)});
 * </pre></code>
 */
class NeuralNetworkDataSet {

    private int countInput;
    private int countOutput;

    /**
     * Входные нейроны
     */
    private Neuron[] inputNeurons;
    /**
     * Ожидаемые выходные нейроны
     */
    private Neuron[] outputNeurons;

    //<editor-fold defaultstate="collapsed" desc="constructors">
    NeuralNetworkDataSet(Neuron[] inputNeurons, Neuron[] outputNeurons) {
        if (inputNeurons == null || inputNeurons.length < 1) {
            throw new IllegalArgumentException("inputNeurons is NULL or empty");
        }
        if (outputNeurons == null || outputNeurons.length < 1) {
            throw new IllegalArgumentException("outputNeurons is NULL or empty");
        }
        this.inputNeurons = inputNeurons;
        this.outputNeurons = outputNeurons;
        this.countInput = inputNeurons.length;
        this.countOutput = outputNeurons.length;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Get/Set">
    public void setInputNeurons(Neuron... inputNeurons) {
        if (inputNeurons.length != countInput) {
            throw new IllegalArgumentException(
                    "number input neurons to data set differenced from required data structure");
        }

        this.inputNeurons = inputNeurons;
    }

    public void setOutputNeurons(Neuron... outputNeurons) {
        if (outputNeurons.length != countOutput) {
            throw new IllegalArgumentException(
                    "number output neurons to data set differenced from required data structure");
        }

        this.outputNeurons = outputNeurons;
    }

    public int getCountInput() {
        return countInput;
    }

    public int getCountOutput() {
        return countOutput;
    }

    public Neuron[] getInputNeurons() {
        return inputNeurons;
    }

    public Neuron[] getOutputNeurons() {
        return outputNeurons;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Base override">
    @Override
    public String toString() {
        final StringBuilder inputs = new StringBuilder("");
        final StringBuilder outputs = new StringBuilder("");
        Arrays.stream(inputNeurons).forEach((i) -> inputs.append(i.getValue() + " | "));
        Arrays.stream(outputNeurons).forEach((i) -> outputs.append(i.getValue() + " | "));
        return String.format("DataSet [%s/%s]: inputs - %s; outputs - %s",
                countInput,
                countOutput,
                inputs.toString(),
                outputs.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof NeuralNetworkDataSet) {
            NeuralNetworkDataSet object = (NeuralNetworkDataSet) obj;
            return countInput == object.countInput &&
                    countOutput == object.countOutput &&
                    Arrays.equals(inputNeurons, object.inputNeurons) &&
                    Arrays.equals(outputNeurons, object.outputNeurons);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(countInput, countOutput);
        result = 31 * result + Arrays.hashCode(inputNeurons);
        result = 31 * result + Arrays.hashCode(outputNeurons);
        return result;
    }
    //</editor-fold>
}
