package ru.strict.file.models;

public class ModelXml extends ModelBase {

    /**
     * Создать новый родительский элемент с указанным наименованием (если файл не инициализирован)
     */
    private String initRootElementName;

    private void initDefault(){
        initRootElementName = "root_element";
    }

    public ModelXml(){
        super();
        initDefault();
    }

    public String getInitRootElementName() {
        return initRootElementName;
    }

    /**
     * Если xml файл не создан, тогда будет установлен корневой элемент с данным наименованием
     * @param initRootElementName
     */
    public void setInitRootElementName(String initRootElementName) {
        this.initRootElementName = initRootElementName;
    }
}
