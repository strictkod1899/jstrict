package ru.strict.db.core.dto;

import ru.strict.validates.ValidateBaseValue;

import java.util.Objects;

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
        if(ValidateBaseValue.isEmptyOrNull(passwordEncode)) {
            throw new IllegalArgumentException("passwordEncode is NULL");
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
        this.passwordEncode = passwordEncode;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("user [%s]: %s/%s", String.valueOf(getId()), getUsername(), passwordEncode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DtoUser<ID> dtoUser = (DtoUser<ID>) o;
        return Objects.equals(passwordEncode, dtoUser.passwordEncode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passwordEncode);
    }

    @Override
    public DtoUser<ID> clone(){
        DtoUserBase<ID> baseClone = super.clone();
        DtoUser<ID> clone = new DtoUser<>();

        clone.setId(getId());
        clone.setBlocked(baseClone.isBlocked());
        clone.setDeleted(baseClone.isDeleted());
        clone.setConfirmEmail(baseClone.isConfirmEmail());
        clone.setUsername(baseClone.getUsername());
        clone.setPasswordEncode(passwordEncode);
        clone.setEmail(baseClone.getEmail());
        clone.setRoles(baseClone.getRoles());
        clone.setProfiles(baseClone.getProfiles());
        return clone;
    }
    //</editor-fold>
}
