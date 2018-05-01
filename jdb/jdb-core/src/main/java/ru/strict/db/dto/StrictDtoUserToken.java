package ru.strict.db.dto;

/**
 * Информация и токене пользователя системы
 */
public class StrictDtoUserToken<ID> extends StrictDtoUserBase<ID>{

    private String token;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictDtoUserToken() {
        super();
        this.token = null;
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
        if(obj instanceof StrictDtoUserToken) {
            StrictDtoUserToken dto = (StrictDtoUserToken) obj;
            return super.equals(dto) && token.equals(dto.getToken());
        }else
            return false;
    }
    //</editor-fold>
}
