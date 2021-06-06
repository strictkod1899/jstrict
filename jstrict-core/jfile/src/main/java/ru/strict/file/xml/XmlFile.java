package ru.strict.file.xml;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import ru.strict.validate.Validator;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class XmlFile implements IXmlFile {
    private File file;
    private SAXBuilder saxParser;
    private Document jdomDocument;
    private XMLOutputter xmlOut;
    private Format formatXmlDoc;

    private Element rootElement;

    private void init(File xmlFile, String rootElement) {
        Validator.isNull(xmlFile, "xmlFile");

        this.file = xmlFile;

        saxParser = new SAXBuilder();
        // Формат вывода в xml-файл
        formatXmlDoc = Format.getPrettyFormat();
        xmlOut = new XMLOutputter(formatXmlDoc);

        createConnection(rootElement);
    }

    public XmlFile(String xmlFilePath) {
        this(new File(xmlFilePath));
    }

    public XmlFile(File file) {
        init(file, null);
    }

    public XmlFile(File file, String expectedRootElement) {
        init(file, expectedRootElement);
    }

    public XmlFile(String xmlFilePath, String expectedRootElement) {
        this(new File(xmlFilePath), expectedRootElement);
    }

    /**
     * Соединение с xml файлом.
     * Если файл не создан, то он будет создан с заданным корневым элементом
     */
    private void createConnection(String rootElement) {
        try {
            // Если xml-файл не создан
            if (!file.exists()) {
                Validator.isEmptyOrNull(rootElement, "rootElement");

                file.createNewFile();

                // Создание документа для работы с xml-файлом
                jdomDocument = new Document();
                // Создание корневого элемента
                this.rootElement = new Element(rootElement);
                // Установка корневого-элемента
                jdomDocument.setRootElement(this.rootElement);
            } else {
                read();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void write() {
        try (OutputStream outputStream = new FileOutputStream(file)) {
            xmlOut.output(jdomDocument, outputStream);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void write(Element element) {
        rootElement = element;
        // Установка корневого-элемента
        jdomDocument.setRootElement(element);
        write();
    }

    @Override
    public Element read() {
        try {
            // Разбираем xml-файл
            jdomDocument = saxParser.build(file);
            // Получаем корневой элемент
            rootElement = jdomDocument.getRootElement();

            return rootElement;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Считать элементы, расположенные на указанном пути
     *
     * @param parents Установленный путь считывания элемента
     * @return
     */
    @Override
    public List<Element> read(XmlNode parents) {
        try {
            if (parents == null) {
                parents = new XmlNode(rootElement);
            }
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XmlReadHandler defaultHandler = null;
            defaultHandler = new XmlReadHandler(parents);
            saxParser.parse(file, defaultHandler);
            return parents.getElementsInner();
        } catch (Exception ex) {
            throw new XmlReadException(String.format("Error xml read [%s]", file.getAbsolutePath()), ex);
        }
    }

    /**
     * Сохранение элементов в xml файл корневого каталога
     *
     * @param elements Сохраняемые элементы в корневой каталог
     */
    @Override
    public void addElements(Element... elements) {
        if (rootElement == null) {
            throw new NullPointerException("rootElement is NULL");
        }

        if (elements != null) {
            for (Element element : elements) {
                rootElement.addContent(element);
            }
        }
    }

    /**
     * Удаление элементов из корневого элемента
     *
     * @param elements
     */
    @Override
    public void removeElements(Element... elements) {
        if (rootElement == null) {
            throw new NullPointerException("rootElement is NULL");
        }

        if (elements != null) {
            for (Element element : elements) {
                rootElement.removeChild(element.getName());
            }
        }
    }

    public File getFile() {
        return file;
    }
}
