package ru.strict.db.entities;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Пользователь системы
 */
public class StrictEntityUser<ID> extends StrictEntityBase<ID>{

    private String username;
    private String passwordEncode;
    private Collection<StrictEntityRoleuser> rolesuser;
    private String token;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictEntityUser() {
        super();
        this.username = null;
        this.passwordEncode = null;
        this.rolesuser = new LinkedList<>();
        this.token = null;
    }

    public StrictEntityUser(String username, String passwordEncode, String token) {
        super();
        this.username = username;
        this.passwordEncode = passwordEncode;
        this.rolesuser = new LinkedList<>();
        this.token = token;
    }

    public StrictEntityUser(ID id, String username, String passwordEncode, String token) {
        super(id);
        this.username = username;
        this.passwordEncode = passwordEncode;
        this.rolesuser = new LinkedList<>();
        this.token = token;
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

    public void addRoleuser(StrictEntityRoleuser roleuser){
        rolesuser.add(roleuser);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
            StrictEntityUser entity = (StrictEntityUser) obj;
            return super.equals(entity) && username.equals(entity.getUsername())
                    && passwordEncode.equals(entity.getPasswordEncode())
                    && (rolesuser.size() == entity.getRolesuser().size() && rolesuser.containsAll(entity.getRolesuser()))
                    && token.equals(entity.getToken());
        }else
            return false;
    }
    //</editor-fold>
}
