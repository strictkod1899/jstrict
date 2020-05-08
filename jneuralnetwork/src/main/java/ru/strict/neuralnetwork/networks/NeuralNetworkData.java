package ru.strict.neuralnetwork.networks;

import ru.strict.validate.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
class NeuralNetworkData implements Cloneable {

    private int countInputs;
    private int countOutputs;

    private List<NeuralNetworkDataSet> trainingSets;
    private List<NeuralNetworkDataSet> testSets;

    //<editor-fold defaultstate="collapsed" desc="constructors">
    NeuralNetworkData(int countInputs, int countOutputs) {
        Validator.isLess(countInputs, "countInputs", 1)
                .reason("inputNeurons is NULL")
                .isLess(countOutputs, "countOutputs", 1)
                .reason("countOutputs is NULL")
                .onThrow();

        this.countInputs = countInputs;
        this.countOutputs = countOutputs;
        trainingSets = new ArrayList<>();
        testSets = new ArrayList<>();
    }
    //</editor-fold>

    /**
     * Получить тестовые данные в случайном порядке
     *
     * @return
     */
    public List<NeuralNetworkDataSet> getRandomTrainingSets() {
        List<NeuralNetworkDataSet> randomTrainingSets = new ArrayList<>();
        Random random = new Random();
        List<Integer> usedIndeces = new ArrayList();
        while (randomTrainingSets.size() != trainingSets.size()) {
            int i = random.nextInt(trainingSets.size());
            if (!usedIndeces.contains(i)) {
                usedIndeces.add(i);
                randomTrainingSets.add(trainingSets.get(i));
            }
        }
        return randomTrainingSets;
    }

    //<editor-fold defaultstate="collapsed" desc="Get/Set">
    public void addTrainingSet(NeuralNetworkDataSet set) {
        if (set.getCountInput() != countInputs || set.getCountOutput() != countOutputs) {
            throw new IllegalArgumentException(
                    "number input neurons or number output neurons to trainings differenced from required data " +
                            "structure");
        }

        trainingSets.add(set);
    }

    public void addTestSet(NeuralNetworkDataSet set) {
        if (set.getCountInput() != countInputs || set.getCountOutput() != countOutputs) {
            throw new IllegalArgumentException(
                    "number input neurons or number output neurons to test differenced from required data structure");
        }

        testSets.add(set);
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
    @Override
    public NeuralNetworkData clone() {
        NeuralNetworkData clone = new NeuralNetworkData(countInputs, countOutputs);
        clone.setTrainingSets(trainingSets);
        clone.setTestSets(testSets);
        return clone;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof NeuralNetworkData) {
            NeuralNetworkData object = (NeuralNetworkData) obj;
            return Objects.equals(trainingSets, object.getTrainingSets()) &&
                    Objects.equals(testSets, object.getTestSets()) &&
                    Objects.equals(countInputs, object.getCountInputs()) &&
                    Objects.equals(countOutputs, object.getCountOutputs());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(countInputs, countOutputs, trainingSets, testSets);
    }
    //</editor-fold>
}
