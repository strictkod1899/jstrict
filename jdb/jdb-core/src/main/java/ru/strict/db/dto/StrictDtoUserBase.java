package ru.strict.db.dto;

import java.util.Collection;
import java.util.LinkedList;
import ru.strict.utils.StrictUtilHashCode;

/**
 * Базовая информация о пользователе (логин, роли, профиль)
 */
public class StrictDtoUserBase<ID> extends StrictDtoBase<ID>{

    /**
     * Логин пользователя
     */
    private String username;
    /**
     * Роли пользователя
     */
    private Collection<StrictDtoRoleuser> rolesuser;
    /**
     * Профиль пользователя
     */
    private StrictDtoProfile profile;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictDtoUserBase() {
        super();
        username = null;
        rolesuser = new LinkedList<>();
        profile = null;
    }

    public StrictDtoUserBase(String username) {
        super();
        this.username = username;
        rolesuser = new LinkedList<>();
        profile = null;
    }

    public StrictDtoUserBase(ID id, String username) {
        super(id);
        this.username = username;
        rolesuser = new LinkedList<>();
        profile = null;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<StrictDtoRoleuser> getRolesuser() {
        return rolesuser;
    }

    /**
     * Добавить роль, которую использует данный пользователь
     * @param roleuser
     */
    public void addRoleuser(StrictDtoRoleuser roleuser){
        rolesuser.add(roleuser);
    }

    public void setRolesuser(Collection<StrictDtoRoleuser> rolesuser) {
        this.rolesuser = rolesuser;
    }

    public StrictDtoProfile getProfile() {
        return profile;
    }

    public void setProfile(StrictDtoProfile profile) {
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
        if(obj instanceof StrictDtoUserBase) {
            StrictDtoUserBase object = (StrictDtoUserBase) obj;
            return super.equals(object) && username.equals(object.getUsername())
                    && (rolesuser.size() == object.getRolesuser().size() && rolesuser.containsAll(object.getRolesuser()))
                    && profile.equals(object.getProfile());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return StrictUtilHashCode.createSubHashCode(superHashCode, username, rolesuser, profile);
    }
    //</editor-fold>
}
