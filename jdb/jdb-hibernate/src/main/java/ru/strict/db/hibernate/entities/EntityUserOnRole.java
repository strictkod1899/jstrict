package ru.strict.db.hibernate.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * Связка пользователя с ролью
 */
@Entity
@Table(name = "user_on_role")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class EntityUserOnRole extends EntityBase<Long> {

    /**
     * Идентификатор пользователя
     */
    @Column(name = "userx_id", nullable = false)
    private Long userId;
    /**
     * Пользователь
     */
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "userx_id", insertable = false, updatable = false)
    private EntityUser user;
    /**
     * Идентификатор роли
     */
    @Column(name = "roleuser_id", nullable = false)
    private Long roleId;
    /**
     * Роль пользователя
     */
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "roleuser_id", insertable = false, updatable = false)
    private EntityRoleuser role;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(Long userId, Long roleId){
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

    public EntityUserOnRole() {
        super();
        userId = null;
        user = null;
        roleId = null;
        role = null;
    }

    public EntityUserOnRole(Long userId, Long roleId) {
        super();
        initialize(userId, roleId);
    }

    public EntityUserOnRole(Long id, Long userId, Long roleId) {
        super(id);
        initialize(userId, roleId);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public EntityUser getUser() {
        return user;
    }

    public void setUser(EntityUser user) {
        this.user = user;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EntityUserOnRole object = (EntityUserOnRole) o;
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
    public EntityUserOnRole clone(){
        try {
            EntityUserOnRole clone = (EntityUserOnRole) super.clone();

            clone.setUser(user == null ? null : user.clone());
            clone.setRole(role == null ? null : role.clone());
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}
