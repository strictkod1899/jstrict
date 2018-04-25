package ru.strict.db.dto;

/**
 * Пользователь системы
 */
public class StrictDtoUser<ID> extends StrictDtoUserBase<ID>{

    private String passwordEncode;
    private String token;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictDtoUser() {
        super();
        this.passwordEncode = null;
        this.token = null;
    }

    public StrictDtoUser(String username, String passwordEncode, ID roleuserId, String token) {
        super(username, roleuserId);
        this.passwordEncode = passwordEncode;
        this.token = token;
    }

    public StrictDtoUser(ID id, String username, String passwordEncode, ID roleuserId, String token) {
        super(id, username, roleuserId);
        this.passwordEncode = passwordEncode;
        this.token = token;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getPasswordEncode() {
        return passwordEncode;
    }

    public void setPasswordEncode(String passwordEncode) {
        this.passwordEncode = passwordEncode;
    }

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
        return String.format("dto[%s]: %s (role: %s).\nToken: %s. Password: %s", String.valueOf(getId()), getUsername(), getRoleuserId(),
                token, passwordEncode);
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof StrictDtoUser) {
            StrictDtoUser entity = (StrictDtoUser) obj;
            return super.equals(entity) && passwordEncode.equals(entity.getPasswordEncode()) && token.equals(entity.getToken());
        }else
            return false;
    }
    //</editor-fold>
}
