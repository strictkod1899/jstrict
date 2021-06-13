package ru.strict.file.properties;

public class AppConfig extends ResourcePropertiesFile {

    public static final String FILE_NAME = "app.properties";

    public AppConfig() {
        super(FILE_NAME);
    }

    public AppConfig(String targetFilePath) {
        super(FILE_NAME, null, targetFilePath);
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
