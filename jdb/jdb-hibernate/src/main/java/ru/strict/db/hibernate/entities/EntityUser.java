package ru.strict.db.hibernate.entities;

import ru.strict.utils.UtilHashCode;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.UUID;

/**
 * Пользователь системы
 */
@Entity
@Table(name = "userx")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class EntityUser extends EntityBase {

    /**
     * Логин пользователя
     */
    @Column(name = "username")
    private String username;
    /**
     * Зашифрованный пароль пользователя
     */
    @Column(name = "passwordencode")
    private String passwordEncode;
    /**
     * Роли пользователя
     */
    @ManyToMany()
    @JoinTable(name = "user_on_role",
            joinColumns = @JoinColumn(name = "userx_id", insertable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "roleuser_id", insertable = false, updatable = false)
    )
    private Collection<EntityRoleuser> rolesuser;
    /**
     * Профиль пользователя. Используется конструкция OneToMany, но фактически реализована связь OneToOne
     */
    @OneToMany(mappedBy = "userId", cascade = CascadeType.REFRESH ,orphanRemoval = true)
    private Collection<EntityProfile> profile;
    /**
     * Токены пользователя
     */
    @OneToMany(mappedBy = "userId", cascade = CascadeType.REFRESH ,orphanRemoval = true)
    private Collection<EntityJWTUserToken> tokens;

    public EntityProfile getProfile() {
        EntityProfile result = null;

        if(profile != null){
            result = profile.stream().findFirst().orElse(null);
        }

        return result;
    }

    public void setProfile(EntityProfile profile) {
        if(this.profile != null && profile != null){
            this.profile.removeAll(this.profile);
            this.profile.add(profile);
        }
    }

    protected void setProfiles(Collection<EntityProfile> profile) {
        this.profile = profile;
    }

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(String username, String passwordEncode){
        if(username == null) {
            throw new NullPointerException("username is NULL");
        } else if(passwordEncode == null) {
            throw new NullPointerException("passwordEncode is NULL");
        }

        this.username = username;
        this.passwordEncode = passwordEncode;
        rolesuser = new LinkedList<>();
        tokens = new LinkedList<>();
        profile = new LinkedList<>();
    }

    public EntityUser() {
        super();
        username = null;
        passwordEncode = null;
        rolesuser = new LinkedList<>();
        tokens = new LinkedList<>();
        profile = new LinkedList<>();
    }

    public EntityUser(String username, String passwordEncode) {
        super();
        initialize(username, passwordEncode);
    }

    public EntityUser(UUID id, String username, String passwordEncode) {
        super(id);
        initialize(username, passwordEncode);
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
    public void addToken(EntityJWTUserToken token){
        if(token == null) {
            throw new NullPointerException("token is NULL");
        }

        if(tokens!=null) {
            tokens.add(token);
        }
    }

    public Collection<EntityJWTUserToken> getTokens() {
        return tokens;
    }

    public void setTokens(Collection<EntityJWTUserToken> tokens) {
        if(tokens == null) {
            throw new NullPointerException("tokens is NULL");
        }

        this.tokens = tokens;
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
