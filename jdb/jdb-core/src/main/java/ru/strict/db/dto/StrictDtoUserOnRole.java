package ru.strict.db.dto;

import ru.strict.utils.StrictUtilHashCode;

/**
 * Связка пользователя с ролью
 */
public class StrictDtoUserOnRole<ID> extends StrictDtoBase<ID> {

    /**
     * Идентификатор пользователя
     */
    private ID userId;
    /**
     * Пользователь
     */
    private StrictDtoUser user;
    /**
     * Идентификатор роли
     */
    private ID roleId;
    /**
     * Роль пользователя
     */
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
        if(obj!=null && obj instanceof StrictDtoUserOnRole) {
            StrictDtoUserOnRole object = (StrictDtoUserOnRole) obj;
            return super.equals(object) && userId.equals(object.getUserId()) && roleId.equals(object.getRoleId())
                    && user.equals(object.getUser()) && role.equals(object.getRole());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return StrictUtilHashCode.createSubHashCode(superHashCode, userId, roleId, user, role);
    }
    //</editor-fold>
}
