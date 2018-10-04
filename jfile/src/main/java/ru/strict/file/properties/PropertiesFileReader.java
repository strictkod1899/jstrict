package ru.strict.file.properties;

import ru.strict.components.Log4jWrapper;
import ru.strict.utils.UtilProperties;
import ru.strict.validates.ValidateBaseValue;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

public abstract class PropertiesFileReader implements Closeable{

    protected static final Log4jWrapper LOGGER = new Log4jWrapper(PropertiesFileReader.class);

    private String pathToDirectory;
    private String propertiesFileName;
    private String suffix;

    private void initialize(String propertiesFileName, String suffix){
        this.propertiesFileName = propertiesFileName;
        this.suffix = suffix;

        pathToDirectory = initializePathToDirectory();

        LOGGER.info("Connected to properties-files:\n\t'%s'\n\t'%s'",
                getPathToFile(), getPathToSuffixFile());
    }

    public PropertiesFileReader(String propertiesFileName) {
        initialize(propertiesFileName, null);
    }

    public PropertiesFileReader(String propertiesFileName, String suffix) {
        initialize(propertiesFileName, suffix);
    }

    protected String getFileName(){
        return String.format("%s.properties", propertiesFileName);
    }

    protected String getSuffixFileName(){
        String result = null;

        if(!ValidateBaseValue.isEmptyOrNull(suffix)){
            result = String.format("%s_%s.properties", propertiesFileName, suffix);
        }else{
            result = getFileName();
        }
        return result;
    }

    public String readValue(String key){
        String result = UtilProperties.getValue(getPathToSuffixFile(), key);
        if(ValidateBaseValue.isEmptyOrNull(result)){
            result = UtilProperties.getValue(getPathToFile(), key);
        }
        return result;
    }

    public String getPathToDirectory() {
        return pathToDirectory;
    }

    protected abstract String initializePathToDirectory();

    public String getPathToFile(){
        return String.format("%s%s%s", getPathToDirectory(), File.separator, getFileName());
    }

    public String getPathToSuffixFile(){
        return String.format("%s%s%s", getPathToDirectory(), File.separator, getSuffixFileName());
    }

    @Override
    public void close() {
        pathToDirectory = null;
        propertiesFileName = null;
        suffix = null;
    }
}
