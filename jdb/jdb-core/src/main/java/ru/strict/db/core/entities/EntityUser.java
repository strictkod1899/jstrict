package ru.strict.db.core.entities;

import java.util.Collection;
import java.util.Objects;
import java.util.TreeSet;


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
     * Профили пользователя
     */
    private Collection<EntityProfile<ID>> profiles;
    /**
     * Токены пользователя
     */
    private Collection<EntityJWTToken<ID>> tokens;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(String username, String passwordEncode, String email){
        if(ValidateBaseValue.isEmptyOrNull(username)) {
            throw new IllegalArgumentException("username is NULL");
        } else if(ValidateBaseValue.isEmptyOrNull(passwordEncode)) {
            throw new IllegalArgumentException("passwordEncode is NULL");
        } else if(ValidateBaseValue.isEmptyOrNull(email)) {
            throw new IllegalArgumentException("email is NULL");
        }

        this.username = username;
        this.passwordEncode = passwordEncode;
        this.email = email;
        isBlocked = false;
        isDeleted = false;
        isConfirmEmail = false;
        roles = new TreeSet<>();
        tokens = new TreeSet<>();
        profiles = new TreeSet<>();
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
        profiles = new TreeSet<>();
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
        this.username = username;
    }

    public String getPasswordEncode() {
        return passwordEncode;
    }

    public void setPasswordEncode(String passwordEncode) {
        this.passwordEncode = passwordEncode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
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
            throw new IllegalArgumentException("roles is NULL");
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
            throw new IllegalArgumentException("role is NULL");
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
            throw new IllegalArgumentException("tokens is NULL");
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
            throw new IllegalArgumentException("token is NULL");
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

    public Collection<EntityProfile<ID>> getProfiles() {
        return profiles;
    }

    public void setProfiles(Collection<EntityProfile<ID>> profiles) {
        if(profiles == null) {
            throw new IllegalArgumentException("profiles is NULL");
        }

        for(EntityProfile<ID> profile : profiles){
            profile.setUser(this);
        }

        this.profiles = profiles;
    }

    public void addProfile(EntityProfile<ID> profile){
        addProfile(profile, true);
    }

    protected void addProfileSafe(EntityProfile<ID> profile){
        addProfile(profile, false);
    }

    private void addProfile(EntityProfile<ID> profile, boolean isCircleMode){
        if(profile == null) {
            throw new IllegalArgumentException("profile is NULL");
        }

        if(profiles != null){
            if(isCircleMode) {
                profile.setUserSafe(this);
            }
            profiles.add(profile);
        }
    }

    public void addProfiles(Collection<EntityProfile<ID>> profiles){
        if(profiles!=null) {
            for(EntityProfile<ID> profile : profiles){
                addProfile(profile);
            }
        }
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
        EntityUser<ID> object = (EntityUser<ID>) o;
        return isBlocked == object.isBlocked &&
                isDeleted == object.isDeleted &&
                isConfirmEmail == object.isConfirmEmail &&
                Objects.equals(username, object.username) &&
                Objects.equals(passwordEncode, object.passwordEncode) &&
                Objects.equals(email, object.email) &&
                Objects.equals(roles, object.roles) &&
                Objects.equals(profiles, object.profiles) &&
                Objects.equals(tokens, object.tokens);
    }

    @Override
    public int hashCode() {
        int rolesHashCode = 1;
        for(EntityRoleuser<ID> role : roles){
            rolesHashCode = 31 * rolesHashCode + (role == null ? 0 : role.hashCodeWithoutUser());
        }

        int tokensHashCode = 1;
        for(EntityJWTToken<ID> token : tokens){
            tokensHashCode = 31 * tokensHashCode + (token == null ? 0 : token.hashCodeWithoutUser());
        }

        int profilesHashCode = 1;
        for(EntityProfile<ID> profile : profiles){
            profilesHashCode = 31 * profilesHashCode + (profile == null ? 0 : profile.hashCodeWithoutUser());
        }

        return Objects.hash(super.hashCode(), username, passwordEncode, email, isBlocked, isDeleted, isConfirmEmail, rolesHashCode, profilesHashCode, tokensHashCode);
    }

    @Override
    public EntityUser<ID> clone(){
        try {
            EntityUser<ID> clone = (EntityUser<ID>) super.clone();

            clone.roles = new TreeSet<>();
            clone.profiles = new TreeSet<>();
            clone.tokens = new TreeSet<>();
            for(EntityRoleuser<ID> role : this.roles){
                clone.addRole(role.cloneSafeUser(clone));
            }
            for(EntityProfile<ID> profile : this.profiles){
                clone.addProfile(profile.cloneSafeUser(clone));
            }
            for(EntityJWTToken<ID> token : this.tokens){
                clone.addToken(token.cloneSafeUser(clone));
            }
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}
