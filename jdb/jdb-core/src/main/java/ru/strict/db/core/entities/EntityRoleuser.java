package ru.strict.db.core.entities;

import java.util.Collection;
import java.util.LinkedList;
import ru.strict.utils.UtilHashCode;

/**
 * Роль пользователя в системе (например, администратор, пользователь, неавторизированный пользователь и др.)
 */
public class EntityRoleuser<ID> extends EntityBase<ID> {

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
    private Collection<EntityUser> users;

    //<editor-fold defaultState="collapsed" desc="constructors">
	public EntityRoleuser() {
        super();
        code = null;
        description = null;
        users = new LinkedList<>();
    }

    public EntityRoleuser(String code, String description) {
    	super();
        this.code = code;
        this.description = description;
        users = new LinkedList<>();
    }

    public EntityRoleuser(ID id, String code, String description) {
        super(id);
        this.code = code;
        this.description = description;
        users = new LinkedList<>();
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<EntityUser> getUsers() {
        return users;
    }

    public void setUsers(Collection<EntityUser> users) {
        this.users = users;
    }

    /**
     * Добавить пользователя использующего данную роль
     * @param user
     */
    public void addUser(EntityUser user) {
        this.users.add(user);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("entity roleuser [%s]: %s (%s)", String.valueOf(getId()), code, description);
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof EntityRoleuser) {
            EntityRoleuser object = (EntityRoleuser) obj;
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