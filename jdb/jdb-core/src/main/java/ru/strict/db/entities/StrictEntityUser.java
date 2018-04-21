package ru.strict.db.entities;

public class StrictEntityUser<ID> extends StrictEntityBase<ID>{

    private String username;
    private String passwordEncode;
    private StrictEntityRoleuser roleuser;
    private String token;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictEntityUser() {
        super();
        this.username = null;
        this.passwordEncode = null;
        this.roleuser = null;
        this.token = null;
    }

    public StrictEntityUser(String username, String passwordEncode, StrictEntityRoleuser roleuser, String token) {
        super();
        this.username = username;
        this.passwordEncode = passwordEncode;
        this.roleuser = roleuser;
        this.token = token;
    }

    public StrictEntityUser(ID id, String username, String passwordEncode, StrictEntityRoleuser roleuser, String token) {
        super(id);
        this.username = username;
        this.passwordEncode = passwordEncode;
        this.roleuser = roleuser;
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
        return String.format("%s: %s (%s). Token: %s; Password: %s",
                super.toString(), username, roleuser.getSymbols(), token, passwordEncode);
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof StrictEntityUser) {
            StrictEntityUser entity = (StrictEntityUser) obj;
            return super.equals(entity) && username.equals(entity.getUsername())
                    && passwordEncode.equals(entity.getPasswordEncode()) && roleuser.equals(entity.getRoleuser())
                    && token.equals(entity.getToken());
        }else
            return false;
    }
    //</editor-fold>
}
