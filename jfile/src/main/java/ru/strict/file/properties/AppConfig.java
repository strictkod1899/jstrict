package ru.strict.file.properties;

public class AppConfig extends PropertiesResourceFileReader {

    public AppConfig() {
        super("app", "development");
    }

    public AppConfig(String environment) {
        super("app", environment);
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

    @Override
    public void close(){
        super.close();
    }
}
