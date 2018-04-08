package ru.strict.controllers.xml;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.helpers.DefaultHandler;
import ru.strict.utils.StrictUtilLogger;
import ru.strict.controllers.StrictControllerBase;
import ru.strict.models.StrictModelXml;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * <pre>
 * Контроллер управления xml-файлом.
 * Возможно использование с конструктором модели по-умолчанию. </pre>
 * <p><b>Пример использования:</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *     var object = new File("path/to/file.xml");
 *     var model = new StrictModelXml();
 *     var controller = new StrictControllerXml(object, model);
 *     controller.build();
 * </pre></code>
 */
public class StrictControllerXml extends StrictControllerBase<File, StrictModelXml> {

    private SAXBuilder parser;
    private Document docXml;
    private XMLOutputter xmlOut;
    private Format formatXmlDoc;

    private Element rootElement;

    /**
     *
     * @param object xml-файл
     * @param model
     */
    public StrictControllerXml(File object, StrictModelXml model) {
        super(object, model);
    }

    /**
     * Создание пустого файла. (Не рекомендуется к использованию)
     * @return
     */
    @Override
    public File build() {
        StrictUtilLogger.info(StrictControllerXml.class, "StrictControllerXml.build - started");
        createConnection();
        StrictUtilLogger.info(StrictControllerXml.class, "StrictControllerXml.build - finished");
        return getObject();
    }

    /**
     * Соединение с xml файлом.
     * Если файл не создан, то он будет создан с заданным корневым элементом
     */
    private StrictControllerXml createConnection() {
        StrictUtilLogger.info(StrictControllerXml.class, "createConnection - started");
        parser = new SAXBuilder();

        // Если xml-файл не создан
        if (!getObject().exists()){
            try {
                // Создаем новый xml-файл
                getObject().createNewFile();
            } catch (IOException ex) {
                StrictUtilLogger.error(StrictControllerXml.class, ex.getClass().toString(), ex.getMessage());
            }
        }

        try {
            // Разбираем xml-файл
            docXml = parser.build(getObject());
            // Получаем корневой элемент
            setRootElement(docXml.getRootElement());
        } catch (IOException | JDOMException ex) {
            StrictUtilLogger.error(StrictControllerXml.class, ex.getClass().toString(), ex.getMessage());
            // Создание документа работы с xml-файлом
            docXml = new Document();
            // Создание корневого-элемента
            setRootElement(new Element(getModel().getInitRootElementName()));
            // Установка корневого-элемента
            docXml.setRootElement(getRootElement());
        }
        // Формат вывода текста в xml-файл
        formatXmlDoc = Format.getPrettyFormat();

        StrictUtilLogger.info(StrictControllerXml.class, "createConnection - finished");
        return this;
    }

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
    public Element createElement(String caption, String content, String[][] attributes, Element... elementsInner) {
        StrictUtilLogger.info(StrictControllerXml.class, "createElement - started");
        Element element = new Element(caption);
        element.addContent(content);
        for (Element e : elementsInner)
            element.addContent(e);

        if (attributes != null) {
            for (String[] arr : attributes)
                element.setAttribute(arr[0], arr[1]);
        }

        StrictUtilLogger.info(StrictControllerXml.class, "createElement - finished");
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
        StrictUtilLogger.info(StrictControllerXml.class, "createElements - started");
        //TODO: Сдлеать проверку, чтобы размер caption = contents
        Element [] elements = new Element[captions.length];
        int i=0;
        for(String capion: captions) {
            Element element = new Element(capion);
            element.addContent(contents[i]);
            elements[i] = element;
            i++;
        }
        StrictUtilLogger.info(StrictControllerXml.class, "createElements - finished");
        return elements;
    }

    /**
     * Сохранение элементов в xml файл корневого каталога
     * @param elements Сохраняемые элементы в корневой каталог
     */
    public void saveElementsToXml(Element...elements) {
        StrictUtilLogger.info(StrictControllerXml.class, "saveElementsToXml - started");
        if(elements!=null) {
            for(Element element:elements)
                getRootElement().addContent(element);
        }

        try {
            xmlOut = new XMLOutputter(formatXmlDoc);
            xmlOut.output(docXml, new FileOutputStream(getObject()));
        } catch (IOException ex) {
            StrictUtilLogger.error(StrictControllerXml.class, ex.getClass().toString(), ex.getMessage());
        }
        StrictUtilLogger.info(StrictControllerXml.class, "saveElementsToXml - finished");
    }

    /**
     * Удаление элементов из корневого элемента
     * @param elements
     */
    private void removeElements(Element...elements){
        for(Element element:elements)
            getRootElement().removeChild(element.getName());
    }

    /**
     * Пересохранение элементов в xml файл корневого каталога
     * @param elements Сохраняемые элементы в корневой каталог
     */
    public void resaveElementsToXml(Element...elements) {
        StrictUtilLogger.info(StrictControllerXml.class, "resaveElementsToXml - started");
        removeElements(elements);
        saveElementsToXml(elements);
        StrictUtilLogger.info(StrictControllerXml.class, "resaveElementsToXml - finished");
    }

    /**
     * Считать элементы, расположенные на указанном пути
     * @param parents Установленный путь считывания элемента
     * @return
     */
    public List<Element> readValue(StrictXmlNode parents){
        StrictUtilLogger.info(StrictControllerXml.class, "readValue - started");
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            DefaultHandler defaultHandler = new StrictHandlerXmlRead(parents);
            saxParser.parse(getObject(), defaultHandler);
            return parents.getElementsInner();
        } catch (Exception ex) {
            StrictUtilLogger.error(StrictControllerXml.class, ex.getClass().toString(), ex.getMessage());
        }

        StrictUtilLogger.info(StrictControllerXml.class, "readValue - finished");
        return null;
    }

    /**
     * Получить корневой элемент
     */
    public Element getRootElement() {
        return rootElement;
    }

    /**
     * Установить корневой элемент
     */
    public void setRootElement(Element rootElement) {
        this.rootElement = rootElement;
    }

    @Override
    public File getObject() {
        return super.getObject();
    }

    @Override
    public void setObject(File object) {
        super.setObject(object);
    }

    @Override
    public StrictModelXml getModel() {
        return super.getModel();
    }

    @Override
    public void setModel(StrictModelXml model) {
        super.setModel(model);
    }
}
