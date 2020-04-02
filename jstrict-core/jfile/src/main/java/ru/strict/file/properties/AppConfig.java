package ru.strict.file.properties;

public class AppConfig extends PropertiesResourceFile {

    public static final String FILE_NAME = "app";

    public AppConfig() {
        super(FILE_NAME, "development");
    }

    public AppConfig(String environment) {
        super(FILE_NAME, environment);
    }

    public AppConfig(String appConfigFileName, String environment) {
        super(appConfigFileName, environment);
    }

    public AppConfig(String appConfigFileName, String environment, String targetFilePath) {
        super(appConfigFileName, environment, targetFilePath);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }

    public String getEnvironmentFileName(){
        return getFileNameWithSuffix();
    }

    public String getEnvironment() {
        return getSuffix();
    }
}
