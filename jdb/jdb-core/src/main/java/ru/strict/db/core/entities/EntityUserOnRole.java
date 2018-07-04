package ru.strict.db.core.entities;

import ru.strict.utils.UtilHashCode;

/**
 * Связка пользователя с ролью
 */
public class EntityUserOnRole<ID> extends EntityBase<ID> {

    /**
     * Идентификатор пользователя
     */
    private ID userId;
    /**
     * Пользователь
     */
    private EntityUser user;
    /**
     * Идентификатор роли
     */
    private ID roleId;
    /**
     * Роль пользователя
     */
    private EntityRoleuser role;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(ID userId, ID roleId){
        if(userId == null) {
            throw new NullPointerException("userId is NULL");
        } else if(roleId == null) {
            throw new NullPointerException("roleId is NULL");
        }

        this.userId = userId;
        user = null;
        this.roleId = roleId;
        role = null;
    }

    public EntityUserOnRole() {
        super();
        userId = null;
        user = null;
        roleId = null;
        role = null;
    }

    public EntityUserOnRole(ID userId, ID roleId) {
        super();
        initialize(userId, roleId);
    }

    public EntityUserOnRole(ID id, ID userId, ID roleId) {
        super(id);
        initialize(userId, roleId);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public ID getUserId() {
        return userId;
    }

    public void setUserId(ID userId) {
        if(userId == null) {
            throw new NullPointerException("userId is NULL");
        }

        this.userId = userId;
    }

    public EntityUser getUser() {
        return user;
    }

    public void setUser(EntityUser user) {
        this.user = user;
    }

    public ID getRoleId() {
        return roleId;
    }

    public void setRoleId(ID roleId) {
        if(roleId == null) {
            throw new NullPointerException("roleId is NULL");
        }

        this.roleId = roleId;
    }

    public EntityRoleuser getRole() {
        return role;
    }

    public void setRole(EntityRoleuser role) {
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
        if(obj!=null && obj instanceof EntityUserOnRole) {
            EntityUserOnRole object = (EntityUserOnRole) obj;
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
