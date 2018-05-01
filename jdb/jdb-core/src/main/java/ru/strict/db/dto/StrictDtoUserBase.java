package ru.strict.db.dto;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Базовая информация о пользователе системы (логин, роль)
 */
public class StrictDtoUserBase<ID> extends StrictDtoBase<ID>{

    private String username;
    private Collection<StrictDtoRoleuser> rolesuser;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictDtoUserBase() {
        super();
        this.username = null;
        this.rolesuser = new LinkedList<>();
    }

    public StrictDtoUserBase(String username) {
        super();
        this.username = username;
        this.rolesuser = new LinkedList<>();
    }

    public StrictDtoUserBase(ID id, String username) {
        super(id);
        this.username = username;
        this.rolesuser = new LinkedList<>();
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

    public void addRoleuser(StrictDtoRoleuser roleuser){
        rolesuser.add(roleuser);
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
            StrictDtoUserBase dto = (StrictDtoUserBase) obj;
            return super.equals(dto) && username.equals(dto.getUsername())
                    && (rolesuser.size() == dto.getRolesuser().size() && rolesuser.containsAll(dto.getRolesuser()));
        }else
            return false;
    }
    //</editor-fold>
}
