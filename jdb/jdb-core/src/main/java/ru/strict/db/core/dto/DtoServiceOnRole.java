package ru.strict.db.core.dto;

import ru.strict.db.core.models.IEnumProvider;

import java.util.Objects;

/**
 * Связка сервиса с ролью
 */
public class DtoServiceOnRole<ID, SERVICE> extends DtoBase<ID> {

    /**
     * Идентификатор сервиса
     */
    private ID serviceId;
    /**
     * Объект, который определяет сервис по Id
     */
    private IEnumProvider<ID, SERVICE> serviceProvider;
    /**
     * Идентификатор роли
     */
    private ID roleId;
    /**
     * Роль пользователя
     */
    private DtoRoleuser<ID> role;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(ID serviceId, ID roleId){
        if(serviceId == null) {
            throw new NullPointerException("serviceId is NULL");
        } else if(roleId == null) {
            throw new NullPointerException("roleId is NULL");
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

    public DtoServiceOnRole(ID serviceId, ID roleId) {
        super();
        initialize(serviceId, roleId);
    }

    public DtoServiceOnRole(ID id, ID serviceId, ID roleId) {
        super(id);
        initialize(serviceId, roleId);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public ID getServiceId() {
        return serviceId;
    }

    public void setServiceId(ID serviceId) {
        if(serviceId == null) {
            throw new NullPointerException("userId is NULL");
        }

        this.serviceId = serviceId;
    }

    public IEnumProvider<ID, SERVICE> getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(IEnumProvider<ID, SERVICE> serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public SERVICE getService() {
        return serviceProvider == null ? null : serviceProvider.getById(serviceId);
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
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof DtoServiceOnRole) {
            DtoServiceOnRole object = (DtoServiceOnRole) obj;
            return super.equals(obj) && Objects.equals(serviceId, object.serviceId)
                    && Objects.equals(serviceProvider, object.serviceProvider)
                    && Objects.equals(getService(), object.getService())
                    && Objects.equals(roleId, object.roleId)
                    && Objects.equals(role, object.role);
        }else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId(), serviceId, serviceProvider, getService(), roleId, role);
    }
    //</editor-fold>
}
