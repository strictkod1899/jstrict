package ru.strict.neuralnetwork.data;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Данные для обучения и тестирования нейронной сети.
 * <p><b>Пример использования (1):</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *      NeuralNetworkData data = new NeuralNetworkData(3, 1);
 *      String n1Caption = "Водка";
 *      String n2Caption = "Дождь";
 *      String n3Caption = "Друзья";
 *      Neuron[] inputSet = new Neuron[]{new Neuron(1, n1Caption), new Neuron(1, n2Caption), new Neuron(0, n3Caption)};
 *      Neuron[] outputSet = new Neuron[]{new Neuron(0)};
 *      data.addTrainingSet(inputSet, outputSet);
 * </pre></code>
 * <p><b>Пример использования (операция xor) (2):</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *      NeuralNetworkData data = new NeuralNetworkData(2, 1);
 *      Neuron[] inputSet = new Neuron[]{new Neuron(1), new Neuron(1)};
 *      Neuron[] outputSet = new Neuron[]{new Neuron(1)};
 *      data.addTrainingSet(inputSet, outputSet);
 * </pre></code>
 * Второй варинат использования:
 * <p><b>Пример использования (операция xor) (3):</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *      NeuralNetworkData data = new NeuralNetworkData(2, 1);
 *      NeuralNetworkDataSet dataSet = new NeuralNetworkDataSet(2, 1);
 *      dataSet.setInputNeurons(new Neuron[]{new Neuron(1), new Neuron(1)});
 *      dataSet.setOutputNeurons(new Neuron[]{new Neuron(1)});
 *      data.addTrainingSet(dataSet);
 * </pre></code>
 */
public class NeuralNetworkData implements Cloneable{

    private int countInputs;
    private int countOutputs;

    private List<NeuralNetworkDataSet> trainingSets;
    private List<NeuralNetworkDataSet> testSets;

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public NeuralNetworkData(int countInputs, int countOutputs) {
        this.countInputs = countInputs;
        this.countOutputs = countOutputs;
        trainingSets = new LinkedList();
        testSets = new LinkedList();
    }
    //</editor-fold>

    /**
     * Получить тестовые данные в случайном порядке
     * @return
     */
    public List<NeuralNetworkDataSet> getRandomTrainingSets() {
        List<NeuralNetworkDataSet> randomTrainingSets = new LinkedList<>();
        Random random = new Random();
        List<Integer> usedIndeces = new LinkedList();
        while(randomTrainingSets.size()!=trainingSets.size()){
            int i = random.nextInt(trainingSets.size());
            if(!usedIndeces.contains(i)) {
                usedIndeces.add(i);
                randomTrainingSets.add(trainingSets.get(i));
            }
        }
        return randomTrainingSets;
    }

    //<editor-fold defaultstate="collapsed" desc="Get/Set">
    public void addTrainingSet(NeuralNetworkDataSet set){
        if(set.getCountInput()!= countInputs || set.getCountOutput()!= countOutputs)
            return;

        trainingSets.add(set);
    }

    public void addTestSet(NeuralNetworkDataSet set){
        if(set.getCountInput()!= countInputs || set.getCountOutput()!= countOutputs)
            return;

        testSets.add(set);
    }

    public void addTrainingSet(Neuron[] inputSet, Neuron[] outputSet){
        if(inputSet.length!= countInputs || outputSet.length!= countOutputs)
            return;

        trainingSets.add(new NeuralNetworkDataSet(inputSet, outputSet));
    }

    public void addTestSet(Neuron[] inputSet, Neuron[] outputSet){
        if(inputSet.length!= countInputs || outputSet.length!= countOutputs)
            return;

        testSets.add(new NeuralNetworkDataSet(inputSet, outputSet));
    }

    public int getCountInputs() {
        return countInputs;
    }

    public int getCountOutputs() {
        return countOutputs;
    }

    public List<NeuralNetworkDataSet> getTrainingSets() {
        return trainingSets;
    }

    public List<NeuralNetworkDataSet> getTestSets() {
        return testSets;
    }

    private void setTrainingSets(List<NeuralNetworkDataSet> trainingSets) {
        this.trainingSets = trainingSets;
    }

    private void setTestSets(List<NeuralNetworkDataSet> testSets) {
        this.testSets = testSets;
    }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Base override">
    public NeuralNetworkData clone(){
        NeuralNetworkData clone = new NeuralNetworkData(countInputs, countOutputs);
        clone.setTrainingSets(trainingSets);
        clone.setTestSets(testSets);
        return clone;
    }
    //</editor-fold>
}
