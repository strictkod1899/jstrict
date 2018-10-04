package ru.strict.file.properties;

import ru.strict.utils.UtilResources;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        if(!(new File(getSuffixFileName()).exists())) {
            UtilResources.getResourceAsFile(getSuffixFileName(), getThisClass());
        }
        File appFile = new File(getFileName());
        if(!appFile.exists()) {
            appFile = UtilResources.getResourceAsFile(getFileName(), getThisClass());
        }

        String pathToDirectory = appFile.getAbsolutePath().substring(0, appFile.getAbsolutePath().lastIndexOf(File.separator));
        return pathToDirectory;
    }
}
