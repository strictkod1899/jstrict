package ru.strict.db.hibernate.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * Связка пользователя с ролью
 */
@Entity
@Table(name = "user_on_role")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class EntityUserOnRole<ID> extends EntityBase<ID> {

    /**
     * Идентификатор пользователя
     */
    @Column(name = "userx_id", nullable = false)
    private ID userId;
    /**
     * Пользователь
     */
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "userx_id", insertable = false, updatable = false)
    private EntityUser<ID> user;
    /**
     * Идентификатор роли
     */
    @Column(name = "roleuser_id", nullable = false)
    private ID roleId;
    /**
     * Роль пользователя
     */
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "roleuser_id", insertable = false, updatable = false)
    private EntityRoleuser<ID> role;

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

    public EntityUser<ID> getUser() {
        return user;
    }

    public void setUser(EntityUser<ID> user) {
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

    public EntityRoleuser<ID> getRole() {
        return role;
    }

    public void setRole(EntityRoleuser<ID> role) {
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
            return super.equals(obj) && Objects.equals(userId, object.getUserId())
                    && Objects.equals(user, object.getUser())
                    && Objects.equals(roleId, object.getRoleId())
                    && Objects.equals(role, object.getRole());
        }else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId(), userId, user, roleId, role);
    }
    //</editor-fold>
}
