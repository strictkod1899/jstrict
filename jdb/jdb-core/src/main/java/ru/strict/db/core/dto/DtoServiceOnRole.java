package ru.strict.db.core.dto;

import ru.strict.db.core.models.IModelProvider;

import java.util.Objects;

/**
 * Связка сервиса с ролью
 */
public class DtoServiceOnRole<ID, SERVICE> extends DtoBase<ID> {

    /**
     * Идентификатор сервиса
     */
    private Integer serviceId;
    /**
     * Объект, который определяет сервис по Id
     */
    private IModelProvider<SERVICE> serviceProvider;
    /**
     * Идентификатор роли
     */
    private ID roleId;
    /**
     * Роль пользователя
     */
    private DtoRoleuser<ID> role;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(Integer serviceId, ID roleId){
        if(serviceId == null) {
            throw new IllegalArgumentException("serviceId is NULL");
        } else if(roleId == null) {
            throw new IllegalArgumentException("roleId is NULL");
        }

        this.serviceId = serviceId;
        serviceProvider = null;
        this.roleId = roleId;
        role = null;
    }

    public DtoServiceOnRole() {
        super();
        serviceId = null;
        roleId = null;
        role = null;
    }

    public DtoServiceOnRole(Integer serviceId, ID roleId) {
        super();
        initialize(serviceId, roleId);
    }

    public DtoServiceOnRole(ID id, Integer serviceId, ID roleId) {
        super(id);
        initialize(serviceId, roleId);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public IModelProvider<SERVICE> getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(IModelProvider<SERVICE> serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public SERVICE getService() {
        return serviceProvider == null ? null : serviceProvider.getById(serviceId);
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
        return String.format("serviceOnRole [%s]. service: %s. role: %s.", String.valueOf(getId()), serviceId, roleId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DtoServiceOnRole<ID, SERVICE> that = (DtoServiceOnRole<ID, SERVICE>) o;
        return Objects.equals(serviceId, that.serviceId) &&
                Objects.equals(serviceProvider, that.serviceProvider) &&
                Objects.equals(roleId, that.roleId) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), serviceId, serviceProvider, roleId, role);
    }

    @Override
    public DtoServiceOnRole<ID, SERVICE> clone(){
        DtoServiceOnRole<ID, SERVICE> clone = new DtoServiceOnRole<>();
        clone.setId(getId());
        clone.setServiceId(serviceId);
        clone.setServiceProvider(serviceProvider);
        clone.setRoleId(roleId);
        clone.setRole(role == null ? null : role.clone());
        return clone;
    }
    //</editor-fold>
}
