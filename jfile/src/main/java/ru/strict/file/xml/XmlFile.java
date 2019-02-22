package ru.strict.file.xml;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class XmlFile implements AutoCloseable{

    private File xmlFile;
    private SAXBuilder parser;
    private Document docXml;
    private XMLOutputter xmlOut;
    private Format formatXmlDoc;

    private Element rootElement;

    public XmlFile(File xmlFile, String rootElementName) throws IOException {
        this.xmlFile = xmlFile;
        createConnection(rootElementName);
    }

    public XmlFile(String xmlFilePath, String rootElementName) throws IOException {
        this(new File(xmlFilePath), rootElementName);
    }

    /**
     * Соединение с xml файлом.
     * Если файл не создан, то он будет создан с заданным корневым элементом
     */
    private void createConnection(String rootElementName) throws IOException {
        parser = new SAXBuilder();

        // Если xml-файл не создан
        if (!xmlFile.exists()){
            xmlFile.createNewFile();
        }

        try {
            // Разбираем xml-файл
            docXml = parser.build(xmlFile);
            // Получаем корневой элемент
            rootElement = docXml.getRootElement();
        } catch (IOException | JDOMException ex) {
            // Создание документа для работы с xml-файлом
            docXml = new Document();
            // Создание корневого элемента
            rootElement = new Element(rootElementName);
            // Установка корневого-элемента
            docXml.setRootElement(rootElement);
        }
        // Формат вывода текста в xml-файл
        formatXmlDoc = Format.getPrettyFormat();
    }

    public void saveToXml() throws IOException {
        xmlOut = new XMLOutputter(formatXmlDoc);
        xmlOut.output(docXml, new FileOutputStream(xmlFile));
    }

    /**
     * Сохранение элементов в xml файл корневого каталога
     * @param elements Сохраняемые элементы в корневой каталог
     */
    public void addElements(Element...elements) {
        if(elements!=null) {
            for(Element element : elements) {
                rootElement.addContent(element);
            }
        }
    }

    /**
     * Удаление элементов из корневого элемента
     * @param elements
     */
    private void removeElements(Element...elements){
        if(elements != null) {
            for (Element element : elements) {
                rootElement.removeChild(element.getName());
            }
        }
    }

    /**
     * Считать элементы, расположенные на указанном пути
     * @param parents Установленный путь считывания элемента
     * @return
     */
    public List<Element> readValue(XmlNode parents) throws XmlReadException {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            HandlerXmlRead defaultHandler = null;
            try {
                defaultHandler = new HandlerXmlRead(parents);
                saxParser.parse(xmlFile, defaultHandler);
                return parents.getElementsInner();
            }finally {
                if(defaultHandler != null){
                    defaultHandler.close();
                }
            }
        } catch (Exception ex) {
            throw new XmlReadException(
                    String.format("Ошибка при обработке xml-файла [%s]", xmlFile.getAbsolutePath()),
                    ex);
        }
    }

    @Override
    public void close(){
        xmlFile = null;
        parser = null;
        docXml = null;
        xmlOut = null;
        formatXmlDoc = null;
        rootElement = null;
    }
}
