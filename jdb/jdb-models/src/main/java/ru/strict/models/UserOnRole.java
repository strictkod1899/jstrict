package ru.strict.models;

import java.util.Objects;

/**
 * Связка пользователя с ролью
 */
public class UserOnRole<ID> extends ModelBase<ID> {

    /**
     * Идентификатор пользователя
     */
    private ID userId;
    /**
     * Пользователь
     */
    private User<ID> user;
    /**
     * Идентификатор роли
     */
    private ID roleId;
    /**
     * Роль пользователя
     */
    private Role<ID> role;

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

    public UserOnRole() {
        super();
        userId = null;
        user = null;
        roleId = null;
        role = null;
    }

    public UserOnRole(ID userId, ID roleId) {
        super();
        initialize(userId, roleId);
    }

    public UserOnRole(ID id, ID userId, ID roleId) {
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

    public User<ID> getUser() {
        return user;
    }

    public void setUser(User<ID> user) {
        this.user = user;
    }

    public ID getRoleId() {
        return roleId;
    }

    public void setRoleId(ID roleId) {
        this.roleId = roleId;
    }

    public Role<ID> getRole() {
        return role;
    }

    public void setRole(Role<ID> role) {
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
        UserOnRole<ID> object = (UserOnRole<ID>) o;
        return Objects.equals(userId, object.userId) &&
                Objects.equals(user, object.user) &&
                Objects.equals(roleId, object.roleId) &&
                Objects.equals(role, object.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, user, roleId, role);
    }

    @Override
    public UserOnRole<ID> clone(){
        try {
            UserOnRole<ID> clone = (UserOnRole<ID>) super.clone();

            clone.setUser(user == null ? null : user.clone());
            clone.setRole(role == null ? null : role.clone());
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}
