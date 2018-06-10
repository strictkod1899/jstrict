package ru.strict.file.controllers.xml;

import org.jdom2.Attribute;
import org.jdom2.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <pre>
 * Обработчик парсинга xml-файла для получения подходящий элементов.
 * Используется для SAX-парсинга.
 * </pre>
 */
public class HandlerXmlRead extends DefaultHandler {

    /**
     * Установленный путь считывания элемента
     */
    private XmlNode path;
    /**
     * Маркер обработки элемента. Если элемент завершился, ставиться false.
     * (Требуется для пропуска считывания пустых символов после завершения элемента)
     */
    private boolean processElement;
    /**
     * Текущий элемент установленного пути
     */
    private Element currentPathElement;
    /**
     * Текущий элемент парсинга
     */
    private Element currentElement;
    /**
     * Содержимое текущего элемента
     */
    private String currentContent;
    /**
     * Список хранит последовательность элементов, значения которых считываются (Нужен для определения вложенных элементов)
     */
    private List<Element> listElements;

    private HandlerXmlRead() {}

    public HandlerXmlRead(XmlNode path) {
        this.path = path;
        currentContent = "";
        listElements = new ArrayList<>();
        processElement = false;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        processElement = true;

        // Если все элементы указанного пути еще не пройдены
        if(path.getCodeState()!=-2) {
            // Если есть неиспользованные элементы
            if (path.getCodeState() != -1)
                currentPathElement = getPathElement();

            checkElement(qName, uri, attributes);
        }else if(path.getCodeState()==-2)
            createCurrentElement(qName, uri, attributes);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(path.getCodeState()==-2 && processElement) {
            currentContent = "";
            currentContent += new String(ch, start, length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // Если список пути был инициализирован и наименование текущего элемента равно текущему элементу пути
        if (path.getCodeState()!=0 && qName.equals(path.getCurrent().getName()))
            endLastPathElement(qName);

        // Если пройдены все элементы пути
        if(path.getCodeState()==-2)
            endCurrentElement();

        // Если осталься финальный (главный) элемент в списке и завершается именно он
        if(listElements.size()==1 && qName.equals(listElements.get(0).getName()))
            endFinalElement();

        // Заврешение обработки текущего элемента
        processElement = false;
    }

    /**
     * Получить следующий элемент пути, только при условии, что текущий маркер уже проставлен
     * @return
     */
    private Element getPathElement() {
        if (path.getCodeState() == 0 || path.getCurrentMark())
            return path.next();
        else
            return path.getCurrent();
    }

    /**
     * Проверка текущего элемента пути
     * @param qName
     */
    private void checkElement(String qName, String uri, Attributes attributes){
        // Если текущий элемент равен соответствующему элементу указанного пути
        if (qName.equals(currentPathElement.getName())) {
            if(path.isCheckAttributes())
                checkAttributes(attributes);
            else
                // Говорим, что мы находимся в подходящем элементе
                path.markCurrent();
        }

        if(path.getCountMarkTrue()==path.size())
            initFinalElement(qName, uri, attributes);
    }

    /**
     * Проверка атрибутов текущего элемента
     * @param attributes
     */
    private void checkAttributes(Attributes attributes){
        boolean result;
        if(!path.isStrongCheckAttributes())
            result = checkAttributeNoStrong(attributes);
        else
            result = checkAttributeStrong(attributes);

        if(result)
            path.markCurrent();
    }

    /**
     * Проверка атрибутов не учитывая точного совпадения,
     * главное чтобы в текущем элементе парсинга присутствовали атрибуты текущего элемента пути
     * @param attributes
     */
    private boolean checkAttributeNoStrong(Attributes attributes){
        Iterator<Attribute> iterAttributesCurrentPathElement = currentPathElement.getAttributes().iterator();
        while(iterAttributesCurrentPathElement.hasNext()){
            Attribute attribute = iterAttributesCurrentPathElement.next();
            if(attributes.getValue(attribute.getName())!=null){
                if(!attributes.getValue(attribute.getName()).equals(attribute.getValue()))
                    return false;
            }else
                return false;
        }

        return true;
    }

    /**
     * Проверка атрибутов учитывая точное совпадение,
     * главное чтобы в текущем элементе парсинга присутствовали атрибуты текущего элемента пути
     * @param attributes
     */
    private boolean checkAttributeStrong(Attributes attributes){
        // Проверка на совпадение по количеству атрибутов
        if(currentPathElement.getAttributes().size()!=attributes.getLength())
            return false;

        return checkAttributeNoStrong(attributes);
    }

    /**
     * Создать текущий элемент
     * @param qName
     * @param attributes
     */
    private void createCurrentElement(String qName, String uri, Attributes attributes){
        // Если в текущем списке элементов уже хранятся элементы, тогда добавляем к последнему последние считанные значения
        if(listElements.size()>0) {
            if(currentContent!=null)
                listElements.get(listElements.size()-1).addContent(currentContent);
            currentContent = "";
        }

        currentElement = new Element(qName, uri);
        for(int i=0; i<attributes.getLength(); i++)
            currentElement.setAttribute(attributes.getQName(i), attributes.getValue(i));
        listElements.add(currentElement);
    }

    /**
     * Инициализировать главный элемент
     */
    private void initFinalElement(String qName, String uri, Attributes attributes){
        Element elementMain = new Element(qName, uri);

        for(int i=0; i<attributes.getLength(); i++)
            elementMain.setAttribute(attributes.getQName(i), attributes.getValue(i));

        listElements.add(elementMain);
    }

    /**
     * Завершение последнего из отмеченных элементов пути
     * @param qName
     */
    private void endLastPathElement(String qName){
        path.unmarkCurrent();
        path.back();
    }

    /**
     * Завершение текущего считываемого элемнта парсинга
     */
    private void endCurrentElement(){
        if(currentContent!=null) {
            listElements.get(listElements.size() - 1).addContent(currentContent);
            currentContent = null;
        }

        // Если текущий элемент является вложенным в предыдущий
        if(listElements.size()>1)
            // Добавляем текущий элемент к предыдущему
            listElements.get(listElements.size()-2).addContent(listElements.get(listElements.size()-1));
        listElements.remove(listElements.size()-1);
    }

    /**
     * Завершение финального элемента
     */
    private void endFinalElement(){
        // Сохраняем этот элемент в список результата
        if(listElements.get(0).getText().equals("") && currentContent!=null && !listElements.get(0).getText().equals(currentContent))
            listElements.get(0).addContent(currentContent);
        path.addInner(listElements.get(0));
        listElements.remove(listElements.size()-1);
    }
}
