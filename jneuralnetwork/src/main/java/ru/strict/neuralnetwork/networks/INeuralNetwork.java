package ru.strict.neuralnetwork.networks;

/**
 * Базовый интерфейс для реализации нейронной сети
 */
public interface INeuralNetwork {
    /**
     * Обучить нейронную сеть на ранее заданных обучающих данных
     *
     * @param epochs Количество эпох
     * @param speed Скорость обучения
     * @paraитать результат на основе переданных входных нейрm moment Момент
     */
    void learn(int epochs, float speed, float moment);

    /**
     * Протестировать нейронную сеть на установленных тестовых данных
     *
     * @return Результат расчета на тестовой выборке
     */
    TestResult[] test();

    /**
     * Расчонов
     *
     * @param inputNeurons Входные нейроны для расчета
     * @return Выходные нейроны
     */
    Neuron[] calc(Neuron[] inputNeurons);
}
