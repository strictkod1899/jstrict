package ru.strict.db.hibernate.entities;

import ru.strict.utils.UtilHashCode;
import ru.strict.validates.ValidateBaseValue;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Пользователь системы
 */
@Entity
@Table(name = "userx")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class EntityUser<ID> extends EntityBase<ID> {

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
    @Column(name = "isBlocked", nullable = false)
    private boolean isBlocked;
    /**
     * Пользователь удален
     */
    @Column(name = "isDeleted", nullable = false)
    private boolean isDeleted;
    /**
     * Адрес электронной почты подтвержден
     */
    @Column(name = "isConfirmEmail", nullable = false)
    private boolean isConfirmEmail;
    /**
     * Роли пользователя
     */
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "user_on_role",
            joinColumns = @JoinColumn(name = "userx_id", insertable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "roleuser_id", insertable = false, updatable = false))
    private Collection<EntityRoleuser<ID>> rolesuser;
    /**
     * Профиль пользователя. Используется конструкция OneToMany, но фактически реализована связь OneToOne
     */
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<EntityProfileBase<ID>> profile;
    /**
     * Токены пользователя
     */
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<EntityJWTToken<ID>> tokens;

    public EntityProfileBase<ID> getProfile() {
        EntityProfileBase<ID> result = null;

        if(profile != null){
            result = profile.stream().findFirst().orElse(null);
        }

        return result;
    }

    public void setProfile(EntityProfileBase<ID> profile) {
        if(this.profile != null && profile != null){
            this.profile.removeAll(this.profile);
            this.profile.add(profile);
        }
    }

    protected void setProfiles(Collection<EntityProfileBase<ID>> profile) {
        this.profile = profile;
    }

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
        if(username == null) {
            throw new NullPointerException("username is NULL");
        }

        this.username = username;
    }

    public String getPasswordEncode() {
        return passwordEncode;
    }

    public void setPasswordEncode(String passwordEncode) {
        if(passwordEncode == null) {
            throw new NullPointerException("passwordEncode is NULL");
        }

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

    public Collection<EntityRoleuser<ID>> getRolesuser() {
        return rolesuser;
    }

    public void setRolesuser(Collection<EntityRoleuser<ID>> rolesuser) {
        if(rolesuser == null) {
            throw new NullPointerException("rolesuser is NULL");
        }

        this.rolesuser = rolesuser;
    }

    /**
     * Добавить роль, которую использует данный пользователь
     * @param roleuser
     */
    public void addRoleuser(EntityRoleuser<ID> roleuser){
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
    public void addToken(EntityJWTToken<ID> token){
        if(token == null) {
            throw new NullPointerException("token is NULL");
        }

        if(tokens!=null) {
            tokens.add(token);
        }
    }

    public Collection<EntityJWTToken<ID>> getTokens() {
        return tokens;
    }

    public void setTokens(Collection<EntityJWTToken<ID>> tokens) {
        if(tokens == null) {
            throw new NullPointerException("tokens is NULL");
        }

        this.tokens = tokens;
    }

    public void setProfile(Collection<EntityProfileBase<ID>> profile) {
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
                    && (rolesuser.size() == object.getRolesuser().size() && rolesuser.containsAll(object.getRolesuser()))
                    && (tokens.size() == object.getTokens().size() && tokens.containsAll(object.getTokens()))
                    && profile.equals(object.getProfile());
        }else
            return false;
    }

    @Override
    public int hashCode(){
    	int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, username, passwordEncode, rolesuser, tokens, profile);
    }
    //</editor-fold>
}
