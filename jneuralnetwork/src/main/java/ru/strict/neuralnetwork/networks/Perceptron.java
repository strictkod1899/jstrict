package ru.strict.neuralnetwork.networks;

import ru.strict.neuralnetwork.data.*;
import ru.strict.neuralnetwork.functions.ActivateFunction;
import ru.strict.neuralnetwork.structures.NeuralNetworkHiddenStructure;

import java.util.Arrays;
import java.util.List;

/**
 * <p>Реализация нейронной сети - Перцептрон</p>
 * <b>Примечание:</b> рекомендуется создавать объект нейронной сети с помощью соответствующего объекта-фабрики
 * <p><b>Пример использования:</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *      NeuralNetworkData data = new NeuralNetworkData(3, 1);
 *      // Установка обучающих значений
 *      Neuron[] inputSet = new Neuron[]{new Neuron(1), new Neuron(1), new Neuron(0)};
 *      Neuron[] outputSet = new Neuron[]{new Neuron(0)};
 *      data.addTrainingSet(inputSet, outputSet);
 *      ...
 *      NeuralNetworkHiddenStructure structure = new NeuralNetworkHiddenStructure(3, 1);
 *      structure.addLayoutHidden(2);
 *      NeuralNetworkHidden network = new Perceptron(data, structure);
 *      ...
 * </pre></code>
 */
public class Perceptron extends NeuralNetworkHidden<NeuralNetworkData, NeuralNetworkHiddenStructure> {

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public Perceptron(NeuralNetworkData data, NeuralNetworkHiddenStructure structure, ActivateFunction activateFunction) {
        super(data, structure, activateFunction);
    }
    //</editor-fold>

    @Override
    protected void implementLearn(NeuralNetworkDataSet trainingSet, float learnRate, float moment) {
          Neuron[] inputs = trainingSet.getInputNeurons();
          Neuron[] outputsExpected = trainingSet.getOutputNeurons();
          Neuron[] outputsActually = feedforward(inputs);

          backPropagation(outputsExpected, outputsActually, learnRate, moment);
    }

    //<editor-fold defaultState="collapse" desc="learn">
    /**
     * Метод обратного распространения
     * @param outputsExpected Ожидаемые значения на выходном слое
     * @param outputsActually Фактические значения на выходном слое\
     * @param learnRate Скорость обучения
     * @param moment Момент
     * @return
     */
    private void backPropagation(Neuron[] outputsExpected, Neuron[] outputsActually, float learnRate, float moment){
        for(int i=0; i<outputsActually.length; i++)
            backPropagationByOutput(outputsActually[i], outputsExpected[i], learnRate, moment);
    }

    private void backPropagationByOutput(Neuron target, Neuron outputExpected, float learnRate, float moment){
        float errorOutput = (outputExpected.getValue() - target.getValue())*target.getValue()*(1-target.getValue());
        calcWeight(target, errorOutput, 0, learnRate, moment);
    }

    private void calcWeight(Neuron target, float preError, float preDelta, float learnRate, float moment){
        Synapse[] synapses = findSynapses(target);
        Neuron[] neurons = Arrays.stream(synapses).map(s -> s.getSourceNeuron()).toArray(Neuron[]::new);
        for(Neuron neuron : neurons){
            Synapse synapse = findSynapse(neuron, target);
            float error = preError * synapse.getWeight() * neuron.getValue() * (1 - neuron.getValue());
            float deltaWeight = neuron.getValue() * preError * learnRate + moment * preDelta;
            float newWeight = synapse.getWeight() + deltaWeight;
            calcWeight(neuron, error, deltaWeight, learnRate, moment);
            setSynapseWeight(synapse, newWeight);
        }
    }

    @Override
    protected Neuron[] feedforward(Neuron[] inputs) {
        feedforwardInputs(inputs);
        feedforwardHiddens();
        feedforwardOutputs();
        return getOutputNeurons();
    }

