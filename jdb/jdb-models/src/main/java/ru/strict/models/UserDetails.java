package ru.strict.models;

import ru.strict.validate.ValidateBaseValue;

import java.util.Objects;

/**
 * Пользователь системы
 */
public class UserDetails<ID> extends User<ID> {

    /**
     * Зашифрованный пароль пользователя
     */
    private String passwordEncode;
    /**
     * Соль для шифрования пароля
     */
    private String salt;
    /**
     * Секретный ключ для шифрования пароля
     */
    private String secret;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(String passwordEncode, String salt, String secret){
        if(ValidateBaseValue.isEmptyOrNull(passwordEncode)) {
            throw new IllegalArgumentException("passwordEncode is NULL");
        }

        this.passwordEncode = passwordEncode;
        this.salt = salt;
        this.secret = secret;
    }
    public UserDetails() {
        super();
    }

    public UserDetails(String username, String email, String passwordEncode, String salt, String secret) {
        super(username, email);
        initialize(passwordEncode, salt, secret);
    }

    public UserDetails(ID id, String username, String email, String passwordEncode, String salt, String secret) {
        super(id, username, email);
        initialize(passwordEncode, salt, secret);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getPasswordEncode() {
        return passwordEncode;
    }

    public void setPasswordEncode(String passwordEncode) {
        this.passwordEncode = passwordEncode;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
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
        UserDetails<ID> user = (UserDetails<ID>) o;
        return Objects.equals(passwordEncode, user.passwordEncode) &&
                Objects.equals(salt, user.salt) &&
                Objects.equals(secret, user.secret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passwordEncode, salt, secret);
    }

    @Override
    public UserDetails<ID> clone(){
        return (UserDetails<ID>) super.clone();
    }
    //</editor-fold>
}
