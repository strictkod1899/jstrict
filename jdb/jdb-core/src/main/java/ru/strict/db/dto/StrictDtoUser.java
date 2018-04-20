package ru.strict.db.dto;

public class StrictDtoUser<ID> extends StrictDtoBase<ID>{

    private String username;
    private String passwordmd5;
    private StrictDtoRoleuser roleuser;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictDtoUser() {
        super();
        this.username = null;
        this.passwordmd5 = null;
        this.roleuser = null;
    }

    public StrictDtoUser(String username, String passwordmd5, StrictDtoRoleuser roleuser) {
        super();
        this.username = username;
        this.passwordmd5 = passwordmd5;
        this.roleuser = roleuser;
    }

    public StrictDtoUser(ID id, String username, String passwordmd5, StrictDtoRoleuser roleuser) {
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

    public StrictDtoRoleuser getRoleuser() {
        return roleuser;
    }

    public void setRoleuser(StrictDtoRoleuser roleuser) {
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
        if(obj instanceof StrictDtoUser) {
            StrictDtoUser entity = (StrictDtoUser) obj;
            return super.equals(entity) && username.equals(entity.getUsername())
                    && passwordmd5.equals(entity.getPasswordmd5()) && roleuser.equals(entity.getRoleuser());
        }else
            return false;
    }
    //</editor-fold>
}
