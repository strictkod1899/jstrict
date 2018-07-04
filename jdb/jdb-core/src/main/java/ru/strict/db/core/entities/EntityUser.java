package ru.strict.db.core.entities;

import java.util.Collection;
import java.util.LinkedList;
import ru.strict.utils.UtilHashCode;

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
        profile = null;
    }

    public EntityUser() {
        super();
        username = null;
        passwordEncode = null;
        rolesuser = new LinkedList<>();
        tokens = new LinkedList<>();
        profile = null;
    }

    public EntityUser(String username, String passwordEncode) {
        super();
        initialize(username, passwordEncode);
    }

    public EntityUser(ID id, String username, String passwordEncode) {
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
