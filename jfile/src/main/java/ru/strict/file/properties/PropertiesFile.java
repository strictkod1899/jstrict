package ru.strict.file.properties;

import ru.strict.utils.UtilProperties;
import ru.strict.validates.ValidateBaseValue;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class PropertiesFile implements AutoCloseable{

    private String pathToDirectory;
    private String fileName;
    private String suffix;

    private void initialize(String filePath, String suffix){
        if(ValidateBaseValue.isEmptyOrNull(filePath)){
            throw new NullPointerException("properties file name is NULL");
        }

        String fileName = filePath;

        if (fileName.endsWith(".properties")){
            fileName = fileName.substring(0, fileName.lastIndexOf(".properties"));
        }

        if(fileName.contains(File.separator)){
            fileName = fileName.substring(fileName.lastIndexOf(File.separator)+1);
        }
        if(fileName.contains("/")){
            fileName = fileName.substring(fileName.lastIndexOf("/")+1);
        }
        if(fileName.contains("\\")){
            fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
        }

        this.fileName = fileName;
        this.suffix = suffix;
        String absoluteFilePath = new File(filePath).getAbsolutePath();
        this.pathToDirectory = absoluteFilePath.substring(0, absoluteFilePath.lastIndexOf(File.separator));
    }

    public PropertiesFile(String filePath) {
        this(filePath, null);
    }

    public PropertiesFile(String filePath, String suffix) {
        initialize(filePath, suffix);
    }

    protected void reload(){
        initialize(pathToDirectory + File.separator + fileName, suffix);
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
        String result = null;
        if(Files.exists(Paths.get(getFilePathWithSuffix()))) {
            result = UtilProperties.getValue(getFilePathWithSuffix(), key, encodingFile, encodingOutput);
        }
        if(ValidateBaseValue.isEmptyOrNull(result)){
            if(Files.exists(Paths.get(getFilePath()))) {
                result = UtilProperties.getValue(getFilePath(), key, encodingFile, encodingOutput);
            }
        }
        return result;
    }

    public String getPathToDirectory() {
        return pathToDirectory;
    }

    public String getFileName(){
        return String.format("%s.properties", fileName);
    }

    public String getFileNameWithSuffix(){
        String result = null;

        if(!ValidateBaseValue.isEmptyOrNull(suffix)){
            result = String.format("%s_%s.properties", fileName, suffix);
        }else{
            result = getFileName();
        }
        return result;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getFilePath(){
        return String.format("%s%s%s", getPathToDirectory(), File.separator, getFileName());
    }

    public String getFilePathWithSuffix(){
        return String.format("%s%s%s", getPathToDirectory(), File.separator, getFileNameWithSuffix());
    }

    protected void setFileName(String fileName) {
        this.fileName = fileName;
    }

    protected void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    protected void setPathToDirectory(String pathToDirectory) {
        this.pathToDirectory = pathToDirectory;
    }

    @Override
    public void close() {
        pathToDirectory = null;
        fileName = null;
        suffix = null;
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof PropertiesFile){
            PropertiesFile object = (PropertiesFile) obj;
            return Objects.equals(pathToDirectory, object.pathToDirectory)
                    && Objects.equals(fileName, object.fileName)
                    && Objects.equals(suffix, object.suffix);
        }else{
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(pathToDirectory, fileName, suffix);
    }
}
