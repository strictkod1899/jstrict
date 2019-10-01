package ru.strict.db.mybatis.connection;

import ru.strict.db.mybatis.mappers.sql.MapperSqlBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Необходимая информация для создания соединения с базой данных, при использовании MyBatis
 * <p><b>Пример использования:</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *     ...
 *     MybatisConnectionInfo connectionInfo = new MybatisConnectionInfo("com.mysql.jdbc.Driver", "jdbc:mysql://mydb", "myuser", "mypassword");
 *     SqlSession connection = connectionCreator.createConnection();
 * </pre></code>
 */
public class MybatisConnectionInfo {
    /**
     * Путь до файла конфигурации mybatis
     */
    private String configFilePath;
    /**
     * Строка драйвера подключаемой базы данных
     */
    private String driver;

    /**
     * Строка url подключаемой базы данных
     */
    private String url;

    /**
     * Пользователь базы данных
     */
    private String username;

    /**
     * Пароль для подключения к базе данных
     */
    private String password;
    /**
     * Классы mybatis-мапперов
     */
    private List<Class<? extends MapperSqlBase>> mappers;

    private String environment;

    public MybatisConnectionInfo(String configFilePath) {
        this.configFilePath = configFilePath;
    }

    public MybatisConnectionInfo(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
        mappers = new ArrayList<>();
        environment = "development";
    }

    public String getConfigFilePath() {
        return configFilePath;
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public void addMapper(Class<? extends MapperSqlBase> mapperClass){
        if(mappers == null){
            throw new UnsupportedOperationException("Fail add mapper class. Adding mapper class is available without use xml-config");
        }

        mappers.add(mapperClass);
    }

    public List<Class<? extends MapperSqlBase>> getMappers() {
        return mappers;
    }

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("%s [%s]. User: %s/%s", driver, url, username, password);
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof MybatisConnectionInfo) {
            MybatisConnectionInfo object = (MybatisConnectionInfo) obj;
            return Objects.equals(configFilePath, object.getConfigFilePath())
                    && Objects.equals(driver, object.getDriver())
                    && Objects.equals(url, object.getUrl())
                    && Objects.equals(password, object.getPassword())
                    && Objects.equals(username, object.getUsername());
        }else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(configFilePath, driver, url, password, username);
    }
    //</editor-fold>
}
