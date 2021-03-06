package ru.strict.db.hibernate.entities;

import javax.persistence.*;
import ru.strict.validate.ValidateBaseValue;

import java.util.Collection;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Пользователь системы
 */
@Entity
@Table(name = "userx")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class EntityUser extends EntityBase<Long> {

    /**
     * Логин пользователя
     */
    @Column(name = "username", nullable = false)
    private String username;
    /**
     * Зашифрованный пароль пользователя
     */
    @Column(name = "passwordencode", nullable = false)
    private String passwordEncode;
    /**
     * Адрес электронной почты
     */
    @Column(name = "email", nullable = false)
    private String email;
    /**
     * Пользователь заблокирован
     */
    @Column(name = "is_blocked", nullable = false)
    private boolean isBlocked;
    /**
     * Пользователь удален
     */
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;
    /**
     * Адрес электронной почты подтвержден
     */
    @Column(name = "is_confirm_email", nullable = false)
    private boolean isConfirmEmail;
    /**
     * Роли пользователя
     */
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "user_on_role",
            joinColumns = @JoinColumn(name = "userx_id", insertable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "roleuser_id", insertable = false, updatable = false))
    private Collection<EntityRoleuser> roles;
    /**
     * Профили пользователя
     */
    @Transient
    private Collection<EntityProfile> profiles;
    /**
     * Токены пользователя
     */
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<EntityJWTToken> tokens;

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

    public EntityUser(Long id, String username, String passwordEncode, String email) {
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

    public Collection<EntityRoleuser> getRoles() {
        return roles;
    }

    public void setRoles(Collection<EntityRoleuser> roles) {
        if(roles == null) {
            throw new IllegalArgumentException("roles is NULL");
        }

        for(EntityRoleuser role : roles){
            role.addUserSafe(this);
        }

        this.roles = roles;
    }

    public void addRole(EntityRoleuser role){
        addRole(role, true);
    }

    protected void addRoleSafe(EntityRoleuser role){
        addRole(role, false);
    }

    private void addRole(EntityRoleuser role, boolean isCircleMode){
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

    public void addRoles(Collection<EntityRoleuser> roles){
        if(roles!=null) {
            for(EntityRoleuser user : roles){
                addRole(user);
            }
        }
    }

    public Collection<EntityJWTToken> getTokens() {
        return tokens;
    }

    public void setTokens(Collection<EntityJWTToken> tokens) {
        if(tokens == null) {
            throw new IllegalArgumentException("tokens is NULL");
        }

        for(EntityJWTToken token : tokens){
            token.setUser(this);
        }

        this.tokens = tokens;
    }

    public void addToken(EntityJWTToken token){
        addToken(token, true);
    }

    protected void addTokenSafe(EntityJWTToken token){
        addToken(token, false);
    }

    private void addToken(EntityJWTToken token, boolean isCircleMode){
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

    public void addTokens(Collection<EntityJWTToken> tokens){
        if(tokens!=null) {
            for(EntityJWTToken city : tokens){
                addToken(city);
            }
        }
    }

    public Collection<EntityProfile> getProfiles() {
        return profiles;
    }

    public void setProfiles(Collection<EntityProfile> profiles) {
        if(profiles == null) {
            throw new IllegalArgumentException("profiles is NULL");
        }

        for(EntityProfile profile : profiles){
            profile.setUser(this);
        }

        this.profiles = profiles;
    }

    public void addProfile(EntityProfileBase profile){
        addProfile(profile, true);
    }

    protected void addProfileSafe(EntityProfileBase profile){
        addProfile(profile, false);
    }

    private void addProfile(EntityProfileBase profile, boolean isCircleMode){
        if(profile == null) {
            throw new IllegalArgumentException("profile is NULL");
        }

        if(profiles != null){
            if(isCircleMode) {
                profile.setUserSafe(this);
            }

            EntityProfile entityProfile = new EntityProfile();
            entityProfile.setId(profile.getId());
            entityProfile.setName(profile.getName());
            entityProfile.setSurname(profile.getSurname());
            entityProfile.setMiddlename(profile.getMiddlename());
            entityProfile.setUserId(profile.getUserId());
            entityProfile.setUser(profile.getUser());
            profiles.add(entityProfile);
        }
    }

    public void addProfiles(Collection<EntityProfile> profiles){
        if(profiles!=null) {
            for(EntityProfile profile : profiles){
                addProfile(profile);
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("entity user [%s]: %s.\nPassword: %s", String.valueOf(getId()), getUsername(),
                passwordEncode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EntityUser object = (EntityUser) o;
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
        for(EntityRoleuser role : roles){
            rolesHashCode = 31 * rolesHashCode + (role == null ? 0 : role.hashCodeWithoutUser());
        }

        int tokensHashCode = 1;
        for(EntityJWTToken token : tokens){
            tokensHashCode = 31 * tokensHashCode + (token == null ? 0 : token.hashCodeWithoutUser());
        }

        int profilesHashCode = 1;
        for(EntityProfile profile : profiles){
            profilesHashCode = 31 * profilesHashCode + (profile == null ? 0 : profile.hashCodeWithoutUser());
        }

        return Objects.hash(super.hashCode(), username, passwordEncode, email, isBlocked, isDeleted, isConfirmEmail, rolesHashCode, profilesHashCode, tokensHashCode);
    }

    @Override
    public EntityUser clone(){
        try {
            EntityUser clone = (EntityUser) super.clone();

            clone.roles = new TreeSet<>();
            clone.profiles = new TreeSet<>();
            clone.tokens = new TreeSet<>();
            for(EntityRoleuser role : this.roles){
                clone.addRole(role.cloneSafeUser(clone));
            }
            for(EntityProfile profile : this.profiles){
                clone.addProfile(profile.cloneSafeUser(clone));
            }
            for(EntityJWTToken token : this.tokens){
                clone.addToken(token.cloneSafeUser(clone));
            }
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}
