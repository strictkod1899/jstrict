package ru.strict.db.core.dto;

import ru.strict.utils.UtilHashCode;

/**
 * Пользователь системы
 */
public class DtoUser<ID> extends DtoUserToken<ID> {

    /**
     * Зашифрованный пароль пользователя
     */
    private String passwordEncode;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public DtoUser() {
        super();
        passwordEncode = null;
    }

    public DtoUser(String username, String passwordEncode, String token) {
        super(username, token);
        this.passwordEncode = passwordEncode;
    }

    public DtoUser(ID id, String username, String passwordEncode, String token) {
        super(id, username, token);
        this.passwordEncode = passwordEncode;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getPasswordEncode() {
        return passwordEncode;
    }

    public void setPasswordEncode(String passwordEncode) {
        this.passwordEncode = passwordEncode;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("dto user [%s]: %s.\nToken: %s. Password: %s", String.valueOf(getId()), getUsername(),
                getToken(), passwordEncode);
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof DtoUser) {
            DtoUser object = (DtoUser) obj;
            return super.equals(object) && passwordEncode.equals(object.getPasswordEncode());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, passwordEncode);
    }
    //</editor-fold>
}
