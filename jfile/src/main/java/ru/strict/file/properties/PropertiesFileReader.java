package ru.strict.file.properties;

import ru.strict.utils.UtilProperties;
import ru.strict.validates.ValidateBaseValue;
import java.io.File;
import java.util.Objects;

public abstract class PropertiesFileReader implements AutoCloseable{

    private String pathToDirectory;
    private String propertiesFileName;
    private String suffix;

    private void initialize(String propertiesFileName, String suffix){
        if(ValidateBaseValue.isEmptyOrNull(propertiesFileName)){
            throw new NullPointerException("properties file name is NULL");
        }

        if (propertiesFileName.endsWith(".properties")){
            propertiesFileName = propertiesFileName.substring(0, propertiesFileName.lastIndexOf(".properties"));
        }

        if(propertiesFileName.contains(File.separator)){
            propertiesFileName = propertiesFileName.substring(propertiesFileName.lastIndexOf(File.separator)+1);
        }
        if(propertiesFileName.contains("/")){
            propertiesFileName = propertiesFileName.substring(propertiesFileName.lastIndexOf("/")+1);
        }
        if(propertiesFileName.contains("\\")){
            propertiesFileName = propertiesFileName.substring(propertiesFileName.lastIndexOf("\\")+1);
        }

        this.propertiesFileName = propertiesFileName;
        this.suffix = suffix;
    }

    protected void reload(){
        initialize(propertiesFileName, suffix);
        initializePathToDirectory();
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

    protected String getFileNameWithSuffix(){
        String result = null;

        if(!ValidateBaseValue.isEmptyOrNull(suffix)){
            result = String.format("%s_%s.properties", propertiesFileName, suffix);
        }else{
            result = getFileName();
        }
        return result;
    }

    public String readValue(String key){
        return readValue(key, null, null);
    }

    public String readValueToUTF8(String key){
        return readValue(key, null, "UTF-8");
    }

    public String readValueToUTF8(String key, String encodingFile){
        return readValue(key, encodingFile, "UTF-8");
    }

    public String readValue(String key, String encodingFile, String encodingOutput){
        String result = UtilProperties.getValue(getPathToFileWithSuffix(), key, encodingFile, encodingOutput);
        if(ValidateBaseValue.isEmptyOrNull(result)){
            result = UtilProperties.getValue(getPathToFile(), key, encodingFile, encodingOutput);
        }
        return result;
    }

    public String getPathToDirectory() {
        if(ValidateBaseValue.isEmptyOrNull(pathToDirectory)){
            pathToDirectory = initializePathToDirectory();
        }

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

    public String getPathToFileWithSuffix(){
        return String.format("%s%s%s", getPathToDirectory(), File.separator, getFileNameWithSuffix());
    }

    protected void setPathToDirectory(String pathToDirectory) {
        this.pathToDirectory = pathToDirectory;
    }

    protected void setPropertiesFileName(String propertiesFileName) {
        this.propertiesFileName = propertiesFileName;
    }

    protected void setSuffix(String suffix) {
        this.suffix = suffix;
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
            return Objects.equals(pathToDirectory, object.pathToDirectory)
                    && Objects.equals(propertiesFileName, object.propertiesFileName)
                    && Objects.equals(suffix, object.suffix);
        }else{
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(pathToDirectory, propertiesFileName, suffix);
    }
}
