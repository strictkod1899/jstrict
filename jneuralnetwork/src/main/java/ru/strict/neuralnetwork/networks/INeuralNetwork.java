package ru.strict.neuralnetwork.networks;

import ru.strict.neuralnetwork.data.Neuron;
import ru.strict.neuralnetwork.data.ResponseTestNeuron;

/**
 * Базовый интерфейс для реализации нейронной сети
 */
public interface INeuralNetwork extends AutoCloseable{
    /**
     * Обучить нейронную сеть на ранее заданных обучающих данных
     * @param epochs Количество эпох
     * @param learnRate Скорость обучения
     * @param moment Момент
     */
    void learn(int epochs, float learnRate, float moment);

    /**
     * Протестировать нейронную сеть на установленных тестовых данных
     * @return Результат расчета на тестовой выборке
     */
    ResponseTestNeuron[] test();

    /**
     * Расчитать результат на основе переданных входных нейронов
     * @param inputNeurons Входные нейроны для расчета
     * @return Выходные нейроны
     */
    Neuron[] calc(Neuron[] inputNeurons);

    void close();
}
