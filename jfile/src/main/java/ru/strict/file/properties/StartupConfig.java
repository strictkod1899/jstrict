package ru.strict.file.properties;

import java.util.Objects;

/**
 * Файл 'startup.properties', который должен находиться в корне проекта.
 * Определяет переменную environment, для задания рабочей среды приложения
 */
public class StartupConfig extends PropertiesResourceFile {

    private String environment;

    public StartupConfig() {
        super("startup", null);
        environment = readValue("environment");
    }

    public StartupConfig(String startupConfigFileName) {
        super(startupConfigFileName, null);
        environment = readValue("environment");
    }

    public String getEnvironment() {
        return environment;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof StartupConfig){
            StartupConfig object = (StartupConfig) obj;
            return super.equals(obj) && Objects.equals(environment, object.getEnvironment());
        }else{
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(getPathToDirectory(), getPropertiesFileName(), getSuffix());
    }

    @Override
    public void close(){
        environment = null;
        super.close();
    }
}
