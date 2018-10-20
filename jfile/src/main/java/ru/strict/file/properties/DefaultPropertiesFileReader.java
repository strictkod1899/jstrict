package ru.strict.file.properties;

import java.io.File;

public class DefaultPropertiesFileReader extends PropertiesFileReader {

    public DefaultPropertiesFileReader(String propertiesFileName) {
        super(propertiesFileName);
    }

    public DefaultPropertiesFileReader(String propertiesFileName, String suffix) {
        super(propertiesFileName, suffix);
    }

    @Override
    protected String initializePathToDirectory(){
        File appFile = new File(getFileName());
        return appFile.getAbsolutePath().substring(0, appFile.getAbsolutePath().lastIndexOf(File.separator));
    }
}
