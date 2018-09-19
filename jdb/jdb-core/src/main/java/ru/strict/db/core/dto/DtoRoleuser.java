package ru.strict.db.core.dto;

import java.util.Collection;
import java.util.TreeSet;
import ru.strict.utils.UtilHashCode;

/**
 * Роль пользователя в системе (например, администратор, пользователь, неавторизированный пользователь и др.)
 */
public class DtoRoleuser<ID> extends DtoBase<ID> {

    /**
     * Набор символов характеризующих роль
     */
    private String code;

    /**
     * Описание роли
     */
    private String description;

    /**
     * Пользователи свзяанные с ролью
     */
    private Collection<DtoUserBase<ID>> users;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(String code, String description){
        if(code == null) {
            throw new NullPointerException("code is NULL");
        }

        this.code = code;
        this.description = description;
        users = new TreeSet<>();
    }

    public DtoRoleuser() {
        super();
        code = null;
        description = null;
        users = new TreeSet<>();
    }

    public DtoRoleuser(String code, String description) {
        super();
        initialize(code, description);
    }

    public DtoRoleuser(ID id, String code, String description) {
        super(id);
        initialize(code, description);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if(code == null) {
            throw new NullPointerException("code is NULL");
        }

        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<DtoUserBase<ID>> getUsers() {
        return users;
    }

    public void setUsers(Collection<DtoUserBase<ID>> users) {
        if(users == null) {
            throw new NullPointerException();
        }

        for(DtoUserBase<ID> user : users){
            user.addRoleSafe(this);
        }

        this.users = users;
    }

    public void addUser(DtoUserBase<ID> user){
        addUser(user, true);
    }

    protected void addUserSafe(DtoUserBase<ID> user){
        addUser(user, false);
    }

    private void addUser(DtoUserBase<ID> user, boolean isCircleMode){
        if(user == null) {
            throw new NullPointerException();
        }

        if(user != null){
            if(isCircleMode) {
                user.addRoleSafe(this);
            }
            users.add(user);
        }
    }

    public void addUsers(Collection<DtoUserBase<ID>> users){
        if(users!=null) {
            for(DtoUserBase<ID> user : users){
                addUser(user);
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("dto roleuser [%s]: %s (%s)", String.valueOf(getId()), code, description);
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof DtoRoleuser) {
            DtoRoleuser object = (DtoRoleuser) obj;
            return super.equals(object) && code.equals(object.getCode())
                    && description.equals(object.getDescription());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, code, description);
    }
    //</editor-fold>
}
