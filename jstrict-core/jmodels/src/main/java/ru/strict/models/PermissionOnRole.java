package ru.strict.models;

import java.util.Objects;

/**
 * Связка права доступа с ролью
 */
public class PermissionOnRole<ID, PERMISSION> extends ModelBase<ID> {

    /**
     * Идентификатор права доступа
     */
    private Integer permissionId;
    /**
     * Объект, который определяет право доступа по Id
     */
    private IModelProvider<PERMISSION> permissionProvider;
    /**
     * Идентификатор роли
     */
    private ID roleId;
    /**
     * Роль пользователя
     */
    private Role<ID> role;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void init(Integer permissionId, ID roleId){
        if(permissionId == null) {
            throw new IllegalArgumentException("permissionId is NULL");
        } else if(roleId == null) {
            throw new IllegalArgumentException("roleId is NULL");
        }

        this.permissionId = permissionId;
        permissionProvider = null;
        this.roleId = roleId;
        role = null;
    }

    public PermissionOnRole() {
        super();
        permissionId = null;
        roleId = null;
        role = null;
    }

    public PermissionOnRole(Integer permissionId, ID roleId) {
        super();
        init(permissionId, roleId);
    }

    public PermissionOnRole(ID id, Integer permissionId, ID roleId) {
        super(id);
        init(permissionId, roleId);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public IModelProvider<PERMISSION> getPermissionProvider() {
        return permissionProvider;
    }

    public void setPermissionProvider(IModelProvider<PERMISSION> permissionProvider) {
        this.permissionProvider = permissionProvider;
    }

    public PERMISSION getPermission() {
        return permissionProvider == null ? null : permissionProvider.getById(permissionId);
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
        return String.format("permissionOnRole [%s]. permission: %s. role: %s.", String.valueOf(getId()), permissionId, roleId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PermissionOnRole<ID, PERMISSION> object = (PermissionOnRole<ID, PERMISSION>) o;
        return Objects.equals(permissionId, object.permissionId) &&
                Objects.equals(permissionProvider, object.permissionProvider) &&
                Objects.equals(roleId, object.roleId) &&
                Objects.equals(role, object.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), permissionId, permissionProvider, roleId, role);
    }

    @Override
    public PermissionOnRole<ID, PERMISSION> clone() {
        try {
            PermissionOnRole<ID, PERMISSION> clone = (PermissionOnRole<ID, PERMISSION>) super.clone();

            clone.setRole(role == null ? null : role.clone());
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}
