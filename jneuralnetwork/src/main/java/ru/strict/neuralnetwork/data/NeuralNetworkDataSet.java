package ru.strict.neuralnetwork.data;

import java.util.Arrays;

/**
 * Единичный набор данных для использования в нейронной сети.
 * <p><b>Пример использования (операция xor):</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *      NeuralNetworkDataSet dataSet = new NeuralNetworkDataSet(2, 1);
 *      dataSet.setInputNeurons(new Neuron[]{new Neuron(1), new Neuron(1)});
 *      dataSet.setOutputNeurons(new Neuron[]{new Neuron(1)});
 * </pre></code>
 */
public class NeuralNetworkDataSet {

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
    public NeuralNetworkDataSet(int countInput, int countOutput) {
        this.countInput = countInput;
        this.countOutput = countOutput;
    }

    public NeuralNetworkDataSet(Neuron[] inputNeurons, Neuron[] outputNeurons) {
        this.inputNeurons = inputNeurons;
        this.outputNeurons = outputNeurons;
        this.countInput = inputNeurons.length;
        this.countOutput = outputNeurons.length;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Get/Set">
    public void setInputNeurons(Neuron...inputNeurons){
        if(inputNeurons.length!=countInput)
            return;

        this.inputNeurons = inputNeurons;
    }

    public void setOutputNeurons(Neuron...outputNeurons){
        if(outputNeurons.length!=countOutput)
            return;

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
    public String toString(){
        final StringBuilder inputs = new StringBuilder("");
        final StringBuilder outputs = new StringBuilder("");
        Arrays.stream(inputNeurons).forEach((i) -> inputs.append(i.getValue() + " | "));
        Arrays.stream(outputNeurons).forEach((i) -> outputs.append(i.getValue() + " | "));
        return String.format("DataSet [%s/%s]: inputs - %s; outputs - %s", countInput, countOutput, inputs.toString(), outputs.toString());
    }

    /*public boolean equals(Object obj){
        if(obj==null)
            return false;

        Neuron neuron = (Neuron) obj;
        return neuron.entryId.equals(entryId) && neuron.value == value && neuron.caption.equals(caption)
                && neuron.getType().equals(getType());
    }

    public Neuron clone(){
        return new Neuron(UUID.fromString(entryId.toString()), value, caption, NeuronType.values()[type.ordinal()]);
    }*/
    //</editor-fold>
}
