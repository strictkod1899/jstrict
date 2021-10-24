package ru.strict.file.properties;

import ru.strict.util.ResourcesUtil;

public class AppPropertiesConfig extends PropertiesFile {

    public static final String DEFAULT_FILE_NAME = "app.properties";

    public AppPropertiesConfig() {
        super(() -> ResourcesUtil.getResourceStream(DEFAULT_FILE_NAME));
    }

    public AppPropertiesConfig(String filePath) {
        super(filePath);
    }
}
