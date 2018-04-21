package ru.strict.db.dto;

public class StrictDtoUserBase<ID> extends StrictDtoBase<ID>{

    private String username;
    private StrictDtoRoleuser roleuser;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictDtoUserBase() {
        super();
        this.username = null;
        this.roleuser = null;
    }

    public StrictDtoUserBase(String username, StrictDtoRoleuser roleuser) {
        super();
        this.username = username;
        this.roleuser = roleuser;
    }

    public StrictDtoUserBase(ID id, String username, StrictDtoRoleuser roleuser) {
        super(id);
        this.username = username;
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
        return String.format("%s: %s (%s)", super.toString(), username, roleuser.getSymbols());
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof StrictDtoUserBase) {
            StrictDtoUserBase entity = (StrictDtoUserBase) obj;
            return super.equals(entity) && username.equals(entity.getUsername()) && roleuser.equals(entity.getRoleuser());
        }else
            return false;
    }
    //</editor-fold>
}
