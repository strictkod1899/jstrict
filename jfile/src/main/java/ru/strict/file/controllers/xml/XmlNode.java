package ru.strict.file.controllers.xml;

import org.jdom2.Element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * Класс используется для задания пути парсинга xml-файла.
 * Чтобы начать пользоваться объектом этого класса, необходимо вызвать метод next(); </pre>
 * <p><b>Пример использования:</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *     ControllerXml controllerXml = new ControllerXml(new File("file.xml"), new ModelXml());
 *     controllerXml.build();
 *     StrictXmlNodes xmlNodes = new StrictXmlNodes();
 *     xmlNodes.add(new Element(controllerXml.getModel().getRootElement().getName()))
 *     java.util.List<Element> values = controllerXml.readValue(xmlNodes);
 *     if(!values.isEmpty())
 *        setVersion(Integer.valueOf(values.get(0).getChild("version").getValue()));
 * </pre></code>
 */
public class XmlNode<E extends Element> {
    /**
     * Список элементов, после последовательного прохождения которых будут считываться значения
     */
    private List<E> nodes;

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
     *
     * @param elements
     */
    public XmlNode(E... elements) {
        nodes = new ArrayList<>();
        for (E element : elements)
            nodes.add(element);
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
    public void add(E element) {
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
    public E get(int index) {
        return nodes.get(index);
    }

    /**
     * Получить следующий элемент
     *
     * @return
     */
    public E next() {
        this.currentIndex++;
        return nodes.get(this.currentIndex);
    }

    /**
     * Получить предыдущий элемент
     *
     * @return
     */
    public E back() {
        return nodes.get(this.currentIndex--);
    }

    /**
     * Получить элемент по текущему индексу
     *
     * @return
     */
    public E getCurrent() {
        return nodes.get(this.currentIndex);
    }

    /**
     * Получить список всех элементов пути
     *
     * @return
     */
    public List<E> getNodes() {
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
