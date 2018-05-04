package ru.strict.db.connections;

/**
 * Необходимая информация для создания соединения с базой данных
 */
public class StrictConnectionInfo {

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
    public StrictConnectionInfo(String dbCaption, String driver, String url, String username, String password) {
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
        if(obj instanceof StrictConnectionInfo) {
            StrictConnectionInfo info = (StrictConnectionInfo) obj;
            return dbCaption.equals(info.getDbCaption()) && password.equals(info.getPassword())
                    && username.equals(info.getUsername()) && driver.equals(info.getDriver())
                    && url.equals(info.getUrl());
        }else
            return false;
    }
    //</editor-fold>
}
