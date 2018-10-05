package ru.strict.file.properties;

import ru.strict.utils.UtilProperties;
import ru.strict.validates.ValidateBaseValue;

import java.io.Closeable;
import java.io.File;
import java.util.Objects;

public abstract class PropertiesFileReader implements Closeable{

    private String pathToDirectory;
    private String propertiesFileName;
    private String suffix;

    private void initialize(String propertiesFileName, String suffix){
        this.propertiesFileName = propertiesFileName;
        this.suffix = suffix;

        pathToDirectory = initializePathToDirectory();
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

    protected String getPropertiesFileName() {
        return propertiesFileName;
    }

    protected String getSuffix() {
        return suffix;
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

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof PropertiesFileReader){
            PropertiesFileReader object = (PropertiesFileReader) obj;
            return Objects.equals(pathToDirectory, object.getPathToDirectory())
                    && Objects.equals(propertiesFileName, object.getPropertiesFileName())
                    && Objects.equals(suffix, object.getSuffix());
        }else{
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(pathToDirectory, propertiesFileName, suffix);
    }
}
