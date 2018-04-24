package ru.strict.db.dto;

public class StrictDtoUserBase<ID> extends StrictDtoBase<ID>{

    private String username;
    private ID roleuserId;
    private StrictDtoRoleuser roleuser;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictDtoUserBase() {
        super();
        this.username = null;
        this.roleuserId = null;
        this.roleuser = null;
    }

    public StrictDtoUserBase(String username, ID roleuserId) {
        super();
        this.username = username;
        this.roleuserId = roleuserId;
        this.roleuser = null;
    }

    public StrictDtoUserBase(ID id, String username, ID roleuserId) {
        super(id);
        this.username = username;
        this.roleuserId = roleuserId;
        this.roleuser = null;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ID getRoleuserId() {
        return roleuserId;
    }

    public void setRoleuserId(ID roleuserId) {
        this.roleuserId = roleuserId;
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
        return String.format("dto[%s]: %s (role: %s)", String.valueOf(getId()), username, roleuserId);
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof StrictDtoUserBase) {
            StrictDtoUserBase entity = (StrictDtoUserBase) obj;
            return super.equals(entity) && username.equals(entity.getUsername()) && roleuser.equals(entity.getRoleuser())
                    && roleuserId.equals(entity.getRoleuserId());
        }else
            return false;
    }
    //</editor-fold>
}
