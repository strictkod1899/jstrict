package ru.strict.enums;

/**
 * Доступные к использованию типы базы данных
 */
public enum StrictEnumDbTypes {

    /**
     * Информация для подключения к базе данных Postgresql
     */
    POSTGRESQL("jdbc:postgresql://localhost:5432/", "org.postgresql.Driver"),

    /**
     * Информация для подключения к базе данных my sql
     */
    MYSQL("jdbc:mysql://","com.mysql.jdbc.Driver"),

    /**
     * Информация для подключения к базе данных ms sql <br/>
     * <b>Внимание: </b>После добавления наименования базы данных к url, возможно необходимо добавление следующего содержимого:
     * ; integratedSecurity=true;)
     */
    MSSQL("jdbc:sqlserver://localhost:1433; databaseName=","com.microsoft.sqlserver.jdbc.SQLServerDriver"),

    /**
     * Информация для подключения к базе данных H2
     */
    H2("jdbc:h2:./", "org.h2.Driver"),

    /**
     * Информация для подключения к базе данных MS Access
     */
    ACCESS("jdbc:ucanaccess://./", "net.ucanaccess.jdbc.UcanaccessDriver");

    /**
     * Стандартный url путь для подключения к базе данных
     */
    private String url;

    /**
     * Путь к классу для получения объекта Driver
     */
    private String driver;

    private StrictEnumDbTypes(String url, String driver) {
        this.url = url;
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
}
