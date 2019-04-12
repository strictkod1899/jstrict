package ru.strict.db.core.dto;

import java.util.Collection;
import java.util.Objects;
import java.util.TreeSet;

import ru.strict.validates.ValidateBaseValue;

/**
 * Базовая информация о пользователе (логин, роли, профиль)
 */
public class DtoUserBase<ID> extends DtoBase<ID> {

    /**
     * Логин пользователя
     */
    private String username;
    /**
     * Адрес электронной почты
     */
    private String email;
    /**
     * Пользователь заблокирован
     */
    private boolean isBlocked;
    /**
     * Пользователь удален
     */
    private boolean isDeleted;
    /**
     * Адрес электронной почты подтвержден
     */
    private boolean isConfirmEmail;
    /**
     * Роли пользователя
     */
    private Collection<DtoRoleuser<ID>> roles;
    /**
     * Профиль пользователя
     */
    private DtoProfile<ID> profile;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(String username, String email){
        if(ValidateBaseValue.isEmptyOrNull(username)) {
            throw new IllegalArgumentException("username is NULL");
        } else if(ValidateBaseValue.isEmptyOrNull(email)) {
            throw new IllegalArgumentException("email is NULL");
        }

        this.username = username;
        this.email = email;
        isBlocked = false;
        isDeleted = false;
        isConfirmEmail = false;
        roles = new TreeSet<>();
        profile = null;
    }

    public DtoUserBase() {
        super();
        username = null;
        email = null;
        isBlocked = false;
        isDeleted = false;
        isConfirmEmail = false;
        roles = new TreeSet<>();
        profile = null;
    }

    public DtoUserBase(String username, String email) {
        super();
        initialize(username, email);
    }

    public DtoUserBase(ID id, String username, String email) {
        super(id);
        initialize(username, email);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isConfirmEmail() {
        return isConfirmEmail;
    }

    public void setConfirmEmail(boolean confirmEmail) {
        isConfirmEmail = confirmEmail;
    }

    public Collection<DtoRoleuser<ID>> getRoles() {
        return roles;
    }

    public void setRoles(Collection<DtoRoleuser<ID>> roles) {
        if(roles == null) {
            throw new IllegalArgumentException("roles is NULL");
        }

        for(DtoRoleuser<ID> role : roles){
            role.addUserSafe(this);
        }

        this.roles = roles;
    }

    public void addRole(DtoRoleuser<ID> role){
        addRole(role, true);
    }

    protected void addRoleSafe(DtoRoleuser<ID> role){
        addRole(role, false);
    }

    private void addRole(DtoRoleuser<ID> role, boolean isCircleMode){
        if(role == null) {
            throw new IllegalArgumentException("role is NULL");
        }

        if(role != null){
            if(isCircleMode) {
                role.addUserSafe(this);
            }
            roles.add(role);
        }
    }

    public void addRoles(Collection<DtoRoleuser<ID>> roles){
        if(roles!=null) {
            for(DtoRoleuser<ID> user : roles){
                addRole(user);
            }
        }
    }

    public DtoProfile<ID> getProfile() {
        return profile;
    }

    public void setProfile(DtoProfile<ID> profile) {
        setProfile(profile, true);
    }

    protected void setProfileSafe(DtoProfile<ID> profile) {
        setProfile(profile, false);
    }

    private void setProfile(DtoProfile<ID> profile, boolean isCircleMode) {
        if(isCircleMode && profile != null){
            profile.setUserSafe(this);
        }
        this.profile = profile;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("user [%s]: %s", String.valueOf(getId()), getUsername());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DtoUserBase<ID> that = (DtoUserBase<ID>) o;
        return isBlocked == that.isBlocked &&
                isDeleted == that.isDeleted &&
                isConfirmEmail == that.isConfirmEmail &&
                Objects.equals(username, that.username) &&
                Objects.equals(email, that.email) &&
                Objects.equals(roles, that.roles) &&
                Objects.equals(profile, that.profile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, email, isBlocked, isDeleted, isConfirmEmail, roles, profile);
    }
    //</editor-fold>
}
