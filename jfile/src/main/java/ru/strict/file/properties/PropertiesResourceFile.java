package ru.strict.file.properties;

import ru.strict.utils.UtilResources;
import ru.strict.validate.ValidateBaseValue;

import java.io.File;

public abstract class PropertiesResourceFile extends PropertiesFile {

    private String resourcesFilePath;

    private void initialize(String resourcesFilePath){
        if (resourcesFilePath.endsWith(".properties")){
            resourcesFilePath = resourcesFilePath.substring(0, resourcesFilePath.lastIndexOf(".properties"));
        }

        this.resourcesFilePath = resourcesFilePath;
        load();
    }

    public PropertiesResourceFile(String resourcesFilePath) {
        super(resourcesFilePath);
        initialize(resourcesFilePath);
    }

    public PropertiesResourceFile(String resourcesFilePath, String suffix) {
        super(resourcesFilePath, suffix);
        initialize(resourcesFilePath);
    }

    public PropertiesResourceFile(String resourcesFilePath, String suffix, String targetFilePath) {
        super(
                ValidateBaseValue.isEmptyOrNull(targetFilePath) ? resourcesFilePath : targetFilePath,
                suffix
        );
        initialize(resourcesFilePath);
    }

    @Override
    protected void reload() {
        super.reload();
        load();
    }

    /**
     * Стандартная реализация: this.getClass();
     * Необходимо для получения ресурса из jar-файла, с этим классом
     */
    protected abstract Class getThisClass();

    private void load(){
        if(!(new File(getFilePathWithSuffix()).exists())) {
            UtilResources.getResourceAsFile(getResourcesFilePathWithSuffix(), getFilePathWithSuffix(), getThisClass());
        }
        if(!(new File(getFilePath()).exists())) {
            UtilResources.getResourceAsFile(getResourcesFilePath(), getFilePath(), getThisClass());
        }
    }

    private String getResourcesFilePath(){
        return String.format("%s.properties", resourcesFilePath);
    }

    private String getResourcesFilePathWithSuffix(){
        String result = null;

        if(!ValidateBaseValue.isEmptyOrNull(getSuffix())){
            result = String.format("%s_%s.properties", resourcesFilePath, getSuffix());
        }else{
            result = getResourcesFilePath();
        }
        return result;
    }
}
