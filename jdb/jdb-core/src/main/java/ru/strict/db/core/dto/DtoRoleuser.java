package ru.strict.db.core.dto;

import java.util.Collection;
import java.util.LinkedList;
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
    private Collection<DtoUser> users;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(String code, String description){
        if(code == null) {
            throw new NullPointerException("code is NULL");
        }

        this.code = code;
        this.description = description;
        users = new LinkedList<>();
    }

    public DtoRoleuser() {
        super();
        code = null;
        description = null;
        users = new LinkedList<>();
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

    public Collection<DtoUser> getUsers() {
        return users;
    }

    public void setUsers(Collection<DtoUser> users) {
        if(users == null) {
            throw new NullPointerException("users is NULL");
        }

        this.users = users;
    }

    /**
     * Добавить пользователя использующего данную роль
     * @param user
     */
    public void addUser(DtoUser user) {
        if(user == null) {
            throw new NullPointerException("user is NULL");
        }

        if(users!=null) {
            users.add(user);
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
                    && description.equals(object.getDescription())
                    && (users.size() == object.getUsers().size() && users.containsAll(object.getUsers()));
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, code, description, users);
    }
    //</editor-fold>
}
