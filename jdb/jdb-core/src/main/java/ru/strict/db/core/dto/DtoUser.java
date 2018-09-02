package ru.strict.db.core.dto;

import ru.strict.utils.UtilHashCode;
import ru.strict.validates.ValidateBaseValue;

/**
 * Пользователь системы
 */
public class DtoUser<ID> extends DtoUserBase<ID> {

    /**
     * Зашифрованный пароль пользователя
     */
    private String passwordEncode;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(String passwordEncode){
        if(!ValidateBaseValue.isNotEmptyOrNull(passwordEncode)) {
            throw new NullPointerException("passwordEncode is NULL");
        }

        this.passwordEncode = passwordEncode;
    }
    public DtoUser() {
        super();
        passwordEncode = null;
    }

    public DtoUser(String username, String email, String passwordEncode) {
        super(username, email);
        initialize(passwordEncode);
    }

    public DtoUser(ID id, String username, String email, String passwordEncode) {
        super(id, username, email);
        initialize(passwordEncode);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getPasswordEncode() {
        return passwordEncode;
    }

    public void setPasswordEncode(String passwordEncode) {
        if(passwordEncode == null) {
            throw new NullPointerException("passwordEncode is NULL");
        }

        this.passwordEncode = passwordEncode;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("dto user [%s]: %s.\nPassword: %s", String.valueOf(getId()), getUsername(),
                passwordEncode);
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
