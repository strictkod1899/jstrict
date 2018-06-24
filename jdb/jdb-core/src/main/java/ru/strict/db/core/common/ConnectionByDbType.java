package ru.strict.db.core.common;

/**
 * Данные (driver и url) для создания соединения с базой данных в зависимости от ее производителя
 */
public enum ConnectionByDbType {

    /**
     * Информация для подключения к базе данных Postgresql. <br/>
     * К url добавлятся наименование базы данных
     */
    POSTGRESQL("jdbc:postgresql://localhost:5432/", "org.postgresql.Driver"),

    /**
     * Информация для подключения к базе данных my sql. <br/>
     * К url добавлятся наименование базы данных
     */
    MYSQL("jdbc:mysql://","com.mysql.jdbc.Driver"),

    /**
     * Информация для подключения к базе данных ms sql. <br/>
     * <b>Внимание: </b>После добавления наименования базы данных к url, возможно необходимо добавление следующего содержимого:
     * ; integratedSecurity=true;)
     */
    MSSQL("jdbc:sqlserver://localhost:1433; databaseName=","com.microsoft.sqlserver.jdbc.SQLServerDriver"),

    /**
     * Информация для подключения к базе данных H2. <br/>
     * К url добавляется наименование базы данных
     */
    H2("jdbc:h2:./", "org.h2.Driver"),

    /**
     * Информация для подключения к базе данных MS Access. <br/>
     * К url добавляется наименование базы данных
     */
    ACCESS("jdbc:ucanaccess://./", "net.ucanaccess.jdbc.UcanaccessDriver"),

    /**
     * Информация для подключения к базе данных SQLite. <br/>
     * К url добавляется путь до файла базы данных
     */
    SQLITE("jdbc:sqlite:", "org.sqlite.JDBC");

    /**
     * Стандартный url путь для подключения к базе данных
     */
    private String url;

    /**
     * Путь к классу для получения объекта Driver
     */
    private String driver;

    ConnectionByDbType(String url, String driver) {
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
