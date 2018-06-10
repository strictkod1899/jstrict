package ru.strict.db.core.connections;

import ru.strict.utils.StrictUtilHashCode;

/**
 * Необходимая информация для создания соединения с базой данных
 */
public class ConnectionInfo {

    /**
     * Наименование базы данных, к которой производится подключение
     */
    private String dbCaption;

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

    //<editor-fold defaultState="collapsed" desc="constructors">
    public ConnectionInfo(String dbCaption, String driver, String url, String username, String password) {
        this.dbCaption = dbCaption;
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getDbCaption() {
        return dbCaption;
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
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("%s [%s] - %s/%s", dbCaption, driver, username, password);
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof ConnectionInfo) {
            ConnectionInfo object = (ConnectionInfo) obj;
            return dbCaption.equals(object.getDbCaption()) && password.equals(object.getPassword())
                    && username.equals(object.getUsername()) && driver.equals(object.getDriver())
                    && url.equals(object.getUrl());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        return StrictUtilHashCode.createHashCode(dbCaption, password, username, driver, url);
    }
    //</editor-fold>
}
