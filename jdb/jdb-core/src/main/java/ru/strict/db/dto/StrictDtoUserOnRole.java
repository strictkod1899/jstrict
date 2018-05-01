package ru.strict.db.dto;

/**
 * Связка пользователя с ролью
 */
public class StrictDtoUserOnRole<ID> extends StrictDtoBase<ID> {

    private ID userId;
    private StrictDtoUser user;
    private ID roleId;
    private StrictDtoRoleuser role;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictDtoUserOnRole() {
        super();
        userId = null;
        user = null;
        roleId = null;
        role = null;
    }

    public StrictDtoUserOnRole(ID userId, ID roleId) {
        super();
        this.userId = userId;
        user = null;
        this.roleId = roleId;
        role = null;
    }

    public StrictDtoUserOnRole(ID id, ID userId, ID roleId) {
        super(id);
        this.userId = userId;
        user = null;
        this.roleId = roleId;
        role = null;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public ID getUserId() {
        return userId;
    }

    public void setUserId(ID userId) {
        this.userId = userId;
    }

    public StrictDtoUser getUser() {
        return user;
    }

    public void setUser(StrictDtoUser user) {
        this.user = user;
    }

    public ID getRoleId() {
        return roleId;
    }

    public void setRoleId(ID roleId) {
        this.roleId = roleId;
    }

    public StrictDtoRoleuser getRole() {
        return role;
    }

    public void setRole(StrictDtoRoleuser role) {
        this.role = role;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("dto useronrole [%s]. user: %s. role: %s.", String.valueOf(getId()), userId, roleId);
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof StrictDtoUserOnRole) {
            StrictDtoUserOnRole dto = (StrictDtoUserOnRole) obj;
            return super.equals(dto) && userId.equals(dto.getUserId()) && roleId.equals(dto.getRoleId())
                    && user.equals(dto.getUser()) && role.equals(dto.getRole());
        }else
            return false;
    }
    //</editor-fold>
}