package ru.strict.db;

import ru.strict.db.enums.StrictConnectionByDbType;

public class StrictConnectionInfo {

    private String dbCaption;
    private StrictConnectionByDbType dbType;
    private String username;
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
