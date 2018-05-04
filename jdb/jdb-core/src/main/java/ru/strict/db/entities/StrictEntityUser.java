package ru.strict.db.entities;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Пользователь системы
 */
public class StrictEntityUser<ID> extends StrictEntityBase<ID>{

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
    private Collection<StrictEntityRoleuser> rolesuser;
    /**
     * Токен пользователя
     */
    private String token;
    /**
     * Профиль пользователя
     */
    private StrictEntityProfile profile;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictEntityUser() {
        super();
        username = null;
        passwordEncode = null;
        rolesuser = new LinkedList<>();
        token = null;
        profile = null;
    }

    public StrictEntityUser(String username, String passwordEncode, String token) {
        super();
        this.username = username;
        this.passwordEncode = passwordEncode;
        rolesuser = new LinkedList<>();
        this.token = token;
        profile = null;
    }

    public StrictEntityUser(ID id, String username, String passwordEncode, String token) {
        super(id);
        this.username = username;
        this.passwordEncode = passwordEncode;
        rolesuser = new LinkedList<>();
        this.token = token;
        profile = null;
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

    public Collection<StrictEntityRoleuser> getRolesuser() {
        return rolesuser;
    }

    public void setRolesuser(Collection<StrictEntityRoleuser> rolesuser) {
        this.rolesuser = rolesuser;
    }

    /**
     * Добавить роль, которую использует данный пользователь
     * @param roleuser
     */
    public void addRoleuser(StrictEntityRoleuser roleuser){
        rolesuser.add(roleuser);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public StrictEntityProfile getProfile() {
        return profile;
    }

    public void setProfile(StrictEntityProfile profile) {
        this.profile = profile;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("entity user [%s]: %s.\nToken: %s. Password: %s", String.valueOf(getId()), getUsername(),
                token, passwordEncode);
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof StrictEntityUser) {
            StrictEntityUser object = (StrictEntityUser) obj;
            return super.equals(object) && username.equals(object.getUsername())
                    && passwordEncode.equals(object.getPasswordEncode())
                    && (rolesuser.size() == object.getRolesuser().size() && rolesuser.containsAll(object.getRolesuser()))
                    && token.equals(object.getToken())
                    && profile.equals(object.getProfile());
        }else
            return false;
    }
    //</editor-fold>
}
