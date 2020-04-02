package ru.strict.file.xml;

import org.jdom2.Element;
import ru.strict.file.IFileReader;
import ru.strict.file.IFileWriter;

import java.util.List;

public interface IXmlFile extends IFileReader<Element>, IFileWriter<Element> {
    List<Element> read(XmlNode parents);
    void addElements(Element...elements);
    void removeElements(Element...elements);
}
