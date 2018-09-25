package ru.strict.file;

import ru.strict.utils.UtilResources;

import java.io.File;

public abstract class PropertiesResourceFileReader extends PropertiesFileReader {

    public PropertiesResourceFileReader(String propertiesFileName) {
        super(propertiesFileName);
        initializePathToDirectory();
    }

    public PropertiesResourceFileReader(String propertiesFileName, String suffix) {
        super(propertiesFileName, suffix);
        initializePathToDirectory();
    }

    protected abstract Class getThisClass();

    @Override
    protected String initializePathToDirectory(){
        UtilResources.getResourceAsFile(getSuffixFileName(), getThisClass());
        File appFile = UtilResources.getResourceAsFile(getFileName(), getThisClass());

        String pathToDirectory = appFile.getAbsolutePath().substring(0, appFile.getAbsolutePath().lastIndexOf(File.separator));
        return pathToDirectory;
    }
}
