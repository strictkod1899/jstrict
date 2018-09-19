package ru.strict.db.core.entities;

import java.util.Collection;
import java.util.TreeSet;

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
    private Collection<EntityRoleuser<ID>> roles;
    /**
     * Профиль пользователя
     */
    private EntityProfile<ID> profile;
    /**
     * Токены пользователя
     */
    private Collection<EntityJWTToken<ID>> tokens;

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
        roles = new TreeSet<>();
        tokens = new TreeSet<>();
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
        roles = new TreeSet<>();
        tokens = new TreeSet<>();
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

    public Collection<EntityRoleuser<ID>> getRoles() {
        return roles;
    }

    public void setRoles(Collection<EntityRoleuser<ID>> roles) {
        if(roles == null) {
            throw new NullPointerException();
        }

        for(EntityRoleuser<ID> role : roles){
            role.addUserSafe(this);
        }

        this.roles = roles;
    }

    public void addRole(EntityRoleuser<ID> role){
        addRole(role, true);
    }

    protected void addRoleSafe(EntityRoleuser<ID> role){
        addRole(role, false);
    }

    private void addRole(EntityRoleuser<ID> role, boolean isCircleMode){
        if(role == null) {
            throw new NullPointerException();
        }

        if(role != null){
            if(isCircleMode) {
                role.addUserSafe(this);
            }
            roles.add(role);
        }
    }

    public void addRoles(Collection<EntityRoleuser<ID>> roles){
        if(roles!=null) {
            for(EntityRoleuser<ID> user : roles){
                addRole(user);
            }
        }
    }

    public Collection<EntityJWTToken<ID>> getTokens() {
        return tokens;
    }

    public void setTokens(Collection<EntityJWTToken<ID>> tokens) {
        if(tokens == null) {
            throw new NullPointerException();
        }

        for(EntityJWTToken<ID> token : tokens){
            token.setUser(this);
        }

        this.tokens = tokens;
    }

    public void addToken(EntityJWTToken<ID> token){
        addToken(token, true);
    }

    protected void addTokenSafe(EntityJWTToken<ID> token){
        addToken(token, false);
    }

    private void addToken(EntityJWTToken<ID> token, boolean isCircleMode){
        if(token == null) {
            throw new NullPointerException();
        }

        if(tokens != null){
            if(isCircleMode) {
                token.setUserSafe(this);
            }
            tokens.add(token);
        }
    }

    public void addTokens(Collection<EntityJWTToken<ID>> tokens){
        if(tokens!=null) {
            for(EntityJWTToken<ID> city : tokens){
                addToken(city);
            }
        }
    }

    public EntityProfile<ID> getProfile() {
        return profile;
    }

    public void setProfile(EntityProfile<ID> profile) {
        setProfile(profile, true);
    }

    protected void setProfileSafe(EntityProfile<ID> profile) {
        setProfile(profile, false);
    }

    private void setProfile(EntityProfile<ID> profile, boolean isCircleMode) {
        if(isCircleMode && profile != null){
            profile.setUserSafe(this);
        }
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
                    && isConfirmEmail == object.isConfirmEmail();
        }else
            return false;
    }

    @Override
    public int hashCode(){
    	int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, username, passwordEncode, email, isBlocked, isDeleted,
                isConfirmEmail);
    }
    //</editor-fold>
}
