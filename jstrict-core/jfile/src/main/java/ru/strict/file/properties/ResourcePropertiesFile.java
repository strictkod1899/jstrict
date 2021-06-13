package ru.strict.file.properties;

import ru.strict.utils.ResourcesUtil;
import ru.strict.validate.CommonValidate;

public abstract class ResourcePropertiesFile extends PropertiesFile {
    private String resourcesFilePath;
    private String resourcesFilePathWithSuffix;
    private boolean allowExtract;

    public ResourcePropertiesFile(String resourcesFilePath, String suffix, String targetFilePath) {
        super(getTargetFilePath(resourcesFilePath, targetFilePath), suffix);
        initByFilePath(resourcesFilePath);
    }

    public ResourcePropertiesFile(String resourcesFilePath) {
        super(ResourcesUtil.getResourceStream(resourcesFilePath));
        this.allowExtract = false;
    }

    public void load() {
        if (!allowExtract) {
            throw new IllegalArgumentException(
                    String.format("Properties file [%s] not allowed to extract", resourcesFilePath)
            );
        }

        ResourcesUtil.getResourceAsFile(resourcesFilePathWithSuffix, getFilePathWithSuffix(), getThisClass());
        ResourcesUtil.getResourceAsFile(resourcesFilePath, getFilePath(), getThisClass());
    }

    @Override
    protected void reload() {
        super.reload();
        if (allowExtract) {
            load();
        }
    }

    private void initByFilePath(String resourcesFilePath) {
        this.allowExtract = true;

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
