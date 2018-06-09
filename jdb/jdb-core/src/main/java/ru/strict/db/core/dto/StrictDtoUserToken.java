package ru.strict.db.core.dto;

import ru.strict.utils.StrictUtilHashCode;

/**
 * Информация о токене пользователя и его основные данные
 */
public class StrictDtoUserToken<ID> extends StrictDtoUserBase<ID>{

    /**
     * Токен пользователя
     */
    private String token;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictDtoUserToken() {
        super();
        token = null;
    }

    public StrictDtoUserToken(String username, String token) {
        super(username);
        this.token = token;
    }

    public StrictDtoUserToken(ID id, String username, String token) {
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
        if(obj!=null && obj instanceof StrictDtoUserToken) {
            StrictDtoUserToken object = (StrictDtoUserToken) obj;
            return super.equals(object) && token.equals(object.getToken());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return StrictUtilHashCode.createSubHashCode(superHashCode, token);
    }
    //</editor-fold>
}
