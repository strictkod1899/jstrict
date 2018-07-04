package ru.strict.db.core.dto;

import ru.strict.utils.UtilHashCode;

/**
 * Связка пользователя с ролью
 */
public class DtoUserOnRole<ID> extends DtoBase<ID> {

    /**
     * Идентификатор пользователя
     */
    private ID userId;
    /**
     * Пользователь
     */
    private DtoUser user;
    /**
     * Идентификатор роли
     */
    private ID roleId;
    /**
     * Роль пользователя
     */
    private DtoRoleuser role;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public DtoUserOnRole() {
        super();
        userId = null;
        user = null;
        roleId = null;
        role = null;
    }

    public DtoUserOnRole(ID userId, ID roleId) {
        super();
        this.userId = userId;
        user = null;
        this.roleId = roleId;
        role = null;
    }

    public DtoUserOnRole(ID id, ID userId, ID roleId) {
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
        if(userId == null) {
            throw new NullPointerException();
        }

        this.userId = userId;
    }

    public DtoUser getUser() {
        return user;
    }

    public void setUser(DtoUser user) {
        this.user = user;
    }

    public ID getRoleId() {
        return roleId;
    }

    public void setRoleId(ID roleId) {
        if(roleId == null) {
            throw new NullPointerException();
        }

        this.roleId = roleId;
    }

    public DtoRoleuser getRole() {
        return role;
    }

    public void setRole(DtoRoleuser role) {
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
        if(obj!=null && obj instanceof DtoUserOnRole) {
            DtoUserOnRole object = (DtoUserOnRole) obj;
            return super.equals(object) && userId.equals(object.getUserId()) && roleId.equals(object.getRoleId())
                    && user.equals(object.getUser()) && role.equals(object.getRole());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, userId, roleId, user, role);
    }
    //</editor-fold>
}
