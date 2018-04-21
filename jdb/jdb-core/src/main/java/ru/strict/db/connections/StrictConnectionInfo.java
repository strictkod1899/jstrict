package ru.strict.db.connections;

import ru.strict.db.enums.StrictConnectionByDbType;

public class StrictConnectionInfo {

    /**
     * Наименование базы данных, к которой производится подключение
     */
    private String dbCaption;

    /**
     * Тип подключаемой базы данных
     */
    private StrictConnectionByDbType dbType;

    /**
     * Пользователь базы данных
     */
    private String username;

    /**
     * Пароль для подключения к базе данных
     */
    private String password;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictConnectionInfo(String dbCaption, StrictConnectionByDbType dbType, String username, String password) {
        this.dbCaption = dbCaption;
        this.dbType = dbType;
        this.username = username;
        this.password = password;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getDbCaption() {
        return dbCaption;
    }

    public StrictConnectionByDbType getDbType() {
        return dbType;
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
        return String.format("%s [%s] - %s/%s", dbCaption, dbType.name(), username, password);
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof StrictConnectionInfo) {
            StrictConnectionInfo info = (StrictConnectionInfo) obj;
            return dbCaption.equals(info.getDbCaption()) && password.equals(info.getPassword())
                    && username.equals(info.getUsername()) && dbType.equals(info.getDbType());
        }else
            return false;
    }
    //</editor-fold>
}
