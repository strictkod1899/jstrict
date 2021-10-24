package ru.strict.neuralnetwork.networks;

public interface INeuralNetworkBuilder<RESULT extends NeuralNetworkHidden> {
    INeuralNetworkBuilder<RESULT> addLayout(int countHiddenNeurons);

    INeuralNetworkBuilder<RESULT> addTrainingSet(Neuron[] inputSet, Neuron[] outputSet);

    INeuralNetworkBuilder<RESULT> addTestSet(Neuron[] inputSet, Neuron[] outputSet);

    INeuralNetworkBuilder<RESULT> useBias(boolean isUseBias);

    RESULT build();
}
