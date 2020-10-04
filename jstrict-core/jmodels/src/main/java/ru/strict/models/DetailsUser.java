package ru.strict.models;

import ru.strict.validate.CommonValidate;

import java.util.Objects;

/**
 * Пользователь системы
 */
public class DetailsUser<ID> extends User<ID> {

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
    private void init(String passwordEncode, String salt, String secret) {
        if (CommonValidate.isEmptyOrNull(passwordEncode)) {
            throw new IllegalArgumentException("passwordEncode is NULL");
        }

        this.passwordEncode = passwordEncode;
        this.salt = salt;
        this.secret = secret;
    }

    public DetailsUser() {
        super();
    }

    public DetailsUser(String username, String email, String passwordEncode, String salt, String secret) {
        super(username, email);
        init(passwordEncode, salt, secret);
    }

    public DetailsUser(ID id, String username, String email, String passwordEncode, String salt, String secret) {
        super(id, username, email);
        init(passwordEncode, salt, secret);
    }

    public DetailsUser(String username,
            String email,
            boolean blocked,
            boolean deleted,
            boolean confirmEmail,
            String passwordEncode,
            String salt,
            String secret) {
        super(username, email, blocked, deleted, confirmEmail);
        init(passwordEncode, salt, secret);
    }

    public DetailsUser(ID id,
            String username,
            String email,
            boolean blocked,
            boolean deleted,
            boolean confirmEmail,
            String passwordEncode,
            String salt,
            String secret) {
        super(id, username, email, blocked, deleted, confirmEmail);
        init(passwordEncode, salt, secret);
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
    public String toString() {
        return String.format("user [%s]: %s/%s", String.valueOf(getId()), getUsername(), passwordEncode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        DetailsUser<ID> user = (DetailsUser<ID>) o;
        return Objects.equals(passwordEncode, user.passwordEncode) &&
                Objects.equals(salt, user.salt) &&
                Objects.equals(secret, user.secret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passwordEncode, salt, secret);
    }

    @Override
    public DetailsUser<ID> clone() {
        return (DetailsUser<ID>) super.clone();
    }
    //</editor-fold>
}
