package ru.strict.db.core.dto;

import java.util.Collection;
import java.util.LinkedList;
import ru.strict.utils.UtilHashCode;

/**
 * Базовая информация о пользователе (логин, роли, профиль)
 */
public class DtoUserBase<ID> extends DtoBase<ID> {

    /**
     * Логин пользователя
     */
    private String username;
    /**
     * Роли пользователя
     */
    private Collection<DtoRoleuser> rolesuser;
    /**
     * Профиль пользователя
     */
    private DtoProfile profile;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(String username){
        if(username == null) {
            throw new NullPointerException("username is NULL");
        }

        this.username = username;
        rolesuser = new LinkedList<>();
        profile = null;
    }

    public DtoUserBase() {
        super();
        username = null;
        rolesuser = new LinkedList<>();
        profile = null;
    }

    public DtoUserBase(String username) {
        super();
        initialize(username);
    }

    public DtoUserBase(ID id, String username) {
        super(id);
        initialize(username);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if(username == null) {
            throw new NullPointerException("username is NULL");
        }

        this.username = username;
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
                    && (rolesuser.size() == object.getRolesuser().size() && rolesuser.containsAll(object.getRolesuser()))
                    && profile.equals(object.getProfile());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, username, rolesuser, profile);
    }
    //</editor-fold>
}
