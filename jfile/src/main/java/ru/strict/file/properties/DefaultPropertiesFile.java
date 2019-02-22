package ru.strict.file.properties;

import java.io.File;

public class DefaultPropertiesFile extends PropertiesFile {

    public DefaultPropertiesFile(String propertiesFilePath) {
        super(propertiesFilePath);
    }

    public DefaultPropertiesFile(String propertiesFilePath, String suffix) {
        super(propertiesFilePath, suffix);
    }

    @Override
    protected String initializePathToDirectory(){
        File appFile = new File(getFileName());
        return appFile.getAbsolutePath().substring(0, appFile.getAbsolutePath().lastIndexOf(File.separator));
    }
}
