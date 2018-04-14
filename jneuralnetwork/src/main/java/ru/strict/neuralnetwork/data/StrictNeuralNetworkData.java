package ru.strict.neuralnetwork.data;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Данные для обучения и тестирования нейронной сети.
 * <p><b>Пример использования (1):</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *      StrictNeuralNetworkData data = new StrictNeuralNetworkData(3, 1);
 *      String n1Caption = "Водка";
 *      String n2Caption = "Дождь";
 *      String n3Caption = "Друзья";
 *      Neuron[] inputSet = new Neuron[]{new Neuron(1, n1Caption), new Neuron(1, n2Caption), new Neuron(0, n3Caption)};
 *      Neuron[] outputSet = new Neuron[]{new Neuron(0)};
 *      data.addTrainingSet(inputSet, outputSet);
 * </pre></code>
 * <p><b>Пример использования (операция xor) (2):</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *      StrictNeuralNetworkData data = new StrictNeuralNetworkData(2, 1);
 *      Neuron[] inputSet = new Neuron[]{new Neuron(1), new Neuron(1)};
 *      Neuron[] outputSet = new Neuron[]{new Neuron(1)};
 *      data.addTrainingSet(inputSet, outputSet);
 * </pre></code>
 * Второй варинат использования:
 * <p><b>Пример использования (операция xor) (3):</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *      StrictNeuralNetworkData data = new StrictNeuralNetworkData(2, 1);
 *      StrictNeuralNetworkDataSet dataSet = new StrictNeuralNetworkDataSet(2, 1);
 *      dataSet.setInputNeurons(new Neuron[]{new Neuron(1), new Neuron(1)});
 *      dataSet.setOutputNeurons(new Neuron[]{new Neuron(1)});
 *      data.addTrainingSet(dataSet);
 * </pre></code>
 */
public class StrictNeuralNetworkData implements Cloneable{

    private int countInputs;
    private int countOutputs;

    private List<StrictNeuralNetworkDataSet> trainingSets;
    private List<StrictNeuralNetworkDataSet> testSets;

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public StrictNeuralNetworkData(int countInputs, int countOutputs) {
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
    public List<StrictNeuralNetworkDataSet> getRandomTrainingSets() {
        List<StrictNeuralNetworkDataSet> randomTrainingSets = new LinkedList<>();
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
    public void addTrainingSet(StrictNeuralNetworkDataSet set){
        if(set.getCountInput()!= countInputs || set.getCountOutput()!= countOutputs)
            return;

        trainingSets.add(set);
    }

    public void addTestSet(StrictNeuralNetworkDataSet set){
        if(set.getCountInput()!= countInputs || set.getCountOutput()!= countOutputs)
            return;

        testSets.add(set);
    }

    public void addTrainingSet(Neuron[] inputSet, Neuron[] outputSet){
        if(inputSet.length!= countInputs || outputSet.length!= countOutputs)
            return;

        trainingSets.add(new StrictNeuralNetworkDataSet(inputSet, outputSet));
    }

    public void addTestSet(Neuron[] inputSet, Neuron[] outputSet){
        if(inputSet.length!= countInputs || outputSet.length!= countOutputs)
            return;

        testSets.add(new StrictNeuralNetworkDataSet(inputSet, outputSet));
    }

    public int getCountInputs() {
        return countInputs;
    }

    public int getCountOutputs() {
        return countOutputs;
    }

    public List<StrictNeuralNetworkDataSet> getTrainingSets() {
        return trainingSets;
    }

    public List<StrictNeuralNetworkDataSet> getTestSets() {
        return testSets;
    }

    private void setTrainingSets(List<StrictNeuralNetworkDataSet> trainingSets) {
        this.trainingSets = trainingSets;
    }

    private void setTestSets(List<StrictNeuralNetworkDataSet> testSets) {
        this.testSets = testSets;
    }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Base override">
    public StrictNeuralNetworkData clone(){
        StrictNeuralNetworkData clone = new StrictNeuralNetworkData(countInputs, countOutputs);
        clone.setTrainingSets(trainingSets);
        clone.setTestSets(testSets);
        return clone;
    }
    //</editor-fold>
}
