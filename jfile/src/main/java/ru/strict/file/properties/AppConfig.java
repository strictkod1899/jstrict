package ru.strict.file.properties;

import ru.strict.file.data.AppEnvironment;

import java.util.Objects;

public class AppConfig extends PropertiesResourceFileReader {

    private AppEnvironment environment;

    public AppConfig() {
        super("app", AppEnvironment.DEVELOPMENT.getValue());
        environment = AppEnvironment.DEVELOPMENT;
    }

    public AppConfig(String appConfigFileName) {
        super(appConfigFileName, AppEnvironment.DEVELOPMENT.getValue());
        environment = AppEnvironment.DEVELOPMENT;
    }

    public AppConfig(AppEnvironment environment) {
        super("app", environment.getValue());
        this.environment = environment;
    }

    public AppConfig(String appConfigFileName, AppEnvironment environment) {
        super(appConfigFileName, environment.getValue());
        this.environment = environment;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }

    public String getEnvironmentFileName(){
        return getSuffixFileName();
    }

    public AppEnvironment getEnvironment() {
        return environment;
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof AppConfig){
            AppConfig object = (AppConfig) obj;
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
