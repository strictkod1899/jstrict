package ru.strict.db.entities;

/**
 * Пользователь системы
 */
public class StrictEntityUser<ID> extends StrictEntityBase<ID>{

    private String username;
    private String passwordEncode;
    private ID roleuserId;
    private StrictEntityRoleuser roleuser;
    private String token;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictEntityUser() {
        super();
        this.username = null;
        this.passwordEncode = null;
        this.roleuserId = null;
        this.roleuser = null;
        this.token = null;
    }

    public StrictEntityUser(String username, String passwordEncode, ID roleuserId, String token) {
        super();
        this.username = username;
        this.passwordEncode = passwordEncode;
        this.roleuserId = roleuserId;
        this.roleuser = null;
        this.token = token;
    }

    public StrictEntityUser(ID id, String username, String passwordEncode, ID roleuserId, String token) {
        super(id);
        this.username = username;
        this.passwordEncode = passwordEncode;
        this.roleuserId = roleuserId;
        this.roleuser = null;
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

    public ID getRoleuserId() {
        return roleuserId;
    }

    public void setRoleuserId(ID roleuserId) {
        this.roleuserId = roleuserId;
    }

    public StrictEntityRoleuser getRoleuser() {
        return roleuser;
    }

    public void setRoleuser(StrictEntityRoleuser roleuser) {
        this.roleuser = roleuser;
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
        return String.format("entity[%s]: %s (role: %s).\nToken: %s. Password: %s", String.valueOf(getId()), getUsername(), getRoleuserId(),
                token, passwordEncode);
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof StrictEntityUser) {
            StrictEntityUser entity = (StrictEntityUser) obj;
            return super.equals(entity) && username.equals(entity.getUsername())
                    && passwordEncode.equals(entity.getPasswordEncode()) && roleuser.equals(entity.getRoleuser())
                    && roleuserId.equals(entity.getRoleuserId()) && token.equals(entity.getToken());
        }else
            return false;
    }
    //</editor-fold>
}
