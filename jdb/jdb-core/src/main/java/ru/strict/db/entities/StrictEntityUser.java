package ru.strict.db.entities;

public class StrictEntityUser<ID> extends StrictEntityBase<ID>{

    private String username;
    private String passwordmd5;
    private StrictEntityRoleuser roleuser;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictEntityUser() {
        super();
        this.username = null;
        this.passwordmd5 = null;
        this.roleuser = null;
    }

    public StrictEntityUser(String username, String passwordmd5, StrictEntityRoleuser roleuser) {
        super();
        this.username = username;
        this.passwordmd5 = passwordmd5;
        this.roleuser = roleuser;
    }

    public StrictEntityUser(ID id, String username, String passwordmd5, StrictEntityRoleuser roleuser) {
        super(id);
        this.username = username;
        this.passwordmd5 = passwordmd5;
        this.roleuser = roleuser;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordmd5() {
        return passwordmd5;
    }

    public void setPasswordmd5(String passwordmd5) {
        this.passwordmd5 = passwordmd5;
    }

    public StrictEntityRoleuser getRoleuser() {
        return roleuser;
    }

    public void setRoleuser(StrictEntityRoleuser roleuser) {
        this.roleuser = roleuser;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("%s: %s (%s). Password: %s", super.toString(), username, roleuser.getSymbols(), passwordmd5);
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof StrictEntityUser) {
            StrictEntityUser entity = (StrictEntityUser) obj;
            return super.equals(entity) && username.equals(entity.getUsername())
                    && passwordmd5.equals(entity.getPasswordmd5()) && roleuser.equals(entity.getRoleuser());
        }else
            return false;
    }
    //</editor-fold>
}
