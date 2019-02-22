package ru.strict.neuralnetwork.networks;

import ru.strict.patterns.IBuilder;

public interface INeuralNetworkBuilder<RESULT extends NeuralNetworkHidden> extends IBuilder<RESULT> {
    INeuralNetworkBuilder addLayout(int countHiddenNeurons);
    INeuralNetworkBuilder addTrainingSet(Neuron[] inputSet, Neuron[] outputSet);
    INeuralNetworkBuilder addTestSet(Neuron[] inputSet, Neuron[] outputSet);
    INeuralNetworkBuilder setUseBias(boolean isUseBias);
}
