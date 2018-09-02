package ru.strict.file;

import ru.strict.utils.UtilResources;

public abstract class PropertiesResourceFileReader extends PropertiesFileReader {

    private String pathToDirectory;

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
    protected String getPathToDirectory() {
        return pathToDirectory;
    }

    private void initializePathToDirectory(){
        pathToDirectory = UtilResources.getResourceAsFile(getFileName(), getThisClass()).getAbsolutePath()
                .replace("\\" + getFileName(), "");
    }
}
