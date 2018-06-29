package ru.strict.db.core.dto;

import ru.strict.utils.UtilHashCode;

/**
 * Информация о токене пользователя и его основные данные
 */
public class DtoUserToken<ID> extends DtoUserBase<ID> {

    /**
     * Токен пользователя
     */
    private String token;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public DtoUserToken() {
        super();
        token = null;
    }

    public DtoUserToken(String username, String token) {
        super(username);
        this.token = token;
    }

    public DtoUserToken(ID id, String username, String token) {
        super(id, username);
        this.token = token;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("dto usertoken [%s]: %s. Token: %s", String.valueOf(getId()), getUsername(), token);
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof DtoUserToken) {
            DtoUserToken object = (DtoUserToken) obj;
            return super.equals(object) && token.equals(object.getToken());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, token);
    }
    //</editor-fold>
}