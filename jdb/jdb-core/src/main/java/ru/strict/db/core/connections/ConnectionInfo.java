package ru.strict.db.core.connections;


import java.util.Objects;

/**
 * Необходимая информация для создания соединения с базой данных
 * <p><b>Пример использования:</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *     ...
 *     ConnectionInfo connectionInfo = new ConnectionInfo("com.mysql.jdbc.Driver", "jdbc:mysql://mydb", "mysqluser", "mysqlpassword");
 *     Connection connection = connectionCreater.createConnection();
 * </pre></code>
 */
public class ConnectionInfo {

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
    public ConnectionInfo(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
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
        return String.format("%s [%s]. UserDetails: %s/%s", driver, url, username, password);
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof ConnectionInfo) {
            ConnectionInfo object = (ConnectionInfo) obj;
            return Objects.equals(password, object.getPassword())
                    && Objects.equals(username, object.getUsername())
                    && Objects.equals(driver, object.getDriver())
                    && Objects.equals(url, object.getUrl());
        }else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(password, username, driver, url);
    }
    //</editor-fold>
}
