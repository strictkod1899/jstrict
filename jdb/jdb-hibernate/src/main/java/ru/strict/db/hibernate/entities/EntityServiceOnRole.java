package ru.strict.db.hibernate.entities;

import ru.strict.db.core.models.IModelProvider;

import javax.persistence.*;
import java.util.Objects;

/**
 * Связка сервиса с ролью
 */
public class EntityServiceOnRole<SERVICE> extends EntityBase<Long> {

    /**
     * Идентификатор сервиса
     */
    @Column(name = "service_id", nullable = false)
    private Integer serviceId;
    /**
     * Объект, который определяет сервис по Id
     */
    private IModelProvider<SERVICE> serviceProvider;
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
    private void initialize(Integer serviceId, Long roleId){
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

    public EntityServiceOnRole() {
        super();
        serviceId = null;
        roleId = null;
        role = null;
    }

    public EntityServiceOnRole(Integer serviceId, Long roleId) {
        super();
        initialize(serviceId, roleId);
    }

    public EntityServiceOnRole(Long id, Integer serviceId, Long roleId) {
        super(id);
        initialize(serviceId, roleId);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        if(serviceId == null) {
            throw new NullPointerException("userId is NULL");
        }

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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
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
        return String.format("serviceOnRole [%s]. service: %s. role: %s.", String.valueOf(getId()), serviceId, roleId);
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof EntityServiceOnRole) {
            EntityServiceOnRole object = (EntityServiceOnRole) obj;
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
