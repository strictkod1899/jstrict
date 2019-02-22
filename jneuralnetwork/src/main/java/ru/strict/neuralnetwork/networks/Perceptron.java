package ru.strict.neuralnetwork.networks;

import ru.strict.neuralnetwork.functions.IActivateFunction;
import ru.strict.patterns.IBuilder;

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
 *      HiddenStructure structure = new HiddenStructure(3, 1);
 *      structure.addLayout(2);
 *      NeuralNetworkHidden network = new Perceptron(data, structure);
 *      ...
 * </pre></code>
 */
public class Perceptron extends NeuralNetworkHidden<NeuralNetworkData, HiddenStructure> {

    //<editor-fold defaultstate="collapsed" desc="constructors">
    Perceptron(NeuralNetworkData data, HiddenStructure structure, IActivateFunction activateFunction) {
        super(data, structure, activateFunction);
    }
    //</editor-fold>

    @Override
    protected void implementLearn(NeuralNetworkDataSet trainingSet, float speed, float moment) {
          Neuron[] inputs = trainingSet.getInputNeurons();
          Neuron[] outputsExpected = trainingSet.getOutputNeurons();
          Neuron[] outputsActually = feedforward(inputs);

          backPropagation(outputsExpected, outputsActually, speed, moment);
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
        for(int i=0; i<inputs.length; i++) {
            setInputValue(i, inputs[i].getValue());
        }

        inputs = getInputNeurons();
        LayoutHidden layoutHidden = getLayoutHidden(0);
        int countHiddens = layoutHidden.size();
        for(int i=0; i<countHiddens; i++){
            float inputValue = 0;
            for(Neuron input : inputs) {
                Synapse synapse = findSynapse(input, layoutHidden.getNeuron(i));
                inputValue += (synapse.getSourceNeuron().getValue() * synapse.getWeight());
            }
            // Расчет нейрона смещения
            Synapse biasSynapse = findSynapse(getBias(), layoutHidden.getNeuron(i));
            if(biasSynapse != null) {
                inputValue += getBias().getValue() * biasSynapse.getWeight();
            }
            setHiddenValue(0, i, getActivateFunction().calc(inputValue));
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
                    if(biasSynapse != null) {
                        hiddenValue += bias.getValue() * biasSynapse.getWeight();
                    }

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
    public TestResult[] test() {
        List<NeuralNetworkDataSet> testSets = getData().getTestSets();
        TestResult[] responses = new TestResult[testSets.size()];
        for(int i=0; i<testSets.size(); i++){
            NeuralNetworkDataSet testSet = testSets.get(i);
            Neuron[] originalActualNeurons = calc(testSet.getInputNeurons());
            Neuron[] actualNeurons = new Neuron[originalActualNeurons.length];
            for (int j=0; j<actualNeurons.length; j++)
                actualNeurons[j] = originalActualNeurons[j].clone();
            responses[i] = new TestResult(testSet.getOutputNeurons(), actualNeurons);
        }
        return responses;
    }

    @Override
    public Neuron[] calc(Neuron[] inputNeurons) {
        return feedforward(inputNeurons);
    }

    public static Builder builder(int countInputs, int countHiddens, int countOutputs, IActivateFunction activateFunction){
        return new Builder(countInputs, countHiddens, countOutputs, activateFunction);
    }

    public static class Builder implements INeuralNetworkBuilder<Perceptron>{
        private NeuralNetworkData data;
        private HiddenStructure structure;
        private IActivateFunction activateFunction;

        private Builder(int countInputs, int countHiddens, int countOutputs, IActivateFunction activateFunction){
            this.data = new NeuralNetworkData(countInputs, countOutputs);
            this.structure = new HiddenStructure(countInputs, countHiddens, countOutputs);
            this.activateFunction = activateFunction;
        }

        /**
         * Добавить скрытый слой
         * @param countHiddenNeurons Количество нейронов в скрытом слое
         */
        @Override
        public Builder addLayout(int countHiddenNeurons){
            structure.addLayoutHidden(countHiddenNeurons);
            return this;
        }

        @Override
        public Builder addTrainingSet(Neuron[] inputSet, Neuron[] outputSet){
            data.addTrainingSet(new NeuralNetworkDataSet(inputSet, outputSet));
            return this;
        }

        @Override
        public Builder addTestSet(Neuron[] inputSet, Neuron[] outputSet){
            data.addTestSet(new NeuralNetworkDataSet(inputSet, outputSet));
            return this;
        }

        @Override
        public Builder setUseBias(boolean isUseBias){
            structure.setUseBias(isUseBias);
            return this;
        }

        @Override
        public Perceptron build(){
            boolean isUseBias = structure.getBias().getValue()==1 ? true : false;
            structure.getLayoutsHidden().stream().forEach(l -> l.setBias(isUseBias));
            return new Perceptron(data, structure, activateFunction);
        }
    }
}
