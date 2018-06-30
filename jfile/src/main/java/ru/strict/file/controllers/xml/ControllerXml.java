package ru.strict.file.controllers.xml;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.helpers.DefaultHandler;
import ru.strict.file.controllers.ControllerBase;
import ru.strict.file.models.ModelXml;
import ru.strict.utils.UtilLogger;

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
 *     var model = new ModelXml();
 *     var controller = new ControllerXml(object, model);
 *     controller.build();
 * </pre></code>
 */
public class ControllerXml extends ControllerBase<File, ModelXml> {

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
    public ControllerXml(File object, ModelXml model) {
        super(object, model);
    }

    /**
     * Создание пустого файла. (Не рекомендуется к использованию)
     * @return
     */
    @Override
    public File build() {
        UtilLogger.info(ControllerXml.class, "ControllerXml.build - started");
        createConnection();
        UtilLogger.info(ControllerXml.class, "ControllerXml.build - finished");
        return getObject();
    }

    /**
     * Соединение с xml файлом.
     * Если файл не создан, то он будет создан с заданным корневым элементом
     */
    private ControllerXml createConnection() {
        UtilLogger.info(ControllerXml.class, "createConnection - started");
        parser = new SAXBuilder();

        // Если xml-файл не создан
        if (!getObject().exists()){
            try {
                // Создаем новый xml-файл
                getObject().createNewFile();
            } catch (IOException ex) {
                UtilLogger.error(ControllerXml.class, ex.getClass().toString(), ex.getMessage());
            }
        }

        try {
            // Разбираем xml-файл
            docXml = parser.build(getObject());
            // Получаем корневой элемент
            setRootElement(docXml.getRootElement());
        } catch (IOException | JDOMException ex) {
            UtilLogger.error(ControllerXml.class, ex.getClass().toString(), ex.getMessage());
            // Создание документа работы с xml-файлом
            docXml = new Document();
            // Создание корневого-элемента
            setRootElement(new Element(getModel().getInitRootElementName()));
            // Установка корневого-элемента
            docXml.setRootElement(getRootElement());
        }
        // Формат вывода текста в xml-файл
        formatXmlDoc = Format.getPrettyFormat();

        UtilLogger.info(ControllerXml.class, "createConnection - finished");
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
        UtilLogger.info(ControllerXml.class, "createElement - started");
        Element element = new Element(caption);
        element.addContent(content);
        for (Element e : elementsInner)
            element.addContent(e);

        if (attributes != null) {
            for (String[] arr : attributes)
                element.setAttribute(arr[0], arr[1]);
        }

        UtilLogger.info(ControllerXml.class, "createElement - finished");
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
        UtilLogger.info(ControllerXml.class, "createElements - started");
        //TODO: Сдлеать проверку, чтобы размер caption = contents
        Element [] elements = new Element[captions.length];
        int i=0;
        for(String capion: captions) {
            Element element = new Element(capion);
            element.addContent(contents[i]);
            elements[i] = element;
            i++;
        }
        UtilLogger.info(ControllerXml.class, "createElements - finished");
        return elements;
    }

    /**
     * Сохранение элементов в xml файл корневого каталога
     * @param elements Сохраняемые элементы в корневой каталог
     */
    public void saveElementsToXml(Element...elements) {
        UtilLogger.info(ControllerXml.class, "saveElementsToXml - started");
        if(elements!=null) {
            for(Element element:elements)
                getRootElement().addContent(element);
        }

        try {
            xmlOut = new XMLOutputter(formatXmlDoc);
            xmlOut.output(docXml, new FileOutputStream(getObject()));
        } catch (IOException ex) {
            UtilLogger.error(ControllerXml.class, ex.getClass().toString(), ex.getMessage());
        }
        UtilLogger.info(ControllerXml.class, "saveElementsToXml - finished");
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
        UtilLogger.info(ControllerXml.class, "resaveElementsToXml - started");
        removeElements(elements);
        saveElementsToXml(elements);
        UtilLogger.info(ControllerXml.class, "resaveElementsToXml - finished");
    }

    /**
     * Считать элементы, расположенные на указанном пути
     * @param parents Установленный путь считывания элемента
     * @return
     */
    public List<Element> readValue(XmlNode parents){
        UtilLogger.info(ControllerXml.class, "readValue - started");
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            DefaultHandler defaultHandler = new HandlerXmlRead(parents);
            saxParser.parse(getObject(), defaultHandler);
            return parents.getElementsInner();
        } catch (Exception ex) {
            UtilLogger.error(ControllerXml.class, ex.getClass().toString(), ex.getMessage());
        }

        UtilLogger.info(ControllerXml.class, "readValue - finished");
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
    public ModelXml getModel() {
        return super.getModel();
    }

    @Override
    public void setModel(ModelXml model) {
        super.setModel(model);
    }
}
