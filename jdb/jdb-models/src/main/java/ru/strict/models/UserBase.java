package ru.strict.models;

import java.util.Collection;
import java.util.Objects;
import java.util.TreeSet;

import ru.strict.validates.ValidateBaseValue;

/**
 * Базовая информация о пользователе (логин, роли, профиль)
 */
public class UserBase<ID> extends DtoBase<ID> {

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
    private Collection<Roleuser<ID>> roles;
    /**
     * Профили пользователя
     */
    private Collection<Profile<ID>> profiles;

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

    public UserBase() {
        super();
        username = null;
        email = null;
        isBlocked = false;
        isDeleted = false;
        isConfirmEmail = false;
        roles = new TreeSet<>();
        profiles = new TreeSet<>();
    }

    public UserBase(String username, String email) {
        super();
        initialize(username, email);
    }

    public UserBase(ID id, String username, String email) {
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

    public Collection<Roleuser<ID>> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Roleuser<ID>> roles) {
        if(roles == null) {
            throw new IllegalArgumentException("roles is NULL");
        }

        for(Roleuser<ID> role : roles){
            role.addUserSafe(this);
        }

        this.roles = roles;
    }

    public void addRole(Roleuser<ID> role){
        addRole(role, true);
    }

    protected void addRoleSafe(Roleuser<ID> role){
        addRole(role, false);
    }

    private void addRole(Roleuser<ID> role, boolean isCircleMode){
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

    public void addRoles(Collection<Roleuser<ID>> roles){
        if(roles!=null) {
            for(Roleuser<ID> user : roles){
                addRole(user);
            }
        }
    }

    public Collection<Profile<ID>> getProfiles() {
        return profiles;
    }

    public void setProfiles(Collection<Profile<ID>> profiles) {
        if(profiles == null) {
            throw new IllegalArgumentException("profiles is NULL");
        }

        for(Profile<ID> profile : profiles){
            profile.setUser(this);
        }

        this.profiles = profiles;
    }

    public void addProfile(Profile<ID> profile){
        addProfile(profile, true);
    }

    protected void addProfileSafe(Profile<ID> profile){
        addProfile(profile, false);
    }

    private void addProfile(Profile<ID> profile, boolean isCircleMode){
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

    public void addProfiles(Collection<Profile<ID>> profiles){
        if(profiles!=null) {
            for(Profile<ID> profile : profiles){
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
        UserBase<ID> object = (UserBase<ID>) o;
        return isBlocked == object.isBlocked &&
                isDeleted == object.isDeleted &&
                isConfirmEmail == object.isConfirmEmail &&
                Objects.equals(username, object.username) &&
                Objects.equals(email, object.email) &&
                Objects.equals(roles, object.roles) &&
                Objects.equals(profiles, object.profiles);
    }

    @Override
    public int hashCode() {
        int rolesHashCode = 1;
        for(Roleuser<ID> role : roles){
            rolesHashCode = 31 * rolesHashCode + (role == null ? 0 : role.hashCodeWithoutUser());
        }

        int profilesHashCode = 1;
        for(Profile<ID> profile : profiles){
            profilesHashCode = 31 * profilesHashCode + (profile == null ? 0 : profile.hashCodeWithoutUser());
        }

        return Objects.hash(super.hashCode(), username, email, isBlocked, isDeleted, isConfirmEmail, rolesHashCode, profilesHashCode);
    }

    @Override
    public UserBase<ID> clone(){
        try {
            UserBase<ID> clone = (UserBase<ID>) super.clone();

            clone.roles = new TreeSet<>();
            clone.profiles = new TreeSet<>();
            for(Roleuser<ID> role : this.roles){
                clone.addRole(role.cloneSafeUser(clone));
            }
            for(Profile<ID> profile : this.profiles){
                clone.addProfile(profile.cloneSafeUser(clone));
            }
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}
