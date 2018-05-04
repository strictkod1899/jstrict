package ru.strict.db.dto;

/**
 * Пользователь системы
 */
public class StrictDtoUser<ID> extends StrictDtoUserToken<ID>{

    /**
     * Зашифрованный пароль пользователя
     */
    private String passwordEncode;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictDtoUser() {
        super();
        passwordEncode = null;
    }

    public StrictDtoUser(String username, String passwordEncode, String token) {
        super(username, token);
        this.passwordEncode = passwordEncode;
    }

    public StrictDtoUser(ID id, String username, String passwordEncode, String token) {
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
        if(obj instanceof StrictDtoUser) {
            StrictDtoUser dto = (StrictDtoUser) obj;
            return super.equals(dto) && passwordEncode.equals(dto.getPasswordEncode());
        }else
            return false;
    }
    //</editor-fold>
}
