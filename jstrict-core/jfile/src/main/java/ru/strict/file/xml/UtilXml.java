package ru.strict.file.xml;

import org.jdom2.Element;

public class UtilXml {

    /**
     * Создать новый элемент
     *
     * @param caption       Наименование элемента
     * @param content       Текстовое содержание элемента
     * @param attributes    Атрибуты создаваемого элемента:
     *                      <p>column[0] = наименование атрибута;</p>
     *                      <p>column[1] = значение атрибута.</p>
     * @param elementsInner Вложеннные (дочерние) элементы
     * @return
     */
    public static Element createElement(String caption, String content, String[][] attributes, Element... elementsInner) {
        Element element = new Element(caption);
        element.addContent(content);
        for (Element e : elementsInner)
            element.addContent(e);

        if (attributes != null) {
            for (String[] arr : attributes)
                element.setAttribute(arr[0], arr[1]);
        }

        return element;
    }

    /**
     * Создать новые элементы xml файла
     *
     * @param captions       Наименования элементов
     * @param contents       Текстовое содержание элементов
     * @return
     */
    public Element[] createElements(String[] captions, String[] contents) {
        Element [] elements = new Element[captions.length];
        int i=0;
        for(String caption: captions) {
            Element element = new Element(caption);
            element.addContent(contents[i]);
            elements[i] = element;
            i++;
        }
        return elements;
    }
}
