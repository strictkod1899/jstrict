package ru.strict.db.entities;

import ru.strict.utils.StrictUtilHashCode;

/**
 * Связка пользователя с ролью
 */
public class StrictEntityUserOnRole<ID> extends StrictEntityBase<ID> {

    /**
     * Идентификатор пользователя
     */
    private ID userId;
    /**
     * Пользователь
     */
    private StrictEntityUser user;
    /**
     * Идентификатор роли
     */
    private ID roleId;
    /**
     * Роль пользователя
     */
    private StrictEntityRoleuser role;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictEntityUserOnRole() {
        super();
        userId = null;
        user = null;
        roleId = null;
        role = null;
    }

    public StrictEntityUserOnRole(ID userId, ID roleId) {
        super();
        this.userId = userId;
        user = null;
        this.roleId = roleId;
        role = null;
    }

    public StrictEntityUserOnRole(ID id, ID userId, ID roleId) {
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

    public StrictEntityUser getUser() {
        return user;
    }

    public void setUser(StrictEntityUser user) {
        this.user = user;
    }

    public ID getRoleId() {
        return roleId;
    }

    public void setRoleId(ID roleId) {
        this.roleId = roleId;
    }

    public StrictEntityRoleuser getRole() {
        return role;
    }

    public void setRole(StrictEntityRoleuser role) {
        this.role = role;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("entity useronrole [%s]. user: %s. role: %s.", String.valueOf(getId()), userId, roleId);
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof StrictEntityUserOnRole) {
            StrictEntityUserOnRole object = (StrictEntityUserOnRole) obj;
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
