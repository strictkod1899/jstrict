package ru.strict.file.properties;

import ru.strict.utils.UtilResources;
import ru.strict.validates.ValidateBaseValue;

import java.io.File;

public abstract class PropertiesResourceFileReader extends PropertiesFileReader {

    private String resourcesFilePath;
    private String targetFilePath;

    private void initialize(String resourcesFilePath, String targetFilePath){
        if (resourcesFilePath.endsWith(".properties")){
            resourcesFilePath = resourcesFilePath.substring(0, resourcesFilePath.lastIndexOf(".properties"));
        }

        targetFilePath = ValidateBaseValue.isEmptyOrNull(targetFilePath) ? resourcesFilePath : targetFilePath;
        if (targetFilePath.endsWith(".properties")){
            targetFilePath = targetFilePath.substring(0, targetFilePath.lastIndexOf(".properties"));
        }

        this.resourcesFilePath = resourcesFilePath;
        this.targetFilePath = targetFilePath;
    }

    public PropertiesResourceFileReader(String resourcesFilePath) {
        super(resourcesFilePath);
        initialize(resourcesFilePath, null);
    }

    public PropertiesResourceFileReader(String resourcesFilePath, String suffix) {
        super(resourcesFilePath, suffix);
        initialize(resourcesFilePath, null);
    }

    public PropertiesResourceFileReader(String resourcesFilePath, String suffix, String targetFilePath) {
        super(
                ValidateBaseValue.isEmptyOrNull(targetFilePath) ? resourcesFilePath : targetFilePath,
                suffix
        );
        initialize(resourcesFilePath, targetFilePath);
    }

    /**
     * Стандартная реализация: this.getClass();
     * Необходимо для получения ресурса из jar-файла, с этим классом
     * @return
     */
    protected abstract Class getThisClass();

    @Override
    protected String initializePathToDirectory(){
        if(!(new File(getTargetFilePathWithSuffix()).exists())) {
            UtilResources.getResourceAsFile(getResourcesFilePathWithSuffix(), getTargetFilePathWithSuffix(), getThisClass());
        }
        File appFile = new File(getTargetFilePath());
        if(!appFile.exists()) {
            appFile = UtilResources.getResourceAsFile(getResourcesFilePath(), getTargetFilePath(), getThisClass());
        }

        String pathToDirectory = appFile.getAbsolutePath().substring(0, appFile.getAbsolutePath().lastIndexOf(File.separator));
        return pathToDirectory;
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

    private String getTargetFilePath(){
        return String.format("%s.properties", targetFilePath);
    }

    private String getTargetFilePathWithSuffix(){
        String result = null;

        if(!ValidateBaseValue.isEmptyOrNull(getSuffix())){
            result = String.format("%s_%s.properties", targetFilePath, getSuffix());
        }else{
            result = getTargetFilePath();
        }
        return result;
    }
}