    //<editor-fold defaultState="collapse" desc="feedforwards">
    /**
     * Обработка входных нейронов
     * @param inputs Входные нейроны
     */
    private void feedforwardInputs(Neuron[] inputs){
        for(int i=0; i<inputs.length; i++)
            setInputValue(i, inputs[i].getValue());

        inputs = getInputNeurons();
        LayoutHidden layoutHidden = getLayoutHidden(0);
        int countHiddens = layoutHidden.size();
        for(int i=0; i<countHiddens; i++){
            float hiddenValue = 0;
            for(Neuron input : inputs) {
                Synapse synapse = findSynapse(input, layoutHidden.getNeuron(i));
                hiddenValue += (synapse.getSourceNeuron().getValue() * synapse.getWeight());
            }
            // Расчет нейрона смещения
            Synapse biasSynapse = findSynapse(getBias(), layoutHidden.getNeuron(i));
            if(biasSynapse != null)
                hiddenValue += getBias().getValue() * biasSynapse.getWeight();
            setHiddenValue(0, i, getActivateFunction().calc(hiddenValue));
        }
    }

    /**
     * Обработка скрытых слоев, если их больше 1
     */
    private void feedforwardHiddens(){
        if(getCountLayoutsHidden()>1){
            for(int i = 1; i< getCountLayoutsHidden(); i++){
                LayoutHidden layoutHidden = getLayoutHidden(i);
                int countHiddens = layoutHidden.size();
                Neuron[] preHiddens = getLayoutHidden(i-1).getNeurons();
                for(int j = 0; j<countHiddens; j++){
                    Neuron hidden = layoutHidden.getNeurons()[j];
                    float hiddenValue = 0;
                    for(Neuron preHidden : preHiddens){
                        Synapse synapse = findSynapse(preHidden, hidden);
                        hiddenValue += (synapse.getSourceNeuron().getValue() * synapse.getWeight());
                    }
                    // Расчет нейрона смещения
                    Neuron bias = getLayoutHidden(i-1).getBias();
                    Synapse biasSynapse = findSynapse(bias, hidden);
                    if(biasSynapse != null)
                        hiddenValue += bias.getValue() * biasSynapse.getWeight();

                    setHiddenValue(i, j, getActivateFunction().calc(hiddenValue));
                }
            }
        }
    }

    /**
     * Обработка выходных нейронов
     */
    private void feedforwardOutputs(){
        Neuron[] hiddens = getLayoutHidden(getCountLayoutsHidden()-1).getNeurons();
        Neuron[] outputs = getOutputNeurons();
        int countOutputs = outputs.length;
        for(int i=0; i<countOutputs; i++){
            float outputValue = 0;
            for(Neuron hidden : hiddens) {
                Synapse synapse = findSynapse(hidden, outputs[i]);
                outputValue += (synapse.getSourceNeuron().getValue() * synapse.getWeight());
            }

            // Расчет нейрона смещения
            Neuron bias = getLayoutHidden(getCountLayoutsHidden()-1).getBias();
            Synapse biasSynapse = findSynapse(bias, outputs[i]);
            if(biasSynapse != null)
                outputValue += bias.getValue() * biasSynapse.getWeight();

            setOutputValue(i, getActivateFunction().calc(outputValue));
        }
    }
    //</editor-fold>
    //</editor-fold>

    @Override
    public ResponseTestNeuron[] test() {
        List<NeuralNetworkDataSet> testSets = getData().getTestSets();
        ResponseTestNeuron[] responses = new ResponseTestNeuron[testSets.size()];
        for(int i=0; i<testSets.size(); i++){
            NeuralNetworkDataSet testSet = testSets.get(i);
            Neuron[] originalActualNeurons = calc(testSet.getInputNeurons());
            Neuron[] actualNeurons = new Neuron[originalActualNeurons.length];
            for (int j=0; j<actualNeurons.length; j++)
                actualNeurons[j] = originalActualNeurons[j].clone();
            responses[i] = new ResponseTestNeuron(testSet.getOutputNeurons(), actualNeurons);
        }
        return responses;
    }

    @Override
    public Neuron[] calc(Neuron[] inputNeurons) {
        return feedforward(inputNeurons);
    }
}
