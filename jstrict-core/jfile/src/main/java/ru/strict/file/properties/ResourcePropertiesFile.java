package ru.strict.file.properties;

import ru.strict.utils.ResourcesUtil;
import ru.strict.validate.CommonValidate;

public abstract class ResourcePropertiesFile extends PropertiesFile {

    private String resourcesFilePath;
    private String resourcesFilePathWithSuffix;

    public ResourcePropertiesFile(String resourcesFilePath) {
        this(resourcesFilePath, null);
    }

    public ResourcePropertiesFile(String resourcesFilePath, String suffix, String targetFilePath) {
        this(getTargetFilePath(resourcesFilePath, targetFilePath), suffix);
    }

    public ResourcePropertiesFile(String resourcesFilePath, String suffix) {
        super(resourcesFilePath, suffix);
        init(resourcesFilePath);
    }

    public void load() {
        ResourcesUtil.getResourceAsFile(resourcesFilePathWithSuffix, getFilePathWithSuffix(), getThisClass());
        ResourcesUtil.getResourceAsFile(resourcesFilePath, getFilePath(), getThisClass());
    }

    @Override
    protected void reload() {
        super.reload();
        load();
    }

    private void init(String resourcesFilePath) {
        String resourcesFilePathWithoutExtension = resourcesFilePath;
        if (resourcesFilePath.endsWith(".properties")) {
            resourcesFilePathWithoutExtension =
                    resourcesFilePath.substring(0, resourcesFilePath.lastIndexOf(".properties"));
        }

        this.resourcesFilePath = createResourcesFilePath(resourcesFilePathWithoutExtension);
        this.resourcesFilePathWithSuffix = createResourcesFilePathWithSuffix(resourcesFilePathWithoutExtension);
    }

    private String createResourcesFilePathWithSuffix(String resourcesFilePathWithoutExtension) {
        if (CommonValidate.isEmptyOrNull(getSuffix())) {
            return createResourcesFilePath(resourcesFilePathWithoutExtension);
        } else {
            return String.format("%s_%s.properties", resourcesFilePathWithoutExtension, getSuffix());
        }
    }

    private String createResourcesFilePath(String resourcesFilePathWithoutExtension) {
        return String.format("%s.properties", resourcesFilePathWithoutExtension);
    }

    private static String getTargetFilePath(String resourcesFilePath, String targetFilePath) {
        return CommonValidate.isEmptyOrNull(targetFilePath) ? resourcesFilePath : targetFilePath;
    }

    /**
     * Стандартная реализация: this.getClass();
     * Необходимо для получения ресурса из jar-файла, с этим классом
     */
    protected abstract Class getThisClass();
}
