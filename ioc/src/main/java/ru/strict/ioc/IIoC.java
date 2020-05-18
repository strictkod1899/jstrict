package ru.strict.ioc;

import ru.strict.ioc.exceptions.MatchInstanceTypeException;

public interface IIoC {
    /**
     * Добавить компонент как Singleton с классом --clazz--, для обращения к нему, как экземпляр класса --clazz--,
     * с передачей в конструктор параметров --constructorArguments--.
     * Если в --constructorArguments-- в качестве одного из элементов передать .class, то, по возможности, он будет
     * внедрен как зависимость.
     *
     * @param clazz Класс создаваемого компонента и для обращения к компоненту
     * @param constructorArguments Параметры передаваемые в конструктор класса --component--
     */
    <RESULT> void addComponent(Class<RESULT> clazz, Object... constructorArguments);

    /**
     * Добавить компонент с классом --clazz--, для обращения к нему, как экземпляр класса --clazz--,
     * с передачей в конструктор параметров --constructorArguments--.
     * Если в --constructorArguments-- в качестве одного из элементов передать .class, то, по возможности, он будет
     * внедрен как зависимость.
     *
     * @param clazz Класс создаваемого компонента и для обращения к компоненту
     * @param type Тип компонента: singleton или при каждом обращении создавать новый экземпляр
     * @param constructorArguments Параметры передаваемые в конструктор класса --component--
     */
    <RESULT> void addComponent(Class<RESULT> clazz, InstanceType type, Object... constructorArguments);

    /**
     * Добавить компонент с названием --caption-- как экземпляр класса --component--,
     * спередачей в конструктор параметров --constructorArguments--.
     * Если в --constructorArguments-- в качестве одного из элементов передать .class, то, по возможности, он будет
     * внедрен как зависимость.
     * При использовании данного метода, компонент также можно получить используя класс --component--
     *
     * @param caption Название компонента для получения из IoC
     * @param component Класс создаваемого компонента
     * @param type Тип компонента: singleton или при каждом обращении создавать новый экземпляр
     * @param constructorArguments Параметры передаваемые в конструктор класса --component--
     */
    void addComponent(String caption, Class component, InstanceType type, Object... constructorArguments);

    /**
     * Добавить компонент с классом --clazz--, для обращения к нему, как экземпляр класса --component--,
     * с передачей в конструктор параметров --constructorArguments--.
     * Если в --constructorArguments-- в качестве одного из элементов передать .class, то, по возможности, он будет
     * внедрен как зависимость.
     *
     * @param clazz Класс компонента для получения из IoC
     * @param component Класс создаваемого компонента
     * @param type Тип компонента: singleton или при каждом обращении создавать новый экземпляр
     * @param constructorArguments Параметры передаваемые в конструктор класса --component--
     */
    <RESULT> void addComponent(Class<RESULT> clazz, Class component, InstanceType type, Object... constructorArguments);

    /**
     * Добавить компонент с названием --caption-- и с классом --clazz--, для обращения к нему, как экземпляр класса
     * --component--,
     * с передачей в конструктор параметров --constructorArguments--.
     * Если в --constructorArguments-- в качестве одного из элементов передать .class, то, по возможности, он будет
     * внедрен как зависимость.
     *
     * @param clazz Класс компонента для получения из IoC
     * @param component Класс создаваемого компонента
     * @param type Тип компонента: singleton или при каждом обращении создавать новый экземпляр
     * @param constructorArguments Параметры передаваемые в конструктор класса --component--
     */
    <RESULT> void addComponent(Class<RESULT> clazz, String caption, Class component, InstanceType type,
            Object... constructorArguments);

    /**
     * Добавить компонент с названием --caption-- как экземпляр класса --component-- типа singleton.
     * При использовании данного метода, компонент также можно получить используя класс --component--
     *
     * @param caption Название компонента для получения из IoC
     * @param component Singleton-компонент
     */
    void addSingleton(String caption, Object component);

    /**
     * Добавить компонент с классом --clazz--, для обращения к нему, как экземпляр класса --component-- типа singleton
     *
     * @param clazz Класс компонента для получения из IoC
     * @param component Singleton-компонент
     */
    <RESULT> void addSingleton(Class<RESULT> clazz, Object component);

    /**
     * Добавить компонент с названием --caption-- и с классом --clazz--, для обращения к нему, как экземпляр класса
     * --component-- типа singleton
     *
     * @param clazz Класс компонента для получения из IoC
     * @param component Singleton-компонент
     */
    <RESULT> void addSingleton(Class<RESULT> clazz, String caption, Object component);

    /**
     * Получить компонент по связанному классу
     *
     * @param clazz
     * @param <RESULT>
     * @return
     */
    <RESULT> RESULT getComponent(Class<RESULT> clazz);

    /**
     * Получить компонент по связанному наименованию
     *
     * @param caption
     * @param <RESULT>
     * @return
     */
    <RESULT> RESULT getComponent(String caption);

    /**
     * Удалить session-компонент
     *
     * @param clazz Класс, используемый в качестве ключа для доступа к компоненту
     * @param <RESULT>
     * @throws MatchInstanceTypeException
     */
    <RESULT> void closeSessionInstance(Class<RESULT> clazz) throws MatchInstanceTypeException;

    /**
     * Удалить session-компонент
     *
     * @param caption Название, используемое в качестве ключа для доступа к компоненту
     * @throws MatchInstanceTypeException
     */
    void closeSessionInstance(String caption) throws MatchInstanceTypeException;
}
