package ru.strict.models;

import ru.strict.validates.ValidateBaseValue;

import java.util.Objects;

/**
 * Пользователь системы
 */
public class User<ID> extends UserBase<ID> {

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
    public User() {
        super();
        passwordEncode = null;
    }

    public User(String username, String email, String passwordEncode) {
        super(username, email);
        initialize(passwordEncode);
    }

    public User(ID id, String username, String email, String passwordEncode) {
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
        User<ID> dtoUser = (User<ID>) o;
        return Objects.equals(passwordEncode, dtoUser.passwordEncode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passwordEncode);
    }

    @Override
    public User<ID> clone(){
        return (User<ID>) super.clone();
    }
    //</editor-fold>
}
