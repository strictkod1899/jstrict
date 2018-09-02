package ru.strict.file;

import ru.strict.utils.UtilProperties;
import ru.strict.validates.ValidateBaseValue;

public abstract class PropertiesFileReader {

    private String propertiesFileName;
    private String suffix;

    public PropertiesFileReader(String propertiesFileName) {
        this.propertiesFileName = propertiesFileName;
        suffix = null;
    }

    public PropertiesFileReader(String propertiesFileName, String suffix) {
        this.propertiesFileName = propertiesFileName;
        this.suffix = suffix;
    }

    protected String getFileName(){
        return String.format("%s.properties", propertiesFileName);
    }

    protected String getSuffixFileName(){
        String result = null;

        if(ValidateBaseValue.isNotEmptyOrNull(suffix)){
            result = String.format("%s_%s.properties", propertiesFileName, suffix);
        }else{
            result = getFileName();
        }
        return result;
    }

    public String readValue(String key){
        String result = UtilProperties.getValue(getPathToSuffixFile(), key);
        if(!ValidateBaseValue.isNotEmptyOrNull(result)){
            result = UtilProperties.getValue(getPathToFile(), key);
        }
        return result;
    }

    protected abstract String getPathToDirectory();

    public String getPathToFile(){
        return String.format("%s/%s", getPathToDirectory(), getFileName());
    }

    public String getPathToSuffixFile(){
        return String.format("%s/%s", getPathToDirectory(), getSuffixFileName());
    }
}
