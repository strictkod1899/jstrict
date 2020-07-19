package ru.strict.models;

import ru.strict.patterns.model.BaseModel;
import ru.strict.patterns.model.IModel;
import ru.strict.validate.Validator;

import java.util.Objects;

/**
 * Связка права доступа с ролью
 */
public class PermissionOnRole<ID, PERMISSION extends IModel<Integer>> extends BaseModel<ID> {

    /**
     * Идентификатор права доступа
     */
    private Integer permissionId;
    /**
     * Объект, который определяет право доступа по Id.
     * Предполагается, что класс Permission будет enum и у него будет метод getById.
     * Тогда объект permissionProvider сможет возвращать объект Permission
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
    private void init(Integer permissionId, ID roleId) {
        Validator.isNull(permissionId, "permissionId").onThrow();
        Validator.isNull(roleId, "roleId").onThrow();

        this.permissionId = permissionId;
        this.roleId = roleId;
    }

    public PermissionOnRole() {
        super();
    }

    public PermissionOnRole(Integer permissionId, ID roleId) {
        super();
        init(permissionId, roleId);
    }

    public PermissionOnRole(ID id, Integer permissionId, ID roleId) {
        super(id);
        init(permissionId, roleId);
    }

    public PermissionOnRole(Integer permissionId, ID roleId, IModelProvider<PERMISSION> permissionProvider) {
        super();
        init(permissionId, roleId);
        this.permissionProvider = permissionProvider;
    }

    public PermissionOnRole(ID id, Integer permissionId, ID roleId, IModelProvider<PERMISSION> permissionProvider) {
        super(id);
        init(permissionId, roleId);
        this.permissionProvider = permissionProvider;
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
    public String toString() {
        return String.format("permissionOnRole [%s]. permission: %s. role: %s.",
                String.valueOf(getId()),
                permissionId,
                roleId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PermissionOnRole<ID, PERMISSION> object = (PermissionOnRole<ID, PERMISSION>) o;
        return Objects.equals(permissionId, object.permissionId) &&
                Objects.equals(getPermission(), object.getPermission()) &&
                Objects.equals(roleId, object.roleId) &&
                Objects.equals(role, object.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissionId, getPermission(), roleId, role);
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
