package ru.strict.neuralnetwork.networks;

import ru.strict.neuralnetwork.data.Neuron;
import ru.strict.neuralnetwork.data.TestResult;

/**
 * Базовый интерфейс для реализации нейронной сети
 */
public interface INeuralNetwork {
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
    TestResult[] test();

    /**
     * Расчитать результат на основе переданных входных нейронов
     * @param inputNeurons Входные нейроны для расчета
     * @return Выходные нейроны
     */
    Neuron[] calc(Neuron[] inputNeurons);
}
