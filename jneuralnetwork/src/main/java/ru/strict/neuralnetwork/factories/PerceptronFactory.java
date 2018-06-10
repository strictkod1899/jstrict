package ru.strict.neuralnetwork.factories;

import ru.strict.neuralnetwork.functions.ActivateFunction;
import ru.strict.neuralnetwork.networks.Perceptron;
import ru.strict.neuralnetwork.structures.NeuralNetworkHiddenStructure;

/**
 * Фабрика нейронной сети - Перцептрон
 * <p><b>Пример использования:</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *     NeuralNetworkHiddenFactory<NeuralNetworkHiddenStructure,Perceptron> factory
 *                 = new PerceptronFactory(3, 6, 2, new Sigmoid(), true);
 *
 *     // Обучающие данные
 *     Neuron[] inputSet = new Neuron[]{new Neuron(1, "Title1" ,NeuronType.INPUT), new Neuron(0, "Title2", NeuronType.INPUT)
 *                     , new Neuron(1, "Title2", NeuronType.INPUT)};
 *     Neuron[] outputSet = new Neuron[]{new Neuron(1, NeuronType.OUTPUT), new Neuron(0, NeuronType.OUTPUT)};
 *     factory.addTrainingSet(inputSet, outputSet);
 *     ...
 *
 *     inputSet = new Neuron[]{new Neuron(1, "Title1" ,NeuronType.INPUT), new Neuron(0, "Title2", NeuronType.INPUT)
 *                     , new Neuron(1, "Title2", NeuronType.INPUT)};
 *     outputSet = new Neuron[]{new Neuron(1, NeuronType.OUTPUT), new Neuron(0, NeuronType.OUTPUT)};
 *     factory.addTestSet(inputSet, outputSet);
 *     ...
 *
 *     NeuralNetworkHidden network = factory.createNeuralNetwork();
 *     network.generateSynapses();
 *     network.learn(5000, 0.2f, 0f);
 *     ResponseTestNeuron[] responses = network.test();
 *
 *     for(ResponseTestNeuron response : responses) {
 *         String expected = "";
 *         String actually = "";
 *         for(Neuron neuron : response.getExpectedNeurons())
 *             expected += neuron.getValue() + " | ";
 *         for(Neuron neuron : response.getActualNeurons())
 *             actually += neuron.getValue() + " | ";
 *         System.out.println(String.format("Expected: %s\t Actually: %s", expected, actually));
 *     }
 * </pre></code>
 */
public class PerceptronFactory<STRUCT extends NeuralNetworkHiddenStructure, NNETWORK extends Perceptron>
        extends NeuralNetworkHiddenFactory<STRUCT, NNETWORK> {

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public PerceptronFactory(int countInputs, int countOutputs, ActivateFunction activateFunction) {
        super(countInputs, countOutputs, activateFunction);
    }

    public PerceptronFactory(int countInputs, int countHiddens, int countOutputs
            , ActivateFunction activateFunction, boolean isUseBias) {
        super(countInputs, countHiddens, countOutputs, activateFunction, isUseBias);
    }
    //</editor-fold>

    @Override
    public NNETWORK createNeuralNetwork() {
        return (NNETWORK) new Perceptron(getData(), getStructure(), getActivateFunction());
    }
}
