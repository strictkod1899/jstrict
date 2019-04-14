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
     * Профили пользователя
     */
    private Collection<DtoProfile<ID>> profiles;

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
        profiles = new TreeSet<>();
    }

    public DtoUserBase() {
        super();
        username = null;
        email = null;
        isBlocked = false;
        isDeleted = false;
        isConfirmEmail = false;
        roles = new TreeSet<>();
        profiles = new TreeSet<>();
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

    public Collection<DtoProfile<ID>> getProfiles() {
        return profiles;
    }

    public void setProfiles(Collection<DtoProfile<ID>> profiles) {
        if(profiles == null) {
            throw new IllegalArgumentException("profiles is NULL");
        }

        for(DtoProfile<ID> profile : profiles){
            profile.setUser(this);
        }

        this.profiles = profiles;
    }

    public void addProfile(DtoProfile<ID> profile){
        addProfile(profile, true);
    }

    protected void addProfileSafe(DtoProfile<ID> profile){
        addProfile(profile, false);
    }

    private void addProfile(DtoProfile<ID> profile, boolean isCircleMode){
        if(profile == null) {
            throw new IllegalArgumentException("profile is NULL");
        }

        if(profiles != null){
            if(isCircleMode) {
                profile.setUserSafe(this);
            }
            profiles.add(profile);
        }
    }

    public void addProfiles(Collection<DtoProfile<ID>> profiles){
        if(profiles!=null) {
            for(DtoProfile<ID> profile : profiles){
                addProfile(profile);
            }
        }
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
                Objects.equals(profiles, that.profiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, email, isBlocked, isDeleted, isConfirmEmail, roles, profiles);
    }

    @Override
    public DtoUserBase<ID> clone(){
        DtoUserBase<ID> clone = new DtoUserBase<>();

        clone.setId(getId());
        clone.setBlocked(isBlocked);
        clone.setDeleted(isDeleted);
        clone.setConfirmEmail(isConfirmEmail);
        clone.setUsername(username);
        clone.setEmail(email);
        for(DtoRoleuser<ID> role : roles){
            clone.addRole(role.clone());
        }
        for(DtoProfile<ID> profile : profiles){
            clone.addProfile(profile.clone());
        }
        return clone;
    }
    //</editor-fold>
}
