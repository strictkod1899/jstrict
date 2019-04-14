package ru.strict.db.core.dto;

import java.util.Objects;

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
    private DtoUser<ID> user;
    /**
     * Идентификатор роли
     */
    private ID roleId;
    /**
     * Роль пользователя
     */
    private DtoRoleuser<ID> role;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(ID userId, ID roleId){
        if(userId == null) {
            throw new IllegalArgumentException("userId is NULL");
        } else if(roleId == null) {
            throw new IllegalArgumentException("roleId is NULL");
        }

        this.userId = userId;
        user = null;
        this.roleId = roleId;
        role = null;
    }

    public DtoUserOnRole() {
        super();
        userId = null;
        user = null;
        roleId = null;
        role = null;
    }

    public DtoUserOnRole(ID userId, ID roleId) {
        super();
        initialize(userId, roleId);
    }

    public DtoUserOnRole(ID id, ID userId, ID roleId) {
        super(id);
        initialize(userId, roleId);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public ID getUserId() {
        return userId;
    }

    public void setUserId(ID userId) {
        this.userId = userId;
    }

    public DtoUser<ID> getUser() {
        return user;
    }

    public void setUser(DtoUser<ID> user) {
        this.user = user;
    }

    public ID getRoleId() {
        return roleId;
    }

    public void setRoleId(ID roleId) {
        this.roleId = roleId;
    }

    public DtoRoleuser<ID> getRole() {
        return role;
    }

    public void setRole(DtoRoleuser<ID> role) {
        this.role = role;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("userOnRole [%s]. user: %s. role: %s.", String.valueOf(getId()), userId, roleId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DtoUserOnRole<ID> that = (DtoUserOnRole<ID>) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(user, that.user) &&
                Objects.equals(roleId, that.roleId) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, user, roleId, role);
    }

    @Override
    public DtoUserOnRole<ID> clone(){
        DtoUserOnRole<ID> clone  = new DtoUserOnRole<>();
        clone.setId(getId());
        clone.setUserId(userId);
        clone.setUser(user == null ? null : user.clone());
        clone.setRoleId(roleId);
        clone.setRole(role == null ? null : role.clone());
        return clone;
    }
    //</editor-fold>
}
