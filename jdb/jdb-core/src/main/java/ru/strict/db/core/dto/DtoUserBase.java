package ru.strict.db.core.dto;

import java.util.Collection;
import java.util.LinkedList;
import ru.strict.utils.UtilHashCode;
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
    private Collection<DtoRoleuser> rolesuser;
    /**
     * Профиль пользователя
     */
    private DtoProfile profile;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(String username, String email){
        if(!ValidateBaseValue.isNotEmptyOrNull(username)) {
            throw new NullPointerException("username is NULL");
        } else if(!ValidateBaseValue.isNotEmptyOrNull(email)) {
            throw new NullPointerException("email is NULL");
        }

        this.username = username;
        this.email = email;
        isBlocked = false;
        isDeleted = false;
        isConfirmEmail = false;
        rolesuser = new LinkedList<>();
        profile = null;
    }

    public DtoUserBase() {
        super();
        username = null;
        email = null;
        isBlocked = false;
        isDeleted = false;
        isConfirmEmail = false;
        rolesuser = new LinkedList<>();
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
        if(!ValidateBaseValue.isNotEmptyOrNull(username)) {
            throw new NullPointerException("username is NULL");
        }

        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(!ValidateBaseValue.isNotEmptyOrNull(email)) {
            throw new NullPointerException("email is NULL");
        }

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

    public Collection<DtoRoleuser> getRolesuser() {
        return rolesuser;
    }

    /**
     * Добавить роль, которую использует данный пользователь
     * @param roleuser
     */
    public void addRoleuser(DtoRoleuser roleuser){
        if(roleuser == null) {
            throw new NullPointerException("roleuser is NULL");
        }

        if(rolesuser!=null) {
            rolesuser.add(roleuser);
        }
    }

    public void setRolesuser(Collection<DtoRoleuser> rolesuser) {
        if(rolesuser == null) {
            throw new NullPointerException("rolesuser is NULL");
        }

        this.rolesuser = rolesuser;
    }

    public DtoProfile getProfile() {
        return profile;
    }

    public void setProfile(DtoProfile profile) {
        this.profile = profile;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("dto userbase [%s]: %s", String.valueOf(getId()), username);
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof DtoUserBase) {
            DtoUserBase object = (DtoUserBase) obj;
            return super.equals(object) && username.equals(object.getUsername())
                    && email.equals(object.getEmail())
                    && isBlocked == object.isBlocked()
                    && isDeleted == object.isDeleted()
                    && isConfirmEmail == object.isConfirmEmail()
                    && (rolesuser.size() == object.getRolesuser().size() && rolesuser.containsAll(object.getRolesuser()))
                    && profile.equals(object.getProfile());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, username, email, isBlocked, isDeleted,
                isConfirmEmail, rolesuser, profile);
    }
    //</editor-fold>
}
