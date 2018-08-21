package ru.strict.db.core.entities;

import java.util.Collection;
import java.util.LinkedList;

import ru.strict.utils.UtilData;
import ru.strict.utils.UtilHashCode;
import ru.strict.validates.ValidateBaseValue;

/**
 * Пользователь системы
 */
public class EntityUser<ID> extends EntityBase<ID> {

    /**
     * Логин пользователя
     */
    private String username;
    /**
     * Зашифрованный пароль пользователя
     */
    private String passwordEncode;
    /**
     * Адрес электронной почты
     */
    private String email;
    /**
     * Пользователь заблокирован
     */
    private boolean isBlocked;
    /**
     * Пользователь удален
     */
    private boolean isDeleted;
    /**
     * Адрес электронной почты подтвержден
     */
    private boolean isConfirmEmail;
    /**
     * Роли пользователя
     */
    private Collection<EntityRoleuser> rolesuser;
    /**
     * Профиль пользователя
     */
    private EntityProfile profile;
    /**
     * Токены пользователя
     */
    private Collection<EntityJWTToken> tokens;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(String username, String passwordEncode, String email){
        if(!ValidateBaseValue.isNotEmptyOrNull(username)) {
            throw new NullPointerException("username is NULL");
        } else if(!ValidateBaseValue.isNotEmptyOrNull(passwordEncode)) {
            throw new NullPointerException("passwordEncode is NULL");
        } else if(!ValidateBaseValue.isNotEmptyOrNull(email)) {
            throw new NullPointerException("email is NULL");
        }

        this.username = username;
        this.passwordEncode = passwordEncode;
        this.email = email;
        isBlocked = false;
        isDeleted = false;
        isConfirmEmail = false;
        rolesuser = new LinkedList<>();
        tokens = new LinkedList<>();
        profile = null;
    }

    public EntityUser() {
        super();
        username = null;
        passwordEncode = null;
        email = null;
        isBlocked = false;
        isDeleted = false;
        isConfirmEmail = false;
        rolesuser = new LinkedList<>();
        tokens = new LinkedList<>();
        profile = null;
    }

    public EntityUser(String username, String passwordEncode, String email) {
        super();
        initialize(username, passwordEncode, email);
    }

    public EntityUser(ID id, String username, String passwordEncode, String email) {
        super(id);
        initialize(username, passwordEncode, email);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if(!ValidateBaseValue.isNotEmptyOrNull(username)) {
            throw new NullPointerException("username is NULL");
        }

        this.username = username;
    }

    public String getPasswordEncode() {
        return passwordEncode;
    }

    public void setPasswordEncode(String passwordEncode) {
        if(!ValidateBaseValue.isNotEmptyOrNull(passwordEncode)) {
            throw new NullPointerException("passwordEncode is NULL");
        }

        this.passwordEncode = passwordEncode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(!ValidateBaseValue.isNotEmptyOrNull(email)) {
            throw new NullPointerException("email is NULL");
        }

        this.email = email;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isConfirmEmail() {
        return isConfirmEmail;
    }

    public void setConfirmEmail(boolean confirmEmail) {
        isConfirmEmail = confirmEmail;
    }

    public Collection<EntityRoleuser> getRolesuser() {
        return rolesuser;
    }

    public void setRolesuser(Collection<EntityRoleuser> rolesuser) {
        if(rolesuser == null) {
            throw new NullPointerException("rolesuser is NULL");
        }

        this.rolesuser = rolesuser;
    }

    /**
     * Добавить роль, которую использует данный пользователь
     * @param roleuser
     */
    public void addRoleuser(EntityRoleuser roleuser){
        if(roleuser == null) {
            throw new NullPointerException("roleuser is NULL");
        }

        if(rolesuser!=null) {
            rolesuser.add(roleuser);
        }
    }

    /**
     * Добавить токен
     */
    public void addToken(EntityJWTToken token){
        if(token == null) {
            throw new NullPointerException("token is NULL");
        }

        if(tokens!=null) {
            tokens.add(token);
        }
    }

    public Collection<EntityJWTToken> getTokens() {
        return tokens;
    }

    public void setTokens(Collection<EntityJWTToken> tokens) {
        if(tokens == null) {
            throw new NullPointerException("tokens is NULL");
        }

        this.tokens = tokens;
    }

    public EntityProfile getProfile() {
        return profile;
    }

    public void setProfile(EntityProfile profile) {
        this.profile = profile;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("entity user [%s]: %s.\nPassword: %s", String.valueOf(getId()), getUsername(),
                passwordEncode);
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof EntityUser) {
            EntityUser object = (EntityUser) obj;
            return super.equals(object) && username.equals(object.getUsername())
                    && passwordEncode.equals(object.getPasswordEncode())
                    && email.equals(object.getEmail())
                    && isBlocked == object.isBlocked()
                    && isDeleted == object.isDeleted()
                    && isConfirmEmail == object.isConfirmEmail()
                    && (rolesuser.size() == object.getRolesuser().size() && rolesuser.containsAll(object.getRolesuser()))
                    && (tokens.size() == object.getTokens().size() && tokens.containsAll(object.getTokens()))
                    && profile.equals(object.getProfile());
        }else
            return false;
    }

    @Override
    public int hashCode(){
    	int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, username, passwordEncode, email, isBlocked, isDeleted,
                isConfirmEmail, rolesuser, tokens, profile);
    }
    //</editor-fold>
}
