package ru.strict.db.hibernate.entities;

import ru.strict.utils.UtilHashCode;

import javax.persistence.*;
import java.util.UUID;

/**
 * Связка пользователя с ролью
 */
@Entity
@Table(name = "user_on_role")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class EntityUserOnRole extends EntityBase {

    /**
     * Идентификатор пользователя
     */
    @Column(name = "userx_id")
    private UUID userId;
    /**
     * Пользователь
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userx_id", insertable = false, updatable = false)
    private EntityUser user;
    /**
     * Идентификатор роли
     */
    @Column(name = "roleuser_id")
    private UUID roleId;
    /**
     * Роль пользователя
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "roleuser_id", insertable = false, updatable = false)
    private EntityRoleuser role;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(UUID userId, UUID roleId){
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

    public EntityUserOnRole(UUID userId, UUID roleId) {
        super();
        initialize(userId, roleId);
    }

    public EntityUserOnRole(UUID id, UUID userId, UUID roleId) {
        super(id);
        initialize(userId, roleId);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
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

    public UUID getRoleId() {
        return roleId;
    }

    public void setRoleId(UUID roleId) {
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