package ru.strict.file.properties;

import java.io.File;

public class DefaultPropertiesFileReader extends PropertiesFileReader {

    public DefaultPropertiesFileReader(String propertiesFilePath) {
        super(propertiesFilePath);
    }

    public DefaultPropertiesFileReader(String propertiesFilePath, String suffix) {
        super(propertiesFilePath, suffix);
    }

    @Override
    protected String initializePathToDirectory(){
        File appFile = new File(getFileName());
        return appFile.getAbsolutePath().substring(0, appFile.getAbsolutePath().lastIndexOf(File.separator));
    }
}
