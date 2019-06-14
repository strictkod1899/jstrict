package ru.strict.file.xml;

import org.jdom2.Element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс используется для задания пути парсинга xml-файла относительно корневого элемента
 */
public class XmlNode {
    /**
     * Список элементов, после последовательного прохождения которых будут считываться значения
     */
    private List<Element> nodes;

    /**
     * Текущий индекс
     */
    private int currentIndex;

    /**
     * Если элемент из списка удовлетворяет текущему пути, тогда в списке соответствующее значение равно true
     */
    private List<Boolean> elementsMark;

    /**
     * Список вложенных элементов
     */
    private List<Element> elementsInner;

    /**
     * Учитывать атрибуты при проверке пути
     */
    private boolean isCheckAttributes;

    /**
     * Строгая проверка атрибутов
     */
    private boolean isStrongCheckAttributes;

    /**
     * Добавляемые элементы пути
     * @param elements
     */
    public XmlNode(Element... elements) {
        nodes = new ArrayList<>();
        for (Element element : elements) {
            nodes.add(element);
        }
        currentIndex = -1;
        elementsMark = new ArrayList<>(10);
        Collections.addAll(elementsMark, false);
        elementsInner = new ArrayList<>();
        isCheckAttributes = false;
        isStrongCheckAttributes = false;
    }

    /**
     * Добавление элемента к построению пути
     *
     * @param element
     */
    public void add(Element element) {
        nodes.add(element);
        elementsMark.add(false);
    }

    /**
     * Удаление элемента из построенного пути
     *
     * @param index
     */
    public void remove(int index) {
        nodes.remove(index);
        elementsMark.remove(index);
    }

    /**
     * Получить элемент из пути
     *
     * @param index
     * @return
     */
    public Element get(int index) {
        return nodes.get(index);
    }

    /**
     * Получить следующий элемент
     *
     * @return
     */
    public Element next() {
        this.currentIndex++;
        return nodes.get(this.currentIndex);
    }

    /**
     * Получить предыдущий элемент
     *
     * @return
     */
    public Element back() {
        return nodes.get(this.currentIndex--);
    }

    /**
     * Получить элемент по текущему индексу
     *
     * @return
     */
    public Element getCurrent() {
        return nodes.get(this.currentIndex);
    }

    /**
     * Получить список всех элементов пути
     *
     * @return
     */
    public List<Element> getNodes() {
        return nodes;
    }

    /**
     * Количество элементов в пути
     *
     * @return
     */
    public int size() {
        return nodes.size();
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    /**
     * Сбросить текущую позицию
     */
    public void resetCurrentIndex() {
        this.currentIndex = 0;
    }

    /**
     * Доступен ли к использованию текущий элемент
     *
     * @return
     */
    public boolean hasNext() {
        int currentIndexTemp = currentIndex + 1;
        return currentIndexTemp < nodes.size();
    }

    /**
     * Пометить текущий элемент как удовлетворяющий текущему пути
     */
    public void markCurrent() {
        elementsMark.set(currentIndex, true);
    }

    /**
     * Пометить элемент как удовлетворяющий текущему пути
     */
    public void markElement(int index) {
        elementsMark.set(index, true);
    }

    /**
     * Пометить текущий элемент как удовлетворяющий текущему пути
     */
    public void unmarkCurrent() {
        elementsMark.set(currentIndex, false);
    }

    /**
     * Пометить элемент как удовлетворяющий текущему пути
     */
    public void unmarkElement(int index) {
        elementsMark.set(index, false);
    }

    /**
     * Получить маркер текущего элемента
     *
     * @return
     */
    public Boolean getCurrentMark() {
        return elementsMark.get(currentIndex);
    }

    /**
     * Получить маркер элемента по индексу
     *
     * @return
     */
    public Boolean getMark(int index) {
        return elementsMark.get(index);
    }

    /**
     * Получить код состояния
     *
     * @return
     * 0 - текущий элемент не инициализирован. <br/>
     * 1 - доступны неиспользованные элементы. <br/>
     * -1 - элементы для дальнейшего использования отсутствуют. <br/>
     * -2 - текущий элемент является последним и он помечен маркером как пройденый.
     */
    public int getCodeState() {
        if (currentIndex == -1)
            return 0;
        else if (hasNext())
            return 1;
        else if (!hasNext() && getCurrentMark())
            return -2;
        else
            return -1;
    }

    /**
     * Получить список вложенных элементов
     * @return
     */
    public List<Element> getElementsInner() {
        return elementsInner;
    }

    /**
     * Добавить вложенный элемент
     * @param element
     */
    public void addInner(Element element){
        elementsInner.add(element);
    }

    /**
     * Получить количество элементов помеченных true
     * @return
     */
    public int getCountMarkTrue(){
        return (int) elementsMark.stream()
                .filter(e->e)
                .count();
    }

    public boolean isCheckAttributes() {
        return isCheckAttributes;
    }

    public void setCheckAttributes(boolean checkAttributes) {
        this.isCheckAttributes = checkAttributes;
    }

    public boolean isStrongCheckAttributes() {
        return isStrongCheckAttributes;
    }

    public void setStrongCheckAttributes(boolean strongCheckAttributes) {
        this.isStrongCheckAttributes = strongCheckAttributes;
    }
}
